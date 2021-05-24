package com.dept.filesite.mapper;

import com.dept.filesite.entity.Anounce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @className: AnounceMapper
 * @description: Anounce 持久层，用于数据库操作
 * @author: 201998
 * @create: 2019-12-25 16:38
 */

@Mapper
public interface AnounceMapper {

    int insertAnounce(Anounce anounce);

    //int countTotal(Anounce anounce);
    int countTotal(Map<String, Object> map);

    //int countTotalSheet(Anounce anounce);

    //int countTotalReport(Anounce anounce);

    Anounce findAnounceById(Integer id);

    Anounce findAnounceByFileNo(String fileNo);

    int updateAnounce(Anounce anounce);

    List<Anounce> listAnounces(Map<String, Object> map);

    //List<Anounce> listAnouncesSheet(Map<String, Object> map);

    //List<Anounce> listAnouncesReport(Map<String, Object> map);

    int deleteAnounceById(@Param("aids") Long[] aids);

}
