package com.gzport.gzgsearch.view;

import com.gzport.gzgsearch.model.bean.UserBean;

/**
 * Created by Administrator on 2015/8/4.
 */
public interface ILoginView {
    public void Login(UserBean userBean);

    /**
     * 显示进度条
     */
    public void showProgressBar();

    /**
     * 停止
     */
    public void stopProgressBar();
    /**
     * 显示登陆错误结果
     * @param s
     */
    public void showErrorMessage(String s);

    /**
     * 跳转到主页
     */
    void NavigateToHome();

}
