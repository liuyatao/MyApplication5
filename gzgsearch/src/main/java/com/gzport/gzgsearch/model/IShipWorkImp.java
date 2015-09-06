package com.gzport.gzgsearch.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.config.Config;
import com.gzport.gzgsearch.model.bean.ShipWorkBean;
import com.gzport.gzgsearch.utils.MyStringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public class IShipWorkImp implements IShipWorkModel {

    private ErrorMsg errorMsg;
    private MyStringRequest stringRequest;

    @Override
    public void getData(final int page, final int start, final int companyID, final OnResultListener onResultListener) {
        stringRequest = new MyStringRequest(Request.Method.POST, Config.HOST+"shipWork", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //进行预处理，
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.getInteger("AppStatusCode")==1) {
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    List<ShipWorkBean> resultList=new ArrayList();
                    for (int i=0;i<jsonArray.size();i++){
                        ShipWorkBean shipWorkBean=new ShipWorkBean();
                        JSONObject object= (JSONObject) jsonArray.get(i);
                        shipWorkBean.setBenginTime(object.getString("beginTime"));
                        shipWorkBean.setEndTime(object.getString("endTime"));
                        shipWorkBean.setMsg_agent(object.getString("msg_agent"));
                        shipWorkBean.setNowEndBerth(object.getString("nowEndBerth"));
                        shipWorkBean.setShipName(object.getString("shipName"));
                        shipWorkBean.setTotalWeight(object.getString("totalWeight"));
                        shipWorkBean.setUniquecode(object.getString("uniquecode"));
                        shipWorkBean.setWorkPercent(object.getString("workPercent"));
                        shipWorkBean.setWorkWeight(object.getString("workWeight"));
                        resultList.add(shipWorkBean);
                    }
                    onResultListener.onSucess(resultList);


                } else if (jsonObject.getInteger("AppStatus")==0) {
                    errorMsg=new ErrorMsg(0,"登录失效");
                    onResultListener.onFail(errorMsg);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMsg=new ErrorMsg(2,"网络连接错误");
                onResultListener.onFail(errorMsg);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap();
                map.put("app_key",Config.KEY);
                map.put("limit",page+"");
                map.put("offset",start+"");
                map.put("token", MyApplication.getInstance().getToken());
                map.put("comid",companyID+"");
                return map;
            }

            @Override
            protected String getParamsEncoding() {
                return "GBK";
            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(stringRequest);
        return ;
    }
}
