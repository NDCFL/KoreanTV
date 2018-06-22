package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class VedioTypeVo implements Serializable {
    private Long id;// '剧类型编号',
    private String title;//剧类型名称
    private String description;//剧类型描述
    private Integer isActive;//状态
    private Date createTime = new Date();//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
