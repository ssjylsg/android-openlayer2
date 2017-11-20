package com.netposa.npmobilesdk.layer;

import com.netposa.npmobilesdk.utils.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * 分组聚合图层参数配置
 */
public class GroupClusterLayerOptions extends BaseClusterLayerOptions  {
    private Map<String,Image> clusterImage;
    private Map<String,Image> singleImage;

    /**
     * 分组聚合图层参数配置
     */
    public GroupClusterLayerOptions() {
        this.clusterImage = new HashMap<>();
        singleImage = new HashMap<>();
    }

    /**
     * 新增聚合点分组图片
     * @param groupName 分组名称
     * @param image 图片
     */
    public void addClusterImage(String groupName,Image image){
        this.clusterImage.put(groupName,image);
    }

    /**
     * 新增散开点分组图片
     * @param groupName 分组名称
     * @param image 图片
     */
    public void addSingleImage(String groupName,Image image){
        this.singleImage.put(groupName,image);
    }

    public Map<String, Image> getClusterImage() {
        return clusterImage;
    }

    public Map<String, Image> getSingleImage() {
        return singleImage;
    }
}
