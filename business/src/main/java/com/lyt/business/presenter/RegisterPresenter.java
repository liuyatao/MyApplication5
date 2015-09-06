package com.lyt.business.presenter;

import com.lyt.business.view.IRegisterView;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public class RegisterPresenter {
    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public String getVerifyCode(){

        return null;
    }
}
