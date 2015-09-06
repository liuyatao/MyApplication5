package com.gzport.gzgsearch.model;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public interface IShipWorkModel {

    /**
     * 根据当前起始页码 和页数获取
     * @param page 每页的数目
     * @param start 当前起始
     * @param onResultListener 结果
     */
    void getData(int page,int start,int companyID,OnResultListener onResultListener);
}
