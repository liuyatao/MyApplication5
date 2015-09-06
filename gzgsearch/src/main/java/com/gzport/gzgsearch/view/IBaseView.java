package com.gzport.gzgsearch.view;

import com.gzport.gzgsearch.model.ErrorMsg;

/**
 * Created by 刘亚涛 on 2015/8/10.
 */
public interface IBaseView {
    void navigateToLogin();
    void showErrorMsg(ErrorMsg errorMsg);
}
