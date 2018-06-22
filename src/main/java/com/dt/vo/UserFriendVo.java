package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class UserFriendVo implements Serializable {
    private Long id;
    private Long myId;//'我的Id',
    private Long yourId;//'关注Id',
    private String myFaceImg;
    private String yourFaceImg;
    private Byte isActive;//'状态',
    private String myName;
    private Date createTime = new Date();//'创建时间'
    private AppUserVo userVo;
    private Long cnt;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMyId() {
        return myId;
    }

    public void setMyId(Long myId) {
        this.myId = myId;
    }

    public Long getYourId() {
        return yourId;
    }

    public void setYourId(Long yourId) {
        this.yourId = yourId;
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

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public AppUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(AppUserVo userVo) {
        this.userVo = userVo;
    }

    public String getMyFaceImg() {
        return myFaceImg;
    }

    public void setMyFaceImg(String myFaceImg) {
        this.myFaceImg = myFaceImg;
    }

    public String getYourFaceImg() {
        return yourFaceImg;
    }

    public void setYourFaceImg(String yourFaceImg) {
        this.yourFaceImg = yourFaceImg;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }
}
