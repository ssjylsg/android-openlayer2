package com.netposa.npmobilesdk.geometry;


import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.layer.Layer;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.utils.Image;
import com.netposa.npmobilesdk.utils.Util;

/**
 * 聚合Marker
 */

public class ClusterMarker extends Entity {
    private Point point;
    private Layer layer;
    private Image image;
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
     *
     * @param id
     * @param point
     * @param image
     */
    public ClusterMarker(String id,Point point,Image image)    {
        super(id);
        this.setClassName("_CM");
        this.setPoint(point);
        this.setImage(image);
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
}
