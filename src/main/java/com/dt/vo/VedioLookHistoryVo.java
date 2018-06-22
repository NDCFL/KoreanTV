package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class VedioLookHistoryVo implements Serializable {
    private Long id;// '评论编号',
    private Long userId;//用户id
    private Long vedioId;//剧id
    private Long vedioSectionId;//剧集id
    private String lastTime;//最后观看时间
    private Integer isActive;//状态
    private Date createTime = new Date();//创建时间
    private Integer vedioIndex;
    private String userName;//用户名称
    private AppUserVo userVo;
    private VedioVo vedioVo;
    private VedioSectionVo vedioSectionVo;

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

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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

    public AppUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(AppUserVo userVo) {
        this.userVo = userVo;
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

    public Integer getVedioIndex() {
        return vedioIndex;
    }

    public void setVedioIndex(Integer vedioIndex) {
        this.vedioIndex = vedioIndex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
