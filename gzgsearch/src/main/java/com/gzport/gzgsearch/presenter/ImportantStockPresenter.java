package com.gzport.gzgsearch.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.IImportantStockModel;
import com.gzport.gzgsearch.model.IImportantStockModelImpl;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.view.IImportantStockView;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by 刘亚涛 on 2015/8/19.
 */
public class ImportantStockPresenter {
    private IImportantStockView iImportantStockView;
    private IImportantStockModel iImportantStockModel;

    public ImportantStockPresenter(IImportantStockView iImportantStockView) {
        this.iImportantStockView = iImportantStockView;
        this.iImportantStockModel=new IImportantStockModelImpl();
    }
    public void getData(int comid){
        iImportantStockModel.getData(comid, new OnResultListener() {
            @Override
/*
            s="{"items":[{"count":"268366","cargoType":"粮食","reportDate":"2015-08-19"},{"count":"142405","cargoType":"煤炭","reportDate":"2015-08-19"},{"count":"141077","cargoType":"金属矿石","reportDate":"2015-08-19"},{"count":"153328","cargoType":"钢铁","reportDate":"2015-08-19"},{"count":"165692","cargoType":"其他","reportDate":"2015-08-19"}],"comid":"5","AppStatusCode":1}"*/
            @DebugLog
            public void onSucess(String s) {
                //List<ImportantStockBean> stockBeans=new ArrayList<ImportantStockBean>();
                List<Float> countList=new ArrayList<Float>();
                List<String> typeList=new ArrayList<String>();

                JSONObject jsonObject= JSON.parseObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("items");
                for (int i=0;i<jsonArray.size();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    countList.add(Float.valueOf(object.getString("count")));
                    typeList.add(object.getString("cargoType"));
                }
                iImportantStockView.loaded(countList,typeList);

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
