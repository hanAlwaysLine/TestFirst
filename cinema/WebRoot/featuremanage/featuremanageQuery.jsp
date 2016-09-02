<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    <title>排期管理</title>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<script type="text/javascript" src="<%=basePath %>featuremanage/featuremanageQuery.js"></script>
	<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <div class="par_tit" style="*width:99%;">排期管理</div>	
  <form action="/cinema/featuremanage/featuremanage!queryfeature.action" id="queryForm" name="queryForm" 
   method="post" target="result">
   <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <input type="hidden" id="cinemano" name="cinemano" value="${placeno}"/>
   <input type="hidden" id="filmno"  name="featureForm.filmno" value="${filmno}"/>
   <div class="qu_div" style="*margin-left:8px;*width:813px;">
		<div>
			<table>
				<tr>
					<td><span>放映时间:</span></td>
					<td colspan="3"><span><input id="startDate" size="20"
								name="featureForm.starttime" class="Wdate" type="text"
								class="qu_inp1"
								onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/></span>
						<span>至</span>
						<span><input id="startDate" size="20"
								name="featureForm.endtime" class="Wdate" type="text"
								class="qu_inp1"
								onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/></span></td>
					<td><span>影片名称：</span>
					<span><input type="text" name="featureForm.filmname" value="${featureForm.filmname }" class="qu_inp1"/></span></td>
				</tr>
				<tr>
					<td><span>是否可用：</span></td>
					<td><span><select name="featureForm.useflag">
								<option value="">--请选择--</option>
								<option value="0">可用</option>
								<option value="1">不可用</option>
							  </select>
						</span>
					</td>
					<td><span>排期号：</span>
					<span><input type="text" name="featureForm.featureno" value="${featureForm.featureno }" class="qu_inp1"/></span></td>
					<td><span><input type="button" value="" onclick="goSubmit()" class="qu_inp2" style="width: 65px"/></span>
						<span><input type="button"  onclick="addfeature()" class="qu_inp4" style="width: 65px"/></span></td>
				</tr>
			</table>
		</div>
  </div>
  <div style="height: 5px;"></div>
		<div style="width: 100%">
			<iframe allowTransparency="true" name="result" id="iframe" 
				width="100%"  frameborder="0" scrolling="auto">
			</iframe>
		</div>
  </form>
  </body>
</html>
