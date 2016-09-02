<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0">    </meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath %>featuremanage/waitingTip.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
	<div id="div1" style="margin:90px;width:200px;height:14px;"/>
  </body>
  
	<script type="text/javascript">
		var w1 = new WaitingTip({innerHTML:"更新中...<br/><img src='common/imgs/waiting.gif' />"});
	    var div1El = document.getElementById("div1");  
	    w1.show(div1El,"center"); 
	</script>
  
</html>
