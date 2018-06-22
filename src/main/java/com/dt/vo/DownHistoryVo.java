package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class DownHistoryVo implements Serializable {
    private Long id;
    private Long userId;
    private Long vedioId;
    private Long  vedioSectionId;
    private Byte isActive;
    private Date createTime = new Date();
    private AppUserVo userVo;
    private VedioVo vedioVo;
    private VedioSectionVo vedioSectionVo;
    private String userName;
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

    public Long getVedioId() {
        return vedioId;
    }

    public void setVedioId(Long vedioId) {
        this.vedioId = vedioId;
    }

    public Long getVedioSectionId() {
        return vedioSectionId;
    }

    public void setVedioSectionId(Long vedioSectionId) {
        this.vedioSectionId = vedioSectionId;
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

    public VedioVo getVedioVo() {
        return vedioVo;
    }

    public void setVedioVo(VedioVo vedioVo) {
        this.vedioVo = vedioVo;
    }

    public VedioSectionVo getVedioSectionVo() {
        return vedioSectionVo;
    }

    public void setVedioSectionVo(VedioSectionVo vedioSectionVo) {
        this.vedioSectionVo = vedioSectionVo;
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
}
