<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>

		<title>My JSP 'userAdd.jsp' starting page</title>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>user/userAdd.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.metadata.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/page.css" />
	</head>
	<script type="text/javascript">
var msg = "${requestScope.msg}"
alertResult(msg,0);

</script>
	<body>
		<form action="/cinema/user!addUser.action" method="post" name="aFrom"
			id="aFrom" style="margin-left: 10px;">
			<div style="width: 100%; height: 100%;" align="center">
				<table style="line-height: 30px">
					<tr>
						<td align="right" width="25%">
							登录用户名：
						</td>
						<td align="left">
							<input id="name" name="users.name" />
							<font color="red" style="vertical-align: middle">*</font>
						</td>
						<td>
							<div id="show">
							</div>
						</td>
						<td>
							<input type="hidden" id="valid" />
						</td>
					</tr>
					<tr>
						<td>用户角色:</td>
						<td>
							<select name="roleid">
								<c:forEach items="${rlist}" var="r">
									<option value="${r.id}">${r.title}</option>
								</c:forEach>	
							</select>
						</td>
					</tr>
					<tr>
						<td width="25%" align="right" style="line-height: 30px;">
							登录密码：
						</td>
						<td align="left" style="line-height: 30px;">
							<input type="password" id="password" name="users.password" />
							<font color="red" style="vertical-align: middle">*</font>
						</td>
					</tr>
					<tr>
						<td width="25%" align="right">
							Email：
						</td>
						<td align="left" style="line-height: 30px;">
							<input id="email" name="users.email" value="" />
						</td>
					</tr>
					<tr>
						<td width="25%" align="right">
							可用标识：
						</td>
						<td align="left" style="line-height: 30px;">
							<input type="radio" name="users.state" value="0"
								checked="checked" />
							可用
							<input type="radio" name="users.state" value="1" />
							不可用
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="mms_bt1" name="btn"
								onclick="doSave()" />
							<input type="button" class="word_ft5" name="btn"
								onclick="cancleDiv()" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
