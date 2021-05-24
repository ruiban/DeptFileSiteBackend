package com.dept.filesite.controller;


import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.entity.User;
import com.dept.filesite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: LoginController
 * @description:
 * @author: 201998
 * @create: 2019-12-25
 */

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    //管理员，部门领导，科室主任有权限注册
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResultVO<String> reg(User user){
        return userService.inserUser(user);

    }


}
