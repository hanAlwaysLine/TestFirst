<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<base href="<%=basePath%>">

		<title>My JSP 'userQuery.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>orderform/orderformQuery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/page.css" />
	</head>
	<body>
		<div class="par_tit" style="width: 99%;">
			订单管理
		</div>
		<form action="/cinema/orderformanage/orderform!queryOrderformList.action"
			id="queryForm" name="queryForm" target="result" method="post">
			<input type="hidden" id="pageNo" name="pageNo" value="1" />
			<input type="hidden" id="pageSize" name="pageSize" value="10" />
			<div class="qu_div" style="margin-left: 25px; * width: 800px;">
				<table>
					<tr>
						<td>
							<span>开始时间:</span>
						</td>
						<td>
							<span><input id="startDate" name="userorder.startDate"
									class="Wdate" type="text" class="qu_inp1"
									onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y/%M/%d'})" />
							</span>
						</td>
						<td>
							<span>结束时间:</span>
						</td>
						<td>
							<span><input id="endDate" name="userorder.endDate"
									class="Wdate" type="text" class="qu_inp1"
									onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" />
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<span>支付流水号:</span>
						</td>
						<td>
							<span><input id="user_mobile" name="userorder.pay_tranno"
									class="qu_inp1" type="text" style="margin-left: 0px;" /> </span>
						</td>
						<td>
							<span>订单号:</span>
						</td>
						<td>
							<span><input id="payorder" name="userorder.order_no"
									class="qu_inp1" type="text" style="margin-left: 0px;" /> </span>
						</td>
<%--						<td>--%>
<%--							<span>手机号:</span>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<span><input id="user_mobile" name="userorder.user_mobile"--%>
<%--									class="qu_inp1" type="text" style="margin-left: 0px;" /> </span>--%>
<%--						</td>--%>
					</tr>
					<tr>
<%--						<td>--%>
<%--							<span>渠道:</span>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<span><select name="userorder.appcode" onchange="queryByVipno()">--%>
<%--									<option value="">--%>
<%--										--请选择----%>
<%--									</option>--%>
<%--									<option value="1">--%>
<%--										网站--%>
<%--									</option>--%>
<%--									<option value="2">--%>
<%--										Andorid--%>
<%--									</option>--%>
<%--									<option value="3">--%>
<%--										IOS--%>
<%--									</option>--%>
<%--									<option value="4">--%>
<%--										微信--%>
<%--									</option>--%>
<%--								</select>--%>
<%--							</span>--%>
<%--						</td>--%>
						<td>
							<span>订单类型:</span>
						</td>
						<td>
							<span><select name="userorder.order_type" onchange="queryByVipno()">
									<option value="">
										--请选择--
									</option>
									<option value="1">
										影片订单
									</option>
									<option value="2">
										卖品订单
									</option>
									<option value="3">
										综合订单
									</option>
									<option value="4">
										活动订单
									</option>
								</select> </span>
						</td>
						<td>
							<span>支付状态:</span>
						</td>
						<td>
							<span> <select name="userorder.pay_status" onchange="queryByVipno()">
									<option value="">
										--请选择--
									</option>
									<option value="0">
										待支付
									</option>
									<option value="1">
										已支付
									</option>
									<option value="2">
										已退费
									</option>
								</select> </span>
						</td>
					</tr>
					<tr>
						<td>
							<span>支付类型:</span>
						</td>
						<td colspan="5">
							<span><select name="userorder.pay_type" onchange="queryByVipno()">
									<option value="">
										--请选择--
									</option>
									<option value="1">
										会员卡支付
									</option>
									<option value="2">
										微信支付
									</option>
									<option value="3">
										支付宝支付
									</option>
								</select>
							</span>
							<span><input type="button" value="" class="qu_inp2"
									style="width: 65px" onclick="queryByVipno()" /> </span>
						</td>
					</tr>
				</table>
			</div>
			<div style="height: 5px;"></div>
			<div style="width: 100%">
				<iframe allowTransparency="true" name="result" id="iframe"
					width="100%" frameborder="0" scrolling="auto">
				</iframe>
			</div>
		</form>
	</body>
</html>
