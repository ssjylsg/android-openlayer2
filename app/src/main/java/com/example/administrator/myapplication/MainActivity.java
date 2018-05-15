package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netposa.npmobilesdk.NPCallBackFunction;
import com.netposa.npmobilesdk.common.Constants;
import com.netposa.npmobilesdk.event.EventArgs;
import com.netposa.npmobilesdk.event.EventObject;
import com.netposa.npmobilesdk.event.MeasureCompletedListener;
import com.netposa.npmobilesdk.event.MeasureEventArgs;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.geometry.Circle;
import com.netposa.npmobilesdk.geometry.ClusterMarker;
import com.netposa.npmobilesdk.geometry.ClusterMarkerList;
import com.netposa.npmobilesdk.geometry.ClusterParmeters;
import com.netposa.npmobilesdk.geometry.LineString;
import com.netposa.npmobilesdk.geometry.LineStringStyle;
import com.netposa.npmobilesdk.geometry.Marker;
import com.netposa.npmobilesdk.geometry.MarkerStyle;
import com.netposa.npmobilesdk.geometry.Point;
import com.netposa.npmobilesdk.geometry.PolylineStyle;
import com.netposa.npmobilesdk.jsbridge.BridgeWebView;
import com.netposa.npmobilesdk.layer.ClusterLayer;
import com.netposa.npmobilesdk.layer.ClusterLayerOptions;
import com.netposa.npmobilesdk.layer.ClusterStatisticInfo;
import com.netposa.npmobilesdk.layer.CustomerLayer;
import com.netposa.npmobilesdk.layer.GroupClusterLayerOptions;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.tool.Measure;
import com.netposa.npmobilesdk.utils.Feature;
import com.netposa.npmobilesdk.utils.GeocoderHelper;
import com.netposa.npmobilesdk.utils.Image;
import com.netposa.npmobilesdk.utils.Size;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NetPosaMap map;
    private Button addBtn, add_cluster_btn;
    private CustomerLayer layer;
    private String clusterApiUrl = "http://192.168.60.216:82/pmvp/api/getgpsinfoofdir?userid=3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetPosaMap.initX5Environment(this.getApplicationContext());

        setContentView(R.layout.activity_main);

        addBtn = (Button) findViewById(R.id.add_btn);
        add_cluster_btn = (Button) findViewById(R.id.add_cluster_btn);

        intListener();
        BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);
        // 初始化NetposaMap

        webView.debug = true;

        map = new NetPosaMap(webView, "mapConfig.json", "http://192.168.60.45:807/mobile/dist/index_c.html", null);
        loadMap();

//        ((Button) findViewById(R.id.data_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {clusterData();
//            }
//        });
        //clusterData();
        // clusterLayer.addOverlaysForMobile(new ClusterParmeters(clusterApiUrl,new Image("img/marker.png", new Size(21, 25))));

        // GeocoderHelper.getPoint("http://192.168.60.242:8088/netposa", "公安局", new NPCallBackFunction<List<Point>>() {
        //     @Override
        //     public void onCallBack(List<Point> data) {
        //         showMessage("提示", data.size() + "");
        //     }
        // });

        points.add(new Point(117.065808367, 40.163818078886));
        points.add(new Point(117.26202823939, 40.135873688834));
        points.add(new Point(117.04098582979, 40.056228370046));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else {

        }
    }

    private void loadMap() {
        // 创建一个自定义图层
        layer = new CustomerLayer("测试");
        // 将图层添加到地图
        map.addLayer(layer);

        map.addEventListener(Constants.EVENT_TYPE_MAP_MOVEEND, new NPEventListener() {
            @Override
            public void processEvent(EventObject sender, EventArgs e) {
                showMessage("提示","地图移动");
            }
        });
    }

    private void mapAddClick() {
        map.addEventListener(Constants.EVENT_TYPE_MAP_CLICK, new NPEventListener() {
            @Override
            public void processEvent(EventObject sender, EventArgs e) {
                Object[] lonlat = (Object[]) e.getArgs();
                android.util.Log.i("map", e.toString());
                //   showMessage("提示",lonlat[0]+","+lonlat[1]);
                double lon = Double.parseDouble(lonlat[0].toString());
                double lat = Double.parseDouble(lonlat[1].toString());
                testAddMarker(new Point(lon, lat));
//                Circle  circle = new Circle(new Point(lon,lat),1000,null);
//                layer.addOverlay(circle);
//                circle.getWKT(new com.netposa.npmobilesdk.event.NPCallBackFunction<String>() {
//                    @Override
//                    public void onCallBack(String data) {
//                        showMessage("提示",data);
//                        map.removeEventListener(Constants.EVENT_TYPE_MAP_CLICK);
//                    }
//                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void testAddMarker(Point point) {

        // 创建一个Marker覆盖物
        if (point == null) {
            point = new Point(116.37948369818618, 39.871976142236186);
        }
        Marker marker = new Marker(point, new MarkerStyle("img/marker.png", 21.0, 25.0));
        // 添加Maker到创建好的自定义图层
        layer.addOverlay(marker);
        // 为Marker设置点击监听
        marker.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<Marker>() {
                    @Override
                    public void processEvent(EventObject<Marker> sender, EventArgs e) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("单个点位点击事件")
                                .setMessage(sender.getSource().getPoint().toString())
                                .show();
                    }
                }
        );
    }


    private void testAddClusterMarker() {

        clusterLayer.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<ClusterMarker>() {
            @Override
            public void processEvent(EventObject<ClusterMarker> sender, EventArgs e) {
                //sender.getSource().changeStyle(new MarkerStyle("img/Flag.png", 21.0, 25.0));
                showMessage("提示", sender.getSource().getMarkType());
            }
        });

        clusterLayer.addEventListener(Constants.EVENT_TYPE_ERROR, new NPEventListener<ClusterLayer>() {
            @Override
            public void processEvent(EventObject<ClusterLayer> sender, EventArgs e) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示").setMessage("加载失败").show();
            }
        });

        clusterLayer.addEventListener(Constants.EVENT_TYPE_SUCCESS, new NPEventListener<ClusterLayer>() {
            @Override
            public void processEvent(EventObject<ClusterLayer> sender, EventArgs e) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示").setMessage("加载成功").show();
            }
        });

        clusterLayer.addEventListener(Constants.EVENT_TYPE_CLUSTERCLICK, new NPEventListener<ClusterLayer>() {
                    @Override
                    public void processEvent(EventObject<ClusterLayer> sender, EventArgs e) {
                        showMessage("提示",((ArrayList)e.getArgs()).size()+"条数据");
                    }
                }
        );
    }

    private void intListener() {
        addBtn.setOnClickListener(this);
        add_cluster_btn.setOnClickListener(this);
    }

    int index = 0;
    private ArrayList<Point> points = new ArrayList<>();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
