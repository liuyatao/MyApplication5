
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
public class IThPutModelImp implements IThPutModel {

    private MyStringRequest stringRequest;
    private ErrorMsg errorMsg;

    @Override
    public void getData(final int comid, final String years, final String token, final OnResultListener onResultListener) {
        stringRequest = new MyStringRequest(Request.Method.POST, Config.HOST + "throughput", new Response.Listener<String>() {
            @Override
            @DebugLog
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                int status = jsonObject.getInteger("AppStatusCode");
                if (status == 1) {
                    onResultListener.onSucess(response);

                } else if (status == 0) {
                    onResultListener.onFail(errorMsg = new ErrorMsg(0, "身份验证失败"));
                }

            }
        }, new Response.ErrorListener() {
            @DebugLog
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMsg = new ErrorMsg(2, "网络连接错误..");
                onResultListener.onFail(errorMsg);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap();
                map.put("app_key", Config.KEY);
                map.put("token", token);
                map.put("comid", comid + "");
                map.put("year", years);
                return map;
            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }
}
