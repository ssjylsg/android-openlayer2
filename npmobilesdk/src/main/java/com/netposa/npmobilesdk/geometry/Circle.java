package com.netposa.npmobilesdk.geometry;

import com.netposa.npmobilesdk.event.NPCallBackFunction;
import com.netposa.npmobilesdk.jsbridge.CallBackFunction;
import com.netposa.npmobilesdk.utils.Util;


/**
 *  圆
 */
public class Circle extends Curve {
   private Point  center;
   private double radius;
   private Style style;

    /**
     *  圆
     * @param center 中心点
     * @param radius 半径单位米
     * @param style 样式
     */
    public Circle(Point  center,double radius,Style style) {
        this.setClassName("Circle");
        this.center = center;
        this.radius = radius;
        this.style = style;
    }

    /**
     * 设置圆中心点
     * @return
     */
    public Point getCenter() {
        return center;
    }

    /**
     * 设置圆中心点
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * 获取圆半径
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 设置圆半径
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * 获取样式
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * 设置样式
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * 获取圆WKT 数据
     * @param callBackFunction
     */
    public void getWKT(final NPCallBackFunction<String> callBackFunction) {
        this.ExecuteJs("getWKT", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (callBackFunction != null && !Util.isEmpty(data)) {
                    callBackFunction.onCallBack(data);
                }
            }
        });
    }
}
