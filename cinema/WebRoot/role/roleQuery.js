$(document).ready(function(){
	$("#queryForm").submit();
});
function getAdd(){
	var url = "/cinema/role!goAddRole.action";
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>角色-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(300, 150);
}
