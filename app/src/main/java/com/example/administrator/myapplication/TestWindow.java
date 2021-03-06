package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.netposa.npmobilesdk.NPWebViewClient;
import com.netposa.npmobilesdk.SimpleJavaJSWebChromeClient;
import com.netposa.npmobilesdk.geometry.ClusterMarker;
import com.netposa.npmobilesdk.geometry.ClusterMarkerList;
import com.netposa.npmobilesdk.geometry.Point;
import com.netposa.npmobilesdk.jsbridge.BridgeWebView;
import com.netposa.npmobilesdk.jsbridge.BridgeWebViewClient;
import com.netposa.npmobilesdk.jsbridge.DefaultHandler;
import com.netposa.npmobilesdk.layer.ClusterLayer;
import com.netposa.npmobilesdk.layer.ClusterLayerOptions;
import com.netposa.npmobilesdk.layer.ClusterStatisticInfo;
import com.netposa.npmobilesdk.map.NetPosaMap;
import com.netposa.npmobilesdk.utils.Image;
import com.netposa.npmobilesdk.utils.Size;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/21.
 */

public class TestWindow extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        NetPosaMap.initX5Environment(this);

        setContentView(R.layout.testwindow);
        //final WebView webView = (WebView) findViewById(R.id.webView);
        final BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setDefaultHandler(new DefaultHandler());



        webView.setWebViewClient(new BridgeWebViewClient(webView));
        ClusterLayerOptions options = new ClusterLayerOptions();
        options.setFontColor("#000000");
        options.setFontSize("23px");
        options.setMinZoom(6);
        final ClusterLayer clusterLayer = new ClusterLayer("聚合图层测试", options);

        ((Button)findViewById(R.id.test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:testCluster()");
            }
        });

        webView.loadUrl("http://192.168.62.63:807/mobile/dist/index.html");
    }

    private  void Test(BridgeWebView webView,ClusterLayer clusterLayer){
        ArrayList<ClusterMarker> markers = new ArrayList<>();
        Image image = new Image("img/marker.png", new Size(21, 25));
        double lon = 108.23;
        double lat = 23.65;
        long start =  new java.util.Date().getTime();
//               for (int j = 0 ;j<2;j++) {
////                   if(j > 4) {
////                       try {
////                           Thread.sleep(1000);
////                       } catch (InterruptedException e) {
////                       }
////                   }
//                   for (Integer i = 0; i < 5000; i++) {
//                       markers.add(new ClusterMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
//                               lat + Math.random() * Math.pow(-1, i + 1) * 0.1), image));
//                   }
//                   android.util.Log.i("序列化之前", new java.util.Date().getTime() + "");
//                   String msg = com.alibaba.fastjson.JSON.toJSONString(markers);
//                  // msg = "javascript:testMethod('" + msg + "')";
//                   android.util.Log.i("序列化之后", new java.util.Date().getTime() + "");
//                   webView.loadUrl(msg);
//                   webView.callHandler("testMethod",msg,null);
//                   android.util.Log.i("调用之后", new java.util.Date().getTime() + "");
//                   //android.util.Log.i("MSG", msg);
//               }
//                webView.loadUrl("javascript:calc("+start+")");

        ClusterMarkerList list = new ClusterMarkerList(image);
        for (int i =0;i<30000;i++){
            list.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1),null,clusterLayer);
        }
        start =  new java.util.Date().getTime();
        android.util.Log.i("序列化之前", new java.util.Date().getTime() + "");
        String msg = list.toString();
        android.util.Log.i("序列化之后", new java.util.Date().getTime() + "");
        webView.callHandler("testMethod",msg,null);
        webView.loadUrl("javascript:calc("+start+")");
        android.util.Log.i("MSG",msg);
//                list = new ClusterMarkerList(image);
//                for (int i =0;i<10000;i++){
//                    list.addMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
//                            lat + Math.random() * Math.pow(-1, i + 1) * 0.1),null);
//                }
//                start =  new java.util.Date().getTime();
//                android.util.Log.i("序列化之前", new java.util.Date().getTime() + "");
//                msg = list.toString();
//                android.util.Log.i("序列化之后", new java.util.Date().getTime() + "");
//                webView.callHandler("testMethod",msg,null);
//                webView.loadUrl("javascript:calc("+start+")");
    }
}
