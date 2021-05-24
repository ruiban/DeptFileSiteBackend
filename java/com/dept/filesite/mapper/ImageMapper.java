package com.dept.filesite.mapper;

import com.dept.filesite.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
    Image findImageById(@Param("id") Integer id);
}
