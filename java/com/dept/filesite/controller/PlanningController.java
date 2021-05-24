package com.dept.filesite.controller;

import com.dept.filesite.entity.Planning;
import com.dept.filesite.entity.Page;
import com.dept.filesite.service.PlanningService;
import com.dept.filesite.utils.FileUtil;
import com.dept.filesite.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/planning")
public class PlanningController {
    @Autowired
    PlanningService planningService;

    @Autowired
    private Environment environment;

    @ApiOperation(value = "插入策划", notes = "")
    @RequestMapping(value = "/insert")
    public ResultVO<String> insertPlanning(Planning planning){
        return planningService.insertPlanning(planning);
    }

    @RequestMapping(value = "/all")
    public Page listPlanning(Planning planning, String tab, Integer currentPageNum, Integer pageSize){

        return planningService.listPlanning(planning, 1, 5);
    }

    @RequestMapping(value = "/update")
    public ResultVO<String> updatePlanning(Planning planning) { return planningService.updatePlanning(planning);}

    @RequestMapping(value = "/delete")
    public ResultVO<String> deletePlanning(Long[] aids) { return planningService.deletePlanning(aids);}

    @RequestMapping(value = "/find")
    public String find() { return "hello world";}

    @RequestMapping(value = "/planning_detail", method = {RequestMethod.POST})
    public Planning getPlanningById(Integer id){
        Planning planning=planningService.findPlanningById(id);
        return planning;
    }

    @RequestMapping(value = "/multipleImageUpload")
    public void multipleImageUpload(@RequestParam("image_list") MultipartFile[] files){
        System.out.println("上传的图片数:" + files.length);
        List<Map<String, Object>> root = new ArrayList<Map<String, Object>>();
        for (MultipartFile file : files) {
            Map<String, Object> result = new HashMap<String, Object>();
            String result_msg = "";

            if(file.getSize() < 10000) {
                result_msg = "图片大小不能超过100MB";
                System.out.println(result_msg);
            }
            else{
                String fileType = file.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/gif")) {
                    String fileName = file.getOriginalFilename();
                    System.out.println("上传的图片名:" + fileName);
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = UUID.randomUUID()+suffixName;
                    String path = environment.getProperty("rootPath")+"image"+"\\"+fileName+suffixName;
                    boolean re = FileUtil.getInstance().saveFile(file, path);
                    System.out.println(re);
                }
                else {
                    System.out.println("上传图片格式错误");
                }
            }
        }

    }

}

