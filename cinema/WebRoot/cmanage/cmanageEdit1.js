$(document).ready(function(){
	$("#editForm").validate( {
	rules : {
		"placeinfo.placeno" : {
			required : true,
			maxlength : 20
		},
		"placeinfo.placename" : {
			required : true,
			maxlength : 30
		},
		"placeinfo.cinemalinkid" : {
			required : true,
			maxlength : 10
		},
		"placeinfo.postion":{
			required : true,
			maxlength:30
		},
		"placeinfo.phone":{
			required : true,
			maxlength:13
		},
		"placeinfo.information":{
			required : true,
			maxlength:5000
		},
		"placeinfo.business_hours":{
			required : true,
			maxlength:100
		},
		"placeinfo.route":{
			required : true,
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
			required : true,
			maxlength:50
		},
		"mapimg":{
			required : true
		}
	},
	messages : {
			"placeinfo.placeno" : {
				required : "不能为空",
				maxlength : "长度为1-20字符!"
			},
			"placeinfo.placename" : {
				required : "不能为空!",
				maxlength : "长度为1-30字符!"
			},
			"placeinfo.postion" : {
				required : "不能为空!",
				maxlength : "长度为1-30字符!"
			},
			"placeinfo.cinemalinkid":{
				required : "不能为空!",
				maxlength:"长度1-10字符!"
			},
			"placeinfo.postion":{
				required : "不能为空!",
				maxlength:"长度1-10字符!"
			},
			"placeinfo.phone":{
				required : "不能为空!",
				maxlength:"长度1-13字符!"
			},
			"placeinfo.information":{
				required : "不能为空!",
				maxlength:"长度1-5000字符!"
			},
			"placeinfo.business_hours":{
				required : "不能为空!",
				maxlength:"长度1-100字符!"
			},
			"placeinfo.route":{
				required : "不能为空!",
				maxlength:"长度1-500字符!"
			},
			"placeinfo.cityno":{
				required : "不能为空!"
			},
			"uploadimg":{
				required : "不能为空!"
			},
			"placeinfo.weibolink":{
				required : "不能为空!",
				url : "请输入正确的微博网址!",
				maxlength:"长度1-100字符!"
			},
			"placeinfo.address":{
				required : "不能为空!",
				maxlength:"长度1-50字符!"
			},
			"mapimg":{
				required : "不能为空!"
			}
		}
	})
	var msg=$("#msg").val();
	if(msg!=""&&msg!="null"){
	    alertResult(msg, 0);
	}
	var upload = new SWFUpload({
		upload_url: UPLOAD_PATH+"manage/cmanage!upload.action", //后台处理路径
		flash_url : UPLOAD_PATH+"common/swfupload/swf/swfupload.swf",  //flash加载路径
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
			var img = UPLOAD_PATH+"upload";
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
		button_image_url:UPLOAD_PATH+'common/swfupload/images/anniu.jpg'
	});
	
	
	var uploadMappic = new SWFUpload({
		upload_url: UPLOAD_PATH+"manage/cmanage!uploadMappic.action", //后台处理路径
		flash_url : UPLOAD_PATH+"common/swfupload/swf/swfupload.swf",  //flash加载路径
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
			var img = UPLOAD_PATH+"upload";
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
		button_image_url:UPLOAD_PATH+'common/swfupload/images/anniu.jpg'
	});
	$("#placenoSP").text("");
    $("#placeno").blur(function(){
  		var name=$("#placeno").val();
  		var n_place_no = $("#place_NO").val();

			if(name==""||name==null)  {
	  			$("#placenoSP").text("请输入影院ID！");   		
	  		}else{
	  			if(name!=n_place_no){
		  		    $.ajax({
		  		    	url:"/cinema/manage/cmanage!isExist.action",
		  		    	type:"POST",
		  		    	dataType:"json",
		  		    	data:{"placeno":name},
		  		    	success:function(data){
		  		    		if(data){
		  		    			$("#placenoSP").text("用户名可用！");
		  		    			return;
		  		    		}else{  		    			
		  		    			$("#placenoSP").text("用户名已存在！");  
		  		    			$("#placeno").val("");
		  		    			$("#placeno").focus();
		  		    			return;
		  		    		}
		  		    	},
		  		    	error:function(){
		  		    	
		  		    	}  		    
		  		    });
	  			}
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
    	$("#add_nbsp").click(function(){$('#u_informator').insertAtCaret('§')});
    	$("#add_br").click(function(){$('#u_informator').insertAtCaret('<br/>')});
})
function doEdit() {
	 $("#placeno").blur();
	 var img = $("#cmanageImg").val();
		var pic = $("#cmanageImg_mappic").val();
		if(img==null||img==""){
			alert("请上传影院图片!")
			return;
		}
		if(pic==null||pic==""){
			alert("请上传地图图片!")
			$("#picSP").val("请上传地图图片!");
		}
	var Validator = $("#editForm").validate();
	if (Validator.form()) {
		document.forms[0].submit();
	}
}