package com.gzport.gzgsearch.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.config.Config;
import com.gzport.gzgsearch.model.bean.UserBean;
import com.gzport.gzgsearch.utils.MyStringRequest;

import java.util.HashMap;
import java.util.Map;

import hugo.weaving.DebugLog;

/**
 * Created by Administrator on 2015/8/4.
 */
public class ILoginModelImp implements ILoginModel {

    private String token;
    private ErrorMsg errorMsg;

    @Override
    public String Login(final UserBean userBean, final OnResultListener onResultListener) {
        String result = "null";
        MyStringRequest stringRequest = new MyStringRequest(Request.Method.POST, Config.HOST + "login", new Response.Listener<String>() {
            @Override
            @DebugLog
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                Log.e("msg", response);
                token = jsonObject.get("token").toString();
                if (token.equals("0")) {
                    errorMsg = new ErrorMsg(0, "App验证异常 token 获取失败");
                    onResultListener.onFail(errorMsg);
                    Log.e("msg", response);
                } else if (jsonObject.getInteger(("AppStatusCode")) == 1) {
                    onResultListener.onSucess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResultListener.onFail(new ErrorMsg(2, "网络连接有问题"));
                Log.e("msg", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("app_key", "2cebada8-aaec-11e2-b76a-20c9d084d52d");
                stringMap.put("username", "admin");
                stringMap.put("password", "456123");
                return stringMap;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        MyApplication.getInstance().addToRequestQueue(stringRequest);
        return result;
    }

    @Override

    public void saveData(String token, int comid) {
        MyApplication.getInstance().saveToken(token,comid);
    }


}
