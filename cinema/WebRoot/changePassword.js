/**
 * 验证方法
 * 
 * @return
 */
$(document).ready(function() {
				jQuery.validator.addMethod("hanzizimu1",function(value, element) {
					var pattern_cn = /^[a-zA-Z0-9]*$/gi;
					return this.optional(element)|| (pattern_cn.test(value));
				}, "登录用户名只能是数字字母");
				jQuery.validator.addMethod("chrnum", function(value, element) {
					var chrnum = /^([0-9]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "只能输入数字!");

				$("#aFrom").validate( {
					rules : {
						"users.loginname" : {
							required : true,
							maxlength : 16,
							hanzizimu1 : true
						},
						"users.password" : {
							required : true,
							maxlength : 64,
						}
					},
					messages : {
							"users.userid" : {
								required : "请输入登录用户名!",
								maxlength : "表名长度为1-16个字符!"
							},
							"users.password" : {
								required : "请输入密码!",
								maxlength : "密码长度为1-64个字符!"
							}
						}
				});
				var msg=$("#msg").val();
				if(msg!=""&&msg!="null")
				{
			        alertResult(msg, 0);
		        }
			});

//添加保存
function doSave() {
	var Validator = $("#aFrom").validate();
	if (Validator.form()) {
		document.forms[0].submit();
	}
}