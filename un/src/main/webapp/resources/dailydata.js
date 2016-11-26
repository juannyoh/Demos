$(function() {
		var x = [];//X轴
		var xtext = [];//X轴TEXT
		var color = [ "gray", "pink", "red", "blue", "yellow", "green", "#fff" ];
		//用户
		$.ajax({
			type : 'get',
			url : 'daily/dailyusercounts',//请求数据的地址
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					if(xtext!=null&&xtext.length<data.length){
						x[i]=dataobj.days;
						xtext[i]=dataobj.days;
					}
					y[i]=dataobj.counts;
					console.log("user"+x[i]+","+y[i]);
				}
				chart.series[0].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		//网点
		$.ajax({
			type : 'get',
			url : 'daily/dailypointcounts',//请求数据的地址
			data: {"eid":"40288e9f48625c010148625c07160000"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					if(xtext!=null&&xtext.length<data.length){
						x[i]=dataobj.days;
						xtext[i]=dataobj.days;
					}
					y[i]=dataobj.counts;
					console.log("point"+x[i]+","+y[i]);
				}
				chart.series[1].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		//分单
		$.ajax({
			type : 'get',
			url : 'daily/dailyordercounts',//请求数据的地址
			data: {"eid":"40288e9f48625c010148625c07160000"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					if(xtext!=null&&xtext.length<data.length){
						x[i]=dataobj.days;
						xtext[i]=dataobj.days;
					}
					y[i]=dataobj.counts;
					console.log("order"+x[i]+","+y[i]);
				}
				chart.series[2].setData(y);//数据填充到highcharts上面
			},
			error : function(e) {
			}
		});
		
		//分单API
		$.ajax({
			type : 'get',
			url : 'daily/dailyfendanapicounts',//请求数据的地址
			data: {"eid":"8a04a77b4cbc865c014cc0b8df9c0019"}, 
			success : function(data) {
				var y = [];//Y轴
				for(var i=0;i<data.length;i++){ 
					var dataobj=data[i];
					if(xtext!=null&&xtext.length<data.length){
						x[i]=dataobj.days;
						xtext[i]=dataobj.days;
					}
					y[i]=dataobj.counts;
					console.log("api"+x[i]+","+y[i]);
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
				color :"#8B0A50",
				lineWidth: 4
			}]
		});
	});