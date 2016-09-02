<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  
       
		    	<script type="text/javascript">
		$(document).ready(
			function() {
		
	setframeifHeight('add');
			});
	</script>
  </head>
<script>
	function text(){
		parent.waitAlert("正在加载...");
		}
	function abc(){
		parent.closeWait();
		}
	
</script>
  <body background="/cinema/common/imgs/welcomebg.png">
<!--  	<input type="button" onclick="text()" value="弹出"/>-->
<!--  	<input type="button" onclick="abc()" value="关闭"/>-->
  </body>
</html>
