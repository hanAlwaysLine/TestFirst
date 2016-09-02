<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgpath="upload";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0"></meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath %>";
		</script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>cmanage/cmanageEdit1.js"></script>
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
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	var msg = "${requestScope.msg}"
	alertResult(msg,0);
	</script>
	</head>

	<body>
  <form action="/cinema/manage/cmanage!updateDetailedinformation1.action" method="post" name="editForm" id="editForm"  enctype="multipart/form-data">
  <input type="hidden" name="placeinfo.image" id="cmanageImg" value="${placeinfo.image }" />
  <input type="hidden" name="placeinfo.mappic" id="cmanageImg_mappic" value="${placeinfo.mappic }"/>
  <input type="hidden" name="placeinfo.id" value="${placeinfo.id }"/>
  <input type="hidden" id="place_NO" value="${placeinfo.placeno }"/>
  <div style="width: 10px"></div>
  <div style="width: 100%;height: 100%;" align="center">
  <table style="width: 90%;">
	 <tr style="height: 25px" align="left">
			<td width="13%">
				城市名称:
			</td>
			<td colspan="3" align="left">
				<select name="placeinfo.cityno">
					<option value="">
						全部
					</option>
					<s:iterator value="#request.lcity" id="city" status="i">
						<option value="<s:property value="#city.Id"/>" 
							<s:if test="%{#city.Id==#request.placeinfo.cityno}">selected</s:if>
						>
							<s:property value="#city.cityName" />
						</option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院ID:
			</td>
			<td colspan="3" align="left">
			<input name="placeinfo.placeno" type="text" id="placeno" value="${placeinfo.placeno }"/><span id="placenoSP" style="color: red"></span>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院名称:
			</td>
			<td width="21%" align="left">
				<input name="placeinfo.placename" type="text" value="${placeinfo.placename }"/>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				CINEMALINKID:
			</td>
			<td colspan="3" align="left">
				<input name="placeinfo.cinemalinkid" id="cmad" value="${placeinfo.cinemalinkid }" type="text"/>
			</td>
		</tr>
		<tr style="height: 25px">
		  	<td>
		  		电话:
		  	</td>
		  	<td align="left"><input name="placeinfo.phone" value="${placeinfo.phone}" type="text" /></td>
		 	</tr>
		<!-- tr style="height: 25px">
			<td>
				经纬度:
			</td>
			<td align="left">
				<input name="placeinfo.postion" value="${placeinfo.postion }" type="text" /><span style="color:red">*格式：经度-纬度</span>
			</td>
		</tr>-->
		<tr style="height: 25px">
			<td>
				微博地址:
			</td>
			<td align="left">
				<input name="placeinfo.weibolink" value="${placeinfo.weibolink }" type="text" /><span style="color:red">*格式：http://weibo.com/</span>
			</td>
		</tr>
		<tr align="left">
			<td><input name="text" id="upload_images" type="button" /></td>
			<td>
				<img src="<%=basePath+imgpath%>${placeinfo.image }" id="img" width="100px;" height="100px;"/>
				<!-- 进度条 -->
				<div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
 						<div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
 						</div>
			</td>
		</tr>
		<tr align="left">
			<td><input name="text" id="upload_images_mappic" type="button" /></td>
			<td>
				<img src="<%=basePath+imgpath%>${placeinfo.mappic}" id="img_mappic" width="130px;" height="100px;"/>
				<!-- 进度条 -->
				<div style="width:130px;position: absolute;display:none" id="upload_progresse_mappic" div-css="10">
 						<div id="upload_progress_bge_mappic"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
 						</div>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院详情:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.information" cols="42" rows="9" id="u_informator">${placeinfo.information}</textarea>
			</td>
			<td><input type="button" id="add_nbsp" value="空格"/><input id="add_br" type="button" value="换行"/></td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				营业时间:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.business_hours" cols="42" rows="4">${placeinfo.business_hours }</textarea>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				交通资讯:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.route" cols="42" rows="4">${placeinfo.route}</textarea>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院地址:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.address" cols="42" rows="4">${placeinfo.address}</textarea>
			</td>
		</tr>
			<tr style="height: 25px" align="left">
			<td>
				会员提示信息:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.vipalert" cols="42" rows="3" >${placeinfo.vipalert}</textarea>
			</td>
		</tr>
	  <tr>
	  	<td colspan="4">
	  		<hr />
	  	</td>
	  </tr>
	  <tr align="center">
	  	<td colspan="2">
	  	<input type="button" onclick="doEdit()" class="word_ft3" />
	  	<input type="button" onclick="cancleDiv()" class="word_ft5"/>
	  	</td>
	  </tr>
  </table>
  </div>
  </form>
	</body>
</html>
