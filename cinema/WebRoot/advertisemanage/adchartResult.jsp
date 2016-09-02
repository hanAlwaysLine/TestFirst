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
		
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>advertisemanage/adchartResult.js"></script>
	    <link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	    <script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript">
		$(document).ready(
			function() {
		
	setIframeHeight();
			});

  </script>
	</head>
	<body>
	
		<table class="data_table">
					<tr>
						<th>
							广告图片
						</th>
					    <th>
							活动名称
						</th>
						<th>
							城市
						</th>
						<th>
							地点
						</th>
						<th>
							广告位置
						</th>
						<th>
							活动ID
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="map" value="#request.admsglist.objects">
						<tr align="center">
							<td>
								<img id="img" style="width:80px;height:40px;display:block" src="<%=basePath%>upload<s:property value="#map.ACTIMG"/>"/>
								
							</td>
							<td>
							    <s:property value="#map.ACTIVITY_NAME"/>
							</td>
							<td width="5%">
							    <s:property value="#map.CITYNAME"/>
							</td>
							<td>
								<div title="<s:property value="#map.LOCALE"/>" style="height:28px;line-height:28px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
									<s:property value="#map.LOCALE"/>
								</div>
														    
							</td>
							<td>
							    <s:property value="#map.POSITION"/>
							</td>
							<td width="5%">
							    <s:property value="#map.ACTIVITY_ID"/>
							</td>
							<td>
							    <a href="javascript:goEditImage('<s:property value="#map.ACTIVITY_ID"/>','<s:property value="#map.ADVERTISEMENT_TYPE"/>')">[编辑海报]</a>
			                	<a href="javascript:goEdit('<s:property value="#map.ADVERTISE_ID"/>')">[内容编辑]</a>
			                	<a href="javascript:posAdvertise('<s:property value="#map.ADVERTISE_ID"/>')">[位置设置]</a>
						        <a href="javascript:deladvertise('<s:property value="#map.ADVERTISE_ID"/>')">[删除内容]</a>
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
