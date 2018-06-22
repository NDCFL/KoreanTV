package com.dt.vo;

import java.io.Serializable;

public class MsgVo implements Serializable {
    private String Account;
    private String Pwd;
    private String Content;
    private String Mobile;
    private String SignId;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getSignId() {
        return SignId;
    }

    public void setSignId(String signId) {
        SignId = signId;
    }
}
