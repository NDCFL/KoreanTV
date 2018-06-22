package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 回复组件bean
 */
public class CharVo implements Serializable {
    private Long id;
    private Long userId;//用户编号
    private Long vedioDiscussId;//评论编号
    private String content;//内容
    private Byte isActive;
    private Date createTime;
    private Date endTime;
    private String userName;
    private VedioDiscussVo vedioDiscussVo;
    private AppUserVo userVo;
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

    public Long getVedioDiscussId() {
        return vedioDiscussId;
    }

    public void setVedioDiscussId(Long vedioDiscussId) {
        this.vedioDiscussId = vedioDiscussId;
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

    public VedioDiscussVo getVedioDiscussVo() {
        return vedioDiscussVo;
    }

    public void setVedioDiscussVo(VedioDiscussVo vedioDiscussVo) {
        this.vedioDiscussVo = vedioDiscussVo;
    }

    public AppUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(AppUserVo userVo) {
        this.userVo = userVo;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
