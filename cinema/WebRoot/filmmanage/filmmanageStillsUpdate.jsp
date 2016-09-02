<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
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
			var imgPath = "<%=imgPath%>";
    </script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>filmmanage/filmmanageStillsUpdate.js"></script>
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
			if(msg.indexOf("失败")>0)
			{
				img="<img src='/cinema/common/Images/cha.png' border='0' align='absmiddle'/>" 
			}
			else
			{
				img="<img src='/cinema/common/Images/gou.png' border='0' align='absmiddle'/>";
			}
			parent.parent.susAlert(img+msg);//显示成功与失败的图片
			
			var boxy_1 = parent.$(".boxy-wrapper").eq(0);
			var divIframe = $(boxy_1).find("iframe");
			var formCopy = divIframe[0].contentWindow.document.forms["editfimeForm"];
			formCopy.submit();

			parent.$(".boxy-modal-blackout").eq(1).remove();
			parent.$(".boxy-wrapper").eq(1).remove();
			
		}
	</script>
  </head>
  <body>
  <form action="/cinema/filmmanage/filmmanage!goUpdatefimestills.action" method="post" enctype="multipart/form-data" name="editForm" id="editForm">
   <input type="hidden" id="msg" value="${msg}"/>
  <input type="hidden" name="fimewarn.id" value="${fimewarn.id}"/>
   <input type="hidden" name="fimewarn.warnpic" id="image" value=""/>
  <div style="width: 10px"></div>
  <div style="width: 100%;height: 100%;" align="center">
  <table border="1" style="width: 90%" class="">
	  <tr align="left">
         <td>剧照: </td>
		 <td>
	      <input name="upload_image" id="upload_image" type="button"/>
		 </td>				      
									
	     <td>
			<img src="<%=imgPath%>${fimewarn.warnpic}" id="img" width="130px;" height="100px;"/>
		 <!-- 进度条 -->
		 <div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
		 <div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
		 </div>
	     </td>
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