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
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<style >
	#apdiv{color:#212121;position:absolute;word-wrap:break-word;background-color:#DBE9C5; line-height:20px;padding:5px;font-size:12px;-webkit-border-radius: 6px;-moz-border-radius: 6px;}
	</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
	<table class="data_table" >
		<tr>
			<th>接口名称</th>
			<th>调用日期</th>
			<th>接口返回</th>
			<th>异常</th>
		</tr>
        <s:iterator id="errorlog" value="#request.list.objects">	
		<tr align="center">
			<td>${errorlog.name }</td>
			<td>${errorlog.time }</td>
			<td>${errorlog.result }</td>
			<td>${errorlog.exception }</td>
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
