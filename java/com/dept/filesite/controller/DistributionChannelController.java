package com.dept.filesite.controller;

import com.dept.filesite.entity.DistributionChannel;
import com.dept.filesite.entity.Page;
import com.dept.filesite.service.DistributionChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distribution_channel")
public class DistributionChannelController {
    @Autowired
    DistributionChannelService distributionChannelService;

    @RequestMapping(value = "/all")
    public Page listDistributionChannel(DistributionChannel distributionChannel, String tab, Integer currentPageNum, Integer pageSize){
        return distributionChannelService.listDistributionChannel(distributionChannel, 1, 5);
    }
}
