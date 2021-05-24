package com.dept.filesite.mapper;

import com.dept.filesite.entity.Office;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: OfficeMapper
 * @description: Office 持久层，用于数据库操作
 * @author: 201998
 * @create: 2019-12-25 16:38
 */

@Mapper
public interface OfficeMapper {

    List<Office> listOffice();

    Office findOfficeById(@Param("id") int id);

    List<Integer> listOfficeId();
}
