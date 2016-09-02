$(document).ready(function(){
	$("#queryForm").submit();
});
function addUser(){

	var url = "/cinema/user!goAddUser.action";
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>用户管理-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(450, 300);
}