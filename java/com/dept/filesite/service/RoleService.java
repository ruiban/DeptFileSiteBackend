package com.dept.filesite.service;

import com.dept.filesite.entity.*;
import com.dept.filesite.mapper.RoleMapper;
import com.dept.filesite.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: RoleServerImpl
 * @description: Role的业务处理类
 * @author: 201998
 * @create: 2019-12-27 14:06
 */

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public ResultVO<String> insertUserRoles(String[] rids, int uid) {
        int result = roleMapper.insertUserRoles(rids,uid);
        if (result == rids.length){
            return new ResultVO<String>("添加成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"添加失败");
        }
    }


    public List<Role> listRoles() {

        List<Role> result = roleMapper.listRoles();

        return result;
    }


    public List<Role> findRolesByUid(int uid) {
        return roleMapper.findRolesByUid(uid);
    }

    public ResultVO<String> deleteUserRoles(String[] rids, int uid){
        int result = roleMapper.deleteUserRoles(rids,uid);
        if (result == rids.length){
            return new ResultVO<String>("删除成功");
        }else{
            return new ResultVO<String>(ResultCodeEnum.FAILED,"删除失败");
        }

    }
}
