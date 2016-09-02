$(document).ready(function() {
	$("#queryForm").submit();
});
function goAdds(id) {     
	var url = "filmmanage/filmmanageStillsAdd.jsp?id="+ id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>剧照-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	
	b.tween(650, 150);
}
function delfimestills(id,film_d) {
	var msg = "您确认要删除?请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function() {
		var url = "/cinema/filmmanage/filmmanage!delfimestills.action";
		params = {
			id : id,
			film_d : film_d
		}
		jQuery.post(url, params, callBackData, 'json');
	});
}
function callBackData(data) {
	var film_d = data.film_d;
	var jsons = film_d.split(",");
	film_d = jsons[0];
	flag = jsons[1];
	var msg;
	if (flag) {
		msg = "删除成功!";
	} else {
		msg = "删除失败!";
	}
	if (msg != null && "" != msg) {
		if (msg.indexOf("失败") > 0) {
			img = "<img src='/cinema/common/Images/cha.png' border='0' align='absmiddle'/>"
		} else {
			img = "<img src='/cinema/common/Images/gou.png' border='0' align='absmiddle'/>";
		}
		parent.parent.susAlert(img + msg);
		window.document.forms[0].submit();
	}
}
         
function goUpdatefimestills(id) {
	var url = "/cinema/filmmanage/filmmanage!goEditfimestills.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>剧照-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(650, 150);

}


