$(document).ready(function(){
	$("#according_id").focus();	
$("#editForm").validate( {
rules : {
	"advertise.cityno":{
		required : true
	}
	,
	"advertise.according_id":{
		required : true
	}
},
messages : {
		"advertise.cityno" : {
			required : "请选择!"
			
		},
		"advertise.according_id" : {
			required : "不能为空!"
		
		}
	}
})
var msg=$("#msg").val();
if(msg!=""&&msg!="null")
{
    alertResult(msg, 0);
}
}
)




function doEdit() {
//	var valid=$("#valid").val();
//	if(valid==1){		
//		$("show").html("");
//		$("#show").html("<font color='red'>请修改可用ID</font>");
//		return;
//	}else if(valid==""){
//		$("show").html("");
//		$("#show").html("<font color='red'>正在校验中,请等待...</font>");
//		return;
//	}else{	
//		var Validator = $("#editForm").validate();
//		if (Validator.form()) {
//			document.forms[0].submit();
//		}
//	}
	
	var Validator = $("#editForm").validate();
	if (Validator.form()) {
		var according_id=$("#according_id").val();
		var a_city = $("#city").val();
		url = "/cinema/advertisemanage/advertise!validat.action?jsonString="+according_id+"&a_city="+a_city;
		jQuery.post(url, null,show);
	}
}

function validat(){
	var according_id=$("#according_id").val() ;
	url = "/cinema/advertisemanage/advertise!validat.action?jsonString="+according_id;
	jQuery.post(url, null,show);
	
}

function show(data){
	  if(data=='T'){
		  $("#valid").val("0");
		  $("#show").html("<font color='green'>可以使用</font>"); 
		  var Validator = $("#editForm").validate();
			if (Validator.form()) {
				document.forms[0].submit();
			}
	    }else if(data=="E"){
	    	$("#valid").val("1");
	    	$("#show").html("<font color='red'>影片ID未发布!</font>");
	    }else if(data=="C"){
	    	$("#show").html("<font color='red'>首页轮播图数量超过限制!</font>");
	    }else{
	    	$("#valid").val("1");
	    	$("#show").html("<font color='red'>影片ID不存在!</font>");
	    }
}