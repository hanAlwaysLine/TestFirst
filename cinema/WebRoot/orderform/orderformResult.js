
/**
 * 查看订单明细
 * @param id
 * @param placename
 * @return
 */
function orderformDetail(orderno){
	var url = "/cinema/orderformanage/orderform!userOrderDetail.action?orderno="+orderno;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>订单明细</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(1000, 700);
}