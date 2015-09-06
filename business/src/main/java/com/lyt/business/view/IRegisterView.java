package com.lyt.business.view;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public interface IRegisterView extends BaseView {
    void getVerifyCode();
    void next();
    void verifyTel(String tel);
}
