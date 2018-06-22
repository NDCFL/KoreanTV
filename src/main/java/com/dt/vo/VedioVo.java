package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 剧
 * @author dengfengfeng
 */
public class VedioVo implements Serializable {

    private Long id;// '剧编号',
    private String title;//剧名称
    private String description;//据描述
    private String faceImg;//剧缩略图
    private String rate;//评分
    private Long vedioModuleId;//属于模块id
    private String vedioTypeId;//剧类型id
    private Integer jishu;//剧的总集数
    private Integer isVip;//是否是vip剧
    private Integer isLast;//是否完结
    private Integer isActive;//状态
    private String area;//国家
    private Date createTime = new Date();//创建时间
    private String vTitle;//类型名称
    private String mTitle;//模型名称
    private VedioModuleVo vedioModuleVo;//模块组件
    private VedioTypeVo vedioTypeVo;//类型组件
    private Integer current;//当前更新集数
    private String typeList[];
    private String year;

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

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Long getVedioModuleId() {
        return vedioModuleId;
    }

    public void setVedioModuleId(Long vedioModuleId) {
        this.vedioModuleId = vedioModuleId;
    }

    public String getVedioTypeId() {
        return vedioTypeId;
    }

    public void setVedioTypeId(String vedioTypeId) {
        this.vedioTypeId = vedioTypeId;
    }

    public Integer getJishu() {
        return jishu;
    }

    public void setJishu(Integer jishu) {
        this.jishu = jishu;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
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

    public VedioModuleVo getVedioModuleVo() {
        return vedioModuleVo;
    }

    public void setVedioModuleVo(VedioModuleVo vedioModuleVo) {
        this.vedioModuleVo = vedioModuleVo;
    }

    public VedioTypeVo getVedioTypeVo() {
        return vedioTypeVo;
    }

    public void setVedioTypeVo(VedioTypeVo vedioTypeVo) {
        this.vedioTypeVo = vedioTypeVo;
    }

    public String getvTitle() {
        return vTitle;
    }

    public void setvTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String[] getTypeList() {
        return typeList;
    }

    public void setTypeList(String[] typeList) {
        this.typeList = typeList;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
