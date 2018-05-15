package com.netposa.npmobilesdk.event;

import com.netposa.npmobilesdk.tool.MeasureState;


public class MeasureEventArgs {
    public MeasureEventArgs(Double total,String unit,MeasureState state){
        this.total = total;
        this.setUnit(unit);
        this.setState(state);
    }

    public MeasureState getState() {
        return state;
    }

    public void setState(MeasureState state) {
        this.state = state;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    private MeasureState state;
    private Double total;
    private String Unit;
}
