package com.netposa.npmobilesdk.layer;

/**
 * 统计信息
 */

public class ClusterStatisticInfo {
    public ClusterStatisticInfo(){

    }
    public ClusterStatisticInfo(double x,double y,String label){
        this.x = x;
        this.y = y;
        this.label = label;
    }
    /**
     * 重写toString，实现对象转为JSON
     * @return Json字符串
     */
    @Override
    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }
    private double x;
    private double y;
    private String label;

    /**
     * X
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * x
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Y
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * 获取显示文字
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置显示的文字
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
