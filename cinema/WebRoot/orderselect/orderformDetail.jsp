<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	</head>
	<body>
		<div style="margin-left: 25px; margin-top: 10px; margin-bottom: 10px;">
			<font color="#69913B" size="2">支付编号:${orderno}</font>
		</div>
		<fieldset style="margin-left: 20px;margin-right: 20px;margin-top: 10px;border-color: #69913B;">
		<legend style="color: #69913B;font-size: 13px;">影票订单信息</legend>
		<div style="height: 210px; overflow-y: auto;">
			<table class="data_table" style="width: 91%">
				<tr>
					<th>
						影厅
					</th>
					<th>
						座位
					</th>
					<th>
						片名
					</th>
					<th>
						观影日期
					</th>
					<th>
						观影时间
					</th>
					<th>
						单张票价
					</th>
					<th>
						购票数量
					</th>
					<th>
						状态
					</th>
				</tr>
<%--				<c:forEach items="${ticketList}" var="fl">--%>
					<tr>
						<td>
							${orderDetail.hallname}
						</td>
						<td>
							${orderDetail.seatinfo}
						</td>
						<td>
							${orderDetail.filmname}
						</td>
						<td>
							${orderDetail.showdate}
						</td>
						<td>
							${orderDetail.showtime}
						</td>
						<td>
							${orderDetail.price}
						</td>
						<td>
							${orderDetail.ticketsnum}
						</td>
						<td>
							<c:if test="${orderDetail.result_pay=='1'}">已出票</c:if>
							<c:if test="${orderDetail.result_pay=='2'}">未出票</c:if>
							<c:if test="${orderDetail.result_pay=='3'}">出票失败</c:if>
							<c:if test="${orderDetail.result_pay=='4'}">已退票</c:if>
						</td>
					</tr>
<%--				</c:forEach>--%>
			</table>
		</div>
		</fieldset>
		
		<fieldset style="margin: 20px;border-color: #69913B;">
		<legend style="color: #69913B;font-size: 13px;">卖品订单信息</legend>
		<div style="height: 210px; overflow-y: auto;">
			<table class="data_table" style="width: 91%">
				<tr>
					<th>
						卖品编号
					</th>
					<th>
						卖品名称
					</th>
					<th>
						卖品数量
					</th>
					<th>
						卖品单价
					</th>
					<th>
						卖品总价
					</th>
				</tr>
				<c:forEach items="${goodsList}" var="gl">
					<tr>
						<td>
							${gl.GOODS_ID}
						</td>
						<td>
							${gl.GOODS_NAME}
						</td>
						<td>
							${gl.GOODS_COUNT}
						</td>
						<td>
							${gl.GOODS_PRICE}
						</td>
						<td>
							${gl.GOODS_COUNTPRICE}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</fieldset>
		
		<fieldset style="margin: 20px;border-color: #69913B;">
		<legend style="color: #69913B;font-size: 13px;">活动信息</legend>
		<div style="height: 210px; overflow-y: auto;">
			<table class="data_table" style="width: 91%">
				<tr>
					<th>
						活动名称
					</th>
					<th>
						排期编号
					</th>
					<th>
						座位数
					</th>
					<th>
						起始时间
					</th>
					<th>
						终止时间
					</th>
					<th>
						活动主题
					</th>
				</tr>
				<s:iterator id="al" value="#request.activityList">
					<tr>
						<td>
							${al.activity_name}
						</td>
						<td>
							${al.featureno}
						</td>
						<td>
							${al.seatcount}
						</td>
						<td>
							<s:date name="#al.starttime" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
						 	<s:date name="#al.endtime" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							${al.title}
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		</fieldset>
	</body>
</html>
