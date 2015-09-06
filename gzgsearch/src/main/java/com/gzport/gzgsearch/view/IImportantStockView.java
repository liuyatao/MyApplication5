package com.gzport.gzgsearch.view;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/19.
 */
public interface IImportantStockView extends IBaseView {

    void loaded(List<Float> countList, List<String> typeList);
}
