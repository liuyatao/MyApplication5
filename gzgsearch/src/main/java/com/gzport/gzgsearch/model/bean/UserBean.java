package com.gzport.gzgsearch.model.bean;

/**
 * Created by Administrator on 2015/8/4.
 */
public class UserBean {
    private String userName;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBean(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }
}
