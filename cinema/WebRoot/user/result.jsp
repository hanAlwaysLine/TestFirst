<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<base href="<%=basePath%>"></base>

	<title>My JSP 'userAdd.jsp' starting page</title>
 	<script type="text/javascript" src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
 	<script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
 	<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
    <script type="text/javascript" src="<%=basePath%>user/userAdd.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.metadata.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
	</head>
<script type="text/javascript">
var msg = "${requestScope.msg}"
alertResult(msg,0);

</script>

