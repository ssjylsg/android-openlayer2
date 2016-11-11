package com.netposa.npmobilesdk;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.netposa.npmobilesdk.jsbridge.BridgeWebView;
import com.netposa.npmobilesdk.jsbridge.BridgeWebViewClient;
import com.netposa.npmobilesdk.map.NetPosaMap;


/**
 * Created by Administrator on 2016/10/18.
 */

public class NPWebViewClient extends BridgeWebViewClient {

    private NetPosaMap map;

    public NPWebViewClient(BridgeWebView view, NetPosaMap map) {
        super(view);
        this.map = map;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        this.map.CreateMap();
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        android.util.Log.e("NPWebViewClient", error.toString());
    }



}
