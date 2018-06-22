package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class VedioSectionVo implements Serializable {
    private Long id;
    private Long vedioId;//'韩剧',
    private String title;//'韩剧集标题',
    private Integer episode;
    private String url;//'韩剧集的视频地址',
    private Integer lookTimes;//'韩剧集观看次数',
    private Integer isVip;//'是否是VIP',
    private Byte isActive;//'韩剧状态',
    private Date createTime;//'创建时间',
    private Date endTime;
    private String vedioName;
    private VedioVo vedioVo;
    private Long max;
    private Long min;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVedioId() {
        return vedioId;
    }

    public void setVedioId(Long vedioId) {
        this.vedioId = vedioId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLookTimes() {
        return lookTimes;
    }

    public void setLookTimes(Integer lookTimes) {
        this.lookTimes = lookTimes;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
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

    public String getVedioName() {
        return vedioName;
    }

    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }
}
