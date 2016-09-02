$(document).ready(function(){
	$("#queryForm").submit();
});

/**
 * 根据会员卡号查询
 * @return
 */
function queryByVipno(){
//	$("#payorder").val("");
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	 if(endDate<startDate){
		 alert("结束时间不能小于开始时间!");
		 return;
	 }
	$("#pageNo").val("1");
	$("#queryForm").submit();
}

/**
 * 根据订单号查询
 * @return
 */
function queryByPay(){
	//$("#vipno").val("");
	 var startDate=$("#startDate").val();
	 var endDate=$("#endDate").val();
	 if(endDate<startDate){
		 alert("结束时间不能小于开始时间!");
		 return;
	 }
	$("#pageNo").val("1");
	$("#queryForm").submit();
}
