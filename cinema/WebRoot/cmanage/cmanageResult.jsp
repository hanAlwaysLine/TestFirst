<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
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
	<meta http-equiv="expires" content="0">    </meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath %>cmanage/cmanageResult.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
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
	<table width="745" class="data_table" >
		<tr>
			<th width="100">
				编号			</th>
			<th width="106">
				影院ID		</th>
			<th width="133">
				影院名称			</th>
			<th width="110">
				所在城市		</th>
			<th width="110">
				联系电话		</th>
			<th width="230">
				操作			</th>
		</tr>
		<s:iterator id="manage" value="#request.list.objects" status="rowNum">
		<tr align="center">
			<td>
				<s:property value="#rowNum.index+1"/>
			</td>
			<td>
				${manage.PLACENO }
			</td>
			<td>
				${manage.PLACENAME }
			</td>
			<td>
				${manage.CITYNAME }
			</td>
			<td>
				${manage.PHONE }
			</td>
			<td>
				<a href="javascript:goEdit('${manage.ID}')">[修改]</a>
				<a href="javascript:goDetailedinformation('${manage.ID}','${manage.PLACENAME}')">[详细信息]</a>
				<c:if test="${manage.USABLE==0}"><a href="javascript:updateUsable('${manage.ID}')">[发布]</a></c:if><c:if test="${manage.USABLE==1}"><font color="red">[已发布]</font></c:if>
				<a href="javascript:del('${manage.ID}')">[删除]</a>
				<a href="javascript:queryFeature('${manage.PLACENO }','${manage.CITYNO}')">[查看排期]</a>
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
