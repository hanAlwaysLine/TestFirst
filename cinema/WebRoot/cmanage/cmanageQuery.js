$(document).ready(function(){
	$("#queryForm").attr("target","result");
	$("#queryForm").submit();
});

/**
 * 跳转添加页
 * @return
 */
function goAdd(){
	var url = "/cinema/manage/cmanage!addcmanage.action";
	location.href = url;
}

/**
 * 查询方法
 * @return
 */
function sub(){
	$("#queryForm").attr("target","result");
	$("#pageNo").val("1");
	$("#queryForm").submit();
}