package com.dept.filesite.mapper;


import com.dept.filesite.entity.Temperature;
import com.dept.filesite.vo.MaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @className: TemperatureMapper
 * @description: Temperature 持久层，用于数据库操作
 * @author: 201998
 * @create: 2020-04-25 16:45
 */

@Mapper
public interface TemperatureMapper {
    int addTemperature(Temperature temperature);
    int insertTemperature(@Param("list") List<Temperature> list);
    int updateTemperature(@Param("list") List<Temperature> list);
    Temperature findTemperatureById(int id);
    List<Temperature> listTemperature(Temperature temperature);
    List<MaskVO> sumMask(@Param("office") String office, @Param("today") String today);
    List<Temperature> listTemperaturePage(Map<String,Object> map);
    int countTemperature(Temperature temperature);
    int deleteTemperatureById(int id);
    int deleteTemperatureByName(@Param("name") String name, @Param("office") String office);
}
