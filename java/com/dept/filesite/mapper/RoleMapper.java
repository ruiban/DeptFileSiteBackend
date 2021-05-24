package com.dept.filesite.mapper;

import com.dept.filesite.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: RoleMapper
 * @description: Role 持久层，用于数据库操作
 * @author: 201998
 * @create: 2019-12-25 16:38
 */

@Mapper
public interface RoleMapper {

    int insertUserRoles(@Param("rids") String[] rids, @Param("uid") int uid);

    List<Role> listRoles();

    List<Role> findRolesByUid(@Param("uid") int uid);

    int deleteUserRoles(@Param("rids") String[] rids, @Param("uid") int uid);
}
