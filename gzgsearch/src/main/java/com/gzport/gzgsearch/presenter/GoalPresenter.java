package com.gzport.gzgsearch.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.IGoalModel;
import com.gzport.gzgsearch.model.IGoalModelImp;
import com.gzport.gzgsearch.model.OnResultListener;
import com.gzport.gzgsearch.model.bean.GoalBean;
import com.gzport.gzgsearch.view.IGoalView;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/10.
 */
public class GoalPresenter {
    private IGoalView iGoalView;
    private IGoalModel iGoalModel;
    private GoalBean goalBean;
    private String ErrorString;

    public GoalPresenter(IGoalView iGoalView) {
        this.iGoalView = iGoalView;
    }

    public GoalBean getData(int companyID) {

        iGoalView.Loading();
        iGoalModel = new IGoalModelImp();
        iGoalModel.getData(companyID, new OnResultListener() {
            @Override
            public void onSucess(String content) {
                JSONObject resultJson= JSON.parseObject(content);
                if (resultJson.getInteger("AppStatusCode")==1) {
                    JSONArray jsonArray = resultJson.getJSONArray("items");
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    GoalBean goalBean = new GoalBean(Float.valueOf(jsonObject.getString("planAmount")), Float.valueOf(jsonObject.getString("finishAmount")), Float.valueOf(jsonObject.getString("planAmountTEU")), Float.valueOf(jsonObject.getString("finishAmountTEU")), jsonObject.getString("stockAmount"));
                    iGoalView.Loaded(goalBean);
                }
            }

            @Override
            public void onSucess(List list) {

            }

            @Override
            public void onFail(ErrorMsg errorMsg) {

            }


        });
        return goalBean;
    }
}
