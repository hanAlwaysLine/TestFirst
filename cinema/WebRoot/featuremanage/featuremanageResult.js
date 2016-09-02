$(document).ready(function() {
	setIframeHeight();
	judgeFeaDate();
});

/**
 * 判断排期是否过期
 * @return
 */
function judgeFeaDate() {
	//系统当前时间
	var sysDate = $("#sysDate").val();
	var input_feadate = $(":input[name='input_feadate']");
	$.each(input_feadate, function(index, obj) {
		var feadate = $(obj).val();
		//判断是否过期
		if(feadate <= sysDate){
			var id = $(obj).attr("id").split("_")[1];
			$(":input[id='"+id+"']").attr("readonly",true);
		}
	})
}

function showcon(obj, event) {
	// alert(event.clientY);
	// alert(event.clientX);
	var con = $(obj).text();
	if (con.length > 0) {
		$("#apdiv").text("");
		var dwidth = "100";
		var dheigth = "220";

		var con_A = con.split(',');
		var dom_tbody = $('<tbody></tbody>');
		var dom_tr = $('<tr></tr>');
		var dom_td = $("<td>时间</td>");

		dom_tr.append(dom_td);
		dom_td = $("<td>影厅</td>");
		dom_tr.append(dom_td);
		dom_td = $("<td>影院价格</td>");
		dom_tr.append(dom_td);
		dom_td = $("<td>客户端价格</td>");
		dom_tr.append(dom_td);
		dom_td = $("<td>微信价格</td>");
		dom_tr.append(dom_td);
		dom_tbody.append(dom_tr);
		$.each(con_A, function(index, con_R) {
			var con_B = con_R.split('(');
			dom_tr = $('<tr></tr>');
			dom_td = $("<td>" + con_B[0] + "</td>");
			dom_tr.append(dom_td);
			dom_td = $("<td>(" + con_B[1] + "</td>");
			dom_tr.append(dom_td);
			dom_td = $("<td>" + con_B[2] + "</td>");
			dom_tr.append(dom_td);
			dom_td = $("<td>" + con_B[3] + "</td>");
			dom_tr.append(dom_td);
			dom_td = $("<td>" + con_B[4] + "</td>");
			dom_tr.append(dom_td);
			dom_tbody.append(dom_tr);
		})

		var dom_table = $("<table class='data_table2'></table>").append(
				dom_tbody);
		$("#apdiv").append(dom_table)

		$("#apdiv").stop(true, true).slideToggle(500);
		$("#apdiv").css("top", event.clientY + 10);// top
		$("#apdiv").css("left", event.clientX - 220);// left
		$("#apdiv").css("height", dwidth);// height
		$("#apdiv").css("width", dheigth);// width
	}
}
function closecon(obj) {
	if ($(obj).text().length > 0) {
		$("#apdiv").stop(true, true).slideToggle(500);
	}
}

function showf_D(feadata) {
	alert(feadata)
}

function bprice(data) {
	var url = "/cinema/featuremanage/featuremanage!updatebprice.action";
	var params = {
		jsonid : data.id,
		jsonpr : data.value
	};
	jQuery.post(url, params, rulenameCallBack, 'json');
}

// 更新客户端价格
function aprice(data) {
	var msg = "是否修改客户端价格,请确认!";
	parent.parent.Boxy.confirm("" + msg + "", function() {
		var url = "/cinema/featuremanage/featuremanage!updateandroidpric.action";
		var params = {
			jsonid : data.id,
			jsonap : data.value
		};
		jQuery.post(url, params, rulenameCallBack, 'json');
	});
}


// 更新微信价格
function wprice(data) {
	var msg = "是否修改微信价格,请确认!";
	parent.parent.Boxy.confirm("" + msg + "", function() {
		var url = "/cinema/featuremanage/featuremanage!updatewinxinpric.action";
		var params = {
			jsonid : data.id,
			jsonwp : data.value
		};
		jQuery.post(url, params, rulenameCallBack, 'json');
	});
}

// 更新网站价格
function wsrice(data) {
	var msg = "是否修改网站价格,请确认!";
	parent.parent.Boxy.confirm("" + msg + "", function() {
		var url = "/cinema/featuremanage/featuremanage!updatewebsitepric.action";
		var params = {
			jsonid : data.id,
			jsonwp : data.value
		};
		jQuery.post(url, params, rulenameCallBack, 'json');
	});
}

// 更新价格回调
function rulenameCallBack(data) {
	if (data.modifySuc) {
		alertResult("更新成功!", null);
	} else {
		alertResult("更新失败!", null);
	}
}

// 更新网站价格
function wapprice(data) {
	var url = "/cinema/featuremanage/featuremanage!queryorderbyappno.action";
	var params = {
		jsonid : data.id,
		jsonpr : data.value,
		jsonwp : data.name
	};
	jQuery.post(url, params, checkorderCallBack(data.id,data.value,data.name), 'json');
}

// 校验排期生成订单  id  value  更改之后价格   更改之前价格
function checkorderCallBack(id,value,apprice) {
	return function(data){  
         if(data.exitsorder){
			var msg = "该排期已经生成过订单，是否继续修改结算价!";
			parent.parent.Boxy.confirm("" + msg + "", function() {
				var url = "/cinema/featuremanage/featuremanage!updateapppric.action";
				var params = {
					jsonid : id,
					jsonwp : value,
					jsonpr : apprice
				};
				jQuery.post(url, params, rulenameCallBack, 'json');
			});
		}else{
			var msg = "是否修改结算价格,请确认!";
			parent.parent.Boxy.confirm("" + msg + "", function() {
				var url = "/cinema/featuremanage/featuremanage!updateapppric.action";
				var params = {
					jsonid : id,
					jsonwp : value,
					jsonpr : apprice
				};
				jQuery.post(url, params, rulenameCallBack, 'json');
			});
		}
    }
}


/**
 * 
 * @param flag 0 设置停售  1开售
 * @return
 */
function updateStatus(flag){
	$("#loading").css("display", "block");
	var ids="";
	var i=0;
	
	$('input[name="modifyprice"]:checked').each(function(){ 
		ids+= $(this).val()+',';
	    i=i+1;
	});
	if(i==0) return;
	var url = "/cinema/featuremanage/featuremanage!updatestatus.action";
	var params = {
		jsonid : ids,
		jsonflag : flag
	};
	jQuery.post(url, params, modifyprceCallBack, 'json');
}

//批量更新价格
function modifyprce(){
	var ids="";
	var i=0;
	var m2price = $('#m2price').val();
	var m3price = $('#m3price').val();
	var mwprice = $('#mwprice').val();
	var priceflag = $('#priceflag').val();
	if(m2price==""||m3price==""||mwprice==""){
		alert("价格为空!");
		return;
	}
	
	$('input[name="modifyprice"]:checked').each(function(){ 
		ids+= $(this).val()+',';
	    i=i+1;
	});
	if(i==0) return;
	$("#loading").css("display", "block");
	var url = "/cinema/featuremanage/featuremanage!updateappprics.action";
	var params = {
		jsonid : ids,
		json2d : m2price,
		json3d : m3price,
		jsonwx : mwprice,
		jsonflag : priceflag
	};
	jQuery.post(url, params, modifyprceCallBack, 'json');
}


function modifyprceCallBack(){
	alertResult("更新完成!", null);
}

function toggleCheck(){
	if ($("#maina").is(":checked")) {
		$("input[name='modifyprice']").prop("checked",true);
	} else {
		$("input[name='modifyprice']").removeAttr("checked");
	}
}