package com.dept.filesite.service;

import com.dept.filesite.entity.*;
import com.dept.filesite.mapper.AnounceMapper;
import com.dept.filesite.utils.FileUtil;
import com.dept.filesite.utils.UserUtil;
import com.dept.filesite.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: AnounceServerImpl
 * @description: Anounce的业务处理类
 * @author: 201998
 * @create: 2019-12-25 16:45
 */

@Service
public class AnounceService  {

    @Autowired
    private Environment environment;

    @Autowired
    AnounceMapper anounceMapper;

//文件上傳
    public ResultVO<String> insertAnounce(Anounce anounce, MultipartFile file) {

        Anounce ano = anounceMapper.findAnounceByFileNo(anounce.getFileNo());
        if(ano != null){
            return new ResultVO(ResultCodeEnum.FAILED,"文档编号重复");
        }
        String issueYear = anounce.getIssueDate().substring(0,4);
        String fileType = anounce.getFileType();
        String fileNo = anounce.getFileNo();
        String fileName = anounce.getFileName();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String path = environment.getProperty("rootPath")+issueYear+"\\"+fileType+"\\"+fileNo+" "+fileName+suffix;
        boolean result = FileUtil.getInstance().saveFile(file,path);
        if (result){
            //插入记录
            anounce.setFilePath(path);
            anounce.setNewVersion("0");
            String oldFileNo = anounce.getOldFileNo();
			Anounce oldAnounce = null;
			//旧版文件编号不为空，查找是否存在旧版记录
            boolean isnull = "".equals(oldFileNo);
			if(!isnull){
				oldAnounce = anounceMapper.findAnounceByFileNo(oldFileNo);
			}
			//存在旧版记录证明是换版
            if(oldAnounce != null){
                //换版
                anounce.setNewVersion("1");
                //更新旧版记录，受控转为替代报废

                oldAnounce.setStatus("替代报废");

                //更新旧版
                ResultVO<String> updateResult = updateAnounce(oldAnounce);
                if (updateResult.getCode() == 1001){
                    return new ResultVO<String>(ResultCodeEnum.FAILED,"更新旧版出错");
                }

            }
            //不存在该旧版编号
            if (!isnull && oldAnounce == null){
                return new ResultVO<String>(ResultCodeEnum.FAILED,"不存在该旧版编号");
            }

            if(anounceMapper.insertAnounce(anounce) > 0){
                return new ResultVO<String>("上传成功");
            }
        }

        return new ResultVO<String>(ResultCodeEnum.FAILED,"保存文件失败");


    }


    public ResultVO<String> updateAnounce(Anounce anounce) {
        //若发放日期年份、文档类型更改
        Anounce oldAnounce = anounceMapper.findAnounceById(anounce.getId());
        String oldYear = oldAnounce.getIssueDate().substring(0,4);
        String newYear = anounce.getIssueDate().substring(0,4);
        String oldType = oldAnounce.getFileType();
        String newType = anounce.getFileType();
        String fileNo_original = oldAnounce.getFileNo();
        String fileNo = anounce.getFileNo();
        String fileName_original = oldAnounce.getFileName();
        String fileName = anounce.getFileName();
        String status = anounce.getStatus();
        if(!oldYear.equals(newYear) || !oldType.equals(newType) ||
                !fileName_original.equals(fileName) ||
                !fileNo_original.equals(fileNo)|| "替代报废".equals(status)){
            String oldPath = oldAnounce.getFilePath();
            String originalFilename = oldPath.substring(oldPath.lastIndexOf("\\")+1);

            //文件路径更改
            String newPath = environment.getProperty("rootPath")+newYear+"\\"+newType+"\\"+originalFilename;
            boolean b = !fileName_original.equals(fileName) || !fileNo_original.equals(fileNo);
            boolean result = false;
            if ("替代报废".equals(status)){
                newPath = environment.getProperty("rootPath")+newYear+"\\替代报废\\"+originalFilename;
                //替代报废，即使新版文档名称与旧版不一样，也不修改旧版的名称
                anounce.setFileName(oldAnounce.getFileName());
                result = FileUtil.getInstance().moveFolder(oldPath,newPath);
            }else {
                //文档编号或文档名称更改，重命名文件
                if(b){
                    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String newName = fileNo+" "+fileName;
                    newPath = environment.getProperty("rootPath")+newYear+"\\"+newType+"\\"+newName+suffix;
                    result = FileUtil.getInstance().renameFile(oldPath,newName);
                }else{

                    result = FileUtil.getInstance().copyFile(oldPath,newPath);
                }
            }

            if(result){
                anounce.setFilePath(newPath);
            }else{
                return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
            }


        }
        if(anounceMapper.updateAnounce(anounce) > 0 ){
            return new ResultVO<String>("更新成功");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
        }
    }


    public Page listAnounces(Anounce anounce,String tab, int currrenPageNum, int pageSize) {

        int startIndex = (currrenPageNum-1)*pageSize;
        User currentUser = UserUtil.getCurrentUser();
        List<Role> roles = currentUser.getRoles();
        anounce.setAuthorization(currentUser.getOffice());
        for(Role role : roles){
            if (role.getId() == 1){
                anounce.setAuthorization(null);
                break;
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("anounce",anounce);
        map.put("tab",tab);
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        int totalCount = anounceMapper.countTotal(map);
        Page page = new Page(currrenPageNum,totalCount);
        page.setStartIndex(startIndex);
        //List<Anounce> records = getAnounceList(activeName,map);
        List<Anounce> records = anounceMapper.listAnounces(map);
        page.setRecords(records);
        return page;
    }


    public ResultVO<String> deleteAnounce(Long[] aids) {
        if(anounceMapper.deleteAnounceById(aids) > 0 ){
            return new ResultVO<String>("删除成功");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"删除失败");
        }
    }


    public void downloadAnounce(String filePath, HttpServletResponse response) {
        FileUtil.getInstance().download(filePath,response);
    }

    public Anounce find(String name) {
        Anounce ano = anounceMapper.findAnounceById(1);
        return ano;
    }

    public void viewImage(String filePath, HttpServletResponse response) {
        FileUtil.getInstance().viewImage(filePath, response);
    }

}
