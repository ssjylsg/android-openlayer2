package com.netposa.npmobilesdk.utils;

import com.netposa.npmobilesdk.geometry.Point;

/**
 * Created by Administrator on 2016/11/21.
 */

public class Feature {
    private String address;
    private String name;
    private Point point;
    private String districtName;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String toString(){
        return this.name + " " + this.address;
    }
}
