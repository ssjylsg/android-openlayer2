package com.netposa.npmobilesdk.map;

import android.webkit.WebSettings;

import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.NPCallBackFunction;
import com.netposa.npmobilesdk.NPWebViewClient;
import com.netposa.npmobilesdk.SimpleJavaJSWebChromeClient;
import com.netposa.npmobilesdk.event.EventCallBackArgs;
import com.netposa.npmobilesdk.event.EventManager;
import com.netposa.npmobilesdk.geometry.Point;
import com.netposa.npmobilesdk.jsbridge.BridgeHandler;
import com.netposa.npmobilesdk.jsbridge.BridgeWebView;
import com.netposa.npmobilesdk.jsbridge.CallBackFunction;
import com.netposa.npmobilesdk.jsbridge.DefaultHandler;
import com.netposa.npmobilesdk.layer.Layer;
import com.netposa.npmobilesdk.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义 NetPosaMap 地图对象的操作方法与接口
 */
public class NetPosaMap extends Entity {
    private static final String NetPosaMap_TAG = "NetPosaMap";
    @JSONField(serialize = false)
    private BridgeWebView webView;
    private String mapConfig;
    private String mapContainer = "viewerContainer";
    private Boolean isMapavaild = false;
    private EventManager manager;
    private boolean isDebug = false;
    private String outMsg;

    /**
     * NetPosaMap 构造函数
     *
     * @param webView   webView
     * @param mapConfig 地图配置地址
     * @param mapUrl    地图地址
     */
    public NetPosaMap(BridgeWebView webView, String mapConfig, String mapUrl) {
        this.setClassName("NPMobile.Map");
        this.mapConfig = mapConfig;

        WebSettings webSettings = webView.getSettings();

        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webView.requestFocus();

        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebViewClient(new NPWebViewClient(webView, this));
        webView.setWebChromeClient(new SimpleJavaJSWebChromeClient(this));

        this.webView = webView;

        this.loadUrl(mapUrl);
        manager = new EventManager();

        /**
         * 注册JS 调用andriod 方法，此方法一般用于JS事件回调
         */
        webView.registerHandler("NPMobileHelper.Event.Call", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                EventCallBackArgs e = com.alibaba.fastjson.JSON.parseObject(data, EventCallBackArgs.class);
                if (e != null) {
                    Entity entity = Util.getEntity(e.getId());
                    entity.processEvent(e.getEventType(), e.getArgs());
                }
            }
        });
    }

    public void CreateMap() {
        if (isMapavaild) {
            return;
        }
        if (this.isDebug) {
            return;
        }
        String msg = this.getJavascript(this, "donothing");
        this.ExecuteJavaScripts(msg, null);
        isMapavaild = true;
        while (!manager.isEmpty()) {
            manager.execute();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadUrl(String url) {
        this.webView.loadUrl(url);
    }

    private Object ExecuteJs(String method, Object... args) {
        return this.ExecuteJs(this, method, args);
    }

    private <T extends Entity> String getJavascript(T obj, String method, Object... args) {
        List<String> list = new ArrayList<>();
        list.add(obj.toString());
        list.add("'" + method + "'");
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    list.add("'" + args[i] + "'");
                } else {
                    list.add(args[i].toString());
                }
            }
        }
        String msg = Util.join(list.toArray(), ",");
        return msg;
    }

    public Object ExecuteJavaScripts(String code, CallBackFunction callBack) {
        final CallBackFunction tempCallBack = callBack;
        webView.callHandler("NPMobileHelper.callMethod", code, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (tempCallBack != null) {
                    tempCallBack.onCallBack(data);
                }
            }
        });
        return outMsg;
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, CallBackFunction callBack, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        return ExecuteJavaScripts(msg, callBack);
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        isMapavaild = true;
        if (!this.isMapavaild) {
            this.manager.push(this, "ExecuteJavaScripts", msg);
            return null;

        } else {
            return ExecuteJavaScripts(msg, null);
        }
    }

    /**
     * 获取地图层级
     *
     * @param zoom
     */
    public void SetZoom(int zoom) {
        this.ExecuteJs("setZoom", zoom);
    }

    /**
     * 获取地图层级
     *
     * @param callBackFunction
     */
    @JSONField(serialize = false)
    public void getZoom(final NPCallBackFunction<Integer> callBackFunction) {
        this.ExecuteJs(this, "getZoom", new CallBackFunction() {

            @Override
            public void onCallBack(String data) {
                if (callBackFunction != null) {
                    callBackFunction.onCallBack(Integer.parseInt(data));
                }
            }
        });
    }

    /**
     * 获取地图配置URL
     *
     * @return mapConfig
     */
    public String getMapConfig() {
        return mapConfig;
    }

    /**
     * 设置地图配置URL
     *
     * @param mapConfig
     */
    public void setMapConfig(String mapConfig) {
        this.mapConfig = mapConfig;
    }

    /**
     * 设置地图容器
     *
     * @return {String}
     */
    public String getMapContainer() {

        return mapContainer;
    }

    /**
     * 获取地图容器
     *
     * @param mapContainer
     */
    public void setMapContainer(String mapContainer) {
        this.mapContainer = mapContainer;
    }

    /**
     * 新增图层
     *
     * @param layer
     */
    public void addLayer(Layer layer) {
        this.ExecuteJs("addLayer", layer);
        layer.setMap(this);
    }

    public boolean parseJsonFromJs(String msg) {
        outMsg = msg;
        return true;
    }

    /**
     * 获取地图中心点
     *
     * @param callBackFunction
     */
    @JSONField(serialize = false)
    public void getCenter(final NPCallBackFunction<Point> callBackFunction) {
        this.ExecuteJs(this, "getCenter", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (callBackFunction != null) {
                        callBackFunction.onCallBack(new Point(jsonObject.getDouble("lon"), jsonObject.getDouble("lat")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置地图中心点
     *
     * @param center
     */
    @JSONField(serialize = false)
    public void setCenter(Point center) {
        this.ExecuteJs("setCenter", center);
    }

    /**
     * 释放地图资源
     * 1.调用map 的destroy 方法，清除页面缓存的对象
     * 2.禁止webview 执行JS 方法
     * 3.清理服务端缓存的Entity 对象
     */
    public void dispose() {
        this.ExecuteJs("destroy");
        this.webView.getSettings().setJavaScriptEnabled(false);
        Util.clearAllEntity();
    }
}
