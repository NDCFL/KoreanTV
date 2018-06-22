package com.dt.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
    private Long id;// '用户编号',
    private String phone;//'用户联系方式',
    private String password;//'用户登录密码',
    private String name;//'用户昵称',
    private String realname;//'用户真实姓名',
    private String sex;//'用户性别',
    private Integer age;//'用户年龄',
    private String qqopenid;//'QQopenid',
    private String wbopenid;//'微博openid',
    private String wxopenid;//'微信openid',
    private String faceImg;//'账户余额',
    private Integer status;//'是否启用，0代表启用，1代表禁用',
    private Date createTime;//'创建时间'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQqopenid() {
        return qqopenid;
    }

    public void setQqopenid(String qqopenid) {
        this.qqopenid = qqopenid;
    }

    public String getWbopenid() {
        return wbopenid;
    }

    public void setWbopenid(String wbopenid) {
        this.wbopenid = wbopenid;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
