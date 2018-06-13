package com.netposa.npmobilesdk.geometry;


import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.layer.Layer;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.utils.Image;

/**
 * 聚合Marker
 */

public class ClusterMarker extends Entity {
    private Point point;
    private Layer layer;
    private Image image;
    private String markType;
    @JSONField(serialize = false)
    private Object tag;

    /**
     * 聚合Marker
     * @param point
     * @param image
     */
    public ClusterMarker(Point point,Image image) {
        this.setClassName("_CM");
        this.setPoint(point);
        this.setImage(image);
    }

    /**
     * 此方法为安卓缓存聚合点设置
     * @param id id  用于页面与安卓端识别个体 不能重复
     * @param point 坐标点
     * @param image 图片，可为null
     */
    public ClusterMarker(String id,Point point,Image image)    {
        super(id);
        this.setClassName("_CM");
        this.setPoint(point);
        this.setImage(image);
    }

    /**
     *
     * @param point 坐标点
     * @param image 可为null
     * @param markType 分组信息
     */
    public ClusterMarker(Point point,Image image,String markType){
        this(point,image);
        this.setMarkType(markType);
    }

    /**
     *
     * @param point 坐标点
     * @param markType 分组信息
     */
    public ClusterMarker(Point point,String markType){
        this(point,null,markType);
    }

    public ClusterMarker setLayer(Layer layer) {
        this.layer = layer;
        return this;
    }

    /**
     * 过时方法，请使用聚合图层的注册方法
     *
     * @param type
     * @param eventListener
     */
    @Deprecated
    public void addEventListener(String type, NPEventListener eventListener) {

    }

    @Deprecated
    public void processEvent(String event, Object... args) {

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * 修改样式，比如修改图片
     * @param options
     */
    public void changeStyle(MarkerStyle options) {
        if(this.layer == null){
            return;
        }
        NetPosaMap map = this.layer.getMap();
        if (map != null) {
            map.ExecuteJs(this, "changeStyle", options);
        }
    }

    public Image getImage() {
        return image;
    }

    /**
     * 设置散开之后的图片
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 获取存储的额外信息
     * @return
     */
    public Object getTag() {
        return tag;
    }

    /**
     * 设置额外信息
     * @param objectTag
     */
    public void setTag(Object objectTag) {
          tag = objectTag;
    }

    /**
     * 获取分组信息
     * @return
     */
    public String getMarkType() {
        return markType;
    }

    /**
     *  设置分组信息
     * @param markType
     */
    public void setMarkType(String markType) {
        this.markType = markType;
    }
}
