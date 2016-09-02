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
		<base href="<%=basePath%>"></base>

		<title>对账报表结果页</title>

		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>reportform/reconciliation/reconciliationResult.js"></script>
	    <link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	</head>
	<body>
		<table class="data_table" style="margin-top: 0px;margin-bottom: -9px;margin-left: 25px;">
					<tr>
					    <th>
							订单编号
						</th>
						<th>
							排期编号
						</th>
					    <th>
							影片名称
						</th>
						<th>
							影厅名称
						</th>
						<th>
							放映日期
						</th>
						<th>
							单价
						</th>
						<th>
							票数
						</th>
						<th>
							结算总额
						</th>
						<th>
							支付类型
						</th>
						<th>
							订单时间	
						</th>
						<th>
							渠道
						</th>
					</tr>
<!--				</table>-->
<!--				<div style="overflow-y: auto;overflow-x: visible;width: 100%;height: 100px;">-->
<!--				<table class="data_table">-->
					<s:iterator id="rc" value="#request.rcList">

						<tr align="center">
						    <td>
						    	<s:if test="%{#rc.order_no!=-1}">
									<s:property value="#rc.order_no"/>
								</s:if>
								<s:else>
									<s:property value="#rc.filmname"/>
								</s:else>
							</td>
							<td>
								<s:if test="%{#rc.order_no!=-1}">
									<s:property value="#rc.featureappno"/>
								</s:if>
							</td>
							<td>
								<s:if test="%{#rc.order_no!=-1}">
							   		<s:property value="#rc.filmname"/>
							   	</s:if>
							</td>
							<td>
							   	<s:property value="#rc.hallname"/>
							</td>
							<td>
							   	<s:property value="#rc.featuredate"/>
							</td>
							<td>
								<s:if test="%{#rc.order_no!=-1}">
							   		<s:property value="#rc.apppric"/>
							   	</s:if>
							</td>
							<td>
							   	<s:property value="#rc.ticketsnum"/>
							</td>
							<td>
							   	<s:property value="#rc.order_price"/>
							</td>
							<td>
							   	<s:property value="#rc.pay_type"/>
							</td>
							<td>
							   	<s:property value="#rc.order_time"/>
							</td>
							<td>
							   	<s:property value="#rc.appcode"/>
							</td>
						</tr>
					</s:iterator>
				</table>
				</div>
			<div style="height: 5px; clear: both;"></div>
	</body>
</html>
