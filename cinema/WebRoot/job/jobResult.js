$(document).ready(function(){
	$("#activity_name",window.parent.document).focus();
});

/**
 * 删除
 * @param id
 * @return
 */
function delActivity(id) {
	var msg = "删除此招聘信息,请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/jobmanage/job!delActivity.action";
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
 * 发布
 * @param id
 * @return
 */
function release(id) {
	var msg = "确定要发布吗？请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/activitymanage/activity!relActivity.action";
		var params = {
				id:id
		};
		jQuery.post(url, params,relCall ,'json');
	}); 
}

function relCall(data) {
	var rs = eval(data.modifySuc);
	var msg;
	if (rs) {
		 msg="发布成功!";
	} else {
		 msg="发布失败,请重试!";
	}
	alertResult(msg,null);
}

/**
 * 修改页
 * @param id
 * @return
 */

function goEdit(id){
	var url = "/cinema/jobmanage/job!goedit.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>招聘-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(550, 300);
}


