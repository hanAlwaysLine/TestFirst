//回显
$(document).ready(function(){
	//角色ID
	var roleId = $("#roleid").val();
	var url ="hymanage/role!checkRole.action";
	var params = {
			"role.id":roleId
	}
	jQuery.post(url,params,callBack,"json");
});
function callBack(data){
	var obj  = eval(data);
	$.each(obj,function(index,o){
		$("#"+o.competence_id).attr("checked",true);
	});
}
//添加保存
function doSave() {
	var Validator = $("#aFrom").validate();
	if (Validator.form()) {
		document.forms[0].submit();
	}
}
//checkbox父模块与子模块的关联
function findFmenu(obj){
	var fid = $(obj).attr("father");
	var fa = $("#"+fid);
	var son = $(obj).attr("checked");
	if(son==true){
		if(fa.attr("checked")!=true){
			fa.attr("checked",true);
		}
	}
	var sonCheck = $(":checkbox[father="+fid+"][checked]");
	if(sonCheck.length==0){
		fa.attr("checked",false);
	}
	
}
//checkbox子模块与父模块的关联
function findSon(obj){
	var fa = $(obj);
	var fid = fa.attr("id");
	if(fa.attr("checked")==false){
		$(":input[father="+fid+"]").attr("checked",false);
	}
}