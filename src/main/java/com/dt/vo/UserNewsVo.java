package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class UserNewsVo implements Serializable {
    private Long id;
    private Long userId;//'用户Id',
    private Long currentUserId;
    private String content;//'发布的内容',
    private Byte isActive;//'朋友圈状态',
    private Date createTime;//'创建时间'
    private Date endTime;
    private String imgUrl;//朋友圈的图片地址
    private String userName;
    private AppUserVo userVo;
    private Long cnt;
    private Long pl;
    private Integer isGood;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AppUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(AppUserVo userVo) {
        this.userVo = userVo;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    public Long getPl() {
        return pl;
    }

    public void setPl(Long pl) {
        this.pl = pl;
    }

    public Integer getIsGood() {
        return isGood;
    }

    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }
}
