package com.dept.filesite.controller;

import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.entity.Role;
import com.dept.filesite.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: RoleController
 * @description:
 * @author: 201998
 * @create: 2019-12-26
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Role> getListRoles(){
        return roleService.listRoles();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO<String> addUserRole(String[] rids, Integer uid){
        return roleService.insertUserRoles(rids,uid);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultVO<String> deleteRoles(String[] rids, Integer uid){
        return roleService.deleteUserRoles(rids,uid);
    }
}
