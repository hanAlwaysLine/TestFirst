if (top.location != self.location) {
		top.location = self.location;
	}
var flag = "0";
function showMsg()
{ 
	var msg="loading";
    var myDivVer=$("#tip");
    
    if(flag==0)
    {
        flag=1;
        myDivVer.html(msg+'.');
    }else if(flag==1)
    {
        flag=2;
        myDivVer.html(msg+'..');  
    }
    else if(flag==2)
    {
        flag=3;
        myDivVer.html(msg+'...');    
    }
    else if(flag==3)
    {
        flag=4;
        myDivVer.html(msg+'....');    
    }else if(flag==4)
    {
        flag=5;
        myDivVer.html(msg+'.....');    
    }
    else{
        flag=0;
        myDivVer.html(msg+'......');    
    }
//    window.setTimeout('showMsg()',200);
}

var int ;
function login(){
		 var user = new Object();
		 user.name = $("#name").val();
		 user.password = $("#password").val();
		 if(user.name==""){
			$("#tip").html("用户名不能为空!");
			return;
		 }
		 else if(user.password==""){
		    $("#tip").html("密码不能为空!");
			return;
		 }
		  userStr = $.toJSON(user);
			var url = "/cinema/login!login.action";
			var params = {
				userStr : userStr
			};
			//Ajax提交登录
			int = window.setInterval('showMsg()',200);
			jQuery.post(url, params, loginCallGrant, 'json');
	}
	//用户登陆权限验证
	function loginCallGrant(data) {
		var isSuccess=eval(data.isSuccess);
		if(isSuccess)
		{
			var url = "/cinema/login!loginGrant.action";
			//Ajax提交
			jQuery.post(url, null, loginAllGrantBack, 'json');	
		}else{
			window.clearInterval(int);
			 $("#tip").html("用户名或密码错误!");
		}
	}
	function loginAllGrantBack(data){
		var isLoginGrant = eval(data.isLoginGrant);
		if(isLoginGrant){
			$("#login").submit();
		}else{
			window.clearInterval(int);
			 $("#tip").html("此用户无权限登录!");
		}
	}
//function loginCallBack(data) {
//var isloginSuc=eval(data.isSuccess);
//if(isloginSuc)
//{
//	var user = new Object();
//	user.loginname = $("#loginname").val();
//	empStr = $.toJSON(emp);
//	
//	var url = "/cinema/user!updating.action";
//	//Ajax提交
//	jQuery.post(url, null, isUpdatingCall, 'json');
//}
//else
//{
//        $("#tip").html("用户名或密码错误!");
//}


//		//判断系统是否正在更新回调函数
//		function isUpdatingCall(date){
//			var isUpdating=eval(date.isUpdating);
//			if(isUpdating){
//				$("#tip").html("系统正在更新中,无法登陆!");
//			}else{
//				var url = "/cinema/user!loginGrant.action";
//				var params = {
//					employerStr : empStr,
//					path : path
//				};
//				//Ajax提交
//				jQuery.post(url, params, loginCallGrant, 'json');
//			}
//		}
		

$(document).keydown(function(e) {

	var flag = true;
	var obj = $($("div:not(':visible')"));
	$.each(obj,function(index,val){
	     if($(val).attr("id")=="loginform")
	     {
	      flag = false;
	     }
	});
	if(e.keyCode==13){ 
   	 	if(flag){
	        $("#btnLogin").click();
   		}else{
    		$("#loginIco").click();
    	}
    }
});
function clearText(){
	$("#tip").html("");
}