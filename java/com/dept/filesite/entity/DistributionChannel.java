package com.dept.filesite.entity;

import org.springframework.scheduling.support.SimpleTriggerContext;

/**
 * @className: DistributionChannel
 * @description: 销售渠道设置实体类
 * @author: 202111
 * @create: 2021-05-15
 */
public class DistributionChannel {
    private int id;
    private String name;
    private int level;
    private int parentId;
    private String describtion;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getParentId() { return parentId; }
    public void setParentId(int parentId) { this.parentId = parentId; }

    @Override
    public String toString() {
        return "DistributionChannel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level = '" + level + '\'' +
                ", parentId = '" + parentId + '\'' +
                "}";
    }
}