//                if(index == points.size()){
//                    index = 0;
//                }
//                map.panTo(points.get(index++));

                // map.searchRoad("西安","http://192.168.60.242:8088/netposa/",new PolylineStyle(10,"blue"));
                // addMarker();
                testMeasure();
                //testGeocoderHelper();
                // mapAddClick();
                //  clusterLayer.removeAllOverlays();
                // this.map.setBaiduTrafficLayerVisable(false);
                break;
            case R.id.add_cluster_btn:
                long start = new java.util.Date().getTime();
                clusterData();
//                clusterLayer.addOverlayList(list,false);
//                clusterLayer.addOverlayList(list1,true);
                //cluster_group();

                // clusterLayer.addOverlaysForMobile(new ClusterParmeters(clusterApiUrl,new Image("img/marker.png", new Size(21, 25))));

                //this.showMessage("提示","耗时:"+(new java.util.Date().getTime() - start));
                break;
            case R.id.data_btn:
                clusterData();
                break;
        }
    }

    ClusterLayer clusterLayer;
    ClusterMarkerList list;
    ClusterMarkerList list1;

    private void cluster_group() {
        double lon = 116.3427702718185;
        double lat = 39.89369592052587;
        map.setCenter(new Point(lon, lat));
        GroupClusterLayerOptions options = new GroupClusterLayerOptions();
        options.setFontColor("#000000");
        options.setFontSize("23px");

        options.addClusterImage("偶数", new Image("img/cluster_marker_bg.png", new Size(32, 32)));
        options.addClusterImage("奇数", new Image("img/Flag.png", new Size(32, 32)));

        options.addSingleImage("偶数", new Image("img/marker-gold.png", new Size(21, 25)));
        options.addSingleImage("奇数", new Image("img/marker-green.png", new Size(21, 25)));
        options.setMinZoom(6);
        if (clusterLayer == null) {
            clusterLayer = new ClusterLayer("聚合图层测试", options);
            this.map.addLayer(clusterLayer);
        }

        ArrayList<ClusterMarker> list = new ArrayList<ClusterMarker>();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1);
            String marker = i % 2 == 0 ? "偶数" : "奇数";
            list.add(new ClusterMarker(p, marker));
        }
        clusterLayer.addClusterMarkers(list);
        testAddClusterMarker();
    }

    private void clusterData() {
        double lon = 116.3427702718185;
        double lat = 39.89369592052587;

        // map.setCenter(new Point(lon,lat));
        ClusterLayerOptions options = new ClusterLayerOptions();
        options.setFontColor("#000000");
        options.setFontSize("23px");
        options.setClusterMarkerClickZoom(11);
        options.setClusterImage(new Image("img/Flag.png", new Size(32, 32)));
        List<ClusterStatisticInfo> statisticInfos = new ArrayList<>();
        statisticInfos.add(new ClusterStatisticInfo(lon, lat, "50"));

        options.setMinZoom(6);

        clusterLayer = new ClusterLayer("聚合图层测试", options);
        this.map.addLayer(clusterLayer);

        testAddClusterMarker();

        Image image = new Image("img/marker.png", new Size(21, 25));


        list = new ClusterMarkerList(image);
        for (int i = 0; i < 20000; i++) {
            list.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1), null, clusterLayer);
        }
        clusterLayer.addOverlayList(list, true);

        // list1 = new ClusterMarkerList(image);
        // for (int i = 0; i < 20000; i++) {
        //     list1.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
        //             lat + Math.random() * Math.pow(-1, i + 1) * 0.1), null, clusterLayer);
        // }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String url = java.net.URLEncoder.encode("http://192.168.60.37:82/pmvp/api/getchannelofdir?userid=3&id=Gps","UTF-8");
