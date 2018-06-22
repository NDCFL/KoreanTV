package com.dt.vo;

import java.util.Date;

public class PermissionVo {
    private Long id;

    private String title;

    private String permission;

    private String description;

    private Long moduleId;

    private Date createTime;

    private Byte isActive;

    public PermissionVo(Long id, String title, String permission, String description, Long moduleId, Date createTime, Byte isActive) {
        this.id = id;
        this.title = title;
        this.permission = permission;
        this.description = description;
        this.moduleId = moduleId;
        this.createTime = createTime;
        this.isActive = isActive;
    }

    public PermissionVo() {
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
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