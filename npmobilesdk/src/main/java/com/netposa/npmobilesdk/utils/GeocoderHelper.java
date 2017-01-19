package com.netposa.npmobilesdk.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netposa.npmobilesdk.NPCallBackFunction;
import com.netposa.npmobilesdk.geometry.Point;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 地址解析类
 */

public class GeocoderHelper {
    /**
     * 地址解析
     *
     * @param netPosaUrl       如http://192.168.60.242:8080/netposa
     * @param address
     * @param callBackFunction
     */
    public static void getPoint(String netPosaUrl, String address, NPCallBackFunction<List<Point>> callBackFunction) {
        final String url = netPosaUrl + "/query/poiname";
        final String parmeter;
        final NPCallBackFunction<List<Point>> temp = callBackFunction;
        HttpRequest.sendGet(netPosaUrl + "/query/poiname", "keyWord=" + address);

        try {
            parmeter = "keyWord=" + URLEncoder.encode(address, "UTF-8");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = HttpRequest.sendGet(url, parmeter);
                    List<Point> features = new ArrayList<>();
                    JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("features");
                    int count = jsonArray.size();
                    for (int i = 0; i < count; i++) {
                        JSONArray coordinates = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates");
                        features.add(new Point(coordinates.getDouble(0), coordinates.getDouble(1)));
                    }
                    if (temp != null) {
                        temp.onCallBack(features);
                    }
                }
            };
            new Thread(runnable).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 地址反解析
     *
     * @param netPosaUrl 如http://192.168.60.242:8080/netposa
     * @param address
     * @return
     */
    public static void getLocation(String netPosaUrl, final Point address, NPCallBackFunction<Feature> callBackFunction) {
        final String url = netPosaUrl + "/query/poicoord";
        final String parmeter;
        if (callBackFunction == null) {
            return;
        }
        final NPCallBackFunction<Feature> temp = callBackFunction;
        try {
            parmeter = "coord=" + URLEncoder.encode(address.getLon() + "," + address.getLat(), "UTF-8");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = HttpRequest.sendGet(url, parmeter);
                    if (Util.isEmpty(result)) {
                        temp.onCallBack(null);
                        return;
                    }
                    Feature feature = com.alibaba.fastjson.JSON.parseObject(result, Feature.class);
                    if (feature == null) {
                        temp.onCallBack(null);
                    } else {
                        feature.setPoint(address);
                        temp.onCallBack(feature);
                    }
                }
            };
            new Thread(runnable).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
    }
}
