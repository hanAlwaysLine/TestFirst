$(document).ready(function(){
	$("#name",window.parent.document).focus();
});
function userDel(id) {
	var msg = "您真的确定要删除吗？请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/user!delUser.action";
		var params = {
				id:id
		};
		jQuery.post(url, params,delCall ,'json');
	}); 
}
function delCall(data) {
	var rs = eval(data.modifySuc);
	var msg;
	if (rs) {
		 msg="删除成功!";
	} else {
		 msg="删除失败,请重试!";
	}
	alertResult(msg,null);
}
function updateUser(userid){

	var url = "/cinema/user!goUpdateUser.action?userid="+userid;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>用户管理-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(450, 300);
}
function detailsUser(id){
	var url = "/cinema/user!userDetails.action?userid="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>用户管理-查看</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(450, 250);
	
}