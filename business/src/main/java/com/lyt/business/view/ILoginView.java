package com.lyt.business.view;

import com.lyt.business.model.User;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public interface ILoginView extends BaseView {
    /**
     * 登陆
     * @param user
     */
    void login(User user);

    /**
     * 登陆完成
     */
    void ToMain();

    /**
     * 注册
     */
    void reg();
}
