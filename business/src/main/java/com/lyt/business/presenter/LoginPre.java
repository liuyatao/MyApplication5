package com.lyt.business.presenter;

import android.content.Context;

import com.lyt.business.app.MyApplication;
import com.lyt.business.model.User;
import com.lyt.business.view.ILoginView;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public class LoginPre {
    private ILoginView iLoginView;
    private Context context;

    public LoginPre(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    public void LoginAction(final User user){

       context= (Context) iLoginView;
        user.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                MyApplication.getInstance().setUser(user);
                iLoginView.showMsg("登陆成功");
                iLoginView.ToMain();
            }

            @Override
            public void onFailure(int i, String s) {
                switch (i){
                    case 101:
                        iLoginView.showMsg("用户名或者密码错误");
                        break;
                }
            }
        });

    }

}
