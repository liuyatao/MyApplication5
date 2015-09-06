package com.gzport.gzgsearch.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.IThPutModel;
import com.gzport.gzgsearch.model.IThPutModelImp;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.view.IThPutView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/18.
 */
public class ThPutPresenter {
    private IThPutView iThPutView;
    private IThPutModel iThPutModel;
    /**
     * 吞吐量
     */


    /**
     * 集装箱
     */


    /**
     * 年份
     */


    public ThPutPresenter(IThPutView iThPutView) {
        this.iThPutView=iThPutView;
        iThPutModel=new IThPutModelImp();
    }
    public void getData(int comid,String years,String token){
        iThPutModel.getData(comid, years, token, new OnResultListener() {
            @Override
            public void onSucess(String s) {
                List<ArrayList<Float>> thPutList=new ArrayList<>();

                List<ArrayList<Float>> containerList=new ArrayList<>();

                List<String> strings=new ArrayList<>();

                JSONObject jsonObject= JSON.parseObject(s);
                /**
                 * 吞吐量
                 */
                JSONArray jsonArrayTh= (JSONArray) jsonObject.get("throughput");
                /**
                 * 集装箱
                 */
                JSONArray jsonArrayCon= (JSONArray) jsonObject.get("container");
                /**
                 * 年份
                 */
                JSONArray jsonArrayYear= (JSONArray) jsonObject.get("year");

                for (int i=0;i<jsonArrayTh.size();i++){
                    JSONArray jsonArray= (JSONArray) jsonArrayTh.get(i);
                    ArrayList<Float> doubleArrayList=new ArrayList<Float>();
                    for (int j=0;j<jsonArray.size();j++){
                        doubleArrayList.add(Float.valueOf(jsonArray.get(j).toString()));
                    }
                    thPutList.add(doubleArrayList);

                }
                for (int i=0;i<jsonArrayCon.size();i++){
                    JSONArray jsonArray= (JSONArray) jsonArrayTh.get(i);
                    ArrayList<Float> doubleArrayList=new ArrayList<Float>();
                    for (int j=0;j<jsonArray.size();j++){
                        doubleArrayList.add(Float.valueOf(jsonArray.get(j).toString()));
                    }
                    containerList.add(doubleArrayList);
                }

                for (int i=0;i<jsonArrayYear.size();i++){
                    strings.add(jsonArrayYear.get(i).toString());
                }
                iThPutView.loaded(thPutList,containerList,strings);
            }

            @Override
            public void onSucess(List list) {

            }

            @Override
            public void onFail(ErrorMsg errorMsg) {
                iThPutView.shoErrorMsg(errorMsg);
            }
        });

    }
}
