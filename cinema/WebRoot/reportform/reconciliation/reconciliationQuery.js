$(document).ready(function(){
	$("#queryForm").submit();
});

/**
 * 导出Excel
 * @return
 */
function showExcel(){
	var startdate = $("#startdate").val();
	var enddate = $("#enddate").val();
	var filmname = $("#filmname").val()

	var obj = new Object();
	obj.startdate = startdate;
	obj.enddate = enddate;
	obj.filmname = filmname;

	$("#jsonInfo").val($.toJSON(obj));
	$("#excelForm").submit();
}