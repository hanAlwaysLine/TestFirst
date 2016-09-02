///-------------------------------------------------------------------------
//jQuery弹出窗口 By Await [2011-02-25]
//--------------------------------------------------------------------------
/*参数：[可选参数在调用时可写可不写,其他为必写]
 ----------------------------------------------------------------------------
 title:	窗口标题
 content:  内容(可选内容为){ text | id | img | url | iframe }
 width:	内容宽度
 height:	内容高度
 drag:  是否可以拖动(ture为是,false为否)
 time:	自动关闭等待的时间，为空是则不自动关闭
 divNo:弹出层编号 0为第一层,1为第二层
 showbg:	[可选参数]设置是否显示遮罩层(0为不显示,1为显示)
 cssName:  [可选参数]附加class名称

 ------------------------------------------------------------------------*/
//示例:
//------------------------------------------------------------------------
//simpleWindown("例子","text:例子","500","400","true","3000","0","0","exa")
//------------------------------------------------------------------------
var showWindown = true;
var templateSrc = ""; // 设置loading.gif路径
function tipsWindown(title, content, width, height, drag, time, divNo, showbg,
		cssName) {
	$("#windown-box" + divNo, window.parent.document).remove(); // 请除内容
	var width = width >= 950 ? this.width = 950 : this.width = width; // 设置最大窗口宽度
	var height = height >= 527 ? this.height = 527 : this.height = height; // 设置最大窗口高度
	if (showWindown == true) {
		var simpleWindown_html = new String;
		simpleWindown_html = "<div id=\"windownbg" + divNo
				+ "\" style=\"height:" + window.document.availHeight
				+ "px;filter:alpha(opacity=0);opacity:0;z-index: 99999" + divNo
				+ "\"></div>";
		simpleWindown_html += "<div id=\"windown-box" + divNo + "\">";
		simpleWindown_html += "<div id=\"windown-title" + divNo
				+ "\"><h2></h2><span id=\"windown-close" + divNo
				+ "\">关闭</span></div>";
		simpleWindown_html += "<div id=\"windown-content-border" + divNo
				+ "\"><div id=\"windown-content" + divNo + "\"></div></div>";
		simpleWindown_html += "</div>";
		$("#indexBody", window.parent.document).append(simpleWindown_html);
		show = false;
	}
	contentType = content.substring(0, content.indexOf(":"));
	content = content.substring(content.indexOf(":") + 1, content.length);
	switch (contentType) {
	case "text":
		$("#windown-content" + divNo, window.parent.document).html(content);
		break;
	case "id":
		$("#windown-content" + divNo, window.parent.document).html(
				$("#" + content + "").html());
		break;
	case "img":
		$("#windown-content" + divNo, window.parent.document)
				.ajaxStart(
						function() {
							$(this)
									.html(
											"<img src='"
													+ templateSrc
													+ "/images/loading.gif' class='loading' />");
						});
		$.ajax( {
			error : function() {
				$("#windown-content" + divNo, window.parent.document).html(
						"<p class='windown-error'>加载数据出错...</p>");
			},
			success : function(html) {
				$("#windown-content" + divNo, window.parent.document).html(
						"<img src=" + content + " alt='' />");
			}
		});
		break;
	case "url":
		var content_array = content.split("?");
		$("#windown-content" + divNo, window.parent.document)
				.ajaxStart(
						function() {
							$(this)
									.html(
											"<img src='"
													+ templateSrc
													+ "/images/loading.gif' class='loading' />");
						});
		$.ajax( {
			type : content_array[0],
			url : content_array[1],
			data : content_array[2],
			error : function() {
				$("#windown-content" + divNo, window.parent.document).html(
						"<p class='windown-error'>加载数据出错...</p>");
			},
			success : function(html) {
				$("#windown-content" + divNo, window.parent.document)
						.html(html);
			}
		});
		break;
	case "iframe":
		$("#windown-content" + divNo, window.parent.document)
				.ajaxStart(
						function() {
							$(this)
									.html(
											"<img src='"
													+ templateSrc
													+ "/images/loading.gif' class='loading' />");
						});
		$
				.ajax( {
					error : function() {
						$("#windown-content" + divNo, window.parent.document)
								.html("<p class='windown-error'>加载数据出错...</p>");
					},
					success : function(html) {
						$("#windown-content" + divNo, window.parent.document)
								.html(
										"<iframe src=\""
												+ content
												+ "\" width=\"100%\" height=\""
												+ parseInt(height)
												+ "px"
												+ "\" id=\"divIframe"+divNo+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
					}
				});
	}
	$("#windown-title" + divNo + " h2", window.parent.document).html(title);
	if (showbg == "true") {
		$("#windownbg" + divNo, window.parent.document).show();
	} else {
		$("#windownbg" + divNo, window.parent.document).remove();
	}
	;
	$("#windownbg" + divNo, window.parent.document).animate( {
		opacity : "0.5"
	}, "normal");// 设置透明度
	$("#windown-box" + divNo, window.parent.document).show();
	if (height >= 527) {
		$("#windown-title" + divNo, window.parent.document).css( {
			width : (parseInt(width) + 22) + "px"
		});
		$("#windown-content" + divNo, window.parent.document).css( {
			width : (parseInt(width) + 17) + "px",
			height : height + "px"
		});
	} else {
		$("#windown-title" + divNo, window.parent.document).css( {
			width : (parseInt(width) + 10) + "px"
		});
		$("#windown-content" + divNo, window.parent.document).css( {
			width : width + "px",
			height : height + "px"
		});
	}
	var cw = document.documentElement.clientWidth, ch = document.documentElement.clientHeight, est = document.documentElement.scrollTop;
	var _version = $.browser.version;
	if (_version == 6.0) {
		$("#windown-box" + divNo, window.parent.document).css( {
			left : "50%",
			top : (parseInt((ch) / 2) + est) + "px",
			marginTop : -((parseInt(height) + 53) / 2) + "px",
			marginLeft : -((parseInt(width) + 32) / 2) + "px",
			zIndex : "99999" + divNo
		});
	} else {
		$("#windown-box" + divNo, window.parent.document).css( {
			left : "50%",
			top : "50%",
			marginTop : -((parseInt(height) + 53) / 2) + "px",
			marginLeft : -((parseInt(width) + 32) / 2) + "px",
			zIndex : "99999" + divNo
		});
	}
	;
	var Drag_ID = $("#windown-box" + divNo, window.parent.document), DragHead = $(
			"#windown-title" + divNo, window.parent.document);

	var moveX = 0, moveY = 0, moveTop, moveLeft = 0, moveable = false;
	if (_version == 6.0) {
		moveTop = est;
	} else {
		moveTop = 0;
	}
	var sw = Drag_ID.scrollWidth, sh = Drag_ID.scrollHeight;
	DragHead.onmouseover = function(e) {
		if (drag == "true") {
			DragHead.style.cursor = "move";
		} else {
			DragHead.style.cursor = "default";
		}
	};
	DragHead.onmousedown = function(e) {
		if (drag == "true") {
			moveable = true;
		} else {
			moveable = false;
		}
		e = window.event ? window.event : e;
		var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop - moveTop;
		moveX = e.clientX - ol;
		moveY = e.clientY - ot;
		document.onmousemove = function(e) {
			if (moveable) {
				e = window.event ? window.event : e;
				var x = e.clientX - moveX;
				var y = e.clientY - moveY;
				if (x > 0 && (x + sw < cw) && y > 0 && (y + sh < ch)) {
					Drag_ID.style.left = x + "px";
					Drag_ID.style.top = parseInt(y + moveTop) + "px";
					Drag_ID.style.margin = "auto";
				}
			}
		}
		document.onmouseup = function() {
			moveable = false;
		};
		Drag_ID.onselectstart = function(e) {
			return false;
		}
	}
	$("#windown-content" + divNo, window.parent.document).attr("class",
			"windown-" + cssName);
	var closeWindown = function() {
		try{
			$("#windownbg" + divNo, window.parent.document).remove();
			$("#windown-box" + divNo, window.parent.document).fadeOut("slow",
					function() {
						$(this).remove();
					});
		}catch(e){
			
		}
		
	}
	if (time == "" || typeof (time) == "undefined") {
		$("#windown-close" + divNo, window.parent.document).click(
				function() {
					$("#windownbg" + divNo, window.parent.document).remove();
					$("#windown-box" + divNo, window.parent.document).fadeOut(
							"slow", function() {
								$(this).remove();
							});
				});
	} else {
		setTimeout(closeWindown, time);
	}
}

// var showWindown = true;
// var templateSrc = ""; // 设置图片路径
// function tipsWindown(title, content, width, height, drag, time, divNo,
// showbg,
// cssName) {
// $("#windown-box" + divNo, window.parent.document).remove(); // 请除内容
// var width = width >= 950 ? this.width = 950 : this.width = width; // 设置最大窗口宽度
// var height = height >= 527 ? this.height = 527 : this.height = height; //
// 设置最大窗口高度
// if (showWindown == true) {
// var simpleWindown_html = new String;
// if (divNo == "0") {
// simpleWindown_html = "<div id='windownbg" + divNo
// + "' style=\"height:" + window.document.availHeight
// + "px;filter:alpha(opacity=0);opacity:0;z-index: 99901"
// + divNo + " \"></div>";
// }
// simpleWindown_html += "<div id='windown-box" + divNo + "'>";
// simpleWindown_html += "<div id='windown-title" + divNo
// + "'><h2></h2><span id='windown-close" + divNo
// + "'>关闭</span></div>";
// simpleWindown_html += "<div id='windown-content-border " + divNo
// + "'><div id='windown-content" + divNo + "'></div></div>";
// simpleWindown_html += "</div>";
//
// $("#indexBody", window.parent.document).append(simpleWindown_html);
// show = false;
// }
//
// contentType = content.substring(0, content.indexOf(":"));
// content = content.substring(content.indexOf(":") + 1, content.length);
// switch (contentType) {
// case "text":
// $("#windown-content" + divNo, window.parent.document).html(content);
// break;
// case "id":
// $("#windown-content" + divNo, window.parent.document).html(
// $("#" + content + "").html());
// break;
// case "img":
// $("#windown-content" + divNo, window.parent.document)
// .ajaxStart(
// function() {
// $(this)
// .html(
// "<img src='"
// + templateSrc
// + "/cinema/common/img/loading.gif' class='loading' />");
// });
// $.ajax( {
// error : function() {
// $("#windown-content" + divNo, window.parent.document).html(
// "<p class='windown-error'>加载数据出错...</p>");
// },
// success : function(html) {
// $("#windown-content" + divNo, window.parent.document).html(
// "<img src=" + content + " alt='' />");
// }
// });
// break;
// case "url":
// var content_array = content.split("?");
// $("#windown-content" + divNo, window.parent.document)
// .ajaxStart(
// function() {
// $(this)
// .html(
// "<img src='"
// + templateSrc
// + "/images/loading.gif' class='loading' />");
// });
// $.ajax( {
// type : content_array[0],
// url : content_array[1],
// data : content_array[2],
// error : function() {
// $("#windown-content" + divNo, window.parent.document).html(
// "<p class='windown-error'>加载数据出错...</p>");
// },
// success : function(html) {
// $("#windown-content" + divNo, window.parent.document)
// .html(html);
// }
// });
// break;
// case "iframe":
// $("#windown-content" + divNo, window.parent.document)
// .ajaxStart(
// function() {
// $(this)
// .html(
// "<img src='"
// + templateSrc
// + "/cinema/common/imgges/loading.gif' class='loading' />");
// });
// $
// .ajax( {
//
// success : function(html) {
// $("#windown-content" + divNo, window.parent.document)
// .html(
// "<iframe src=\""
// + content
// + "\" width=\"100%\" height=\""
// + parseInt(height)
// + "px"
// + "\" scrolling=\"auto\" id='divIframe"
// + divNo
// + "' frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
// }
// });
// }
// $("#windown-title" + divNo + " h2", window.parent.document).html(title);
// if (divNo == "0") {
// if (showbg == "true") {
// $("#windownbg" + divNo, window.parent.document).show();
// } else {
// $("#windownbg" + divNo, window.parent.document).remove();
// }
// ;
// $("#windownbg" + divNo, window.parent.document).animate( {
// opacity : "0.5"
// }, "normal");// 设置透明度
// }
// $("#windown-box" + divNo, window.parent.document).show();
// if (height >= 527) {
// $("#windown-title" + divNo, window.parent.document).css( {
// width : (parseInt(width) + 22) + "px"
// });
// $("#windown-content" + divNo, window.parent.document).css( {
// width : (parseInt(width) + 17) + "px",
// height : height + "px"
// });
// } else {
// $("#windown-title" + divNo, window.parent.document).css( {
// width : (parseInt(width) + 10) + "px"
// });
// $("#windown-content" + divNo, window.parent.document).css( {
// width : width + "px",
// height : height + "px"
// });
// }
// var cw = document.documentElement.clientWidth, ch =
// document.documentElement.clientHeight, est =
// document.documentElement.scrollTop;
// var _version = $.browser.version;
// if (_version == 6.0) {
// $("#windown-box" + divNo, window.parent.document).css( {
// left : "50%",
// top : (parseInt((ch) / 2) + est) + "px",
// marginTop : -((parseInt(height) + 53) / 2) + "px",
// marginLeft : -((parseInt(width) + 32) / 2) + "px",
// zIndex : "99999" + divNo
// });
// } else {
// $("#windown-box" + divNo, window.parent.document).css( {
// left : "50%",
// top : "50%",
// marginTop : -((parseInt(height) + 53) / 2) + "px",
// marginLeft : -((parseInt(width) + 32) / 2) + "px",
// zIndex : "99999" + divNo
// });
// }
// ;
// var Drag_ID = $("#windown-box" + divNo, window.parent.document), DragHead =
// $(
// "#windown-title" + divNo, window.parent.document);
//
// var moveX = 0, moveY = 0, moveTop, moveLeft = 0, moveable = false;
// if (_version == 6.0) {
// moveTop = est;
// } else {
// moveTop = 0;
// }
// var sw = Drag_ID.scrollWidth, sh = Drag_ID.scrollHeight;
// DragHead.onmouseover = function(e) {
// if (drag == "true") {
// DragHead.style.cursor = "move";
// } else {
// DragHead.style.cursor = "default";
// }
// };
// DragHead.onmousedown = function(e) {
// if (drag == "true") {
// moveable = true;
// } else {
// moveable = false;
// }
// e = window.event ? window.event : e;
// var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop - moveTop;
// moveX = e.clientX - ol;
// moveY = e.clientY - ot;
// document.onmousemove = function(e) {
// if (moveable) {
// e = window.event ? window.event : e;
// var x = e.clientX - moveX;
// var y = e.clientY - moveY;
// if (x > 0 && (x + sw < cw) && y > 0 && (y + sh < ch)) {
// Drag_ID.style.left = x + "px";
// Drag_ID.style.top = parseInt(y + moveTop) + "px";
// Drag_ID.style.margin = "auto";
// }
// }
// }
// document.onmouseup = function() {
// moveable = false;
// };
// Drag_ID.onselectstart = function(e) {
// return false;
// }
// }
// $("#windown-content" + divNo, window.parent.document).attr("class",
// "windown-" + cssName);
// var closeWindown = function() {
// $("#windownbg" + divNo, window.parent.document).remove();
// $("#windown-box" + divNo, window.parent.document).fadeOut("slow",
// function() {
// $(this).remove();
// });
// }
// if (time == "" || typeof (time) == "undefined") {
// $("#windown-close" + divNo, window.parent.document).click(
// function() {
// $("#windownbg" + divNo, window.parent.document).remove();
// $("#windown-box" + divNo, window.parent.document).fadeOut(
// "slow", function() {
// $(this).remove();
// });
// });
// } else {
// setTimeout(closeWindown, time);
// }
// }
