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
	<script type="text/javascript" src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.metadata.js"></script>
    <script type="text/javascript" src="<%=basePath%>role/squan.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />

  </head>
<script type="text/javascript">
var msg = "${requestScope.msg}"
alertResult(msg,0);
</script>
  <body>
  <form action="/cinema/role!sqRole.action" method="post"
			name="aFrom" id="aFrom" style="margin-left: 10px;">
			<input type="hidden" id="roleid" value="<s:property value="role.id"/>" name="role.id"/>
			<input type="hidden" value="<s:property value="power.id"/>" name="power.id"/>
			<div style="width: 95%;height:85%;position: absolute;overflow: auto" align="center">
			<table style="line-height: 30px;" >
				<tr>
					<td width="20%" align="left">
						角色名:
					</td>
					<td align="left">
						<input id="rolename" name="role.title" value="<s:property value="role.title"/>" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td width="20%" align="left">
					</td>
					<td align="left">

					</td>
				</tr>
				<s:iterator  value="#request.allList" id="menu">
				<tr>
					<td colspan="2">
						${menu.title }<input type="checkbox" id="${menu.id}" name="power.competence_id" value="${menu.id}" onclick="findSon(this)"/>
					</td>	
				</tr>
				<tr>
					<td></td>
					<td>
						<table>
						<tr> 
							<s:iterator value="#menu.seList" id="sonMenu" status="ind">
							 <td>
							 	${sonMenu.title }<input type="checkbox" id="${sonMenu.id}" father="${sonMenu.father }" name="power.competence_id" value="${sonMenu.id }" onclick="findFmenu(this)"/>&nbsp;&nbsp;
							 </td>
							</s:iterator>
						</tr>
						</table>	
					</td>
				</tr>
			</s:iterator>
			</table>
			</div>
			 <div style="width:90%;position: absolute;overflow: auto;margin: 85% 0px 0px 0px;" align="center">
						<hr />
						<input type="button" class="mms_bt1"
							name="btn" onclick="doSave()"/>
						<input type="button" class="word_ft5" name="btn" onclick="cancleDiv()"/>
			 </div>
		</form>
  </body>
</html>
