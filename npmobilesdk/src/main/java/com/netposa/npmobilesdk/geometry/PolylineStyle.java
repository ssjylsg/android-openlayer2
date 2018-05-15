package com.netposa.npmobilesdk.geometry;

public class PolylineStyle extends Style {
    private int strokeWidth = 5;
    private String strokeColor = "red";

    public PolylineStyle(){

    }
    /**
     *
     * @param strokeWidth 线宽 默认5
     * @param strokeColor 颜色 默认red
     */
    public PolylineStyle(int strokeWidth,String strokeColor){
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }
}
