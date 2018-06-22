package com.dt.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VedioModuleVo implements Serializable {
    private Long id;// '模块编号',
    private String title;//模块名称
    private String imgUrl;//模块图标
    private String description;//模块描述
    private Integer isActive;//状态
    private Date createTime = new Date();//创建时间
    private Float level;
    private List<VedioVo> vedioVoList;

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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<VedioVo> getVedioVoList() {
        return vedioVoList;
    }

    public void setVedioVoList(List<VedioVo> vedioVoList) {
        this.vedioVoList = vedioVoList;
    }

    public Float getLevel() {
        return level;
    }

    public void setLevel(Float level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
