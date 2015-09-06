package com.lyt.business.app;

import android.app.Application;

import com.lyt.business.model.User;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication=new MyApplication();
    private User user;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }

    public MyApplication() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
