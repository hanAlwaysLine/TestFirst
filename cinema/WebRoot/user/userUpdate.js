/**
 * 验证方法
 * 
 * @return
 */
$(document).ready(function() {
	$("#name").focus();
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
					   "users.name":{
						required:true,
						hanzizimu1:true,
						maxlength : 16
					},
							"users.password" : {
								required : true,
								maxlength : 64
							},
							"users.email":{
								email:true,
								maxlength:80
							}
					},
					messages : {
						"users.name" : {
						required : "请输入登录用户名!",
						maxlength : "表名长度为1-16个字符!"
					    },
					   "users.password" : {
						required : "请输入密码!",
						maxlength : "密码长度为1-64个字符!"
					   },
					  "users.email" :{
						email:"邮箱格式不正确！",
						maxlength:"邮箱长度1-80个字符"
					  }
				  }
				});
				var msg=$("#msg").val();
				if(msg!=""&&msg!="null")
				{
			        alertResult(msg, 0);
		        }
			});
//回显操作
$(document).ready(function(){
	//角色ID
	var userid = $("#userid").val();
	var url ="/cinemamanage/user!checkUser.action";
	var params = {
			userid:userid
	}
	jQuery.post(url,params,callBack,"json");
});
//回调函数
function callBack(data){
	var obj  = eval(data);
	$.each(obj,function(index,o){
		$(":checkbox[roleid="+o.roleid+"]").attr("checked",true);
	});
}
//添加保存
function doSave() {
	var id=$("#userid").val() ;
	var name=$("#name").val() ;
	url = "/cinema/user!addYz.action?id="+id+"&loginname="+name;
	jQuery.post(url, null,show);
}

function show(data){
	  if(data=='true'){		 
		  $("#show").html("<font color='green'>可以使用</font>"); 
		  var Validator = $("#aFrom").validate();
			if (Validator.form()) {
				document.forms[0].submit();
			}
	   }else if(data=='false'){
	      $("#show").html("<font color='red'>用户名已存在</font>");
	   }
}