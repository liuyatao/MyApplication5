package com.gzport.gzgsearch.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.ILoadAndUnloadModelImp;
import com.gzport.gzgsearch.model.ILoadAndUnloadModel;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.view.ILoadAndUnloadView;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by 刘亚涛 on 2015/8/19.
 */
public class LoadAndUnloadPresenter {

    private ILoadAndUnloadView iLoadAndUnloadView;
    private ILoadAndUnloadModel iLoadAndUnloadModel;

    public LoadAndUnloadPresenter(ILoadAndUnloadView iLoadAndUnloadView) {
        this.iLoadAndUnloadView = iLoadAndUnloadView;
        iLoadAndUnloadModel = new ILoadAndUnloadModelImp();
    }

    @DebugLog
    public void getData(int comid, String token) {
        iLoadAndUnloadModel.getdData(comid, token, new OnResultListener() {
            @Override
            @DebugLog/*
            response="{"loadArray":[{"value":6.336092E7,"operationdate":"2015-08-19"},{"value":2.32732451204E8,"operationdate":"2015-08-18"},{"value":1.7852031184E8,"operationdate":"2015-08-17"},{"value":1.8030139822E8,"operationdate":"2015-08-16"},{"value":2.02440672805E8,"operationdate":"2015-08-15"}],"unloadArray":[{"value":6.524478E7,"operationdate":"2015-08-19"},{"value":1.782238634E8,"operationdate":"2015-08-18"},{"value":2.0827852461E8,"operationdate":"2015-08-17"},{"value":2.52469868639E8,"operationdate":"2015-08-16"},{"value":2.23188155877E8,"operationdate":"2015-08-15"}],"comid":"5","AppStatusCode":1}"*/
            public void onSucess(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                JSONArray loadJsonArray = jsonObject.getJSONArray("loadArray");
                List<String> loadDateList=new ArrayList<String>();
                List<Float> loadValueList=new ArrayList<Float>();

                for (int i = 0; i < loadJsonArray.size(); i++) {
                   JSONObject object= (JSONObject) loadJsonArray.get(i);
                    loadDateList.add(object.getString("operationdate"));
                    loadValueList.add(Float.valueOf(object.getString("value")));
                }

                JSONArray unloadJsonArray = jsonObject.getJSONArray("unloadArray");
                List<String> unLoadDateList=new ArrayList<String>();
                List<Float> unLoadValueList=new ArrayList<Float>();
                for (int i = 0; i < loadJsonArray.size(); i++) {
                    JSONObject object= (JSONObject) unloadJsonArray.get(i);
                    unLoadDateList.add(object.getString("operationdate"));
                    unLoadValueList.add(Float.valueOf(object.getString("value")));
                }
                iLoadAndUnloadView.loaded(loadDateList,loadValueList,unLoadDateList,unLoadValueList);
            }

            @Override
            public void onSucess(List list) {

            }

            @Override
            public void onFail(ErrorMsg errorMsg) {

            }
        });
    }
}
