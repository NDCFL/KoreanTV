package com.dt.vo;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;

public class AppSettingVo implements Serializable {
    private Long id;
    private Long userId;
    private Integer isDownWifi=0;//'是否允许在非WIFI网络下下载：0：不允许，1：允许',
    private Integer isWifiPlay=0;//'非wifi网络播放提醒0：不允许，1：允许',
    private Integer isWifiDown=0;//'wifi下自动开始为完成下载0：不允许，1：允许',
    private Date createTime = new Date();//

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

    public Integer getIsDownWifi() {
        return isDownWifi;
    }

    public void setIsDownWifi(Integer isDownWifi) {
        this.isDownWifi = isDownWifi;
    }

    public Integer getIsWifiPlay() {
        return isWifiPlay;
    }

    public void setIsWifiPlay(Integer isWifiPlay) {
        this.isWifiPlay = isWifiPlay;
    }

    public Integer getIsWifiDown() {
        return isWifiDown;
    }

    public void setIsWifiDown(Integer isWifiDown) {
        this.isWifiDown = isWifiDown;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
