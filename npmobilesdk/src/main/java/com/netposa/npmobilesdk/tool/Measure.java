package com.netposa.npmobilesdk.tool;


import com.alibaba.fastjson.JSONObject;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.event.EventArgs;
import com.netposa.npmobilesdk.event.EventObject;
import com.netposa.npmobilesdk.event.MeasureCompletedListener;
import com.netposa.npmobilesdk.event.MeasureEventArgs;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.jsbridge.CallBackFunction;
import com.netposa.npmobilesdk.map.NetPosaMap;

/**
 * 测量工具类
 */

public class Measure extends Entity {

    private NetPosaMap map;
    private MeasureState state;

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
    public void setMode(String mode, final MeasureCompletedListener listener) {
        //final NPCallBackFunction<String> temp = callBackFunction;
        state = MeasureState.UnMeasure;
        this.ExecuteJs("setMode",null, mode);
        this.events.clear();
        this.events.put("MeasureCompleted", new NPEventListener() {
            @Override
            public void processEvent(EventObject sender, EventArgs e) {
                if(listener != null){
                    JSONObject obj = (JSONObject)com.alibaba.fastjson.JSONObject.parse (((Object[])e.getArgs())[0].toString());
                    listener.processEvent((Measure)sender.getSource(),new MeasureEventArgs(obj.getDouble("measure"),obj.getString("unit"),MeasureState.Measured));
                }
            }
        });
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
    public void destory(){
        this.ExecuteJs("destory", null);
    }
}
