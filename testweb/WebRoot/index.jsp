<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <!--  <title>My JSP 'index.jsp' starting page</title> -->
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  
  <!--  <title>SuperMap iClient for JavaScript:TiledDynamicRESTLayer</title>
    引用需要的脚本
    <script src="./libs/SuperMap.Include.js"></script>
    <script type="text/javascript">
    //声明变量map、layer、url
    var map, layer,
    url = "http://localhost:8080/iserver/services/map-world/rest/maps/World";
    //创建地图控件
    function init() {
        map = new SuperMap.Map ("map");
        //创建分块动态REST图层，该图层显示iserver 7C 服务发布的地图,
        //其中"world"为图层名称，url图层的服务地址，{transparent: true}设置到url的可选参数
        layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, 
        null, {maxResolution:"auto"});
        layer.events.on({"layerInitialized": addLayer});          
    }             
    function addLayer() {
        //将Layer图层加载到Map对象上
        map.addLayer(layer);
        //出图，map.setCenter函数显示地图
        map.setCenter(new SuperMap.LonLat(0, 0), 0);        
    }
    </script> -->
    <style type="text/css">
body{
margin: 0;
overflow: hidden;
background: #fff;
}
#map{
position: relative;
height: 558px;
border:1px solid #3473b7;
}
</style>
<script src='./libs/SuperMap.Include.js'></script>
<script type="text/javascript">
var map, layer;
function init(){
map = new SuperMap.Map("map", { controls:[
new SuperMap.Control.ScaleLine(),
new SuperMap.Control.LayerSwitcher(),
new SuperMap.Control.Zoom(),
new SuperMap.Control.Navigation({
dragPanOptions:{
enableKinetic:true
}
})]
});

layer = new SuperMap.Layer.CloudLayer();

map.addLayers([layer]);

map.setCenter(new SuperMap.LonLat(11339634.286396, 4588716.5813769), 4);
}

//添加数据
function addData()
{ markerlayer.removeMarker(marker);
var size = new SuperMap.Size(44,33);
var offset = new SuperMap.Pixel(-(size.w/2), -size.h);
var icon = new SuperMap.Icon('../images/marker.png', size, offset);
marker =new SuperMap.Marker(new SuperMap.LonLat(104.06819624933915,30.537365954187788),icon) ;
markerlayer.addMarker(marker);
}

</script>
 </head> 
  <body onload="init()">
    <!--地图显示的div-->
    <div id="map" style="position:absolute;left:0px;right:0px;width:1500px;height:800px;" >             
    </div>    
</body>

</html>
