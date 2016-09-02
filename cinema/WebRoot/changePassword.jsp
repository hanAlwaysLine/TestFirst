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
 	<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
    <script type="text/javascript" src="<%=basePath%>user/userUpdate.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
	</head>
<script type="text/javascript">
var msg = "${requestScope.msg}"
alertResultt(msg,0);


function alertResultt(msg,index)
{
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
		parent.parent.susAlert(img+msg);
		if(index==null)
		{
			var frameaif=$("#frameaif",window.parent.parent.document);
			frameaif[0].contentWindow.document.forms[0].submit();
		}
	    if(index==0)
		{
			var frameaif=$("#frameaif",window.parent.document);
		//	frameaif[0].contentWindow.document.forms[0].submit();
			parent.$(".boxy-modal-blackout").eq(index).remove();
			parent.$(".boxy-wrapper").eq(index).remove();
		}
	    if(index==1)
		{
			var frameaif=$("#frameaif",window.parent.document);
			frameaif[0].contentWindow.document.forms[0].submit();
			parent.$(".boxy-modal-blackout").eq(index).remove();
			parent.$(".boxy-wrapper").eq(index).remove();
		}
		
	}
}
</script>
	<body>
		<form action="/cinema/login!pudateuserpass.action" method="post"
			name="aFrom" id="aFrom" style="margin-left: 10px;">
			<input type="hidden" value="<s:property value="users.id"/>" name="users.id" id="userid"/>
			<input type="hidden" value="<s:property value="users.password"/>" name="password" id="password"/>
			<div style="width: 100%;height: 100%;" align="center">
			<table style="line-height: 30px;">
				<tr>
					<td width="25%" align="right">
						用户名：
					</td>
					<td align="left">
						<input id="name" name="users.name" value="<s:property value="users.name"/>" disabled="disabled"/>
						<font color="red" style="vertical-align: middle">*</font>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						登录密码：
					</td>
					<td align="left">
						<input type="password" id="password" name="users.password" />
						<font color="red" style="vertical-align: middle">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<hr />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="word_ft3" name="btn" onclick="doSave()"/>
						<input type="reset" class="word_ft4" name="btn" value=""/>
						<input type="button" class="word_ft5" name="btn" onclick="cancleDiv()"/>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>
