package com.netposa.npmobilesdk.geometry;

import com.netposa.npmobilesdk.layer.ClusterLayer;
import com.netposa.npmobilesdk.utils.Image;


import java.util.ArrayList;
import java.util.List;


public class ClusterMarkerList {
    private List<Object> markers;
    private Image defaultUrl;


    public ClusterMarkerList(Image image) {
        this.markers = new ArrayList<>();
        this.defaultUrl = image;
    }

    /**
     * 增加聚合点
     * @param point 位置不可空
     * @param image 如果为null，才采用默认的image
     * @param layer 所在图层，不能为空
     */
    public ClusterMarker addMarker(Point point, Image image, ClusterLayer layer) throws NullPointerException {
        if(point == null){
            throw new NullPointerException("Point 位置点不能为空");
        }
        if(layer == null){
            throw new NullPointerException("layer 图层不能为空");
        }
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        ClusterMarker marker =  new ClusterMarker(point,image);
        marker.setLayer(layer);
        jsonObject.put("id", marker.getId());
        jsonObject.put("lon", point.getLon());
        jsonObject.put("lat", point.getLat());
        if (image != null && this.defaultUrl != image) {
            jsonObject.put("image", image);
        }
        markers.add(jsonObject);
        return marker;
    }

    public Image getDefaultUrl() {
        return defaultUrl;
    }

    @Override
    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    public List<Object> getList() {
        return markers;
    }
}
