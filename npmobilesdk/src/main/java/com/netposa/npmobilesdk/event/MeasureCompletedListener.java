package com.netposa.npmobilesdk.event;

import com.netposa.npmobilesdk.Entity;
import com.netposa.npmobilesdk.tool.Measure;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface MeasureCompletedListener extends java.util.EventListener {
    void processEvent(Measure sender, MeasureEventArgs e);
}
