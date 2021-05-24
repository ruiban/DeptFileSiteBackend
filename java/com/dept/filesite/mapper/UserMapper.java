package com.dept.filesite.mapper;

import com.dept.filesite.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: UserMapper
 * @description: User 持久层，用于数据库操作
 * @author: 201998
 * @create: 2019-12-26 16:45
 */

@Mapper
public interface UserMapper {

    int insertUser(User user);


    User findUserById(@Param("id") int id);

    User findUserByUsername(@Param("username") String username);

    User findUserByEmployeeId(@Param("employeeId") String employeeId);

    User findUserByMail(@Param("mail") String mail);

    List<User> listUserByOffice(@Param("office") String office);

    int countTotalUser(User user);

    List<HashMap<String,Object>> countUserGroupByOffice();

    List<User> listUser(Map<String,Object> map);

    int updateUserEnabled(User user);

    int updateUserPassword(User user);

    int deleteUserById(@Param("id") int id);

    List<User> listUserByRoleAndOffice(@Param("rid") int rid, @Param("offices") String[] offices);

}