//                    android.util.Log.i("",url);
//                    HttpURLConnection connection = ((HttpURLConnection) new URL("http://192.168.62.63:807/mobile/dist/data.json").openConnection());
//                    connection.setRequestProperty("Charset", "UTF-8");
//                    connection.connect();
//                    InputStream is = connection.getInputStream();
//                    byte[] buffer = new byte[1024];
//                    int length = 0;
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    while (length != -1) {
//                        try {
//                            length = is.read(buffer);
//                        } catch (IOException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                        if (length != -1) {
//                            out.write(buffer, 0, length);
//                        }
//                    }
//                    JSONObject o = (JSONObject) com.alibaba.fastjson.JSONObject.parse(new String(out.toByteArray(), "utf-8"));
//                    JSONArray channel = (JSONArray)((JSONObject)o.get("data")).get("channel");
//                    for (int i =0;i<channel.size();i++){
//                        JSONObject m = (JSONObject)channel.get(i);
//                        new ClusterMarker(m.getString("id"),new Point(m.getBigDecimal("longitude").doubleValue(),m.getBigDecimal("latitude").doubleValue()),null)
//                                .setLayer(clusterLayer);
//                    }
//                    android.util.Log.i("",channel.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();
        showMessage("提示", "数据整理完成");
    }

    private void testMeasure() {
        List<Point> list =
                Arrays.asList(new Point[]{new Point(116.44622466238592, 39.898044516377375)
                        , new Point(116.44742988998291, 39.89844240091022), new Point(116.44758066337462, 39.89757032364166)});
        LineStringStyle style = new LineStringStyle();
        layer.addOverlay(new LineString(list, style));
        map.setCenter(list.get(0));
        map.SetZoom(18);
        addMarker(list.get(0));
        Measure measure = new Measure(this.map);
        measure.setMode(Constants.MeasureMode_DISTANCE, new MeasureCompletedListener() {
            @Override
            public void processEvent(Measure sender, MeasureEventArgs e) {
                showMessage("提示", e.getTotal() + e.getUnit());
            }
        }); //MeasureMode_DISTANCE
    }

    Handler handler = new Handler();

    private void testGeocoderHelper() {
        GeocoderHelper.getLocation("http://192.168.60.242:8080/netposa", new Point(119.95797015899728, 31.812219565250494), new NPCallBackFunction<Feature>() {
            @Override
            public void onCallBack(Feature data) {
                showMessage("提示", data != null ? data.toString() : "请求为空");
            }
        });

        GeocoderHelper.getPoint("http://192.168.60.242:8080/netposa", "天宁商场店", new NPCallBackFunction<List<Point>>() {
            @Override
            public void onCallBack(List<Point> features) {
                showMessage("提示", features.size() + "");
            }
        });
    }

    private void addMarker(Point point) {
        double lon = point != null ? point.getLon() : 116.3427702718185;
        double lat = point != null ? point.getLat() : 39.89369592052587;
        Point randomPoint = new Point(lon, lat);
        this.map.setCenter(randomPoint);
        MarkerStyle style = new MarkerStyle(
                "img/marker.png", 21.0, 25.0
        );
        style.setRotation(30.0); // 设置旋转角度
        style.setLabel("测试");   // 设置文字
        style.setFontColor("red");  // 设置文字颜色


        style.setLabelXOffset(-21.0 / 2); // 设置文字X轴偏移量
        style.setLabelYOffset(-25.0 / 2); // 设置文字Y轴偏移量
        final Marker marker = new Marker(randomPoint, style);
        layer.addOverlay(marker);
        // 添加点击事件
        marker.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<Marker>() {
                    @Override
                    public void processEvent(EventObject<Marker> sender, EventArgs e) {
                        //final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        //builder.setTitle("单个点位点击事件").setMessage(sender.getSource().getPoint().toString()).show();
                        // layer.removeOverlay(marker);
                        showMessage("提示", "单击事件");
                    }
                }
        );
//        map.getDistance(marker.getPoint(), new
//                Point(lon, lat), new NPCallBackFunction<Double>() {
//            @Override
//            public void onCallBack(Double data) {
//                ShowMessage("信息", data.toString());
//            }
//        });
    }

    private void showMessage(String title, String msg) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(title).setMessage(msg).show();
        } else {
            final String temp_title = title, temp_msg = msg;
            handler.post(new Runnable() {
                public void run() {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(temp_title).setMessage(temp_msg).show();
                }
            });
        }
    }
}

   