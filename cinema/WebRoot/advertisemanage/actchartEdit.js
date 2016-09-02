$(document).ready(function(){
	$("#according_id").focus();
$("#editForm").validate( {
rules : {
	"advertise.cityno":{
		required : true
	}
	,
	"advertise.according_id":{
		required : true,
		maxlength:4
	},
	"advertise.locale":{
		required : true,
		maxlength:50
	}
},
messages : {
		"advertise.cityno" : {
			required : "请选择!"
			
		},
		"advertise.according_id" : {
			required : "不能为空!",
			maxlength: "请输入1-4个字符!"
		
		},
		"advertise.locale":{
			required : "不能为空!",
			maxlength: "请输入1-50个字符!"
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



//根据city查询区域内的所有影院
function selcinema(){
	var city=$("select option:selected").val() ;
	url = "/cinema/advertisemanage/advertise!cinemaList.action?jsonString="+city;
	jQuery.post(url, null,insercinema,'json');
}

//ajax往下拉菜单中添加option
function insercinema(data){
	$("#cinema").empty();
	$("#cinema").prepend("<option value=''>请选择</option>");   //为Select插入一个Option(第一个位置)
	var list=eval(data);
	var i=0;
	while(list[i]!=null){
		$("#cinema").append("<option value='"+list[i].id+"'>"+list[i].placename+"</option>");   //为Select追加一个Option(下拉项)
		i=i+1;
	}
}	


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
	var according_id=$("#according_id").val() ;
	url = "/cinema/advertisemanage/advertise!advervalidat.action?jsonString="+according_id+"&type="+3;
	jQuery.post(url, null,show);
}

function validat(){
	var according_id=$("#according_id").val() ;
	url = "/cinema/advertisemanage/advertise!advervalidat.action?jsonString="+according_id+"&type="+3;
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
	   }else if(data=='F'){
		   $("#valid").val("1");
	      $("#show").html("<font color='red'>活动ID不存在或未发布!</font>");
	    }else {
	    	$("#valid").val("1");
		    $("#show").html("<font color='red'>此ID对应的类型不是广告图或未发布!</font>");	
		}
}