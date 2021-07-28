package com.dept.filesite.service;

import com.dept.filesite.entity.DistributionChannel;
import com.dept.filesite.entity.ResultCodeEnum;
import com.dept.filesite.mapper.DistributionChannelMapper;
import com.dept.filesite.utils.UserUtil;
import com.dept.filesite.vo.ResultVO;
import org.apache.ibatis.mapping.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dept.filesite.entity.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
@Service
public class DistributionChannelService {
    @Autowired
    DistributionChannelMapper distributionChannelMapper;
    public List<DistributionChannelChildren> distributionChannelWithChildren() {
        return distributionChannelMapper.distributionChannelWithChildren();
    }



}
