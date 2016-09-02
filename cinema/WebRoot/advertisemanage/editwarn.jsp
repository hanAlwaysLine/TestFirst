<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgpath="upload";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath%>";
    </script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>advertisemanage/editwarn.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/newMainIframeDiv_2.css"/>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/handlers.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var msg = "${requestScope.msg}"
		var img="";
		if (msg != null && "" != msg) {
			alertResult(msg, 0);
		}
	</script>
  </head>
  <body>
  <form action="/cinema/advertisemanage/advertise!doeditwarn.action" method="post" enctype="multipart/form-data" name="editForm" id="editForm">
   <input type="hidden" id="msg" value="${msg}"/>
  <input type="hidden" name="filmmessage.id" value="${filmmessage.id }"/>
  <input type="hidden" name="filmmessage.image" id="image" value="${filmmessage.image}"/>
  <div style="width: 10px"></div>
  <div style="width: 100%;height: 100%;" align="center">
  <table border="1" style="width: 90%" class="">  
	  <tr align="left">
		 		<td>海报:</td>
				<td><input name="text" id="upload_image" type="button" /></td>
		     	<td>
				<img src="<%=basePath+imgpath%>${filmmessage.image}" id="img" width="100px;" height="100px;"/>
				<!-- 进度条 -->
				<div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
				<div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
				</div>
				</td>
				<td><font color="red">*首页轮播活动图片尺寸为238*103px</font></td>
	 </tr>
	  <tr align="center">
	  	<td colspan="4">
	  	<input type="button" value="" class="word_ft3" onclick="checktypet();" />
	  	<input type="button" value="" onclick="cancleDiv();" class="word_ft5"/>
	  	</td>
	  </tr>
  </table>
  </div>
  </form>
  </body>
</html>