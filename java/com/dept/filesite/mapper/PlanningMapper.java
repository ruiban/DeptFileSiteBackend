package com.dept.filesite.mapper;
import com.dept.filesite.entity.Planning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @className: PlanningMapper
 * @description: Planning 持久层，用于数据库操作
 * @author: 202110
 * @create: 2019-05-14
 */

@Mapper
public interface PlanningMapper {
    int insertPlanning(Planning planning);

    int countTotal(Map<String, Object> map);

    Planning findPlanningById(@Param("id") Integer id);

    Planning findPlanningByName(@Param("name") String name);

    int updatePlanning(Planning planning);

    List<Planning> listPlanning(Map<String, Object> map);

    int deletePlanningById(@Param("aids") Long[] aids);
}