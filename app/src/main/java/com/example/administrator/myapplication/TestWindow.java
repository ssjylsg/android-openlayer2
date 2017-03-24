package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/3/21.
 */

public class TestWindow extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testwindow);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                return false;

            }
        });
      //  webView.loadUrl("http://192.168.61.28:807/OpenLayersMap/examples/mobile.html");
        //  webView.loadUrl("http://192.168.61.28:807/mobile/source/examples/mobile.html");
        //  webView.loadUrl("http://ditu.amap.com/");
   webView.loadUrl("http://192.168.61.28:807/mobile/dist/index.html");
       // webView.loadUrl("http://lbsyun.baidu.com/jsdemo/demo/a1_2.htm");
        //  webView.loadUrl("http://192.168.61.28:1081/newroot/gis-manager/demos/Layers/BaiduTileLayer.html");


    }
}
