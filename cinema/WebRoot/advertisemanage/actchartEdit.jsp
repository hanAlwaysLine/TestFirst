
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
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
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript" src="<%=basePath %>advertisemanage/actchartEdit.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		<form action="/cinema/advertisemanage/advertise!editadvertise.action"
			method="post" name="editForm" id="editForm">
			<input type="hidden" id="msg" value="${msg}"/>
			<input type="hidden" name="advertise.advertisement_type" value="31"/>
			<input type="hidden" name="advertise.advertise_id" value="${advertise.advertise_id}"/>
		   
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
					<tr>
						<td  align="left">
							城市:
						</td>	
						<td  align="left">
							<s:select theme="simple" value="advertise.cityno" name="advertise.cityno" id="city"  list="listall" listKey="Id" listValue="cityName"> 
				             </s:select>
						</td>
					</tr>
					<tr>
					  <td  align="left">活动ID:</td>
					  <td  align="left">
					    <input type="text" id="according_id" name="advertise.according_id" value="${advertise.according_id}"/>
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
					  <td  align="left">地点:</td>
					  <td  align="left">
					    <textarea rows="4" cols="25" name="advertise.locale">${advertise.locale}</textarea>
					  </td>
					</tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" onclick="doEdit()" class="word_ft3" />
							<input type="button" onclick="cancleDiv()" class="word_ft5" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
