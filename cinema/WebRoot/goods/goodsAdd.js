$(document).ready(function(){
$("#editForm").validate( {
rules : {
	"goods.GOODS_NAME" : {
		required : true,
		maxlength : 50,
		remote : {
			type : "post",
			url : "/cinema/goods/goods!isNotExist.action",
			data : {
				jsonString : function(){
					var goodsname = $("#GOODS_NAME").val();
					var obj = new Object();
					obj.goodsname = goodsname;
					obj.oldgoodsname = "";
					return $.toJSON(obj);
				}
			}
		}
	},
	"goods.GOODS_PRICE" : {
		required : true,
		number:true
	},
	"goods.GOODS_COUNT" : {
		required : true,
		number:true
	},
	"goods.GOODS_MEMO" : {
		maxlength : 500
	}
},
messages : {
		"goods.GOODS_NAME" : {
			required : "不能为空!",
			maxlength : "长度为1-100字符!",
			remote : "此卖品名称已存在!"
		},
		"goods.GOODS_PRICE" : {
			required : "不能为空!",
			number : "请输入合法的数字!",
		},
		"goods.GOODS_COUNT" : {
			required : "不能为空!",
			number : "请输入合法的数字!",
		},
		"goods.GOODS_MEMO" : {
			maxlength : "长度为0-500字符!"
		}
	}
})
var msg=$("#msg").val();
if(msg!=""&&msg!="null")
{
    alertResult(msg, 0);
}
var upload = new SWFUpload({
	upload_url: UPLOAD_PATH+"hallmanage/goods!upload.action", //后台处理路径
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
    	jQuery("#img").attr("src",img+result);
		jQuery("#GOODS_PIC").val(result);
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

var upload_h = new SWFUpload({
	upload_url: UPLOAD_PATH+"hallmanage/goods!upload.action", //后台处理路径
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
		  $('#upload_progresse_h').show();
		  $('#upload_progress_bge_h').css({'width': percent + '%'});
		  $('#upload_progress_bge_h').html("<font color='#FFFFFF'>"+percent + '%'+"</font>");
	},
	
	//清空进度条
	upload_complete_handler : function (file){
		this.startUpload();
        $('#upload_progress_bge_h').css({'width': '0%'});
 		$('#upload_progress_bge_h').html('');
	},
	
	//上传成功回调函数
    upload_success_handler : function(file,result){
    	var img = imgPath;
    	jQuery("#img_h").attr("src",img+result);
	},				
	
	
	//关于按钮的一些属性设置
	button_placeholder_id : "upload_images_h", //绑定按钮的id
	button_text:"点此上传横版图片",
	button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
	button_width: 100,
	button_height: 22,
	button_cursor: SWFUpload.CURSOR.HAND, //鼠标样式
	button_action:SWFUpload.BUTTON_ACTION.SELECT_FILE, //单文件上传
	button_image_url:UPLOAD_PATH+'common/swfupload/images/anniu.jpg'
})

})

function doSave() {
	var img = $("#GOODS_PIC").val();
	if(img==null||img==""){
		alert("请上传卖品图片!");
		return;
	}
	var Validator = $("#editForm").validate();
	if (Validator.form()) {
		document.forms[0].submit();
	}
}	