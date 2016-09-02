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
	var url = "/cinema/reportform/clientnum!findClientInfo.action";
	var params = {
			startdate : startdate,
			enddate : enddate
	};
	//销毁折线视图
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
	var line3 = [[]];

	if(line!=""&&line!=null){
		var dateType = $("#dateType").val();
		var lineArray = line.split("_");
		if(dateType=="1"){
			line1 = eval(lineArray[0]);
		}else if(dateType=="2"){
			line2 = eval(lineArray[1]);
		}else if(dateType=="3"){
			line3 = eval(lineArray[2]);
		}else{
			line1 = eval(lineArray[0]);
			line2 = eval(lineArray[1]);
			line3 = eval(lineArray[2]);
		}
	}
	
//	line1 = [ [1,4,'mid'], [3,5,'hi'], [7,2,'low'] ];
//			var line2 = [ [ 0, 2 ], [ 2, 2 ], [ 4, 20 ], [ 6, 15 ] ];
//			
//			var line3 = [ [ 0, 2 ], [ 1, 2 ], [ 2, 2 ], [ 3, 5 ],[ 4, 20 ],[ 5, 13 ],[ 6, 15 ],[ 7, 33 ],[ 9, 85 ],[ 11, 219 ]];
	//line = [[1,1],[3,1],[6,2]],[[6,1],[28,1],[29,1]],[[1,1],[3,1],[6,3],[28,1],[29,1]];
	plot = $.jqplot('reportdiv', [ line1,line2,line3 ], {
				animate: !$.jqplot.use_excanvas, // 是否动画显示
				seriesColors: ["#d8b83f", "#ff5800", "#0085cc"],  // 默认显示的分类颜色，
				seriesDefaults: {
					     shadow: false,
					     showMarker: true // 是否强调显示图中的数据节点
//					     pointLabels: { 
//							show: true,
//							labels : ['mid', 'hi', 'low']
//						 }
				},
				series:[  
			            {label:'Android'},
			            {label:'IOS'}, 
			            {label:'总数'}, 
			            {renderer:$.jqplot.CanvasAxisTickRenderer},
			    ],
				axes : {
					xaxis : {
						label : '日期(单位：月份)',
						min : 1,
						max : 12,
						numberTicks : 12
					},
					yaxis : {
						label : '数量',
						min : 0
					}
				},
				highlighter: {
					show : true,
					sizeAdjust: 7, // 当鼠标移动到数据点上时，数据点扩大的增量增量
					showTooltip: true, // 是否显示提示信息栏
					tooltipLocation: 'nw', // 提示信息显示位置（英文方向的首写字母）: n, ne, e,
											// se, s, sw, w, nw.
					tooltipAxes: 'y', // 提示信息框显示数据点那个坐标轴上的值，目前有横/纵/横纵三种方式。
										// 值分别为 x, y or xy.
					fadeTooltip: true,      // 设置提示信息栏出现和消失的方式（是否淡入淡出）
					useAxesFormatters: false,
					tooltipFormatString: '数量:%P',
//					formatString: '%.5P'
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
	var dateType = $("#dateType option:selected").val();
	
	var obj = new Object();
	obj.startdate = startdate;
	obj.enddate = enddate;
	obj.app_type = dateType;

	$("#jsonInfo").val($.toJSON(obj));
	
	$("#excelForm").submit();
}