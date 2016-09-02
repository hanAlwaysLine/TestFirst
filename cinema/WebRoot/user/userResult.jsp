<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>

		<title>My JSP 'userList.jsp' starting page</title>

		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>user/userResult.js"></script>
		
	    <link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	</head>
	<body>
		<table class="data_table">
					<tr>
						<th>
							登录用户名
						</th>
					    <th>
							角色
						</th>
						<th>
							邮箱
						</th>
						<th>
							可用标识
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="user" value="#request.list.objects">
						<tr align="center">
							<td>
								${user.name }
							</td>
							<td>
								${user.title }
							</td>
							<td>
								${user.email }
							</td>
							<td>
							<s:if test="%{#user.state==\"0\"}">
		                                                              可用
		                	</s:if>
							<s:else>
		                  	    不可用
		                	</s:else>
							</td>
							 <td>
								<a href="javascript:updateUser('${user.id}')">[修改]</a>
								<a href="javascript:userDel('${user.id}')">[删除]</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<div style="height: 5px; clear: both;"></div>
				<div id="page">
						<cinemamanage:pages formId="queryForm" className="button_page"/>
					<div style="height: 20px; clear: both;"></div>
				</div>
	</body>
</html>
