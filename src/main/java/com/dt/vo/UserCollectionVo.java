package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class UserCollectionVo implements Serializable {
    private Long id;
    private Long userId;
    private Long vedioId;//'韩剧编号',
    private Byte isActive;//'韩剧状态',
    private Date createTime = new Date();//'创建时间'
    private VedioVo vedioVo;
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
}
