<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<title>会员数量统计报表结果页</title>
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<!-- jquery -->
		<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/My97DatePicker/WdatePicker.js"></script>
		<!-- jqplot -->
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/jquery.jqplot.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/excanvas.js"></script>
		<!-- jqplot工具 -->
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/plugins/jqplot.barRenderer.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/plugins/jqplot.pointLabels.min.js" />
		<script type="text/javascript" src="<%=basePath%>common/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js" />		
		
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>reportform/membernumber/memberNumResult.js"></script>
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/jqplot/jquery.jqplot.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	</head>
	<body>
		<form id="excelForm" action="/cinema/reportform/membernum!showExcel.action" method="post">
			<input id="jsonInfo" name="jsonInfo" type="hidden" value=""/>
		</form>
		<div class="par_tit" style="width: 99%;">
			会员数量统计报表
		</div>
		<div class="qu_div" style="margin-left: 25px; * width: 800px;">
			<table>
				<tr>
					<td>
						开始日期:
					</td>
					<td>
						<span><input type="text" class="Wdate" class="qu_inp1" id="startdate"
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy',maxDate:'%y/%M'})" /> </span>
					</td>
					<td>
						结束日期:
					</td>
					<td>
						<span><input type="text" class="Wdate" class="qu_inp1" id="enddate" 
							onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy',maxDate:'%y/%M'})" /> </span>
					</td>
					<td>
						类型:
					</td>
					<td>
						<span>
							<select onchange="initPlot()" id="dateType">
								<option value="">--全部--</option>
								<option value="1">增量</option>
								<option value="2">总量</option>
							</select>
						</span>
					</td>
					<td>
						<span><input type="button" value="" class="qu_inp2"
								style="width: 65px" onclick="initPlot()" /> </span>
						<span><input type="button" value="" class="qu_daochu"
								style="width: 65px" onclick="showExcel()" /> </span>
					</td>
				</tr>
			</table>
		</div>
		<div id="reportdiv" style="margin-top:50px; width:100%; height:400px;"></div>
	</body>
</html>
