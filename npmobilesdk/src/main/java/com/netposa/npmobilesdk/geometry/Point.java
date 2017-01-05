package com.netposa.npmobilesdk.geometry;

import com.netposa.npmobilesdk.Entity;

/**
 * 点位
 */

public class Point extends Entity {

    private double lon = 0.0;
    private double lat = 0.0;

    public Point() {
        this.setClassName("P");
        this.setId("");
    }

    /**
     * 84坐标系Point
     * @param lon 横坐标
     * @param lat Y坐标
     */
    public Point(double lon, double lat) {
        this();
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


}
