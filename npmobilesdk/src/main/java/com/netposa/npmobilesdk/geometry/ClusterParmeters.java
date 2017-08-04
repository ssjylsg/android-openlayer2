package com.netposa.npmobilesdk.geometry;

import com.netposa.npmobilesdk.utils.Image;

public class ClusterParmeters {
    private String url;
    private Image defaultImage;
    public ClusterParmeters(String url,Image defaultImage){
        this.url = url;
        this.defaultImage = defaultImage;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Image defaultImage) {
        this.defaultImage = defaultImage;
    }
    /**
     * 重写toString，实现对象转为JSON
     * @return Json字符串
     */
    @Override
    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }
}
