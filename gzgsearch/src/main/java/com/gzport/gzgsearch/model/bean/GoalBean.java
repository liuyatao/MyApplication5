package com.gzport.gzgsearch.model.bean;

/**
 * Created by 刘亚涛 on 2015/8/10.
 * 目标实体类
 */
public class GoalBean {
    /**
     * 月计划
     */
    private float planAmount;
    /**
     * 月完成量
     */
    private float finishAmonut;
    /**
     * 集装箱计划
     */
    private float planAmountTEU;
    /**
     * 集装箱完成量
     */
    private float finishAmountTEU;

    /**
     * 库存量
     */
    private String stockAmount;

    /**
     * 月完成百分比
     */
    private double monthFinishPer;

    /**
     * 集装箱完成百分比
     */
    private double TEUFinishPer;

    public GoalBean(float planAmount, float finishAmonut, float planAmountTEU, float finishAmountTEU, String stockAmount) {
        this.planAmount = planAmount;
        this.finishAmonut = finishAmonut;
        this.planAmountTEU = planAmountTEU;
        this.finishAmountTEU = finishAmountTEU;
        this.stockAmount = stockAmount;
    }

    public float getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(float planAmount) {
        this.planAmount = planAmount;
    }

    public float getFinishAmonut() {
        return finishAmonut;
    }

    public void setFinishAmonut(float finishAmonut) {
        this.finishAmonut = finishAmonut;
    }

    public float getPlanAmountTEU() {
        return planAmountTEU;
    }

    public void setPlanAmountTEU(float planAmountTEU) {
        this.planAmountTEU = planAmountTEU;
    }

    public float getFinishAmountTEU() {
        return finishAmountTEU;
    }

    public void setFinishAmountTEU(float finishAmountTEU) {
        this.finishAmountTEU = finishAmountTEU;
    }

    public String getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(String stockAmount) {
        this.stockAmount = stockAmount;
    }

    public double getMonthFinishPer() {
        return finishAmonut/planAmount;
    }

    public void setMonthFinishPer(double monthFinishPer) {
        this.monthFinishPer = monthFinishPer;
    }

    public double getTEUFinishPer() {
        return finishAmountTEU/planAmountTEU;
    }

    public void setTEUFinishPer(double TEUFinishPer) {
        this.TEUFinishPer = TEUFinishPer;
    }
}
