package com.gzport.gzgsearch.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.ILoginModel;
import com.gzport.gzgsearch.model.ILoginModelImp;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.model.bean.UserBean;
import com.gzport.gzgsearch.view.ILoginView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/4.
 */
public class LoginPresenter {
    private ILoginView iLoginView;
    private ILoginModel iLoginModel;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView=iLoginView;
        iLoginModel=new ILoginModelImp();
    }
    public void LoginAction(UserBean userBean){
        iLoginView.showProgressBar();
        iLoginModel.Login(userBean, new OnResultListener() {
            @Override
            public void onSucess(String str) {
                JSONObject jsonObject= JSON.parseObject(str);
                iLoginModel.saveData(jsonObject.getString("token"),Integer.parseInt(jsonObject.getString("comid")));
                iLoginView.stopProgressBar();
                iLoginView.NavigateToHome();
            }

            @Override
            public void onSucess(List list) {

            }

            @Override
            public void onFail(ErrorMsg errorMsg) {
                iLoginView.stopProgressBar();
                iLoginView.showErrorMessage(errorMsg.getErrorInfor());
            }

        });
    }
}
