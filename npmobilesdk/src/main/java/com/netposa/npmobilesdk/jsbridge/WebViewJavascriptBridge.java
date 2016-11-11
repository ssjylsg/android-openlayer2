package com.netposa.npmobilesdk.jsbridge;

/**
 * WebViewJavascriptBridge
 */

public interface WebViewJavascriptBridge {
    public void send(String data);

    public void send(String data, CallBackFunction responseCallback);
}
