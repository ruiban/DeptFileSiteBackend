package com.dept.filesite.controller;



import com.dept.filesite.entity.Page;
import com.dept.filesite.vo.ResultVO;
import com.dept.filesite.entity.User;
import com.dept.filesite.service.UserService;
import com.dept.filesite.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: UserController
 * @description:
 * @author: 201998
 * @create: 2019-12-27 09:21
 */

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        //return  JSONObject.toJSONString(UserUtil.getCurrentUser());
        return UserUtil.getCurrentUser();
    }

    @RequestMapping(value = "/currentUsername", method = RequestMethod.GET)
    public String getCurrentUsername(){
      return UserUtil.getCurrentUser().getUsername();
    }

    @RequestMapping(value = "/currentUserId", method = RequestMethod.GET)
    public Integer getCurrentUserId(){
        return UserUtil.getCurrentUser().getId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page listUser(User user, Integer currentPageNum, Integer pageSize){

        return userService.listUser(user,currentPageNum,pageSize);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getOneUserById(@PathVariable Integer id){

        //return JSONObject.toJSONString(userServer.findUserById(id));
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ResultVO<String> deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/enabled", method = RequestMethod.POST)
    public ResultVO<String> updateUserEnabled(User user){
        return userService.updateUserEnabled(user);
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ResultVO<String> updateUserPassword(User user){
        return userService.updateUserPassword(user);
    }

}
