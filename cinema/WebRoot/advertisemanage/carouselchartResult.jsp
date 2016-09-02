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
		<script type="text/javascript"
			src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>advertisemanage/carouselchartResult.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />
		<script type="text/javascript">
		$(document).ready(
			function() {
	setIframeHeight();
			});
	</script>
	</head>
	<body>
		<input type="hidden" id="type" value="1" />
		<table class="data_table">
			<tr>
				<th>
					影片ID
				</th>
				<th>
					图片名称
				</th>
				<th>
					海报图片
				</th>
				<th>
					操作
				</th>
			</tr>
			<s:iterator id="map" value="#request.filmmsglist.objects">
				<tr align="center">
					<td width="5%">
						<s:property value="#map.ID" />
					</td>
					<td>
						<s:property value="#map.FILMNAME" />
					</td>
					<td style="text-align: center;">
						<img id="img" style="width: 80px; height: 40px; display: block"
							src="<%=basePath%>upload<s:property value="#map.IMAGE"/>" />
					</td>


					<td>
						<a href="javascript:goEditImage('<s:property value="#map.ID"/>')">[编辑海报]</a>
						<a
							href="javascript:goEdit('<s:property value="#map.ADVERTISE_ID"/>')">[内容编辑]</a>
						<a
							href="javascript:deladvertise('<s:property value="#map.ADVERTISE_ID"/>')">[删除内容]</a>
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
