package com.gzport.gzgsearch.model.bean;

/**
 * Created by 刘亚涛 on 2015/8/18.
 */
public class ThPutBean {
    /**
     * 年份
     */
    private String year;
    /**
     * 吞吐量
     */
    private double[] thPut;
    /**
     * 集装箱
     */
    private double[] container;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double[] getThPut() {
        return thPut;
    }

    public void setThPut(double[] thPut) {
        this.thPut = thPut;
    }

    public double[] getContainer() {
        return container;
    }

    public void setContainer(double[] container) {
        this.container = container;
    }
}
