<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
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
		<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath%>";
			var imgPath = "<%=imgPath%>";
		</script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>activitymanage/activityEdit.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath %>common/css/page.css" />
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
	    <script type="text/javascript" src="<%=basePath%>common/swfupload/js/handlers.js"></script>
	    <script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
	    <script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>	
	    <!-- 富文本 -->
		<script type="text/javascript" src="<%=basePath%>common/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/ueditor/ueditor.all.min.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		<form action="/cinema/activitymanage/activity!updActivity.action"
			method="post" name="editForm" id="editForm" enctype="multipart/form-data">
			<input type="hidden" name="activity.actimg" id="activityImg" value="${activity.actimg}"/>
			<input type="hidden" name="activity.actimg_h" id="activityImg_h" value="${activity.actimg_h}"/>
			<input type="hidden" name="activity.activity_id" value="${activity.activity_id }"/>
			<input type="hidden" name="oldPicUrl" value="${activity.actimg}"/>
			<input type="hidden" id="msg" value="${msg}"/>
			<input type="hidden" id="input_appcode" value="${activity.appcode }"/>
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
				    <tr style="height: 25px" align="left">
						<td width="13%">
							活动名:
						</td>
						<td colspan="3" align="left">
							<input name="activity.activity_name" value="${activity.activity_name}" type="text" />
						</td>                     
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							主题:
						</td>
						<td colspan="3" align="left">
						    <textarea name="activity.title" rows="4" cols="40">${activity.title}</textarea>(最多50字)
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							排期编号:
						</td>
						<td colspan="3" align="left">
						    <input type="text" name="activity.featureno" id="featureno" value="${activity.featureno}"/>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							座位数:
						</td>
						<td colspan="3" align="left">
						    <input type="text" name="activity.seatcount" value="${activity.seatcount}"/>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="10%">
							起始时间:
						</td>
						<td align="left" width="35%" colspan="3">
						   <input id="startDate"  name="activity.starttime" value="<s:date name="activity.starttime" format="yyyy-MM-dd HH:mm" />" readonly="readonly" class="Wdate" type="text"  class="qu_inp1" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
						</td> 
					</tr>
					<tr style="height: 25px" align="left">
						<td width="10%">
							终止时间:
						</td>
						<td align="left" width="35%" colspan="3">
					       <input id="endDate"  name="activity.endtime" value="<s:date name="activity.endtime" format="yyyy-MM-dd HH:mm" />" readonly="readonly" class="Wdate" type="text"  class="qu_inp1" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
						</td>     
					</tr>
					<tr style="height: 25px" align="left">
						<td>活动数量:</td>
						<td align="left" colspan="3">
					       <input id="count"  name="activity.count"  type="text" value="${activity.count}"/>
						</td>     
					</tr>
					<tr style="height: 25px" align="left">
						<td>活动金额:</td>
						<td align="left" colspan="3">
					       <input id="price"  name="activity.price"  type="text" value="${activity.price}"/>
						</td>     
					</tr>
					<tr style="height: 25px" align="left">
						<td>渠道:</td>
						<td align="left" colspan="3">
					       	<input type="checkbox" name="activity.appcode" value="1"/>网站
							<input type="checkbox" name="activity.appcode"  value="2"/>Andorid
							<input type="checkbox" name="activity.appcode" value="3"/>IOS
							<input type="checkbox" name="activity.appcode" value="4"/>微信
						</td>     
					</tr>
					<tr style="height: 50px" align="left">
					  	<td>活动介绍:
					  	</td>
					  		<td colspan="3">
							<!-- 富文本 -->
						    <script type="text/plain" id="myEditor" name="activity.cont">${activity.cont}</script>
							<script type="text/javascript">
								new UE.ui.Editor().render("myEditor");
							</script>
							<strong><font size="-6"><span id="getshow" style="color:#F00"></span></font></strong>最多2000字
				 		</td>	  	
	  				</tr>
					<tr style="height: 25px" align="left">
						<td>
							是否发布:
						</td>
						<td colspan="3" align="left">
							<p>
								<input type="radio" name="activity.activestate" value="0"     <c:if test="${activity.activestate==0}">checked="checked"</c:if> />
								不发布
								<input type="radio" name="activity.activestate" value="1" <c:if test="${activity.activestate==1}">checked="checked"</c:if>/>
								发布
							</p>
						</td>
					</tr>
					    <tr align="left">
								<td><input name="text" id="upload_images" type="button" /></td>
								<td>
									<img src="<%=imgPath%>${activity.actimg}" id="img" width="175px;" height="200px;"/>
									<!-- 进度条 -->
									<div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
					 						<div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
					 						</div>
								</td>
								<td>
						    	<div style="color: red;">
						    		图片尺寸为175*200px
						    	</div>
						    </td>
						</tr>
					<tr>
						<td colspan="3">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="3">
							<input type="button" onclick="doEdit()" class="word_ft3" />
							<input type="button" onclick="cancleDiv()" class="word_ft8" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
