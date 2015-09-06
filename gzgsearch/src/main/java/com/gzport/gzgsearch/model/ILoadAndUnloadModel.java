package com.gzport.gzgsearch.model;

/**
 * Created by 刘亚涛 on 2015/8/18.
 * 装卸货的业务接口
 */
public interface ILoadAndUnloadModel {
    void getdData(int comId,String token,OnResultListener onResultListener);
}
