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
		<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>user/userQuery.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
	</head>

	<body>
	  <div class="par_tit" style="*width:99%;">用户管理</div>	
		<form action="/cinema/manage/user!userList.action" id="queryForm"
			name="queryForm" target="user_result" method="post">
   <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <div class="qu_div" style="*margin-left:8px;*width:813px;">
				<ul>
					<div>
						<span>用户名:</span>
						<span><input type="text" id="name" name="users.name"/></span>
						&nbsp;
						<input type="submit" value="" class="qu_inp2"/>
						&nbsp;
						<input type="button" onclick="addUser()" class="qu_inp3"/>
					</div>
				</ul>
			</div><div style="height: 5px;"></div>
			<div style="height: 600px; width: 100%">
				<iframe allowTransparency="true" name="user_result" id="user_result"
					width="100%" height="600" frameborder="0" scrolling="auto">
				</iframe>
			</div>
		</form>
	</body>
</html>
