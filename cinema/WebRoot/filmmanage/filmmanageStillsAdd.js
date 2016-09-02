/**
 * 验证方法
 * 
 * @return
 */
$(document).ready(function(){
    	var msg=$("#msg").val();
		if(msg!=""&&msg!="null")
	    {
	        alertResult(msg, 0);
		}
		var filmid= $('#id').val();
		var upload = new SWFUpload({
			upload_url: UPLOAD_PATH+"filmmanage/filmmanage!uploadtwo.action?pianhua=1&id="+filmid, //后台处理路径
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

		
})
function checktype(){	
	    var imagevlue = document.getElementById("image").value;
	    if(imagevlue==""){
			alert("请选择要上传剧照！");
			return;
		}	
		document.forms[0].submit();	
}
