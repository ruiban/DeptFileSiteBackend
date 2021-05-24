package com.dept.filesite.controller;

import com.dept.filesite.entity.Office;
import com.dept.filesite.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: OfficeController
 * @description:
 * @author: 201998
 * @create: 2019-12-26
 */

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping("/all")
    public List<Office> listOffice(){
        return officeService.listOffice();
    }

    @RequestMapping("/{username}")
    public String findOfficeByUsername(@PathVariable String username){
        return officeService.findOfficeByUsername(username);
    }

}
