package com.dept.filesite.entity;

import java.util.List;

public class DistributionChannelChildren extends DistributionChannel{
    private List<DistributionChannel> children;

    public List<DistributionChannel> getChildren() {
        return children;
    }
    public void setChildren(List<DistributionChannel> children) {
        this.children = children;
    }
}
