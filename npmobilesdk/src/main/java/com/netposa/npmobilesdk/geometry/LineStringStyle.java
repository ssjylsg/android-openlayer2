package com.netposa.npmobilesdk.geometry;


public class LineStringStyle extends Style {
    private String graphicName = "triangle";
    private String strokeColor = "red";
    private Double strokeWidth = 2.0;
    private String strokeDashstyle = "solid";
    private Double pointRadius = 6.0;

    public String getGraphicName() {
        return graphicName;
    }

    public void setGraphicName(String graphicName) {
        this.graphicName = graphicName;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeDashstyle() {
        return strokeDashstyle;
    }

    public void setStrokeDashstyle(String strokeDashstyle) {
        this.strokeDashstyle = strokeDashstyle;
    }

    public Double getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(Double pointRadius) {
        this.pointRadius = pointRadius;
    }

    public String getStrokeLinecap() {
        return strokeLinecap;
    }

    public void setStrokeLinecap(String strokeLinecap) {
        this.strokeLinecap = strokeLinecap;
    }

    private String strokeLinecap = "'round";

}
