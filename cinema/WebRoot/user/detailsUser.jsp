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
    <script type="text/javascript" src="<%=basePath%>user/detailsUsers.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.metadata.js"></script>
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
			<input type="hidden" value="<s:property value="users.userid"/>" name="users.userid" id="userid"/>
			<div align="center">
			<table style="line-height: 15px;">
			    <tr>
					<td width="25%" align="right">
						用户id：
					</td>
					&nbsp;&nbsp;
			        <td align="left">
						<s:property value="users.userid"/>
					</td>
				</tr>
			    <tr>
					<td width="25%" align="right">
						登录用户名：
					</td>
					&nbsp;&nbsp;
			        <td align="left">
					<s:property value="users.loginname"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						用户名：
					</td>
					&nbsp;&nbsp;
					<td align="left">
					<s:property value="users.username"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						用户描述：
					</td>
					<td align="left">
						<s:property value="users.userdesc"/>
					</td>
				</tr>
				<tr>
				<td width="25%" align="right"  style="line-height:30px;">
						角色：
				</td>
				<td>
				<s:iterator value="#request.rlist" id="role" status="st">
				<s:if test="%{#st.index%3==0}">
				<br/>
				</s:if>
				<input type="checkbox" roleid = "${role.roleid }" id="roleid" name="role.roleid" value="${role.roleid }" readonly="readonly" disabled="disabled"/>${role.rolename }
				</s:iterator>
				</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						Email：
					</td>
					<td align="left">
						<s:property value="users.email"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						电话：
					</td>
					<td align="left">
						<s:property value="users.phonenum"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						手机号：
					</td>
					<td align="left">
						<s:property value="users.mobilenum"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						部门：
					</td>
					<td align="left">
						<s:property value="users.department"/>
					</td>
				</tr>
				<tr>
					<td width="25%" align="right">
						可用标识：
					</td>
					<td align="left">
					<s:if test="%{users.USABLE==\"0\"}">
						可用
				    </s:if>
					<s:else>
						不可用
					</s:else>
					</td>
				</tr>
			</table>
			</div>
		</form>
  </body>
</html>
