<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath %>filmmanage/fimewarnmanageResult.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.table_tda td a{float:left;margin:6px 5px 0 5px;*margin-top:4px;}
	</style>
  </head>
  <body>
  <form id="editfimeForm" action="/cinema/filmmanage/filmmanage!editfimewarn.action" method="post">
     <input type="hidden" name="id" value="${id}"/>
      <input type="hidden" name="filmnameStr" value='${filmnameStr}' />
  </form>
	
	<div  style="margin-left:8px;">
		<a href="javascript:goAdd(${id})">&ensp;&ensp;[添加片花]&ensp;&ensp;</a>
		&ensp;&ensp;
		<a href="javascript:cancleDiv()">&ensp;&ensp;[返回影片列表]&ensp;&ensp;</a>
	</div>
	<table class="data_table table_tda" >
		<tr>
			<th>id</th>
			<th>时间</th>
			<th>操作</th>
		</tr>
        <c:forEach items="${list}" var="list">
		<tr align="center" id="commtr${list.id }">
			<td>${list.id }</td>
			<td><fmt:formatDate  value="${list.addtime }" type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>           
			<td style="padding-left:37px;">
	            <a href="javascript:goEditfimewarn('${list.id }')">[编辑]</a>
	            <a href="javascript:goSeeFimwarn('${list.warnpic }')">[片花预览]</a>
	            <img  src="common/imgs/redx.png" onclick="javascript:delfimewarn('${list.id}','${list.film_d}')">
            </td>
		</tr>
		</c:forEach>
	</table>
  </body>
  
</html>
