package com.netposa.npmobilesdk.layer;

import com.netposa.npmobilesdk.utils.Image;

/**
 * 聚合图层参数配置
 */

public class ClusterLayerOptions extends BaseClusterLayerOptions {

    private Image clusterImage;
    private Image singleImage;


    public Image getClusterImage() {
        return this.clusterImage;
    }

    /**
     * 设置聚合图片路径和图片大小
     *
     * @param clusterImage
     */
    public void setClusterImage(Image clusterImage) {
        this.clusterImage = clusterImage;
    }


    public Image getSingleImage() {
        return this.singleImage;
    }

    /**
     * 设置聚合散开点图片路径和图片大小
     *
     * @param singleImage
     */
    public void setSingleImage(Image singleImage) {
        this.singleImage = singleImage;
    }
}
