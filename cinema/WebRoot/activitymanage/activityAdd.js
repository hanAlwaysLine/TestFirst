$(document)
		.ready(
				function() {
					//校验非负正整数
					jQuery.validator.addMethod("chrInteger",function(value,element){
						var chrnum = /^\d+$/;
						return this.optional(element) || (chrnum.test(value));
					},"请输入非负正整数!");
					//校验非负数字
					jQuery.validator.addMethod("chrFloat",function(value,element){
						var chrnum = /^\d+(\.\d+)?$/;
						return this.optional(element) || (chrnum.test(value));
					},"请输入非负数字!");
					//校验浮点型格式不得超过5位整数2位小数
					jQuery.validator.addMethod("chrPriceFloat", function(value, element) {
						var index = value.indexOf(".");
						if (index > 0) {
							var moneyArray = value.split(".");
							if (moneyArray[0].length > 5) {
								return false;
							}
							if (moneyArray[1].length > 2) {
								return false;
							}
						} else {
							if (value.length > 5) {
								return false;
							}
						}
						return true;
					}, "最大金额为5位整数2位小数!");
					
					//校验排期座位数是否可为空
					jQuery.validator.addMethod("feaSeatNull", function(value, element) {
						var featureno = $("#featureno").val();
						var result = true;
						if(featureno!=""){
							if(value==""){
								result = false;
							}
						}
						
						return result;
					}, "座位数不能为空!");
					
					//校验排期座位数大小
					jQuery.validator.addMethod("feaSeatNum", function(value, element) {
						var result = true;
						if(value>4){
							result = false;
						}
						
						return result;
					}, "座位数不能大于4!");
					$("#editForm").validate( {
						rules : {
							"activity.activity_name" : {
								required : true,
								maxlength : 20
							},
							"activity.title" : {
								required : true,
								maxlength : 50
							},
							"activity.starttime" : {
								required : true
							},
							"activity.endtime" : {
								required : true
							},
//							"activity.featureno" : {
//								remote : {
//								type : "post",
//								url : "/cinema/advertisemanage/picture!isNotExist.action",
//								data : {
//									jsonString : function(){
//											var picturename = $("#picturename").val();
//											var obj = new Object();
//											obj.picturename = picturename;
//											obj.oldpicturename = "";
//											return $.toJSON(obj);
//										}
//									}
//								}
//							},
							"activity.price" : {
								chrFloat : true,
								chrPriceFloat : true
							},
							"activity.seatcount" : {
								feaSeatNull : true,
								feaSeatNum : true,
								chrInteger : true
							},
							"activity.cont" : {
								maxlength : 2000,
							},
							"activity.count" : {
								chrInteger : true
							}
						},
						messages : {
							"activity.activity_name" : {
								required : "不能为空!",
								maxlength : "长度为1-20字符!"
							},
							"activity.title" : {
								required : "不能为空!",
								maxlength : "长度为1-50汉字!"
							},
							"activity.starttime" : {
								required : "不能为空!"

							},
							"activity.endtime" : {
								required : "不能为空!"

							},
//							"activity.featureno" : {
//								required : "不能为空!"
//							},
							"activity.cont" : {
								maxlength : "长度1-2000汉字!"
							}
						}
					})
					var msg = $("#msg").val();
					if (msg != "" && msg != "null") {
						alertResult(msg, 0);
					}
					var upload = new SWFUpload(
							{
								upload_url : UPLOAD_PATH
										+ "activitymanage/activity!upload.action", // 后台处理路径
								flash_url : UPLOAD_PATH
										+ "common/swfupload/swf/swfupload.swf", // flash加载路径
								file_size_limit : "50 MB", // 文件大小限制
								file_upload_limit : "0", // 允许上传文件的数量,0为无限制
								file_queue_limit : "0", // 上传队列允许等待文件的数量
								file_types : "*.jpg;", // 文件类型
								file_types_description : "Image Files", // 文件类型说明
								file_post_name : 'file', // 文件名称,默认为Filedata，Linux下只能用默认名称
								file_dialog_start_handler : fileDialogStart,
								file_queued_handler : fileQueued,
								file_queue_error_handler : fileQueueError, // 出错时执行的函数
								file_dialog_complete_handler : function(
										numFilesSelected, numFilesQueued) {
									if (numFilesSelected > 1) {
										alert("只能选择一张图片");
									} else {
										this.startUpload();
									}
								},
								upload_start_handler : uploadStart,
								upload_error_handler : uploadError,

								// 处理进度条
								upload_progress_handler : function(file,
										bytesLoaded, bytesTotal) {
									var percent = Math
											.ceil((bytesLoaded / bytesTotal) * 100);
									$('#upload_progresse').show();
									$('#upload_progress_bge').css( {
										'width' : percent + '%'
									});
									$('#upload_progress_bge').html(
											"<font color='#FFFFFF'>" + percent
													+ '%' + "</font>");
								},

								// 清空进度条
								upload_complete_handler : function(file) {
									this.startUpload();
									$('#upload_progress_bge').css( {
										'width' : '0%'
									});
									$('#upload_progress_bge').html('');
								},

								// 上传成功回调函数
								upload_success_handler : function(file, result) {
									var img = imgPath;
									jQuery("#img").attr("src", img + result);
									jQuery("#activityImg").val(result);
								},

								// 关于按钮的一些属性设置
								button_placeholder_id : "upload_images", // 绑定按钮的id
								button_text : "点此上传图片",
								button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
								button_width : 80,
								button_height : 22,
								button_cursor : SWFUpload.CURSOR.HAND, // 鼠标样式
								button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE, // 单文件上传
								button_image_url : UPLOAD_PATH + 'common/swfupload/images/anniu.jpg'
							})

					var upload_h = new SWFUpload(
							{
								upload_url : UPLOAD_PATH
										+ "activitymanage/activity!upload.action", // 后台处理路径
								flash_url : UPLOAD_PATH
										+ "common/swfupload/swf/swfupload.swf", // flash加载路径
								file_size_limit : "50 MB", // 文件大小限制
								file_upload_limit : "0", // 允许上传文件的数量,0为无限制
								file_queue_limit : "0", // 上传队列允许等待文件的数量
								file_types : "*.jpg;", // 文件类型
								file_types_description : "Image Files", // 文件类型说明
								file_post_name : 'file', // 文件名称,默认为Filedata，Linux下只能用默认名称
								file_dialog_start_handler : fileDialogStart,
								file_queued_handler : fileQueued,
								file_queue_error_handler : fileQueueError, // 出错时执行的函数
								file_dialog_complete_handler : function(
										numFilesSelected, numFilesQueued) {
									if (numFilesSelected > 1) {
										alert("只能选择一张图片");
									} else {
										this.startUpload();
									}
								},
								upload_start_handler : uploadStart,
								upload_error_handler : uploadError,

								// 处理进度条
								upload_progress_handler : function(file,
										bytesLoaded, bytesTotal) {
									var percent = Math
											.ceil((bytesLoaded / bytesTotal) * 100);
									$('#upload_progresse_h').show();
									$('#upload_progress_bge_h').css( {
										'width' : percent + '%'
									});
									$('#upload_progress_bge_h').html(
											"<font color='#FFFFFF'>" + percent
													+ '%' + "</font>");
								},

								// 清空进度条
								upload_complete_handler : function(file) {
									this.startUpload();
									$('#upload_progress_bge_h').css( {
										'width' : '0%'
									});
									$('#upload_progress_bge_h').html('');
								},

								// 上传成功回调函数
								upload_success_handler : function(file, result) {
									var img = imgPath;
									jQuery("#img_h").attr("src", img + result);
									jQuery("#activityImg_h").val(result);
								},

								// 关于按钮的一些属性设置
								button_placeholder_id : "upload_images_h", // 绑定按钮的id
								button_text : "点此上传横版图片",
								button_text_style : ".redText { cursor: pointer;color: #333333; font-weight: bold; }",
								button_width : 100,
								button_height : 22,
								button_cursor : SWFUpload.CURSOR.HAND, // 鼠标样式
								button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE, // 单文件上传
								button_image_url : UPLOAD_PATH + 'common/swfupload/images/anniu.jpg'
							})

				})

