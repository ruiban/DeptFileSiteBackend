package com.dept.filesite.service;

import com.dept.filesite.entity.ResultCodeEnum;
import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.utils.FileUtil;
import com.dept.filesite.vo.ArchiveVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @className: ArchiverServerImpl
 * @description: 文件的业务处理类
 * @author: 201998
 * @create: 2020-04-25 14:06
 */

@Service
public class ArchiverService {

    public List<ArchiveVO> getFileList(String path) {
        File[] files = new File(path).listFiles();
        List<ArchiveVO> list = new ArrayList<ArchiveVO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (files != null){
            for(File f : files){
                ArchiveVO archiveVO = new ArchiveVO();
                archiveVO.setFileName(f.getName());
                archiveVO.setFileSize(f.length()/(float)1024+"KB");
                Date date = new Date(f.lastModified());
                archiveVO.setUploadTime(sdf.format(date));
                if (f.isDirectory()){
                    archiveVO.setDir(true);
                }
                list.add(archiveVO);
            }
        }

        return list;
    }


    public ResultVO<String> upload(String path, MultipartFile file) {
        path+="\\"+file.getOriginalFilename();
        boolean result = FileUtil.getInstance().saveFile(file,path);
        if (result){
            return new ResultVO<String>("归档成功");
        }else{
            return new ResultVO<String>("归档失败");
        }
    }


    public ResultVO<String> delete(String path) {
        if (!"".equals(path)){
            Boolean result = FileUtil.getInstance().deleteFile(new File(path));
            if (result){
                return new ResultVO<String>("删除成功");
            }else{
                return new ResultVO<String>(ResultCodeEnum.FAILED,"删除失败");
            }
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"文件不存在");
        }
    }


    public ResultVO<String> rename(String oldPath, String newName) {
        if(!"".equals(oldPath)){
            Boolean result = FileUtil.getInstance().renameFile(oldPath,newName);
            if (result){
                return new ResultVO<String>("重命名成功");
            }else{
                return new ResultVO<String>(ResultCodeEnum.FAILED,"重命名失败");
            }
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"文件不存在");
        }
    }


    public ResultVO<String> mkdirs(String path) {
        Boolean result = false;
        File file = new File(path);
        if(!file.exists()){
            result = file.mkdirs();
        }

        if (result){
            return new ResultVO<String>("文件夹创建成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"文件夹已存在");
        }
    }


    public ResultVO<String> move(String[] oldPaths, String newPath) {

        if (oldPaths.length > 0){
            Boolean result = false;
            for (int i = 0; i < oldPaths.length; i++){
                String path = newPath + oldPaths[i].substring(oldPaths[i].lastIndexOf(File.separator),oldPaths[i].length());
                result = FileUtil.getInstance().moveFolder(oldPaths[i],path);

            }

            if (result){
                return new ResultVO<String>("移动文件成功");
            }else{
                return new ResultVO<String>(ResultCodeEnum.FAILED,"移动文件失败");
            }
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"不存在该文件");
        }
    }


    public void download(String path, HttpServletResponse response) {
        FileUtil.getInstance().download(path,response);
    }
}
