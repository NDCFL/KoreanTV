package com.dt.vo;

import java.util.Date;

public class RoleVo {
    private Long id;

    private String title;

    private String description;

    private Date createTime;

    private Byte isActive;

    public RoleVo(Long id, String title, String description, Date createTime, Byte isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createTime = createTime;
        this.isActive = isActive;
    }

    public RoleVo() {
        super();
    }

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
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}