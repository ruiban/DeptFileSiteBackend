package com.dept.filesite.controller;

import com.dept.filesite.entity.DistributionChannel;
import com.dept.filesite.entity.DistributionChannelChildren;
import com.dept.filesite.entity.Page;
import com.dept.filesite.service.DistributionChannelService;
import com.dept.filesite.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/distribution_channel")
public class DistributionChannelController {
    @Autowired
    DistributionChannelService distributionChannelService;
    @Autowired
    private Environment environment;
    public DistributionChannelController() throws IOException {
    }

    @ResponseBody
    @RequestMapping(value = "/withChildren", method = RequestMethod.GET)
    public List<DistributionChannelChildren> listWithChildren() {
        List<DistributionChannelChildren> list = distributionChannelService.distributionChannelWithChildren();
        return list;
    }
}
