package com.gzport.gzgsearch.view;

import com.gzport.gzgsearch.model.ErrorMsg;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public interface IShipWorkView {

    /**
     * 开始加载
     * @param pageCount 页数
     * @param start 起始页号
     * @param companyID 机构id
     */
    void benginLoad(int pageCount,int start,int companyID);

    /**
     * 显示错误信息
     * @param errorMsg 错误信息
     */
    void showErroeMsg(ErrorMsg errorMsg);

    /**
     * 获得了数据
     * @param list 结果
     */
    void endLoading(List list);
}
