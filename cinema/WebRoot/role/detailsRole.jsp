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
  <form action="" method="post"
			name="aFrom" id="aFrom" style="margin-left: 10px;">
			<div align="center">
			<input type="hidden" value="<s:property value="role.roleid"/>" name="role.roleid"/>
			<table style="line-height: 20px;">
			    <tr>
					<td width="100px" align="left">
						角色id:
					</td>
					<td align="left">
						<s:property value="role.roleid"/>
					</td>
				</tr>
				<tr>
					<td  width="100px" align="left">
						角色名:
					</td>
					<td align="left">
						<s:property value="role.rolename"/>
					</td>
				</tr>
				<tr>
					<td width="100px" align="left">
						角色描述:
					</td>
					<td align="left">
						<s:property value="role.roledesc"/>
					</td>
				</tr>
			</table>
			</div>
		</form>
  </body>
</html>
