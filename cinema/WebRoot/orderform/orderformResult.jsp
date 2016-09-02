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
		<script type="text/javascript"
			src="<%=basePath%>orderform/orderformResult.js"></script>
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
				<th width="15%">
					订单编号
				</th>
				<th width="20%">
					订单时间
				</th>
				<th width="10%">
					订单总额
				</th>
				<th width="10%">
					订单类型
				</th>
				<th width="10%">
					支付类型
				</th>
				<th width="10%">
					支付状态
				</th>
				<th width="10%">
					渠道
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
			<s:iterator id="order" value="#request.list.objects">
				<tr align="center">
					<td>
						${order.ORDER_NO}
					</td>
					<td>
						${order.ORDER_TIME}
					</td>
					<td>
						${order.ORDER_PRICE}
					</td>
					<td>
						<c:if test="${order.ORDER_TYPE=='1'}">影片订单</c:if>
						<c:if test="${order.ORDER_TYPE=='2'}">卖品订单</c:if>
						<c:if test="${order.ORDER_TYPE=='3'}">综合订单</c:if>
						<c:if test="${order.ORDER_TYPE=='4'}">活动订单</c:if>
					</td>
					<td>
						<c:if test="${order.PAY_TYPE=='1'}">会员卡支付</c:if>
						<c:if test="${order.PAY_TYPE=='2'}">微信支付</c:if>
						<c:if test="${order.PAY_TYPE=='3'}">支付宝支付</c:if>
					</td>
					<td>
						<c:if test="${order.PAY_STATUS=='0'}">待支付</c:if>
						<c:if test="${order.PAY_STATUS=='1'}">已支付</c:if>
						<c:if test="${order.PAY_STATUS=='2'}">已退费</c:if>
					</td>
					<td>
						<c:if test="${order.APPCODE=='1'}">网站</c:if>
						<c:if test="${order.APPCODE=='2'}">Andorid</c:if>
						<c:if test="${order.APPCODE=='3'}">IOS</c:if>
						<c:if test="${order.APPCODE=='4'}">微信</c:if>
					</td>
					<td>
						<a href="javascript:orderformDetail('${order.ORDER_NO}')">[订单明细]</a>
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
