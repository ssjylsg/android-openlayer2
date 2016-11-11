package com.netposa.npmobilesdk.event;


import com.netposa.npmobilesdk.Entity;

/**
 * 事件监听器
 */

public interface NPEventListener<T extends Entity> extends java.util.EventListener {
    void processEvent(EventObject<T> sender, EventArgs e);
}
