$(document).ready(function(){
	$("#title",window.parent.document).focus();
});

/**
 * 删除
 * @param id
 * @return
 */
function delRole(id) {
	var msg = "您真的确定要删除吗？请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/role!delRole.action";
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
/**
 * 更新
 * @param userid
 * @return
 */
function updateRole(id){

	var url = "/cinema/role!goUpdateRole.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>角色-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(300, 150);
}
/**
 * 授权
 * @param userid
 * @return
 */
function sqRole(roleid){
	var url = "/cinema/role!goSqRole.action?id="+roleid;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>角色-授权</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(380, 400);
}
/**
 * 查看详细信息
 * @param id
 * @return
 */
function detailsRole(id){
	var url = "/cinema/role!roleDetails.action?roleid="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>查看-角色</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(280, 100);
	
}