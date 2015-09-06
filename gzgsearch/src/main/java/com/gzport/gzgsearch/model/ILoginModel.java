package com.gzport.gzgsearch.model;


import com.gzport.gzgsearch.model.bean.UserBean;

/**
 * Created by Administrator on 2015/8/4.
 */
public interface ILoginModel {
    /**
     * 登陆
     * @param userBean
     * @param onResultListener
     * @return
     */
    String Login(UserBean userBean,OnResultListener onResultListener);

    /**
     * 保存返回的数据
     * @param token
     * @param comid
     */
    void  saveData(String token,int comid);

}
