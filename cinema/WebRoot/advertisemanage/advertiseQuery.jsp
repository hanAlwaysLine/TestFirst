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
		<script type="text/javascript" src="<%=basePath%>advertisemanage/advertiseQuery.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
	    
	    <style>
	      ul,li {
			 list-style:none;
			 margin:8px;
			 margin-left:21px;
			 padding:0px;}
		  #tab li{
			color: #588528;
			margin:1px;
			margin-bottom:0px;
			margin-left:0px;
			float:left;
			padding-left:5px;
			cursor:pointer}
			
			
		  #tab li.tabin{
			background-color:#B6B6B6;
			border:1px solid;
			border-bottom:0px;
			position:relative;
			z-index:100;
			}
       </style>
	</head>

	<body>
	  <div class="par_tit" style="*width:99%;">广告管理</div>	
	  
	    <!-- tab -->
		<ul id="tab">
		   <li class="tabin">影片首页轮播图
		  </li>
		  <li>广告图设置
		  </li>
		  <li>首页活动图
		  </li>
		</ul>
		<form action="/cinema/advertisemanage/advertise!selList.action" id="queryForm"
			name="queryForm" target="result" method="post">
   <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <div class="qu_div" style="margin-left:4px;width:813px;">
				<ul>
					<div style="margin-left:0px;width:813px;">
					           <input type="submit" value="" class="qu_inp2" style="width: 65px"/>
					           <input type="button" value="" onclick="goAdd()" class="qu_inp3" style="width: 65px"/>
					</div>
				</ul>
			</div><div style="height: 5px;"></div>
			<div style="width: 100%">
				<iframe allowTransparency="true" name="result" id="iframe"
					width="100%"  frameborder="0" scrolling="auto">
				</iframe>
			</div>
		</form>
	</body>
</html>
