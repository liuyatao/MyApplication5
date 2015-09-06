package com.gzport.gzgsearch.model;

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
import com.gzport.gzgsearch.utils.MyStringRequest;

import java.util.HashMap;
import java.util.Map;

import hugo.weaving.DebugLog;

/**
 * Created by 刘亚涛 on 2015/8/18.
 */
public class ILoadAndUnloadModelImp implements ILoadAndUnloadModel {

    MyStringRequest stringRequest;
    ErrorMsg errorMsg;

    @Override
    public void getdData(int comId, String token, final OnResultListener onResultListener) {
        stringRequest=new MyStringRequest(Request.Method.POST, Config.HOST + "freightHanding", new Response.Listener<String>() {
            @Override
            @DebugLog
            public void onResponse(String response) {/*
                response="{"loadArray":[{"value":6.336092E7,"operationdate":"2015-08-19"},{"value":2.32732451204E8,"operationdate":"2015-08-18"},{"value":1.7852031184E8,"operationdate":"2015-08-17"},{"value":1.8030139822E8,"operationdate":"2015-08-16"},{"value":2.02440672805E8,"operationdate":"2015-08-15"}],"unloadArray":[{"value":6.524478E7,"operationdate":"2015-08-19"},{"value":1.782238634E8,"operationdate":"2015-08-18"},{"value":2.0827852461E8,"operationdate":"2015-08-17"},{"value":2.52469868639E8,"operationdate":"2015-08-16"},{"value":2.23188155877E8,"operationdate":"2015-08-15"}],"comid":"5","AppStatusCode":1}"*/
                JSONObject jsonObject= JSON.parseObject(response);
                if (jsonObject.getInteger("AppStatusCode")==1){
                    onResultListener.onSucess(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMsg=new ErrorMsg(2,"网络连接错误...");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();
                map.put("token",MyApplication.getInstance().getToken());
                map.put("comid",MyApplication.getInstance().getComId()+"");
                map.put("app_key",Config.KEY);
                return map;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }
}
