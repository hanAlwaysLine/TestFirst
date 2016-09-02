/**
 * 验证方法
 * 
 * @return
 */
$(document).ready(function() {
				jQuery.validator
						.addMethod(
								"hanzizimu1",
								function(value, element) {
									var pattern_cn = /^[a-zA-Z0-9]*$/gi;
									return this.optional(element)
											|| (pattern_cn.test(value));
								}, "登录用户名只能是数字字母");
				jQuery.validator.addMethod("hanzizimu2",
				function(value, element) {
					var pattern_cn = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0]|[\a-zA-Z0-9-]|[\s])*$/gi;
					return this.optional(element)|| (pattern_cn.test(value));
				}, "输入文字有误!");
				jQuery.validator.addMethod("chrnum", function(value, element) {
					var chrnum = /^([0-9]+)$/;
					return this.optional(element) || (chrnum.test(value));
				}, "只能输入数字!");
				
				jQuery.validator.addMethod("charName", function(value, element) {
					var charName = /^([a-zA-Z]+)$/;
					return this.optional(element) || (charName.test(value));
				}, "只能输入字母!");

				$("#aFrom").validate( {
					rules : {
						"role.rolename" : {
							required : true,
							maxlength : 25,
						},
						"role.roledesc" : {
							required : true,
							maxlength : 100
						}
					},
					messages : {
							"role.rolename" : {
								required : "请输入角色名!",
								maxlength : "密码长度为1-25个字符!"
							},
							"role.roledesc" : {
								maxlength : "用户名长度1-100个字符！"
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