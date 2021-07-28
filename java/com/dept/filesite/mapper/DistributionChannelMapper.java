package com.dept.filesite.mapper;

import com.dept.filesite.entity.DistributionChannel;
import com.dept.filesite.entity.DistributionChannelChildren;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DistributionChannelMapper {
    int insertDistributionChannel(DistributionChannel distributionChannel);
    int countTotal(Map<String, Object> map);
    List<DistributionChannelChildren> distributionChannelWithChildren();
}