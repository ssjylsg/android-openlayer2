package com.netposa.npmobilesdk;

import com.alibaba.fastjson.annotation.JSONField;
import com.netposa.npmobilesdk.event.EventArgs;
import com.netposa.npmobilesdk.event.EventObject;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.utils.Util;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽象实体类
 */
public abstract class Entity {
    @JSONField(serialize = false)
    protected Map<String, NPEventListener> events = new HashMap<>();
    private String id;
    private String className;

    protected Entity() {
        this.id = Util.generateId();
        Util.AddEntity(this);
    }

    protected Entity(String id) {
        this.id = id;
        Util.AddEntity(this);
    }

    /**
     *
     * @return className
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * 设置与之对应的JS对象名称
     * @param className
     */
    protected void setClassName(String className) {
        this.className = className;
    }

    /**
     * 设置ID
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * 获取ID
     * @param id
     */
    protected void setId(String id) {
        this.id = id;
    }


    /**
     * 重写toString，实现对象转为JSON
     * @return Json字符串
     */
    @Override
    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    /**
     * 注册事件
     * @param    type 事件类型
     * @param eventListener
     */
    public void addEventListener(String type, NPEventListener eventListener){};

    /**
     * 触发事件
     * @param event 事件类型
     * @param args 事件参数
     */
    public void processEvent(String event, Object... args) {
        NPEventListener e = this.events.get(event);
        if(e != null){
            e.processEvent(new EventObject(this), new EventArgs(args));
        }
    }
}
