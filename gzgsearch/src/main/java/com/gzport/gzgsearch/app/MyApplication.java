package com.gzport.gzgsearch.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2015/8/4.
 */
public class MyApplication extends Application {
    private static MyApplication ourInstance = new MyApplication();
    public static final String TAG = MyApplication.class
            .getSimpleName();
    private RequestQueue requestQueue;
    public static MyApplication getInstance() {
        return ourInstance;
    }

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance=this;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(this);
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /**
     * Token的保存
     * @param token
     */
    public void saveToken(String token,int comid){
        SharedPreferences sharedPreferences=getSharedPreferences("token",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token",token);
        editor.putInt("comid",comid);
        editor.commit();
    }

    /**
     * Token的读取
     * @return
     */
    public String getToken(){
        SharedPreferences sharedPreferences=getSharedPreferences("token",MODE_PRIVATE);
        return sharedPreferences.getString("token",null);
    }

    public int getComId(){
        SharedPreferences sharedPreferences=getSharedPreferences("token",MODE_PRIVATE);
        return sharedPreferences.getInt("comid",0);
    }

}