function doSave() {
	var img = $("#activityImg").val();
	if (img == null || img == "") {
		parent.parent.boxyAlert("请上传活动图片!");
		return;
	}
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (endDate < startDate) {
		parent.parent.boxyAlert("结束时间不能小于开始时间");
		return;
	}
	//排期编号
	var featureno = $("#featureno").val();
	// var rbContent = FCKeditorAPI.GetInstance("activity.cont").GetXHTML(true);
	// if(rbContent == null || rbContent == ""){
	// var oEditor = FCKeditorAPI.GetInstance("activity.cont");
	// oEditor.Focus();
	// $("#getshow").text("不能为空!");
	// return false;
	// }
	// if(rbContent.length>2000)
	// {
	// $("#getshow").text("长度不能大于2000");
	// return false;
	// }
	var Validator = $("#editForm").validate();
	if (Validator.form()) {
		//检验输入排期号是否可用
		var obj = new Object();
		obj.feano = featureno;
		obj.starttime = startDate;
		obj.endtime = endDate;
		var objJson = $.toJSON(obj);
		var url = "/cinema/activitymanage/activity!checkFeatureNo.action";
		var params = {
				jsonString : objJson
		}
		jQuery.post(url, params, doSaveCallBack, 'json');
	}
}

/**
 * 添加回调
 * @param data
 * @return
 */
function doSaveCallBack(data){
	var resultMsg = data.resultMsg;
	if(resultMsg!=""){
		parent.parent.boxyAlert(resultMsg);
	}else{
		document.forms[0].submit();
	}
}