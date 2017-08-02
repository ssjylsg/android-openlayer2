package com.netposa.npmobilesdk.layer;


import com.netposa.npmobilesdk.event.EventArgs;
import com.netposa.npmobilesdk.event.EventObject;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.geometry.ClusterMarker;
import com.netposa.npmobilesdk.geometry.ClusterMarkerList;
import com.netposa.npmobilesdk.utils.Util;

import java.util.ArrayList;


/**
 * 聚合图层
 */
public class ClusterLayer extends Layer {
    private ClusterLayerOptions options;

    /**
     * 聚合图层
     *
     * @param name    图层名称
     * @param options 参数配置
     */
    public ClusterLayer(String name, ClusterLayerOptions options) {
        super(name);
        this.setOptions(options);
        this.setClassName("NPMobile.Layers.ClusterLayer");
    }

    /**
     * 新增聚合Marker
     *
     * @param clusterMarkers
     * @param isComplete     聚合新增是否结束。由于webview 传输限制，聚合每5K组数据传输一次。
     */
    public void addClusterMarkers(ArrayList<ClusterMarker> clusterMarkers, boolean isComplete) {
        for (int i = 0; i < clusterMarkers.size(); i++) {
            clusterMarkers.get(i).setLayer(this);
        }
        this.ExecuteJs("addOverlays", clusterMarkers, isComplete);
    }

    /**
     * 新增聚合Marker 自动5000条数据提交一次
     *
     * @param clusterMarkers
     */
    public void addClusterMarkers(ArrayList<ClusterMarker> clusterMarkers) {
        ArrayList<ClusterMarker> list = new ArrayList<>();
        int length = clusterMarkers.size();
        ClusterMarker marker;
        for (int i = 0; i < length; i++) {
            marker = clusterMarkers.get(i);
            marker.setLayer(this);
            list.add(marker);
            if (list.size() != 0 && list.size() % 5000 == 0) {
                this.ExecuteJs("addOverlays", list, false);
                list.clear();
            }
        }
        this.ExecuteJs("addOverlays", list, true);
    }

    /**
     * 批量数据加载 建议20000 数据提交一次
     * @param markers
     * @param isComplete 是否加载完成
     */
    public void addOverlayList(ClusterMarkerList markers,boolean isComplete) {
        String msg = "javascript:WebViewJavascriptBridge._handleMessageFromNative('{\"handlerName\":\"NPMobileHelper.callMethod\"}'," + this.toString() + ",'addOverlayList'," + markers.toString() + "," + (isComplete ? "true" : "false") + ")";
        this.map.loadUrl(msg);
    }
    @Override
    public void processEvent(String event, Object... args) {
        ClusterMarker marker = (ClusterMarker) Util.getEntity(args[0].toString());
        this.events.get(event).processEvent(new EventObject(marker), new EventArgs(null));
    }


    public ClusterLayerOptions getOptions() {
        return options;
    }

    public void setOptions(ClusterLayerOptions options) {
        this.options = options;
    }

    /**
     * 注册事件
     *
     * @param type          如click
     * @param eventListener
     */
    @Override
    public void addEventListener(String type, NPEventListener eventListener) {
        this.ExecuteJs("register", type);
        this.events.put(type, eventListener);
    }
}
