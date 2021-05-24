package com.dept.filesite.mapper;

import com.dept.filesite.entity.DistributionChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DistributionChannelMapper {
    int insertDistributionChannel(DistributionChannel distributionChannel);
    int countTotal(Map<String, Object> map);
    DistributionChannel findDistributionChannelByName(String name);
    DistributionChannel findDistributionChannelByFather(int father);
    DistributionChannel findDistributionChannelById(Integer id);
    int updateDistributionChannel(DistributionChannel distributionChannel);
    int deleteDistributionChannel(@Param("aids") Long[] aids);
    List<DistributionChannel> listDistributionChannel(Map<String, Object> map);
}