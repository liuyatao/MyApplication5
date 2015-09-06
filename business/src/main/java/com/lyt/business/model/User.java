package com.lyt.business.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public class User extends BmobUser {
    private String imageHeadUrl;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageHeadUrl() {
        return imageHeadUrl;
    }

    public void setImageHeadUrl(String imageHeadUrl) {
        this.imageHeadUrl = imageHeadUrl;
    }
}
