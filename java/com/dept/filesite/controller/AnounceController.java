package com.dept.filesite.controller;


import com.dept.filesite.entity.Anounce;
import com.dept.filesite.entity.Page;
import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.service.AnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @className: AnounceController
 * @description:
 * @author: 201998
 * @create: 2019-12-25 15:45:00
 */

@RestController
@RequestMapping("/anounce")
public class AnounceController {

    @Autowired
    AnounceService anounceService;
//    文件上傳
    @RequestMapping(value = "/insert")
    public ResultVO<String> insertAnounce( Anounce anounce, @RequestParam(value = "file") MultipartFile file){
        return  anounceService.insertAnounce(anounce,file);
    }

    @RequestMapping(value = "/all")
    public Page listAnounces(Anounce anounce,String tab, Integer currentPageNum, Integer pageSize){
        return anounceService.listAnounces(anounce,tab,currentPageNum,pageSize);
    }

    @RequestMapping(value = "/download")
    public void downloadFile(String filePath, HttpServletResponse response) {
        anounceService.downloadAnounce(filePath,response);

    }
    @RequestMapping(value = "/viewImage")
    public void viewImage(String filePath, HttpServletResponse response) {
        anounceService.viewImage(filePath, response);
    }

    @RequestMapping(value = "/update")
    public ResultVO<String> updateAnounce(Anounce anounce){
        return anounceService.updateAnounce(anounce);
    }

    @RequestMapping(value = "/delete")
    public ResultVO<String> deleteAnounce(Long[] aids){
        return anounceService.deleteAnounce(aids);
    }

    @RequestMapping(value ="/find")
    public Anounce find(){
        return anounceService.find("x");
    }
}
