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

/**
 * Created by 刘亚涛 on 2015/8/19.
 */
public class IImportantStockModelImpl implements IImportantStockModel {

    MyStringRequest stringRequest;
    ErrorMsg errorMsg;

    @Override
    public void getData(int comid, final OnResultListener onResultListener) {
        stringRequest=new MyStringRequest(Request.Method.POST, Config.HOST + "importantCargoStock", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject= JSON.parseObject(response);
                int status=jsonObject.getInteger("AppStatusCode");
                if (status==1){
                    onResultListener.onSucess(response);
                }else if (status==-1){
                    errorMsg=new ErrorMsg(-1,"后台程序出错啦~");
                    onResultListener.onFail(errorMsg);
                }else if (status==0){
                    errorMsg=new ErrorMsg(0,"验证失败");
                    onResultListener.onFail(errorMsg);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMsg=new ErrorMsg(2,"网络有点问题");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("token", MyApplication.getInstance().getToken());
                map.put("app_key",Config.KEY);
                map.put("comid",MyApplication.getInstance().getComId()+"");
                return map;
            }
        };

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }


}
