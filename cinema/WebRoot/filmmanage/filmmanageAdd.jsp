<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
 <%@ taglib prefix="cinema" uri="/WEB-INF/cinemamanage.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />
		<script type="text/javascript"
			src="<%=basePath%>filmmanage/filmmanageAdd.js"></script>
	    <script type="text/javascript" src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/handlers.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>			

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
		<script type="text/javascript">
			var msg = "${requestScope.msg}"
			alertResult(msg,0);
				$(document).ready(
			function() {
	setframeifHeight('add');
			});
		</script>
	<body>
		<form action="/cinema/filmmanage/filmmanage!savefilm.action"
			method="post" name="addForm" enctype="multipart/form-data"
			id="addForm">
			
			<input type="hidden" name="filmmessage.image" id="image" value=""/>
			<input type="hidden" name="filmmessage.images" id="images" value=""/>
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%" class="">
				<tr style="height: 25px" align="left">
					<td width="20%">
						影片名称:
					</td>
					<td width="60%">
						<input type="text" name="filmmessage.filmname" />
					</td>
					<td align="left" width="20%"><font color="red">*不超过20个汉字（影片全名，网站读取）</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td style="width: 130">
							影片编码:
						</td>
						<td>
							<input type="text" name="filmmessage.filmno" id="filmno"/>
						</td>
						<td>
							<span id="show">
							</span>
							<span style="color:red">*广电总局标准12位码</span>
						</td>
					</tr>
<!--					<tr style="height: 25px" align="left">-->
<!--						<td width="139" style="width: 130">-->
<!--							影片短名称:-->
<!--						</td>-->
<!--						<td>-->
<!--							<input type="text" name="filmmessage.filmename" />-->
<!--						</td>-->
<!--						<td align="left"><font color="red">*不超过15个汉字（用于手机读取，如片名小于15字，可不填）</font></td>-->
<!--					</tr>-->
					<tr style="height: 25px" align="left">
						<td>
							片长:
						</td>
						<td>
							<input type="text" name="filmmessage.filmtime" />分钟
						</td>
					</tr>
					<tr align="left">
						<td>
							导演:
						</td>
						<td>
							<input type="text" name="filmmessage.direct" />
							格式:张艺谋,冯小刚,***
						</td>
						<td align="left"><font color="red">*不超过20个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							演员:
						</td>
						<td>
							<input type="text" name="filmmessage.player" />
							格式:刘德华,梁朝伟,***
						</td>
						<td align="left"><font color="red">*不超过200个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							编剧:
						</td>
						<td>
							<input type="text" name="filmmessage.writer" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							评分:
						</td>
						<td>
							<input type="text" name="filmmessage.grade" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影片类型:
						</td>
						<td>
							<s:iterator id="list" value="#request.genremessageList" status="i">
								<input type="checkbox" name="filmmessage.genreno" value="${list.id}"/>${list.title}&nbsp;&nbsp;&nbsp;&nbsp;
							</s:iterator>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							版本类型:
						</td>
						<td>
							<s:select name="filmmessage.version_type"
								list="#{1:'imax',2:'2D',3:'3D'}" listKey="key" listValue="value"
								headerKey="" headerValue="请选择" theme="simple"></s:select>

						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							上映时间:
						</td>
						<td>
						 <input id="startDate"  name="filmmessage.showtime"  readonly="readonly" class="Wdate" type="text"  class="qu_inp1" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>		
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="131" style="width: 130">
							国家:
						</td>
						<td>
							<select name="filmmessage.parea">
								<s:iterator id="parea" value="#request.nationList" status="i">
									<option value="${parea.id}">
										${parea.name}
									</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影片简介:
						</td>
						<td>
							<label>
								<textarea name="filmmessage.filmsis" cols="45" rows="5"></textarea>
							</label>
						</td>
						<td align="left"><font color="red">*不超过2000个汉字</font></td>
					</tr>
<!--					<tr style="height: 25px" align="left">-->
<!--						<td>-->
<!--							剧情简介:-->
<!--						</td>-->
<!--						<td>-->
<!--							<label>-->
<!--								<textarea name="filmmessage.getshow" cols="45" rows="5"></textarea>-->
<!--							</label>-->
<!--						</td>-->
<!--						<td align="left"><font color="red">*不超过200个汉字</font></td>-->
<!--					</tr>-->

<!--					  <tr align="left">-->
<!--									<td>-->
<!--									    <input name="text" id="upload_images" type="button" />-->
<!--									</td>-->
<!--								    <td>-->
<!--									   <img src="<%=basePath%>common/swfupload/images/alum_def_img.jpg" id="imgtwo" width="130px;" height="100px;"/>-->
<!--									   进度条 -->
<!--									  <div style="width:130px;position: absolute;display:none" id="upload_progresses" div-css="10">-->
<!--		    						  <div id="upload_progress_bges"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>-->
<!--		    						  </div>-->
<!--								    </td>-->
<!--								    <td align="left"><font color="red">*最小规格尺寸（1440*762px），用于网站端首页大图展示</font></td>-->
<!--						 </tr>-->

					  <tr align="left">
							<td>
							    <input name="text" id="upload_image" type="button" />
							</td>
						    <td>
							   <img src="<%=basePath%>common/swfupload/images/alum_def_img.jpg" id="imgone" width="140px;" height="189px;"/>
							  <!-- 进度条 -->
							  <div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
    						  <div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
    						  </div>
						    </td>
						    <td align="left"><font color="red">*规格尺寸（140*189px）等比5:7的图片</font></td>
					   </tr>
						
					<tr>
						<td colspan="4">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" onclick="doSave1()" class="word_ft9" />
							<input type="button" onclick="javascript:window.location='<%=basePath%>filmmanage/filmmanageQuery.jsp'" class="word_ft8" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
