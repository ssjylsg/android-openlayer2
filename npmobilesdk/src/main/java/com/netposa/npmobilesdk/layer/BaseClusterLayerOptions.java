package com.netposa.npmobilesdk.layer;



import com.netposa.npmobilesdk.utils.Size;

import java.util.List;

/**
 * 基类聚合图层参数配置
 */

public abstract class BaseClusterLayerOptions {
    private int selectZoom;
    private int threshold;
    private int distance = 200;
    private int maxZoom;
    private Integer minZoom = null;
    private boolean isAsynchronous = true;
    private String fontColor;
    private String fontSize;
    private String customLabelFontColor;
    private Size customLabelOffset;
    private Integer clusterMarkerClickZoom = null;
    private List<ClusterStatisticInfo> statistics;
    public int getSelectZoom() {
        return selectZoom;
    }

    public void setSelectZoom(int selectZoom) {
        this.selectZoom = selectZoom;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    public Integer getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
    }

    public boolean isAsynchronous() {
        return isAsynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        isAsynchronous = asynchronous;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getCustomLabelFontColor() {
        return customLabelFontColor;
    }

    public void setCustomLabelFontColor(String customLabelFontColor) {
        this.customLabelFontColor = customLabelFontColor;
    }

    public Size getCustomLabelOffset() {
        return customLabelOffset;
    }

    public void setCustomLabelOffset(Size customLabelOffset) {
        this.customLabelOffset = customLabelOffset;
    }

    public List<ClusterStatisticInfo> getStatistics() {
        return statistics;
    }

    /**
     * 设置统计信息
     * @param statistics
     */
    public void setStatistics(List<ClusterStatisticInfo> statistics) {
        this.statistics = statistics;
    }

    public Integer getClusterMarkerClickZoom() {
        return clusterMarkerClickZoom;
    }

    /**
     * 设置聚合事件响应的地图层级 
     * @param clusterMarkerClickZoom
     */
    public void setClusterMarkerClickZoom(Integer clusterMarkerClickZoom) {
        this.clusterMarkerClickZoom = clusterMarkerClickZoom;
    }
}
