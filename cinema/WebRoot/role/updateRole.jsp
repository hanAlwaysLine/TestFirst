<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></basePath>
    
    <title>角色管理</title>
    
	<script type="text/javascript" src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.metadata.js"></script>
    <script type="text/javascript" src="<%=basePath%>role/updateRole.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
</head>
<script type="text/javascript">
var msg = "${requestScope.msg}"
alertResult(msg,0);
</script>
  <body>
  <form action="/cinema/role!updateRole.action" method="post"
			name="aFrom" id="aFrom" style="margin-left: 10px;">
			<input type="hidden" value="<s:property value="role.id"/>" name="role.id"/>
			<div style="width: 100%;height: 100%;" align="center">
			<table>
				<tr>
					<td width="25%" align="right" style="line-height:30px;">
						角色名:
					</td>
					<td align="left">
						<input id="roleid" name="role.title" value="<s:property value="role.title"/>" style="width: 160px;"/>
						<font color="red" style="vertical-align: middle">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" style="line-height:30px;">
						<input type="button" class="word_ft3"
							name="btn" onclick="doSave()"/>
						<input type="reset" class="word_ft4" name="btn" value=""/>
						<input type="button" class="word_ft5" name="btn" onclick="cancleDiv()"/>
					</td>
				</tr>
			</table>
			</div>
			
		</form>
  </body>
</html>
