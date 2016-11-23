package com.netposa.npmobilesdk.tool;


import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.jsbridge.CallBackFunction;
import com.netposa.npmobilesdk.map.NetPosaMap;

/**
 * 测量工具类
 */

public class Measure extends Entity {

    private NetPosaMap map;

    /**
     * 测量工具类
     *
     * @param map 当前地图
     */
    public Measure(NetPosaMap map) {
        this.setClassName("NPMobile.Tool.Measure");
        this.map = map;
    }

    public NetPosaMap getMap() {
        return map;
    }

    public void setMap(NetPosaMap map) {
        this.map = map;
    }

    /**
     * @param mode 面积测量:Constants.MeasureMode_AREA Constants.
     *             距离测量:MeasureMode_DISTANCE
     */
    public void setMode(String mode) {
        //final NPCallBackFunction<String> temp = callBackFunction;
        this.ExecuteJs("setMode",null, mode);
//        this.events.put("callback", new NPEventListener() {
//            @Override
//            public void processEvent(EventObject sender, EventArgs e) {
//
//            }
//        });
    }

    private void ExecuteJs(String method, CallBackFunction callBack, Object... args) {
        this.map.ExecuteJs(this, method, callBack, args);
    }

    /**
     * 清除测量工具
     */
    public void remove() {
        this.ExecuteJs("remove", null);
    }
}
