
function checktype(){
	var uploadimg = document.getElementById("uploadimg").value;
	var uploadimgs = document.getElementById("uploadimgs").value;
	if(uploadimg==""){
		parent.parent.parent.boxyAlert("请选择要上传视频！");
		return;
	}
	if(uploadimg!=null&&uploadimg!=""){			
		var filehz = uploadimg.substr(uploadimg.length-3,uploadimg.length);
		if(!(filehz=="flv"||filehz=="mp4")){
			parent.parent.parent.boxyAlert("视频只支持flv|mp4格式！");
			return;
		}
	}
	//校验文件大小
	var filesize = parseInt(document.getElementById("uploadimg").files[0].size/(1024*1024));
	if(filesize>100){
		parent.parent.parent.boxyAlert("上传视频不能大于100MB！");
		return;
	}
	if(uploadimgs==""){
		parent.parent.parent.boxyAlert("请选择要上传预览图！");
		return;
	}
	
	if(uploadimgs!=null&&uploadimgs!=""){			
		var filehzs = uploadimgs.substr(uploadimgs.length-3,uploadimgs.length);
		if(!(filehzs=="jpg")){
			parent.parent.parent.boxyAlert("预览图只支持jpg格式！");
			return;
		}
	}
	
	$('#ty').val(filehz);
	document.forms[0].submit();
	
	
	
}
