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
		<script type="text/javascript" src="<%=basePath%>activitymanage/activityResult.js"></script>
			<script type="text/javascript">
		$(document).ready(
			function() {
	setIframeHeight();
			});
	</script>
	    <link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	</head>
	<body>
		<table class="data_table">
					<tr>
					    <th>
							活动ID
						</th>
						<th>
							活动名称
						</th>
					    <th>
							起始时间
						</th>
						<th>
							终止时间
						</th>
						<th>
							活动数量
						</th>
						<th>
							活动金额
						</th>
						<th>
							活动主题
						</th>
						<th width="15%">
							操作
						</th>
					</tr>
					<s:iterator id="activity" value="#request.activitylist.objects">
						<tr align="center">
						    <td>
								<s:property value="#activity.activity_id"/>
							</td>
							<td>
								<s:property value="#activity.activity_name"/>
							</td>
							<td>
							    <s:date name="#activity.starttime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							    <s:date name="#activity.endtime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							    <s:property value="#activity.count"/>
							</td>
							<td>
							    <s:property value="#activity.price"/>
							</td>
							<td>
								<div title="<s:property value="#activity.title"/>" style="height:25px;line-height:25px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
									<s:property value="#activity.title"/>
								</div>
							</td>
							<td>
								<s:if test="%{#activity.activestate==\"0\"}">
			                       	<a href="javascript:release('<s:property value="#activity.activity_id"/>')">[发布]</a>                                
			                	</s:if>
								<s:elseif test="%{#activity.activestate==\"1\"}">
			                  	    <font color="red">已发布</font> 
			                	</s:elseif>
			                	<a href="javascript:goEdit('<s:property value="#activity.activity_id"/>')">[修改]</a>
						        <a href="javascript:delActivity('<s:property value="#activity.activity_id"/>')">[删除]</a>
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
