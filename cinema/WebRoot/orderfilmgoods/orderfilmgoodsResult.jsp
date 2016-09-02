<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
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
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	$(document).ready(function() {
		setIframeHeight();
	});
</script>
	</head>
	<body>
		<table class="data_table">
			<tr>
				<th width="10%">
					排期应用号
				</th>
				<th width="10%">
					订单号
				</th>
				<th width="10%">
					订单总价
				</th>
				<th width="10%">
					影票总价
				</th>
				<th width="10%">
					活动价
				</th>
				<th width="10%">
					影票结算总价
				</th>
				<th width="10%">
					影票结算价
				</th>
				<th width="5%">
					影票数量
				</th>
				<th width="5%">
					卖品价格
				</th>
				<th width="10%">
					订单时间
				</th>
			</tr>
			<s:iterator id="order" value="#request.list.objects">
				<tr align="center">
					<td>
						${order.FEATUREAPPNO}
					</td>
					<td>
						${order.ORDER_NO}
					</td>
					<td>
						${order.ORDER_PRICE}
					</td>
					<td>
						${order.COUNTPRICE}
					</td>
					<td>
						${order.ACTIVEPRICE}
					</td>
					<td>
						${order.APPTOALPRICE}
					</td>
					<td>
						${order.APPPRIC}
					</td>
					<td>
						${order.TICKETSNUM}
					</td>
					<td>
						${order.GOODTOTALPRICE}
					</td>
					<td>
						${order.ORDER_TIME}
					</td>
				</tr>
			</s:iterator>
		</table>
		<div style="height: 5px; clear: both;"></div>
		<div id="page">
			<cinemamanage:pages formId="queryForm" className="button_page" />
			<div style="height: 20px; clear: both;"></div>
		</div>
	</body>

</html>
