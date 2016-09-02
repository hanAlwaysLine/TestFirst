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
);



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


function doSave() {
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
	url = "/cinema/advertisemanage/advertise!advervalidat.action?jsonString="+according_id+"&type="+2;
	jQuery.post(url, null,show);
	
}

function validat(){
	var according_id=$("#according_id").val() ;
	url = "/cinema/advertisemanage/advertise!advervalidat.action?jsonString="+according_id+"&type="+2;
	jQuery.post(url, null,show);
	
}

function show(data){
	  if(data=='T'){
		  $("#valid").val("0");
		  $("#show").html("<font color='green'>可以使用</font>"); 
		  var city = $("#city").val();
		  var advertisement_type = $("#advertisement_type").val();
		  url = "/cinema/advertisemanage/advertise!count_advert.action?a_city="+city+"&advertisement_type="+advertisement_type;
		  jQuery.post(url, null,count_advert);//回调函数，判断同城市，同类型，是否大于五张广告
	   }else if(data=='F'){
		   $("#valid").val("1");
	      $("#show").html("<font color='red'>活动ID不存在或未发布!</font>");
	    }else {
	    	 $("#valid").val("1");
		    $("#show").html("<font color='red' style='size:-3;'>此ID对应的类型<br/>不是广告图或未发布!</font>");	
		}
}

function count_advert(data){
	if(data=="Y"){
		var Validator = $("#editForm").validate();
		if (Validator.form()) {
			document.forms[0].submit();
		}
	}else{
		$("#count_t").html("<font color='red'>同城市，同类型的广告发布达到上限!</font>");
	}
}