<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <title>影片管理</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<script type="text/javascript" src="<%=basePath %>filmmanage/filmmanageQuery.js"></script>


	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <div class="par_tit" style="*width:99%;">影片管理</div>	
  <form action="/cinema/manage/filmmanage!queryfilmmanage.action" id="queryForm" name="queryForm" 
   method="post" target="result">
   <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <div class="qu_div" style="*margin-left:8px;*width:813px;">
 	 <ul>
		<div>
			<span>影片名称：</span>
			<span>
			<input type="text" name="filmmessage.filmname" class="qu_inp1"/>
			</span>
			<span>影片编号：</span>
			<span><input type="text" name="filmmessage.filmno" class="qu_inp1"/></span>
			<input type="button" value="" class="qu_inp2" style="width: 65px" onclick="goSubmit()"/>
			<input type="button"  onclick="javascript:window.location='<%=basePath%>filmmanage/filmmanage!addfilm.action'" class="qu_inp3" style="width: 70px"/>
		</div>
	</ul>
  </div>
  <div style="height: 5px;"></div>
		<div style="width: 100%">
			<iframe allowTransparency="true" name="result" id="iframe"
				width="100%" frameborder="0" scrolling="auto">
			</iframe>
		</div>
  </form>
  </body>
</html>
