
/**
 * 查看订单明细
 * @param id
 * @param placename
 * @return
 */
function orderformDetail(orderno){
	var url = "/cinema/orderformanage/orderselect!userOrderDetail.action?orderno="+orderno;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>订单明细</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(1000, 700);
}

/**
 * 变更订单领取状态
 * @return
 */
function receiveOp(orderno){
	var msg = "是否执行领取操作，请确认!";
	parent.parent.Boxy.confirm(msg,function(){
		var url = "/cinema/orderformanage/orderselect!receiveOp.action";
		var params = {
				orderno : orderno
		};
		jQuery.post(url, params, receiveOpCallBack, 'json');
	});
}

/**
 * 变更订单领取状态回调
 */
function receiveOpCallBack(data){
	var result = data.result;
	var msg = "";
	if(result){
		msg = "领取成功!";
	}else{
		msg = "领取失败!";
	}
	alertResult(msg,null);
}