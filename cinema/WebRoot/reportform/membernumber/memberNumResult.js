var plot;

$(document).ready(function(){
	initPlot();
});

/**
 * 初始化报表图像
 * 
 * @return
 */
function initPlot() {
	var startdate = $("#startdate").val();
	var enddate = $("#enddate").val();
	var url = "/cinema/reportform/membernum!findMemInfo.action";
	var params = {
			startdate : startdate,
			enddate : enddate
	};
	// 销毁折线视图
	releasePlotChart("reportdiv",plot);
	
	jQuery.post(url, params, initPlotCallBack, 'json');

}

/**
 * 初始化报表图像回调
 * 
 * @return
 */
function initPlotCallBack(data) {
	var line = data.line;
	var line1 = [[]];
	var line2 = [[]];

	if(line!=""&&line!=null){
		var dateType = $("#dateType").val();
		var lineArray = line.split("_");
		if(dateType=="1"){
			line1 = eval(lineArray[0]);
		}else if(dateType=="2"){
			line2 = eval(lineArray[1]);
		}else{
			line1 = eval(lineArray[0]);
			line2 = eval(lineArray[1]);
		}
	}
	//添加x轴刻度数据
	var ticks = new Array();
	for(i=1;i<13;i++){
		ticks.push(i);
	}
	
	plot = $.jqplot('reportdiv', [ line1, line2 ], {
		animate: !$.jqplot.use_excanvas, // 是否动画显示
		seriesColors: ["#d8b83f", "#0085cc"],  // 默认显示的分类颜色，
		seriesDefaults: {
				 renderer: $.jqplot.BarRenderer,
				 pointLabels: { 
					show: true,
					formatString: '%.5P'
				 }
		},
		series:[  
	            {label:'增量'},
	            {label:'总量'}, 
	            {renderer:$.jqplot.CanvasAxisTickRenderer}
	    ],
		axes : {
			xaxis : {
				renderer: $.jqplot.CategoryAxisRenderer,
				label : '日期(单位：月份)',
				ticks: ticks
			},
			yaxis : {
				label : '数量',
				min : 0
			}
		},
		legend: {
			show: true,  
            placement: 'outsideGrid',
            location: 'ne' // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.
	    }
	});
}

/**
 * 将plot图表从容器中销毁。
 * 
 * @param containerId
 *            容器ID。
 * @param plot
 *            在容器中的图表。
 */  
function releasePlotChart(containerId, plot) {
    if (plot) {
        plot.destroy();
  
        var elementId = '#' + containerId;
        $(elementId).unbind(); // for iexplorer
        $(elementId).empty();   
  
        plot = null;   
    }   
}

/**
 * 导出Excel
 * @return
 */
function showExcel(){
	var startdate = $("#startdate").val();
	var enddate = $("#enddate").val();

	var obj = new Object();
	obj.startdate = startdate;
	obj.enddate = enddate;
	
	$("#jsonInfo").val($.toJSON(obj));
	
	$("#excelForm").submit();
}