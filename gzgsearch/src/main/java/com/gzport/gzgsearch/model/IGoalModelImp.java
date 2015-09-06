package com.gzport.gzgsearch.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.config.Config;
import com.gzport.gzgsearch.utils.MyStringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘亚涛 on 2015/8/10.
 */
public class IGoalModelImp implements IGoalModel {
    private JSONObject jsonObject;

    @Override
    public void getData(final int companyID, final OnResultListener onResultListener) {
        MyStringRequest stringRequest = new MyStringRequest(Request.Method.POST,Config.HOST + "goals", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject resultJson = JSON.parseObject(response);

                onResultListener.onSucess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResultListener.onFail(new  ErrorMsg(2,"网络连接错误..."));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("app_key", Config.KEY);
                map.put("token", MyApplication.getInstance().getToken());
                map.put("comid", companyID + "");
                return map;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }
}
