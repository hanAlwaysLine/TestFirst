<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowDate = dateFormat.format(new Date());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>对账报表查询页</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>reportform/reconciliation/reconciliationQuery.js"></script>

		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/page.css" />
	</head>

	<body>
		<div class="par_tit" style="width: 99%;">
			对账报表
		</div>
		<form id="excelForm"
			action="/cinema/reportform/reconciliation!showExcel.action"
			method="post">
			<input id="jsonInfo" name="jsonInfo" type="hidden" value="" />
		</form>
		<form action="/cinema/reportform/reconciliation!findOrderInfo.action"
			id="queryForm" name="queryForm" target="result" method="post">
			<div class="qu_div" style="margin-left: 25px; * width: 813px;">
				<span>放映日期:</span>
				<span><input id="startdate" style="width: 90px;"
						onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y/%M/%d'})"
						name="reconciliation.startdate" class="Wdate" value="<%=nowDate%>"
						type="text" readonly="readonly" /> 到 <input id="enddate"
						style="width: 90px;"
						onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y/%M/%d'})"
						name="reconciliation.enddate" class="Wdate" value="<%=nowDate%>"
						type="text" readonly="readonly" /> </span>
				<span>影片名称:</span>
				<span><input id="filmname" size="10"
						name="reconciliation.filmname" class="qu_inp1" value=""
						type="text" /> </span>
						
				<span><input type="submit" class="qu_inp2" value=""
						style="width: 65px" />
				</span>
				<span><input type="button" value="" class="qu_daochu"
						style="width: 65px" onclick="showExcel()" /> </span>
			</div>
			<div style="height: 5px;"></div>
			<div style="width: 100%">
				<iframe allowTransparency="true" name="result" id="iframe"
					height="500px" width="100%" frameborder="0" scrolling="auto">
				</iframe>
			</div>
		</form>
	</body>
</html>
