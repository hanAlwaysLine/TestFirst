//回显操作
$(document).ready(function(){
	//角色ID
	var userid = $("#userid").val();
	var url ="/cinemamanage/user!checkUser.action";
	var params = {
			userid:userid
	}
	jQuery.post(url,params,callBack,"json");
});
//回调函数
function callBack(data){
	var obj  = eval(data);
	$.each(obj,function(index,o){
		$(":checkbox[roleid="+o.roleid+"]").attr("checked",true);
	});
}