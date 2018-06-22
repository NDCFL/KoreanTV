package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class UserNewsPlVo implements Serializable {
    private Long id;
    private Long userNewsId;
    private Long userId;
    private String content;
    private Byte isActive;
    private Date createTime;
    private String userName;
    private Date endTime;
    private AppUserVo userVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserNewsId() {
        return userNewsId;
    }

    public void setUserNewsId(Long userNewsId) {
        this.userNewsId = userNewsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public AppUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(AppUserVo userVo) {
        this.userVo = userVo;
    }
}
