package com.gzport.gzgsearch.view;

import com.gzport.gzgsearch.model.ErrorMsg;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/18.
 * 吞吐量对比的界面动作接口
 */
public interface IThPutView {
    void shoErrorMsg(ErrorMsg errorMsg);
    void loaded(List thPut,List container,List year);
}
