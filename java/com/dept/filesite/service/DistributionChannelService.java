package com.dept.filesite.service;

import com.dept.filesite.entity.DistributionChannel;
import com.dept.filesite.entity.ResultCodeEnum;
import com.dept.filesite.mapper.DistributionChannelMapper;
import com.dept.filesite.utils.UserUtil;
import com.dept.filesite.vo.ResultVO;
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

    public ResultVO<String> insertDistributionChannel(DistributionChannel distributionChannel) {
        DistributionChannel dis = distributionChannelMapper.findDistributionChannelByFather(distributionChannel.getFather());
        if(dis == null) {
            return new ResultVO<String>(ResultCodeEnum.FAILED, "不存在该一级目录");
        }
        return new ResultVO<String>("success");
    }



    public Page listDistributionChannel(DistributionChannel distributionChannel, int currentPageNum, int pageSize) {
        int startIndex = (currentPageNum -1) * pageSize;
        User currentUser = UserUtil.getCurrentUser();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("distributionChannel", distributionChannel);
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        int totalCount = distributionChannelMapper.countTotal(map);
        Page page = new Page(currentPageNum, totalCount);
        page.setStartIndex(startIndex);
        List<DistributionChannel> records = distributionChannelMapper.listDistributionChannel(map);
        page.setRecords(records);
        return page;
    }

}
