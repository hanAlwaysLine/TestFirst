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
		</basePath>

		<title>角色管理</title>

		<script type="text/javascript" src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>role/roleResult.js"></script>
			
		<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />

	</head>
	<body>
		<table class="data_table" style="height: 100%">
			<tr>
				<th>
					角色名
				</th>
				<th>
					角色描述
				</th>
				<th>
					操作
				</th>
			</tr>
			
			<s:iterator id="role1" value="#request.list.objects">
				<tr align="center">
					<td>
						<s:property value="#role1.title"/>
					</td>
					<td>
						<s:property value="#role1.addtime"/>
					</td>
					<td>
						<a href="javascript:updateRole('${role1.id}')">[修改]</a>
						<a href="javascript:delRole('${role1.id}')">[删除]</a>
						<a href="javascript:sqRole('${role1.id}')">[授权]</a>
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
