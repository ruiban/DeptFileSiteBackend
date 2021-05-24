package com.dept.filesite.controller;


import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.service.ArchiverService;
import com.dept.filesite.vo.ArchiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @className: ArchiveController
 * @description:
 * @author: 201998
 * @create: 2020-04-20 15:45:00
 */
@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Autowired
    ArchiverService archiverService;

    @RequestMapping("/fileList")
    public List<ArchiveVO> getFileList(String path){
        return  archiverService.getFileList(path);
    }

    @RequestMapping("/upload")
    public ResultVO<String> upload(String path, @RequestParam(value = "file") MultipartFile file){
        return archiverService.upload(path,file);

    }

    @RequestMapping("/delete")
    public ResultVO<String> delete(String path){
        return archiverService.delete(path);
    }

    @RequestMapping("/rename")
    public ResultVO<String> rename(String oldPath,String newName){
        return archiverService.rename(oldPath,newName);
    }

    @RequestMapping("/mkdirs")
    public ResultVO<String> mkdirs(String path){
        return archiverService.mkdirs(path);
    }

    @RequestMapping("/download")
    public void download(String path, HttpServletResponse response){
        archiverService.download(path, response);
    }

    @RequestMapping("/move")
    public ResultVO<String> move(String[] oldPaths, String newPath){
        return archiverService.move(oldPaths, newPath);
    }
}
