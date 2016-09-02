/**
 * 通用分页JS.
 * chenbl.
 * 引用在结果页面中
 * 查询页面与结果页面为IFRAME 嵌套方式进行.
 */
/**
 * 跳转.
 * formID 查询form ID.
 * pageNo 跳转页.
 */
function gotoPage(pageNo,formId){
	$('#pageNo',window.parent.document).val(pageNo);
	$('#'+formId,window.parent.document)[0].submit();
}