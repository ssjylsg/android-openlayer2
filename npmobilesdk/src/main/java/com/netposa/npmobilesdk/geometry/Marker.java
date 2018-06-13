package com.netposa.npmobilesdk.geometry;

/**
 *  图片覆盖物
 */
public class Marker extends Curve {
    private Point point;
    private MarkerStyle options;

    public Marker(Point point, MarkerStyle options) {
        this.setClassName("NPMobile.Geometry.Marker");
        this.setPoint(point);
        this.setOptions(options);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
        this.ExecuteJs("setPoint",point);
    }

    public MarkerStyle getOptions() {
        return options;
    }

    public void setOptions(MarkerStyle options) {
        this.options = options;
        this.ExecuteJs("setStyle",options);
    }


}
