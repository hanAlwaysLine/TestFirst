$(document).ready(function(){
$("#editForm").validate( {
	rules : {
	"filmmessage.filmno" : {
		required : true,
		rangelength:[12,12]
	},
	"filmmessage.filmname" : {
		required : true,
		maxlength : 20
	},
//	"filmmessage.filmename" : {
//		required : true,
//		maxlength : 15
//	},
	"filmmessage.direct" : {
		required : true
	},
	"filmmessage.player" : {
		required : true
	},
//	"filmmessage.genreno" : {
//		required : true
//	},
	"filmmessage.writer" : {
		required : true
	},
	"filmmessage.version_type": {
		required : true
	},
	"filmmessage.showtime" : {
		required : true
	},
	"filmmessage.parea" : {
		required : true
	},
	"filmmessage.filmsis" : {
		required : true,
		maxlength : 2000
	},
//	"filmmessage.getshow" : {
//		required : true,
//		maxlength : 200
//	}

},
messages : {
		"filmmessage.filmno" : {
			required : "不能为空!",
			rangelength : "长度必须为12位字符!"
		},
		"filmmessage.filmname" : {
			required : "不能为空!",
			maxlength : "长度为1-20字符!"
		},
		"filmmessage.filmename" : {
			required : "不能为空!",
			maxlength : "长度为1-15字符!"
		},
//		"filmmessage.genreno" : {
//			required : "不能为空!"
//		},
		"filmmessage.direct" : {
			required : "不能为空!"
		},
		"filmmessage.player" : {
			required : "不能为空!"
		},
		"filmmessage.writer" : {
			required : "不能为空!"
		},
		"filmmessage.version_type": {
			required : "不能为空!"
		},
		"filmmessage.showtime" : {
			required : "不能为空!"
		},
		"filmmessage.parea" : {
			required : "不能为空!"
		},
		"filmmessage.filmsis" : {
			required : "不能为空!",
			maxlength : "长度为1-2000字符!"
		},
		"filmmessage.getshow" : {
			required : "不能为空!",
			maxlength : "长度为1-200字符!"
		}
	}
})
	var msg=$("#msg").val();
	if(msg!=""&&msg!="null")
	{
	    alertResult(msg, 0);
	}
	
	var upload = new SWFUpload({
		upload_url: UPLOAD_PATH+"filmmanage/filmmanage!uploadone.action", //后台处理路径
		flash_url : UPLOAD_PATH+"common/swfupload/swf/swfupload.swf",  //flash加载路径
		file_size_limit : "50 MB", //文件大小限制
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
	    	var img = imgPath;
	    	jQuery("#imgone").attr("src",img+result);
			jQuery("#image").val(result);
		},				
		
		
		//关于按钮的一些属性设置
		button_placeholder_id : "upload_image", //绑定按钮的id
		button_text:"点此上传图片",
		button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
		button_width: 80,
		button_height: 22,
		button_cursor: SWFUpload.CURSOR.HAND, //鼠标样式
		button_action:SWFUpload.BUTTON_ACTION.SELECT_FILE, //单文件上传
		button_image_url:UPLOAD_PATH+'common/swfupload/images/anniu.jpg'
	})


	var uploads = new SWFUpload({
		upload_url: UPLOAD_PATH+"filmmanage/filmmanage!uploadone.action", //后台处理路径
		flash_url : UPLOAD_PATH+"common/swfupload/swf/swfupload.swf",  //flash加载路径
		file_size_limit : "50 MB", //文件大小限制
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
			 $('#upload_progresses').show();
			  $('#upload_progress_bges').css({'width': percent + '%'});
			  $('#upload_progress_bges').html("<font color='#FFFFFF'>"+percent + '%'+"</font>");
		},
		
		//清空进度条
		upload_complete_handler : function (file){
			this.startUpload();
			 $('#upload_progress_bges').css({'width': '0%'});
		  		$('#upload_progress_bges').html('');
		},
		
		//上传成功回调函数
	    upload_success_handler : function(file,result){
			var img = imgPath;
	    	jQuery("#imgtwo").attr("src",img+result);
			jQuery("#images").val(result);
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
	})
	appcodeChecked();
});

/**
 * 影片类型赋值
 * 
 * @return
 */
function appcodeChecked() {
	var appcode = $("#genreno").val();
	if (appcode != "") {
		var appcodeArray = appcode.split(",");
		$.each(appcodeArray, function(index, Obj) {
			$("input[name='filmmessage.genreno'][value='" + Obj.trim() + "']")
					.attr("checked", true);
		});
	}
}

function doEdit() {
	var update_filmno = $("#u_filmno").val();
	var doU_filmno = $("#do_filmno").val();
	if(update_filmno!=doU_filmno){
		url = "/cinema/filmmanage/filmmanage!addFilmmanageValidate.action?filmno="+doU_filmno;
		jQuery.post(url, null,show);
	}else{
		var Validator = $("#editForm").validate();
		if(Validator.form()){
			document.forms[0].submit();
		}
	 }
}

function show(data){
	  if(data=='true'){
		  $("#show").html("<font color='green'>可以使用!</font>"); 
		  var Validator = $("#editForm").validate();
			if(Validator.form()){
				document.forms[0].submit();
			}
	   }else{
	      $("#show").html("<font color='red'>此影片编码已存在请重新输入!</font>");
	      $("#do_filmno").focus();
	   }
}
