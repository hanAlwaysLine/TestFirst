$(document).ready(function(){
	$("#queryForm").submit();
});

function queryByVipno(){
	$("#pageNo").val("1");
	$("#queryForm").submit();
}


function exportByVipno(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	window.location.href ="/cinema/orderformanage/orderform!madeExport.action?startDate=" +startDate+"&endDate="+endDate;
}