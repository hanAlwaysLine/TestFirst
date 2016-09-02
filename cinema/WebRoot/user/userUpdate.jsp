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
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>user/userUpdate.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
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
		<form action="/cinema/user!updateUser.action?${users.password }"
			method="post" name="aFrom" id="aFrom" style="margin-left: 10px;">
			<input type="hidden" value="<s:property value="users.id"/>"
				name="users.id" id="userid" />
			<input type="hidden" value="<s:property value="users.password"/>"
				name="password" id="password" />
			<div style="width: 100%; height: 100%;" align="center">
				<table style="line-height: 30px;">
					<tr>
						<td width="25%" align="right">
							用户名：
						</td>
						<td align="left">
							<input id="name" name="users.name"
								value="<s:property value="users.name"/>" />
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
						<td>
							用户角色:
						</td>
						<td>
							<select name="roleid">
								<c:forEach items="${rlist}" var="r">
									<option value="${r.id}" <c:if test="${r.id==users.role_id}">selected="selected"</c:if>>
										${r.title}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td width="25%" align="right">
							登录密码：
						</td>
						<td align="left">
							<input type="password" id="password" name="users.password"
								value="<s:property value="users.password"/>" />
							<font color="red" style="vertical-align: middle">*</font>
						</td>
					</tr>

					<tr>
						<td width="25%" align="right">
							Email：
						</td>
						<td align="left">
							<input id="email" name="users.email"
								value="<s:property value="users.email"/>" />
						</td>
					</tr>
					<tr>
						<td width="25%" align="right">
							可用标识：
						</td>
						<td align="left">
							<input type="radio" name="users.state" value="0"
								<s:if test="%{users.state==\"0\"}">checked</s:if> />
							可用
							<input type="radio" name="users.state" value="1"
								<s:if test="%{users.state==\"1\"}">checked</s:if> />
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
							<input type="button" class="word_ft3" name="btn"
								onclick="doSave()" />
							<input type="reset" class="word_ft4" name="btn" value="" />
							<input type="button" class="word_ft5" name="btn"
								onclick="cancleDiv()" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
