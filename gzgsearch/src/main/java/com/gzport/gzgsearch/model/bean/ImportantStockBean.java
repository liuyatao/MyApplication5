package com.gzport.gzgsearch.model.bean;

/**
 * Created by 刘亚涛 on 2015/8/19.
 * 重点库存
 */
public class ImportantStockBean {
    private  float count;
    private  String type;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public ImportantStockBean(float count, String type) {
        this.count = count;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
