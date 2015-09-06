package com.gzport.gzgsearch.view;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/18.
 */
public interface ILoadAndUnloadView {
    void loaded(List<String> loadDateList, List<Float> loadValueList, List<String> unLoadDateList, List<Float> unLoadValueList);
}
