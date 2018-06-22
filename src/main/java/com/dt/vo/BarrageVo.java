package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class BarrageVo implements Serializable {
    private Long id;//编号
    private Long sectionId;//集编号
    private Long userId;//用户编号
    private String content;//弹幕内容
    private Byte isActive;//状态
    private Long times;//当前的秒数
    private Date createTime = new Date();//创建时间
    private AppUserVo userVo;
    private VedioSectionVo vedioSectionVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
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

    public VedioSectionVo getVedioSectionVo() {
        return vedioSectionVo;
    }

    public void setVedioSectionVo(VedioSectionVo vedioSectionVo) {
        this.vedioSectionVo = vedioSectionVo;
    }
}
