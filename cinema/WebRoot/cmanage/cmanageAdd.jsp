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
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/handlers.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath %>common/css/page.css" />
		<script type="text/javascript">
		$(document).ready(function(){
		 var upload = new SWFUpload({
				upload_url: "<%=basePath%>manage/cmanage!upload.action", //后台处理路径
				flash_url : "<%=basePath%>common/swfupload/swf/swfupload.swf",  //flash加载路径
				file_size_limit : "5 MB", //文件大小限制
				file_upload_limit : "0",  //允许上传文件的数量,0为无限制
				file_queue_limit : "0",   //上传队列允许等待文件的数量
				file_types : "*.jpg;", //文件类型
				file_types_description : "Image Files", //文件类型说明		
				file_post_name: 'file', //文件名称,默认为Filedata，Linux下只能用默认名称
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError, //出错时执行的函数
				file_dialog_complete_handler : function(numFilesSelected, numFilesQueued){
					if(numFilesSelected>1){
						alert("只能选择一张图片");
					}else{
						this.startUpload();
					}
				},
				upload_start_handler : uploadStart,
				upload_error_handler : uploadError,
				
				//处理进度条
				upload_progress_handler : function (file, bytesLoaded, bytesTotal){
					var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
					  $('#upload_progresse').show();
					  $('#upload_progress_bge').css({'width': percent + '%'});
					  $('#upload_progress_bge').html("<font color='#FFFFFF'>"+percent + '%'+"</font>");
				},
				
				//清空进度条
				upload_complete_handler : function (file){
					this.startUpload();
			        $('#upload_progress_bge').css({'width': '0%'});
			 		$('#upload_progress_bge').html('');
				},
				
				//上传成功回调函数
			    upload_success_handler : function(file,result){
					var img = "<%=basePath%>upload";
					jQuery("#img").attr("src",img+result);
					jQuery("#cmanageImg").val(result);
				},				
				
				//关于按钮的一些属性设置
				button_placeholder_id : "upload_images", //绑定按钮的id
				button_text:"点此上传图片",
				button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
				button_width: 80,
				button_height: 22,
				button_cursor: SWFUpload.CURSOR.HAND, //鼠标样式
				button_action:SWFUpload.BUTTON_ACTION.SELECT_FILE, //单文件上传
				button_image_url:'<%=basePath%>common/swfupload/images/anniu.jpg'
			});
			
			var uploadMappic = new SWFUpload({
				upload_url: "<%=basePath%>manage/cmanage!uploadMappic.action", //后台处理路径
				flash_url : "<%=basePath%>common/swfupload/swf/swfupload.swf",  //flash加载路径
				file_size_limit : "5 MB", //文件大小限制
				file_upload_limit : "0",  //允许上传文件的数量,0为无限制
				file_queue_limit : "0",   //上传队列允许等待文件的数量
				file_types : "*.jpg;", //文件类型
				file_types_description : "Image Files", //文件类型说明		
				file_post_name: 'file', //文件名称,默认为Filedata，Linux下只能用默认名称
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError, //出错时执行的函数
				file_dialog_complete_handler : function(numFilesSelected, numFilesQueued){
					if(numFilesSelected>1){
						alert("只能选择一张图片");
					}else{
						this.startUpload();
					}
				},
				upload_start_handler : uploadStart,
				upload_error_handler : uploadError,
				
				//处理进度条
				upload_progress_handler : function (file, bytesLoaded, bytesTotal){
					var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
					  $('#upload_progresse_mappic').show();
					  $('#upload_progress_bge_mappic').css({'width': percent + '%'});
					  $('#upload_progress_bge_mappic').html("<font color='#FFFFFF'>"+percent + '%'+"</font>");
				},
				
				//清空进度条
				upload_complete_handler : function (file){
					this.startUpload();
			        $('#upload_progress_bge_mappic').css({'width': '0%'});
			 		$('#upload_progress_bge_mappic').html('');
				},
				
				//上传成功回调函数
			    upload_success_handler : function(file,result){
					var img = "<%=basePath%>upload";
					jQuery("#img_mappic").attr("src",img+result);
					jQuery("#cmanageImg_mappic").val(result);
				},				
				
				//关于按钮的一些属性设置
				button_placeholder_id : "upload_images_mappic", //绑定按钮的id
				button_text:"点此上传地图图片",
				button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
				button_width: 100,
				button_height: 22,
				button_cursor: SWFUpload.CURSOR.HAND, //鼠标样式
				button_action:SWFUpload.BUTTON_ACTION.SELECT_FILE, //单文件上传
				button_image_url:'<%=basePath%>common/swfupload/images/anniu.jpg'
			});
			$("#addForm").validate( {
	rules : {
		"placeinfo.placeno" : {
			maxlength : 8
		},
		"placeinfo.placename" : {
			required : true,
			maxlength : 20
		},
		"placeinfo.cinemalinkid" : {
			required : true,
			maxlength : 10
		},
		"placeinfo.postion":{
			maxlength:30
		},
		"placeinfo.phone":{
			required : true,
			maxlength:13
		},
		"placeinfo.information":{
			maxlength:500
		},
		"placeinfo.business_hours":{
			maxlength:100
		},
		"placeinfo.route":{
			maxlength:500
		},
		"placeinfo.cityno":{
			required : true,
			maxlength:10
		},
		"uploadimg":{
			required : true
		},
		"placeinfo.weibolink":{
			required : true,
			url:true,
			maxlength:100
		},
		"placeinfo.address":{
			maxlength:50
		},
		"mapimg":{
			required : true
		}
	},
	messages : {
			"placeinfo.placeno" : {
				required:"",
				maxlength : ""
			},
			"placeinfo.placename" : {
				required:"",
				maxlength : ""
			},
			"placeinfo.postion" : {
				maxlength : ""
			},
			"placeinfo.cinemalinkid":{
				required:"",
				maxlength:""
			},
			"placeinfo.postion":{
				maxlength:""
			},
			"placeinfo.phone":{
				required:"",
				maxlength:""
			},
			"placeinfo.information":{
				maxlength:"长度1-5000字符!"
			},
			"placeinfo.business_hours":{
				maxlength:"长度1-100字符!"
			},
			"placeinfo.route":{
				maxlength:"长度1-500字符!"
			},
			"placeinfo.cityno":{
				required : "不能为空!"
			},
			"uploadimg":{
				required : ""
			},
			"placeinfo.weibolink":{
				required : "不能为空!",
				url : "请输入正确的微博网址!",
				maxlength:"长度1-100字符!"
			},
			"placeinfo.address":{
				maxlength:"长度1-50字符!"
			}
		}
	});
	$("#placenoSP").text("");
    $("#placeno").blur(function(){
  		var name=$("#placeno").val();
  		if(name==""||name==null)  {
  			$("#placenoSP").text("请输入影院ID!");
  			return;  		
  		}
  		if(name.length>8){
  			$("#placenoSP").text("请输入正确的影院ID!");
  			return;
  		}else{
  		    $.ajax({
  		    	url:"/cinema/manage/cmanage!isExist.action",
  		    	type:"POST",
  		    	dataType:"json",
  		    	data:{"placeno":name},
  		    	success:function(data){
  		    		if(data){
  		    			$("#placenoSP").text("影院ID可用！");
  		    			return;
  		    		}else{  		    			
  		    			$("#placenoSP").text("影院ID已存在！");  
  		    			$("#placeno").val("");
  		    			$("#placeno").focus();
  		    			return;
  		    		}
  		    	},
  		    	error:function(){
  		    	}  		    
  		    });
  		}
  	 });
$.fn.insertAtCaret = function (tagName) {
return this.each(function(){
if (document.selection) {
//IE support
this.focus();
sel = document.selection.createRange();
sel.text = tagName;
this.focus();
}else if (this.selectionStart || this.selectionStart == '0') {
//MOZILLA/NETSCAPE support
startPos = this.selectionStart;
endPos = this.selectionEnd;
scrollTop = this.scrollTop;
this.value = this.value.substring(0, startPos) + tagName + this.value.substring(endPos,this.value.length);
this.focus();
this.selectionStart = startPos + tagName.length;
this.selectionEnd = startPos + tagName.length;
this.scrollTop = scrollTop;
} else {
this.value += tagName;
this.focus();
}
});
};
$("#add_nbsp").click(function(){$('#a_informator').insertAtCaret('§')});
$("#add_br").click(function(){$('#a_informator').insertAtCaret('<br/>')});
});
</script>
<script>



