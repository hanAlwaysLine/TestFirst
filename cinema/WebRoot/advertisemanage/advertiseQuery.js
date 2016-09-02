var type;
var timeoutid;
$(document)
		.ready(function() {
			// 初始页面
				document.queryForm.action = "/cinema/advertisemanage/advertise!selList.action?type=" + 1;
				type = 1;
				$("#queryForm").submit();

				$("#tab li")
						.each(function(index) {

							// 每一个包含li的对象都会执行函数中的代码
								// index是当前执行这个函数的li的索引，也可以利用这个索引和div对应。
								$(this)
										.click(function() {
											// 将原来隐藏的div显示，原来显示的隐藏
												timeoutid = setTimeout(
														function() {
															// 延时
															$("#tab li.tabin")
																	.removeClass(
																			"tabin");
															$("li")
																	.eq(index)
																	.addClass(
																			"tabin");
														}, 3)
												if (index == 0) {
													document.queryForm.action = "/cinema/advertisemanage/advertise!selList.action?type=" + 1;
													type = 1;
													$("#queryForm").submit();

												} else if (index == 1) {
													document.queryForm.action = "/cinema/advertisemanage/advertise!selList.action?type=" + 2;
													type = 2;
													$("#queryForm").submit();
												} else if (index == 2) {
													document.queryForm.action = "/cinema/advertisemanage/advertise!selList.action?type=" + 3;
													type = 3;
													$("#queryForm").submit();
													// $("#").load("form.php");
												}

											}).mouseout(function() {
											clearTimeout(timeoutid);
										})
							})
			})

// 备用
// 对于loading图片 绑定ajax请求开始和结束事件
$("#loading img").bind("ajaxStart", function() {
	$(this).show();
	// 只要ajax交互开始，functiong就开始执行
	}).bind("ajaxStop", function() {
	$(this).slideUp(1000)
})

function goAdd() {
	var url = "/cinema/advertisemanage/advertise!addadvertise.action?type=" + type;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>广告-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	if (type == 1) {
		b.tween(550, 100);
	}
	if (type == 2) {
		b.tween(550, 220);
	}
	if (type == 3) {
		b.tween(550, 220);
	}
}
