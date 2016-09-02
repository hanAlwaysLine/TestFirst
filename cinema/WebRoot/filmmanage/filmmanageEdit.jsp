<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
		<base href="<%=basePath%>"></base>
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath%>";
			var imgPath = "<%=imgPath%>";
	</script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>filmmanage/filmmanageEdit.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath %>common/css/page.css" />
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/handlers.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<script type="text/javascript">
	var msg = "${requestScope.msg}"
		var img="";
		if (msg != null && "" != msg) {
			 alertResult(msg, 0);
			
			
		}
	</script>
	<body>
		<form action="/cinema/filmmanage/filmmanage!updatefilm.action"
			method="post" enctype="multipart/form-data" name="editForm"
			id="editForm">
			<input type="hidden" name="filmmessage.id" value="${filmmessage.id}" />
			<input type="hidden" name="filmmessage.image" id="image" value="" />
			<input type="hidden" name="filmmessage.images" id="images" value="" />
			<input type="hidden" id="u_filmno" value="${filmmessage.filmno}" />
			<input type="hidden" id="genreno" value="${filmmessage.genreno}"/>
			<div style="width: 18px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">

					<tr style="height: 25px" align="left">
						<td width="20%">
							影片名称:
						</td>
						<td width="80%" colspan="3">
							<input type="text" name="filmmessage.filmname"
								value="${filmmessage.filmname }" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="15%">
							电影编号:
						</td>
						<td width="60%"  colspan="2" >
							<input type="text" name="filmmessage.filmno"
								value="${filmmessage.filmno }" id="do_filmno" />
						</td>
						<td width="20%">
							<font id="show" color="red"></font>
						</td>
					</tr>
					<!--	 <tr style="height: 25px" align="left">-->
					<!--	  	<td>-->
					<!--	  		影片短名称:-->
					<!--	  	</td>-->
					<!--	  	<td>-->
					<!--	  	-->
					<!--	  		<input type="text" name="filmmessage.filmename" value="${filmmessage.filmename }"/>-->
					<!--	  -->
					<!--	  	</td>-->
					<!--	</tr>-->
					<tr style="height: 25px" align="left">
						<td width="139" style="width: 130">
							片长:
						</td>
						<td colspan="3">
							<input type="text" name="filmmessage.filmtime"
								value="${filmmessage.filmtime}" />
							分钟
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="139" style="width: 130">
							导演:
						</td>
						<td colspan="3">
							<input type="text" name="filmmessage.direct"
								value="${filmmessage.direct }" maxlength="15"/>
							格式:张艺谋,冯小刚,***
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="131" style="width: 130">
							演员:
						</td>
						<td colspan="3">
							<input type="text" name="filmmessage.player"
								value="${filmmessage.player }" maxlength="15"/>
							格式:刘德华,梁朝伟,***
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="131" style="width: 130">
							编剧:
						</td>
						<td colspan="3">
							<input type="text" name="filmmessage.writer"
								value="${filmmessage.writer }" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="131" style="width: 130">
							评分:
						</td>
						<td colspan="3">
							<input type="text" name="filmmessage.grade"
								value="${filmmessage.grade }" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影片类型:
						</td>
						<td colspan="3">
							<s:iterator id="list" value="#request.genremessageList" status="i">
								<input type="checkbox" name="filmmessage.genreno" value="${list.id}"/>${list.title}&nbsp;&nbsp;&nbsp;&nbsp;
							</s:iterator>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="131" style="width: 130">
							版本类型:
						</td>
						<td colspan="3">
							<s:select name="filmmessage.version_type"
								list="#{1:'imax',2:'2D',3:'3D'}" listKey="key" listValue="value"
								value="filmmessage.version_type" theme="simple"></s:select>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							上映时间:
						</td>
						<td colspan="3">
							<input id="startDate" type="text" readonly="readonly"
								class="Wdate" name="filmmessage.showtime"
								value="${filmmessage.showtime }"
								onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							国家:
						</td>
						<td colspan="3">
							<select name="filmmessage.parea">
								<s:iterator id="list" value="#request.nationList" status="i">
									<option
										<c:if test="${filmmessage.parea==list.id}">selected="selected"</c:if>
										value="${list.id}">
										${list.name}
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影片状态:
						</td>
						<td colspan="3">
							<select name="filmmessage.release">
								<option value="0"
									<c:if test="${filmmessage.release==0}">selected="selected"</c:if>>
									未发布
								</option>
								<option value="1"
									<c:if test="${filmmessage.release==1}">selected="selected"</c:if>>
									即将上映
								</option>
								<option value="2"
									<c:if test="${filmmessage.release==2}">selected="selected"</c:if>>
									正在上映
								</option>
								<option value="3"
									<c:if test="${filmmessage.release==3}">selected="selected"</c:if>>
									已过期
								</option>
							</select>
						</td>
					</tr>
					<!--	  <tr style="height: 25px" align="left">	  -->
					<!--		  <td>剧情简介:</td>-->
					<!--		  <td>-->
					<!--			<textarea name="filmmessage.getshow" cols="45" rows="5">${filmmessage.getshow}</textarea><br/>(最多200字)-->
					<!--		  </td> -->
					<!--	  </tr>-->
					<tr style="height: 25px" align="left">
						<td>
							影片简介:
						</td>
						<td colspan="3">
							<textarea name="filmmessage.filmsis" cols="45" rows="5" />
							${filmmessage.filmsis }
							</textarea>
						</td>
					</tr>

<!--					<tr align="left">-->
<!--						<td>-->
<!--							修改海报(横版):-->
<!--						</td>-->
<!--						<td>-->
<!--							<input name="text" id="upload_images" type="button" />-->
<!--						</td>-->
<!---->
<!--						<td>-->
<!--							<img src="<%=imgPath%>${filmmessage.images}" id="imgtwo"-->
<!--								width="130px;" height="100px;" />-->
<!--							 进度条 -->
<!--							<div style="width: 130px; position: absolute; display: none"-->
<!--								id="upload_progresses" div-css="10">-->
<!--								<div id="upload_progress_bges"-->
<!--									style="background: #8BABCE; width: 20%; height: 20px; float: left; margin-top: 35px;"></div>-->
<!--							</div>-->
<!--						</td>-->
<!--					</tr>-->
					<tr align="left">
						<td width="20%">
							修改海报(竖版):
						</td>
						<td width="20%">
							<input name="text" id="upload_image" type="button" />
						</td>
						<td width="40">
							<img src="<%=imgPath%>${filmmessage.image}" id="imgone"
								width="140px;" height="189px;" />
							<!-- 进度条 -->
							<div style="width: 130px; position: absolute; display: none"
								id="upload_progresse" div-css="10">
								<div id="upload_progress_bge"
									style="background: #8BABCE; width: 20%; height: 20px; float: left; margin-top: 35px;"></div>
							</div>
						</td>
						<td align="left" width="20%"><font color="red">*规格尺寸（140*189px）等比5:7的图片</font></td>
					</tr>

					<tr>
						<td colspan="4">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" value="" onclick="doEdit();"
								class="word_ft3" />
							<input type="button" onclick="cancleDiv()" class="word_ft5" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
