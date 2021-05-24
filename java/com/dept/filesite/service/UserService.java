package com.dept.filesite.service;

import com.dept.filesite.entity.*;
import com.dept.filesite.mapper.UserMapper;
import com.dept.filesite.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: UserServerImpl
 * @description: User的业务处理类
 * @author: 201998
 * @create: 2019-12-26 09:19
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    TemperatureService temperatureService;

    public ResultVO<String> inserUser(User user) {
        User temp = userMapper.findUserByEmployeeId(user.getEmployeeId());
        if (temp != null){
            return new ResultVO<String>(ResultCodeEnum.FAILED,"员工编号重复"); //员工编号重复
        }
        temp = userMapper.findUserByMail(user.getMail());
        if (temp != null){
            return new ResultVO<String>(ResultCodeEnum.FAILED,"邮箱重复"); //邮箱重复
        }
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String now = sdf.format(new Timestamp());
        //Timestamp gmtCreate = sdf.parse(now);
        Timestamp gmtCreate = new Timestamp(System.currentTimeMillis());
        user.setGmtCreate(gmtCreate);
        //默认初始密码123456
        user.setPassword("123456");
        user.setEnabled(true);
        int result = userMapper.insertUser(user);

        //默认普通用户
        String[] rids = new String[]{"4"};
        ResultVO<String> result_role = roleService.insertUserRoles(rids,user.getId());
        boolean b = (result_role.getCode() == 1000 && result == 1);
        if (b){
            return new ResultVO<String>("注册成功");
        }

        return new ResultVO<String>(ResultCodeEnum.FAILED,"注册失败");

    }

    public Page listUser(User user, int currentPageNum, int pageSize) {
        int startIndex = (currentPageNum-1)*pageSize;
        int totalCount = userMapper.countTotalUser(user);
        Page page = new Page(currentPageNum,pageSize);
        page.setTotalCount(totalCount);
        if(totalCount == 0){
            return page;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user",user);
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        List<User> records = userMapper.listUser(map);
        //设置角色
        for(int i = 0; i < records.size(); i++){
            User temp = records.get(i);
            int id = temp.getId();
            List<Role> roles = roleService.findRolesByUid(id);
            temp.setRoles(roles);
        }
        page.setRecords(records);
        return page;
    }

    public List<User> listUserByOffice(String office) {
        return userMapper.listUserByOffice(office);
    }


    public ResultVO<String> deleteUserById(int id) {
        User user = userMapper.findUserById(id);
        if(userMapper.deleteUserById(id) > 0){
            int result = temperatureService.deleteTemperatureByName(user.getUsername(),user.getOffice());
            if (result > 0){
                return new ResultVO<String>("删除成功");
            }else {
                return new ResultVO<String>("删除用户成功，但删除该用户的体温记录失败");
            }

        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"删除失败");
        }
    }

    public ResultVO<String> updateUserEnabled(User user) {
        if (userMapper.updateUserEnabled(user) > 0){
            return new ResultVO<String>("更新成功");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
        }
    }

    public ResultVO<String> updateUserPassword(User user) {
        Timestamp gmtModified = new Timestamp(System.currentTimeMillis());
        user.setGmtModified(gmtModified);
        if(userMapper.updateUserPassword(user) > 0){
            return new ResultVO<String>("更新成功");
        }else {
            return new ResultVO<String>(ResultCodeEnum.FAILED,"更新失败");
        }
    }

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public List<HashMap<String, Object>> countUserGroupByOffice() {
        return userMapper.countUserGroupByOffice();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findUserByMail(s);
        if(user == null){
            return new User();
        }
        List<Role> roles = roleService.findRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    public List<User> listUserByRoleAndOffice(int rid, String[] offices){
        return userMapper.listUserByRoleAndOffice(rid,offices);
    }
}
