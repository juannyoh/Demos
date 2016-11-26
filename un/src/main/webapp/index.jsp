<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("ctx", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>
	<script src="Highcharts-5.0.2/code/highcharts.js"></script>
	<script type="text/javascript" src="resources/jquery-1.8.3.min.js"></script>
	<!-- <script
		src="https://www.highcharts.com/samples/static/highslide-full.min.js"></script>
	<script
		src="https://www.highcharts.com/samples/static/highslide.config.js"
		charset="utf-8"></script> -->
	

<script type="text/javascript" src="resources/dailydata.js">
	/* $(function() {
		var x = [];//X轴
		var xtext = [];//X轴TEXT
		var color = [ "gray", "pink", "red", "blue", "yellow", "green", "#fff" ];
		//用户
		$.ajax({
			type : 'get',
			url : '${ctx}/daily/dailyusercounts',//请求数据的地址
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					x[i]=dataobj.days;
					xtext[i]=dataobj.days;
					y[i]=dataobj.counts;
					//console.log(x[i]+","+y[i]);
				}
				chart.series[0].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		//网点
		$.ajax({
			type : 'get',
			url : '${ctx}/daily/dailypointcounts',//请求数据的地址
			data: {"eid":"40288e9f48625c010148625c07160000"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					x[i]=dataobj.days;
					xtext[i]=dataobj.days;
					y[i]=dataobj.counts;
					//console.log(x[i]+","+y[i]);
				}
				chart.series[1].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		//分单
		$.ajax({
			type : 'get',
			url : '${ctx}/daily/dailyordercounts',//请求数据的地址
			data: {"eid":"40288e9f48625c010148625c07160000"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					x[i]=dataobj.days;
					xtext[i]=dataobj.days;
					y[i]=dataobj.counts;
					console.log(x[i]+","+y[i]);
				}
				chart.series[2].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		
		//分单API
		$.ajax({
			type : 'get',
			url : '${ctx}/daily/dailyfendanapicounts',//请求数据的地址
			data: {"eid":"8a04a77b4cbc865c014cc0b8df9c0019"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					x[i]=dataobj.days;
					xtext[i]=dataobj.days;
					y[i]=dataobj.counts;
					console.log(x[i]+","+y[i]);
				}
				chart.series[3].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		
		
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'spline' ,//显示类型 曲线
				backgroundColor:'#FFF0F5'
			},
			credits: { 
	            //enabled: false,   //右下角不显示LOGO 
	            text: 'dituhui.com', //设置LOGO区文字 
            	href: 'http://e.dituhui.com' //设置LOGO链接地址
	        },
			title : {
				text : 'Daily counts' //图表的标题
			},
			xAxis : {
				categories : xtext
			},
			yAxis : {
				title : {
					text : 'counts' //Y轴的名称
				},
				tickPixelInterval:20
			},
			plotOptions:{
				spline:{ 
	                dataLabels:{ 
	                    enabled:true  //在数据点上显示对应的数据值 
	                }, 
	                enableMouseTracking: true //取消鼠标滑向触发提示框 
            	} 
			},
			series : [{
				name : "daily users",
				color :"#CD3333",
				lineWidth: 4
			},
			{
				name : "daily points",
				color :"#00CED1",
				lineWidth: 4
			},
			{
				name : "daily orders",
				color :"#CD00CD",
				lineWidth: 4
			},
			{
				name : "daily apis",
				color :"#363636",
				lineWidth: 4
			}]
		});
	}); */
</script>
</head>
<body>
	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<%-- <div align="right">
		<a href="${ctx}/point.jsp">网点</a>
	</div> --%>
</body>
</html>