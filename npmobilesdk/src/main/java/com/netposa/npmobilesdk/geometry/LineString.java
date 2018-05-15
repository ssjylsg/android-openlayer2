package com.netposa.npmobilesdk.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * 线段
 */

public class LineString extends Curve {
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    private List<Point> points;
    private Style style;

    /**
     * 线段
     * @param points
     * @param style
     */
    public LineString(List<Point> points,Style style){
        this.setPoints(points);
        this.setStyle(style);
        this.setClassName("NPMobile.Geometry.LineString");
    }
}
