[TOC]
## 概述
NPMobile 是一个简易移动端地图，主要实现加载百度、谷歌、天地图、高德、Arcgis、PGIS等地图。实现了Marker 覆盖物和聚合覆盖物。

[API帮助文档](\JavaDocs\index.html)


准备工作：
采用nginx 将NPMobile解压到HTML部署起来，并启动nginx，访问http://localhost:8000/dist/mapconfig.json 确认服务是否正常。

### 1. 第一章 hello World  
使用本套类库创建第一个应用,加载地图配置 
```js
 BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);
 // 初始化NetposaMap
 map = new NetPosaMap(webView, "mapconfig.json", "http://192.168.61.28:807/mobile/dist/index_c.html");

 // mapconfig.json 地图配置
 // http://192.168.61.28:807/mobile/dist/index_c.html 地图URL

```

----

### 2. 第二章 申明自定义图层
初始化图层

```js
 // 创建一个自定义图层
  layer = new CustomerLayer("测试");
  // 将图层添加到地图
  map.addLayer(layer);
```


----
### 3. 第三章 加载Marker 并注册事件
新增Marker 

```js

 double lon = 116.3427702718185;
 double lat = 39.89369592052587;

 Point randomPoint = new Point(lon,lat);
 Marker marker = new Marker(randomPoint, new MarkerStyle(
    "img/marker.png", 21.0, 25.0
 ));

layer.addOverlay(marker);
// 添加点击事件
marker.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<Marker>() {
                    @Override
                    public void processEvent(EventObject<Marker> sender, EventArgs e) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("单个点位点击事件").setMessage(sender.getSource().getPoint().toString()).show();
                    }
                }
        );
```

----
### 4. 第四章 申明聚合图层
申明图层 

```js

 ClusterLayerOptions options = new ClusterLayerOptions();
 options.setClusterImage(new Image("img/Flag.png", new Size(32, 32)));  
 ClusterLayer clusterLayer = new ClusterLayer("聚合图层测试", options);

// 设置统计信息
 List<ClusterStatisticInfo> statisticInfos = new ArrayList<>();
 statisticInfos.add(new ClusterStatisticInfo(lon, lat, "50"));
 options.setStatistics(statisticInfos);
 options.setMinZoom(6); // 地图层级6之前都显示统计信息

 this.map.addLayer(clusterLayer);
```

### 5. 第五章 申明聚合Marker
申明聚合Marker
```js
double lon = 116.3427702718185;
double lat = 39.89369592052587;

ArrayList<ClusterMarker> markers = new ArrayList<>();
Image image = new Image("img/marker.png", new Size(21, 25));
 for (Integer i = 0; i < 10; i++) {
            markers.add(new ClusterMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1,
                    lat + Math.random() * Math.pow(-1, i + 1) * 0.1), image));
 }
clusterLayer.addClusterMarkers(markers);

// 注册click 事件
clusterLayer.addEventListener(Constants.EVENT_TYPE_CLICK, new NPEventListener<ClusterMarker>() {
@Override
public void processEvent(EventObject<ClusterMarker> sender, EventArgs e) { 
    // 更改图片
    sender.getSource().changeStyle(new MarkerStyle("img/Flag.png", 21.0, 25.0));
}
});
```
---
### 6. 第六章 距离测量
距离测量
```js
 Measure measure = new Measure(this.map);
 measure.setMode(Constants.MeasureMode_DISTANCE);  
```
---

### 7. 第七章 面积测量
面积测量
```js
 Measure measure = new Measure(this.map);
 measure.setMode(Constants.MeasureMode_AREA);  
```
---

### 8. 第八章 计算两点之间距离
计算两点之间距离
```js
 double lon = 116.3427702718185;
 double lat = 39.89369592052587;
 map.getDistance(marker.getPoint(), new Point(lon, lat), new NPCallBackFunction<Double>() {
            @Override
            public void onCallBack(Double data) {
                ShowMessage("信息", data.toString());
            }
        }); 
```
---

### 9. 第九章 地址解析
地址解析
```js
 GeocoderHelper.getPoint("http://192.168.60.242:8080/netposa", "天宁商场店", new NPCallBackFunction<List<Point>>() {
            @Override
            public void onCallBack(List<Point> features) {
                ShowMessage("提示", features.size() + "");
            }
        });
```
---

### 10. 第十章 逆地址解析
逆地址解析
```js
GeocoderHelper.getLocation("http://192.168.60.242:8080/netposa", new Point(119.95797015899728, 31.812219565250494), new NPCallBackFunction<Feature>() {
            @Override
            public void onCallBack(Feature data) {
                ShowMessage("提示", data != null ? data.toString() : "请求为空");
            }
        });
```
---
### 11. 第十一章 Map 注册单击事件
单击事件
```js
map.addEventListener(Constants.EVENT_TYPE_MAP_CLICK, new NPEventListener() {
            @Override
            public void processEvent(EventObject sender, EventArgs e) {
                Object[] lonlat = (Object[])e.getArgs();                
                double lon = Double.parseDouble(lonlat[0].toString());
                double lat = Double.parseDouble(lonlat[1].toString());
                Point point = new Point(lon,lat);               
            }
        });
```
---
### 12. 第十二章 圆覆盖物
圆覆盖物
```js
 Circle  circle = new Circle(new Point(108.5651,32.56),1000,null);
 layer.addOverlay(circle);
 // 获取WKT 数据
 circle.getWKT(new NPCallBackFunction<String>() {
       @Override
       public void onCallBack(String data) {
            
       }
  });
```
---