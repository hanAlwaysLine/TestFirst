<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath%>";
		</script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>job/jobAdd.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath %>common/css/page.css" />
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/handlers.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>
		<script type="text/javascript">
		$(document).ready(
			function() {
	setframeifHeight('add');
			});
		
	</script>	
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
		<form action="/cinema/job/job!savejob.action"
			method="post" name="editForm" id="editForm" enctype="multipart/form-data">
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
				    <tr style="height: 25px" align="left">
						<td width="13%">
							招聘职位:
						</td>
						<td colspan="2" align="left">
							<input name="job.JOB_NAME" id="JOB_NAME" type="text" />
						</td>                     
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							地点:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="job.JOB_LOCATION" id="JOB_LOCATION" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							工作类别:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="job.JOB_TYPE" id="JOB_TYPE" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							招聘人数:
						</td>
						<td colspan="2" align="left">
							<input type="text" name="job.JOB_COUNT" id="JOB_COUNT" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							学历:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="job.JOB_SCHOOL" id="JOB_SCHOOL" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							招聘内容:
						</td>
						<td colspan="2" align="left">
							<textarea  rows="4" cols="40" name="job.JOB_CONTENT" id="JOB_CONTENT"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" onclick="doSave()" class="word_ft3" />
							<input type="button" onclick="cancleDiv()" class="word_ft5" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
