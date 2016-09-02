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
		<script type="text/javascript" src="<%=basePath%>activitymanage/activityQuery.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
	    <script>	
	    function toAdd(){
			var url = "/cinema/jobmanage/job!addjob.action";
			var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
					{
						title : "<div style='padding:10px;'>招聘-新增</div>", // 标题
						modal : true,// 是否开遮罩
						unloadOnHide : true
					});
			b.tween(550, 300);
		}
	    </script>
	</head>

	<body>
	  <div class="par_tit" style="*width:99%;">招聘管理</div>	
		<form action="/cinema/jobmanage/job!jobList.action" id="queryForm"
			name="queryForm" target="result" method="post">
   <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <div class="qu_div" style="*margin-left:8px;*width:813px;">
				<ul>
					<div>
					    <span>招聘职位:</span>
						<span><input id="JOB_NAME" size="10" name="job.JOB_NAME" class="qu_inp1" value="" type="text" /></span>
						
					           <input type="button" value="" onclick="dosub()" class="qu_inp2" style="width: 65px"/>
			                   <!--  <input type="button" value="" onclick="goAdd()" class="qu_inp3" style="width: 65px"/>-->
			                    <input type="button" value="" onclick="toAdd()" class="qu_inp3" style="width: 65px"/>
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
