package com.gzport.gzgsearch.view;

import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.bean.GoalBean;

/**
 * Created by 刘亚涛 on 2015/8/10.
 */
public interface IGoalView {
    void Loading();
    void Loaded(GoalBean goalBean);
    void showErrorMsg(ErrorMsg errorMsg);
}
