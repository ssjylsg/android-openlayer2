package com.netposa.npmobilesdk.utils;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * Size
 */

public class Size {
    private double height;
    @JSONField(name="w")
    public double getWidth() {
        return width;
    }
    @JSONField(name="w")
    public void setWidth(double width) {
        this.width = width;
    }
    @JSONField(name="h")
    public double getHeight() {
        return height;
    }
    @JSONField(name="h")
    public void setHeight(double hight) {
        this.height = hight;
    }

    private double width;




    public Size(double height, double width) {
        this.height = height;
        this.width = width;
    }


}
