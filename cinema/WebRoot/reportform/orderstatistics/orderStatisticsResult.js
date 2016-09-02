var plot;

$(document).ready(function() {
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
	var ordertype = $("#ordertype option:selected").val();

	var url = "/cinema/reportform/orderstatis!findOrdStaticInfo.action";
	var params = {
		startdate : startdate,
		enddate : enddate,
		ordertype : ordertype,
	};
	// 销毁折线视图
	releasePlotChart("reportdiv", plot);

	jQuery.post(url, params, initPlotCallBack, 'json');

}

/**
 * 初始化报表图像回调
 * 
 * @return
 */
function initPlotCallBack(data) {
	var line = data.line;
	var line1 = [ [1,0] ];
	var line2 = [ [1,0] ];
	var line3 = [ [1,0] ];
	var line4 = [ [1,0] ];
	var line5 = [ [1,0] ];

	if (line != "" && line != null) {
		var dateType =  $("#appcode option:selected").val();
		//判断折线显示类型
		var lineArray = line.split("_");
		if (dateType == "1") { // 网站
			line1 = eval(lineArray[0]);
		} else if (dateType == "2") { // Android
			line2 = eval(lineArray[1]);
		} else if (dateType == "3") { // IOS
			line3 = eval(lineArray[2]);
		} else if (dateType == "4") { // 微信
			line4 = eval(lineArray[3]);
		} else if (dateType == "5") { // 总数
			line5 = eval(lineArray[4]);
		} else {
			line1 = eval(lineArray[0]);
			line2 = eval(lineArray[1]);
			line3 = eval(lineArray[2]);
			line4 = eval(lineArray[3]);
			line5 = eval(lineArray[4]);
		}
	}
	//初始化视图设定
	plot = $.jqplot('reportdiv', [ line1, line2, line3, line4, line5 ], {
		animate : !$.jqplot.use_excanvas, // 是否动画显示
		seriesColors : [ "#953579", "#279429", "#0085cc", "#EAA228", "#EF240A"], // 默认显示的分类颜色，
		series : [ {
			label : '网站'
		}, {
			label : 'Android'
		}, {
			label : 'IOS'
		}, {
			label : '微信'
		}, {
			label : '总数'
		}, {
			renderer : $.jqplot.CanvasAxisTickRenderer
		} ],
		axes : {
			xaxis : {
				label : '日期(单位：月份)',
				min : 1,
				max : 12,
				numberTicks : 12
			},
			yaxis : {
				label : '订单总额',
				min : 0
			}
		},
		highlighter : {
			show : true,
			sizeAdjust : 7, // 当鼠标移动到数据点上时，数据点扩大的增量增量
			showTooltip : true, // 是否显示提示信息栏
			tooltipLocation : 'nw', // 提示信息显示位置（英文方向的首写字母）: n, ne, e,
			// se, s, sw, w, nw.
			tooltipAxes : 'y', // 提示信息框显示数据点那个坐标轴上的值，目前有横/纵/横纵三种方式。
			// 值分别为 x, y or xy.
			fadeTooltip : true, // 设置提示信息栏出现和消失的方式（是否淡入淡出）
			useAxesFormatters : false,
			tooltipFormatString : '金额:%P'
		},
		legend : {
			show : true,
			placement : 'outsideGrid',
			location : 'ne' // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.
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
	var ordertype = $("#ordertype option:selected").val()
	var appcode = $("#appcode option:selected").val();

	var obj = new Object();
	obj.startDate = startdate;
	obj.endDate = enddate;
	obj.order_type = ordertype;
	obj.appcode = appcode;

	$("#jsonInfo").val($.toJSON(obj));
	
	$("#excelForm").submit();
}