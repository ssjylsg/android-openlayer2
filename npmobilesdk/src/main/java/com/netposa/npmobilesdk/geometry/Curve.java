package com.netposa.npmobilesdk.geometry;

import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.event.NPCallBackFunction;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.jsbridge.CallBackFunction;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.utils.Util;

/**
 * 覆盖物抽象类
 */

public abstract class Curve extends Entity {

    @JSONField(serialize = false)
    private NetPosaMap map;

    /**
     * 面积获取
     */
    @JSONField(serialize = false)
    public void getArea(final NPCallBackFunction<Double> callBackFunction) {
        this.ExecuteJs("getArea", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (callBackFunction != null && !Util.isEmpty(data)) {
                    callBackFunction.onCallBack((Double.parseDouble(data)));
                } else {
                    callBackFunction.onCallBack(0.0);
                }
            }
        });
    }

    /**
     * 长度获取
     */
    @JSONField(serialize = false)
    public void getLength(final NPCallBackFunction<Double> callBackFunction) {
        this.ExecuteJs("getLength", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (callBackFunction != null && !Util.isEmpty(data)) {
                    callBackFunction.onCallBack((Double.parseDouble(data)));
                } else {
                    callBackFunction.onCallBack(0.0);
                }
            }
        });
    }

    public void hide() {
        this.ExecuteJs("hide");
    }

    public void refresh() {
        this.ExecuteJs("refresh");
    }

    public void setMap(NetPosaMap map) {
        this.map = map;
    }

    protected Object ExecuteJs(String method, Object... args) {
        if (this.map != null) {
            return this.map.ExecuteJs(this, method, args);
        }
        return null;
    }

    protected void ExecuteJs(String method, CallBackFunction callBack, Object... args) {
        if (this.map != null) {
            this.map.ExecuteJs(this, method, callBack, args);
        }

    }

    /**
     * 注册事件
     *
     * @param type
     * @param eventListener
     */
    @Override
    public void addEventListener(String type, NPEventListener eventListener) {
        this.ExecuteJs("register", type);
        this.events.put(type, eventListener);
    }
}
