function goAdd(){
	var url = "/cinema/advertisemanage/advertise!addadvertise.action?type="+2;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>广告-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(550, 220);
}

//删除
function deladvertise(id){
	var msg = "您真的确定要删除吗？请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/advertisemanage/advertise!deladvertise.action";
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


function goEdit(id){
	var url = "/cinema/advertisemanage/advertise!goedit.action?id="+id+"&type="+2;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>广告-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(550, 220);
}

/**
 * 位置设置
 * @param id
 * @return
 */


function posAdvertise(id){
	var url = "advertisemanage/adchartPosition.jsp?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>位置-设置</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(350,100);
}

function goEditImage(id,advertisement_type){						
	var url = "/cinema/advertisemanage/advertise!editactimg.action?activity.activity_id="+id+"&advertisement_type="+advertisement_type;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>海报-编辑</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(550, 300);
}