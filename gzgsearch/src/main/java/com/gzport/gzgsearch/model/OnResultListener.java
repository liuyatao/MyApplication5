package com.gzport.gzgsearch.model;

import java.util.List;

/**
 * Created by Administrator on 2015/8/4.
 */
public interface OnResultListener {
    /**
     * 返回字符
     * @param s
     */
    void onSucess(String s);

    /**
     * 返回列表
     * @param list
     */

    void onSucess(List list);

    /**
     * 请求失败的返回结果
     * @param
     */
    void onFail(ErrorMsg errorMsg);
}
