package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.res.Configuration;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.netposa.npmobilesdk.NPCallBackFunction;
import com.netposa.npmobilesdk.common.Constants;
import com.netposa.npmobilesdk.event.EventArgs;
import com.netposa.npmobilesdk.event.EventObject;
import com.netposa.npmobilesdk.event.NPEventListener;
import com.netposa.npmobilesdk.geometry.Circle;
import com.netposa.npmobilesdk.geometry.ClusterMarker;
import com.netposa.npmobilesdk.geometry.ClusterMarkerList;
import com.netposa.npmobilesdk.geometry.Marker;
import com.netposa.npmobilesdk.geometry.MarkerStyle;
import com.netposa.npmobilesdk.geometry.Point;
import com.netposa.npmobilesdk.jsbridge.BridgeWebView;
import com.netposa.npmobilesdk.layer.ClusterLayer;
import com.netposa.npmobilesdk.layer.ClusterLayerOptions;
import com.netposa.npmobilesdk.layer.ClusterStatisticInfo;
import com.netposa.npmobilesdk.layer.CustomerLayer;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.tool.Measure;
import com.netposa.npmobilesdk.utils.Feature;
import com.netposa.npmobilesdk.utils.GeocoderHelper;
import com.netposa.npmobilesdk.utils.Image;
import com.netposa.npmobilesdk.utils.Size;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NetPosaMap map;
    private Button addBtn, add_cluster_btn;
    private CustomerLayer layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = (Button) findViewById(R.id.add_btn);
        add_cluster_btn = (Button) findViewById(R.id.add_cluster_btn);

        intListener();
        BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);
        // 初始化NetposaMap

       // webView.debug = true;

        map = new NetPosaMap(webView, "mapConfig.json", "http://192.168.62.63:807/mobile/dist/index_c.html");
        loadMap();

        ((Button)findViewById(R.id.data_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clusterData();
            }
        });
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


    }
    private void mapAddClick(){
        map.addEventListener(Constants.EVENT_TYPE_MAP_CLICK, new NPEventListener() {
            @Override
            public void processEvent(EventObject sender, EventArgs e) {
                Object[] lonlat = (Object[])e.getArgs();
                android.util.Log.i("map",e.toString());
                double lon = Double.parseDouble(lonlat[0].toString());
                double lat = Double.parseDouble(lonlat[1].toString());
                Circle  circle = new Circle(new Point(lon,lat),1000,null);
                layer.addOverlay(circle);
                circle.getWKT(new com.netposa.npmobilesdk.event.NPCallBackFunction<String>() {
                    @Override
                    public void onCallBack(String data) {
                        showMessage("提示",data);
                        map.removeEventListener(Constants.EVENT_TYPE_MAP_CLICK);
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void testAddMarker() {

        // 创建一个Marker覆盖物
        Marker marker = new Marker(
                new Point(116.37948369818618, 39.871976142236186),
                new MarkerStyle("img/marker.png", 21.0, 25.0));
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

//                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("聚合事件").setMessage(sender.getSource().getPoint().toString()).show();
//
//                // 获取当前缩放等级
//                map.getZoom(new NPCallBackFunction<Integer>() {
//                    @Override
//                    public void onCallBack(Integer data) {
//                        Util.Info("getZoom", "ok");
//                    }
//                });
//                // 获取当前中心点
//                map.getCenter(new NPCallBackFunction<Point>() {
//                    @Override
//                    public void onCallBack(Point data) {
//
//                    }
//                });
//                map.setCenter(sender.getSource().getPoint());
                sender.getSource().changeStyle(new MarkerStyle("img/Flag.png", 21.0, 25.0));
            }
        });
    }

    private void intListener() {
        addBtn.setOnClickListener(this);
        add_cluster_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                // addMarker();
                // testMeasure();
                //testGeocoderHelper();
               mapAddClick();
               // this.map.setBaiduTrafficLayerVisable(false);
                break;
            case R.id.add_cluster_btn:
                long start = new java.util.Date().getTime();
                clusterLayer.addOverlayList(list,false);
                clusterLayer.addOverlayList(list1,true);
                this.showMessage("提示","耗时:"+(new java.util.Date().getTime() - start));
                break;
            case R.id.data_btn:
                clusterData();
                break;
        }
    }
    ClusterLayer clusterLayer;
    ClusterMarkerList list;
    ClusterMarkerList list1;
    private  void clusterData(){
        double lon = 116.3427702718185;
        double lat = 39.89369592052587;

        map.setCenter(new Point(lon,lat));
        ClusterLayerOptions options = new ClusterLayerOptions();
        options.setFontColor("#000000");
        options.setFontSize("23px");
        options.setClusterImage(new Image("img/Flag.png", new Size(32, 32)));
        List<ClusterStatisticInfo> statisticInfos = new ArrayList<>();
        statisticInfos.add(new ClusterStatisticInfo(lon, lat, "50"));

        options.setMinZoom(6);

        clusterLayer = new ClusterLayer("聚合图层测试", options);
        this.map.addLayer(clusterLayer);

        testAddClusterMarker();

        Image image = new Image("img/marker.png", new Size(21, 25));


        list  = new ClusterMarkerList(image);
        for (int i =0;i<20000;i++){
            list.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1),null,clusterLayer);
        }


        list1 = new ClusterMarkerList(image);
        for (int i =0;i<20000;i++){
            list1.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1),null,clusterLayer);
        }
        showMessage("提示","数据整理完成");
    }

    private void testMeasure() {
        Measure measure = new Measure(this.map);
        measure.setMode(Constants.MeasureMode_AREA); //MeasureMode_DISTANCE
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

    private void addMarker() {
        double lon = 116.3427702718185;
        double lat = 39.89369592052587;
        this.map.setCenter(new Point(lon,lat));
        Point randomPoint = new Point(lon + Math.random() * Math.pow(-1, 3) * 0.1, lat + Math.random() * Math.pow(-1, 3) * 0.1);
        final Marker marker = new Marker(randomPoint, new MarkerStyle(
                "img/marker.png", 21.0, 25.0
        ));

        layer.addOverlay(marker);
        // 添加点击事件
        marker.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<Marker>() {
                    @Override
                    public void processEvent(EventObject<Marker> sender, EventArgs e) {
                        //final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        //builder.setTitle("单个点位点击事件").setMessage(sender.getSource().getPoint().toString()).show();
                        layer.removeOverlay(marker);
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
