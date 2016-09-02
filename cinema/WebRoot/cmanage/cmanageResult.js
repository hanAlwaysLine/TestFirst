$(document).ready(function(){
	$("#placename",window.parent.document).focus();
});
/**
 * 删除方法
 * @param id 影院ID
 * @return
 */
function del(id){
	var msg = "您确认要删除?请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/manage/cmanage!delCmanage.action";
		params = {
				id:id
		}
		jQuery.post(url,params,callBackData,'json');
	});
}

/**
 * 删除方法回调函数
 * @param data
 * @return
 */
function callBackData(data){
	var flag = eval(data.flag);
	if(flag){
		alertResult("删除成功!",null);
	}else{
		alertResult("删除失败!",null);
	}
}

/**
 * 发布影院信息
 * @param id 影院ID
 * @return
 */
function updateUsable(id){
	var msg = "您确认要发布?请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/manage/cmanage!updateUsable.action";
		params = {
				id:id
		}
		jQuery.post(url,params,callBackUpdate,'json');
	});
}

/**
 * 发布影院回调函数
 * @param data
 * @return
 */
function callBackUpdate(data){
	var uFlag = eval(data.uFlag);
	if(uFlag){
		alertResult("发布成功!",null);
	}else{
		alertResult("发布失败!",null);
	}
}

/**
 * 修改影院信息
 * @param id 影院ID
 * @return
 */
function goEdit(id){
	var url = "/cinema/manage/cmanage!goedit.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影院-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(750, 500);
}

/**
 * 
 * @param id
 * @param placename
 * @return
 */
function goDetailedinformation(id,placename){
	var url = "/cinema/manage/cmanage!goDetailedinformation.action?id="+id+"&placename="+placename;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影院-明细</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(800, 400);
}

/**
 * 查询影院排期信息
 * @param placename 影院
 * @return
 */
function queryFeature(id,cityno){
	var url = "/cinema/featuremanage/featuremanage!query.action?placeno="+id+"&cityno="+cityno;
	var moudleID = "menu_31";
	parent.parent.zmueu(parent.parent.$("#Menu_30"));
	parent.parent.gotoul(moudleID,url);
}

