package com.netposa.npmobilesdk.layer;

import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.map.NetPosaMap;

/**
 * 图层抽象类
 */

public abstract class Layer extends Entity {

    @JSONField(serialize = false)
    protected NetPosaMap map;
    private String name;

    /**
     * 图层抽象类
     * @param name
     */
    protected Layer(String name) {
        this.name = name;
    }

    /**
     * 显示或隐藏
     * @param   display
     */
    public void display(boolean display) {
        this.ExecuteJs("display", display);
    }

    public NetPosaMap getMap() {
        return this.map;
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

    public String getName() {
        return name;
    }


}