function doSave() {
	$("#placeno").blur();
	var img = $("#cmanageImg").val();
	var pic = $("#cmanageImg_mappic").val();
	var a_informator = $("#a_informator").val();//影院详情
	var a_business_hours = $("#a_business_hours").val();//营业时间
	var a_route = $("#a_route").val();//交通资讯
	var a_address = $("#a_address").val();//影院地址 
	if(img==null||img==""){
		alert("请上传影院图片!");
		return;
	}
	if(pic==null||pic==""){
		alert("请上传地图图片!");
		return;
	}
	if(a_informator==null||a_informator==""){
		alert("影院详情不能为空!");
		return;
	}
	if(a_business_hours==null||a_business_hours==""){
		alert("营业时间不能为空!");
		return;
	}
	if(a_route==null||a_route==""){
		alert("交通资讯不能为空!");
		return;
	}
	if(a_address==null||a_address==""){
		alert("影院地址不能为空!");
		return;
	}
	var Validator = $("#addForm").validate();
	if (Validator.form()) {
		document.forms[0].submit();
	}
}
function goBack_cmanage(){
		var url = "/cinema/manage/cmanage!queryCmanage.action";
		location.href = url;
}
</script>
<script type="text/javascript">
		$(document).ready(function(){
			setframeifHeight('add');
		});
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	</head>

	<body>
		<form action="/cinema/manage/cmanage!saveCmanage.action"
			method="post" name="addForm" id="addForm"
			enctype="multipart/form-data" target="_self">
			<input type="hidden" name="placeinfo.image" id="cmanageImg" value="" />
			<input type="hidden" name="placeinfo.mappic" id="cmanageImg_mappic"
				value="" />
			<input type="hidden" id="msg" value="${msg }" />
			<input type="hidden" name="placeinfo.id" value="${placeinfo.id }" />
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
					<tr style="height: 25px" align="left">
						<td width="13%">
							城市名称:
						</td>
						<td align="left">
					
							<select name="placeinfo.cityno">
								<option value="">
									全部
								</option>
								<s:iterator value="#request.lcity" id="city" status="i">
									<option value="<s:property value="#city.Id"/>">
										<s:property value="#city.cityName" />
									</option>
								</s:iterator>
							</select>
						</td>
						<td align="left"><font color="red">*影院所在城市</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影院ID:
						</td>
						<td align="left">
							<input name="placeinfo.placeno" type="text" id="placeno" />
							<span id="placenoSP" style="color: red"></span>
						</td>
						<td align="left"><font color="red">*广电总局标准8位码</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影院名称:
						</td>
						<td width="21%" align="left">
							<input name="placeinfo.placename" type="text" />
						</td>
						<td align="left"><font color="red">*不超过20个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							CINEMALINKID:
						</td>
						<td align="left">
							<input name="placeinfo.cinemalinkid" id="cma" type="text" />
						</td>
						<td align="left"><font color="red">*不超过10字符</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							电话:
						</td>
						<td align="left">
							<input name="placeinfo.phone" value="" type="text" />
						</td>
						<td align="left"><font color="red">*不超过100个汉字</font></td>
					</tr>
					<!--tr style="height: 25px">
						<td>
							经纬度:
						</td>
						<td align="left">
							<input name="placeinfo.postion" value="" type="text" /><span style="color:red">*格式：经度-纬度</span>
						</td>
					</tr>-->
					<tr style="height: 25px" align="left">
						<td>
							微博地址:
						</td>
						<td align="left">
							<input name="placeinfo.weibolink" value="" type="text" />
							<span style="color: red">*格式：http://weibo.com/</span>
						</td>
						<td align="left"><span style="color: red">*影院微博url（微博地址）</span></td>
					</tr>
					<tr align="left">
						<td>
							<input name="text" id="upload_images" type="button" />
						</td>
						<td>
							<img src="<%=basePath%>common/swfupload/images/alum_def_img.jpg"
								id="img" width="130px;" height="100px;" />
							<!-- 进度条 -->
							<div style="width: 130px; position: absolute; display: none"
								id="upload_progresse" div-css="10">
								<div id="upload_progress_bge"
									style="background: #8BABCE; width: 20%; height: 20px; float: left; margin-top: 35px;"></div>
							</div>
							<span id="imgSP" style="color: red"></span>
						</td>
					</tr>
					<tr align="left">
						<td>
							<input name="text" id="upload_images_mappic" type="button" />
						</td>
						<td>
							<img src="<%=basePath%>common/swfupload/images/alum_def_img.jpg"
								id="img_mappic" width="130px;" height="100px;" />
							<!-- 进度条 -->
							<div style="width: 130px; position: absolute; display: none"
								id="upload_progresse" div-css="10">
								<div id="upload_progress_bge_mappic"
									style="background: #8BABCE; width: 20%; height: 20px; float: left; margin-top: 35px;"></div>
							</div>
							<span id="picSP" style="color: red"></span>
							<td align="left"><span style="color: red">*以图片形式提交</span></td>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影院详情:
						</td>
						<td align="left">
							<textarea name="placeinfo.information" cols="42" rows="9" id="a_informator"></textarea>
						</td>
						<td><input type="button" value="空格" id="add_nbsp"/><input type="button" value="换行" id="add_br"/></td>
						<td align="left"><font color="red">*不超过5000个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							营业时间:
						</td>
						<td align="left">
							<textarea name="placeinfo.business_hours" cols="42" rows="4" id="a_business_hours"></textarea>
						</td>
						<td align="left"><font color="red" id="a_business_hours">*不超过100个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							交通资讯:
						</td>
						<td align="left">
							<textarea name="placeinfo.route" cols="42" rows="4" id="a_route"></textarea>
						</td>
						<td align="left"><font color="red">*不超过500个汉字</font></td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影院地址:
						</td>
						<td align="left">
							<textarea name="placeinfo.address" cols="42" rows="4" id="a_address"></textarea>
						</td>
						<td align="left"><font color="red">*不超过50个汉字</font></td>
					</tr>
					<tr>
							<td>
							会员信息提示:
						</td>
						<td align="left">
							<textarea name="placeinfo.vipalert" cols="42" rows="3" id="vipalert"></textarea>
						</td>
						<td align="left"><font color="red">*不超过20个汉字</font></td>
					</tr>
					<tr>
						<td colspan="6">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
							<input type="button" onclick="doSave()" class="word_ft9" />
							<input type="button" onclick="goBack_cmanage()" class="word_ft8" />
						</td>
					</tr>
					<tr>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="6">&nbsp;</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
