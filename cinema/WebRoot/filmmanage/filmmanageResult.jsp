<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>filmmanage/filmmanageResult.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		$(document).ready(
			function() {
	setIframeHeight();
			});
	</script>
	</head>

	<body>
		<table class="data_table">
			<tr>
			    <th width="5%">
					影片ID
				</th>
				<th width="12%">
					影片名称
				</th>
				<th width="11%">
					影片编码
				</th>
				<th width="10%">
					上映日期
				</th>
				<th width="10%">
					导演
				</th>
				<th width="10%">
					演员
				</th>
				<th width="7%">
					版本类型
				</th>
				<th width="5%">
					位置 
				</th>
				<th width="8%">
					管理
				</th>
				<th width="16%">
					操作
				</th>
			</tr>
			<s:iterator id="filmmanage" value="#request.list.objects">
				<tr align="center">
				    <td>
						${filmmanage.id }
					</td>
					<td>
						${filmmanage.filmname }
					</td>
					<td>
						${filmmanage.filmno }
					</td>
					<td>
						${filmmanage.showtime }
					</td>
					<td>
						${filmmanage.direct}
					</td>
					<td>
						${filmmanage.player }
					</td>
					<td>
						<c:if test="${filmmanage.version_type==1}">IMAX</c:if>
						<c:if test="${filmmanage.version_type==2}">2D</c:if>
						<c:if test="${filmmanage.version_type==3}">3D</c:if>
					</td>
					<td>
						${filmmanage.position}
					</td>
					<td>
						<a
							href="javascript:goFilmStills('${filmmanage.id}','${filmmanage.filmname }')">[剧照管理]
						</a>
					</td>
					<td>
						<a href="javascript:del('${filmmanage.id}')">[删除]</a>
						<a href="javascript:goEdit('${filmmanage.id}')">[编辑]</a>
						<c:if test="${filmmanage.release==0 }"><a href="javascript:goRelease('${filmmanage.id}')">[发布]</a></c:if>
						<c:if test="${filmmanage.release==1}"><font color="red">[即将上映]</font></c:if>
						<c:if test="${filmmanage.release==2}"><font color="red">[正在上映]</font></c:if>
						<c:if test="${filmmanage.release==3}"><font color="red">[已过期]</font></c:if>
						<a href="javascript:queryFeatures('${filmmanage.filmno}')">[查看排期]</a>
						<a href="javascript:position('${filmmanage.id}','${filmmanage.position}')">[位置]</a>
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
