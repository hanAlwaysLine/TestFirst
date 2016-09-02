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

		<title>My JSP 'userList.jsp' starting page</title>

		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>goods/goodsResult.js"></script>
			<script type="text/javascript">
		$(document).ready(
			function() {
	setIframeHeight();
			});
	</script>
	    <link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	</head>
	<body>
		<table class="data_table">
					<tr>
					    <th>
							卖品名称
						</th>
						<th>
							价格
						</th>
					    <th>
							分类
						</th>
						<th>
							是否为促销卖品
						</th>
						<th>
							可用数量
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="hall" value="#request.goodslist.objects">
						<tr align="center">
						    <td>
								<s:property value="#hall.GOODS_NAME"/>
							</td>
							<td>
								<s:property value="#hall.GOODS_PRICE"/>
							</td>
							<td>
							    <s:property value="#hall.GOODS_TYPE"/>
							</td>
							<td>
							<s:if test="#hall.IS_DISCOUNT==0">
								否
							</s:if>
							<s:if test="#hall.IS_DISCOUNT==1">
								是
							</s:if>
							</td>
							<td>
								<s:property value="#hall.GOODS_COUNT"/>
							</td>
							<td>
			                	<a href="javascript:goEdit('<s:property value="#hall.GOODS_ID"/>')">[修改]</a>
						        <a href="javascript:delActivity('<s:property value="#hall.GOODS_ID"/>')">[删除]</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			<div style="height: 5px; clear: both;"></div>
				<div id="page">
						<cinemamanage:pages formId="queryForm" className="button_page"/>
					<div style="height: 20px; clear: both;"></div>
				</div>
	</body>
</html>
