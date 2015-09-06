package com.gzport.gzgsearch.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by 刘亚涛 on 2015/8/15.
 */
public class MyStringRequest extends StringRequest {


    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public MyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    /**
     * 解析玩过返回结果  转成gbk
     * @param response
     * @return
     */
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
            json = new String(response.data, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
    }



}
