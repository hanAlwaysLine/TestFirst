<%@ page language="java" import="java.util.*,cn.cinema.manage.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.cinema.manage.entity.sys.T0003_Ad_Competence"%>
<%@page import="cn.cinema.manage.entity.sys.T0005_Ad_Comprole"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>银谷影城管理后台</title>
<link href="common/css/Main.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="common/Script/jquery-1.4.1.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js"></script>
<script language="javascript" src="common/Script/popup_layer.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/boxy/css/boxy.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />
<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
<script language="javascript">
//整体跳转
	if (top.location != self.location) {
		top.location = self.location;
	}
	
$(document).ready(function() {
	$("#leftmenu .menu > a").click(function(){
		//	$("#leftmenu .menu > a").toggle(2000);//显示之前隐藏的
		//	$(this).hide(1000);//隐藏自身
		//	$(this).parent().find(".head").html("");//清空显示区
			
			//将菜单项附加到DIV
			$(this).parent().find(".menu").append($(this).clone());
			$(this).parent().find(".menu").append($(this).next(".selectitem").clone());
			//将菜单项展现
<%--			$(this).next().find("a").eq(0).addClass("select");--%>
			//将菜单下的以一个设置click跳转
<%--			$(this).next().find("a").eq(0).click();--%>
			$(this).next().toggle(400);		
		});
	
	
	
	//二菜单项的展开和折叠
//	$("#leftmenu .menu .selectitem .category").live('click',function(){
	//	if($(this).next(".categorylist").css("display")=="block"){
	//		$(this).removeClass("categoryexpand");
	//	}
	//	else{
	//		$(this).addClass("categoryexpand");
	//	}
	//	$(this).next(".categorylist").animate({ height: 'toggle', opacity: 'toggle' }, "toggle");									
	//});
	
	//菜单头部区域的展开和折叠
	$("#leftmenu .menu .head > a").live('click',function(){
		$(this).next("div").animate({ height: 'toggle', opacity: 'toggle' }, "toggle");									
	});
});

function zmueu(obj){
	//将菜单项附加到DIV
<%--	$(obj).parent().find(".menu").append($(obj).clone());--%>
<%--	$(obj).parent().find(".menu").append($(obj).next(".selectitem").clone());--%>
<%--	//将菜单项展现--%>
<%--	$(obj).parent().find(".menu > a").addClass("select");--%>
<%--	if($(obj).next.style)--%>
	if($(obj).next().attr("style")==undefined || $(obj).next().attr("style")=="display: none; "){
		$(obj).next().toggle(400);
	}
		
}

var ucsemoid="";
//点击菜单页面跳转
function gotoul(hrefid,url){
	if(ucsemoid!=""){
		$("#"+ucsemoid).removeAttr("style");
	}
	$("#"+hrefid).attr("style","background:url(/cinema/common/imgs/hover_jiao.jpg) 104px 6px no-repeat");
  	ucsemoid=hrefid;
  	try{
  		//如果返回真,才跳转.
  		if(document.getElementById("frameaif").contentWindow.checkSave())
  		{
  			Boxy.confirm("有数据未保存,是否离开此页面?",function(){
  				$("#frameaif",parent.document.body).attr("src",url); 
  				},2);
 		}
 		else
 		{
 			$("#frameaif",parent.document.body).attr("src",url); 
 		}
  	}catch(e)
  	{
  		//如果没有此事件,直接跳转.
  		$("#frameaif",parent.document.body).attr("src",url); 	
  	}
  
  }

//点击菜单页面跳转
function gotourl(obj){
  	try{
  		//清除选择标识
  		$("a[id^=menu]").removeAttr("style");
  		//重新加载选择标识
  		
  		$(obj).parent().parent().find("a").removeAttr("class");
  		$(obj).attr("style","background:url(/cinema/common/imgs/hover_jiao.jpg) 104px 6px no-repeat");
<%--  		$(obj).addClass("select");--%>
  		var url = $(obj).attr("url");
  		//如果返回真,才跳转.
  		if(document.getElementById("frameaif").contentWindow.checkSave())
  		{
  			Boxy.confirm("<h2>有数据未保存,是否离开此页面?</h2>",function(){
  				$("#frameaif",parent.document.body).attr("src",url); 
  				});
 		}
 		else
 		{
 			$("#frameaif",parent.document.body).attr("src",url); 
 		}
  	}catch(e)
  	{
  		//如果没有此事件,直接跳转.
  		$("#frameaif",parent.document.body).attr("src",url); 	
  	}
  }

//注消 
function gotologin(){
		window.location.href = "/cinema/loginout.jsp";	
}
//修改密码
function changepassword(){
	var url = "/ynmovie/login!showuserpass.action";
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>用户密码管理-修改密码</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(450, 450);
}
</script>
</head>

<body style="overflow-x:hidden;">
<div id="container" style="overflow-x:hidden">
<div id="wrapper">
    <div id="logo">
   
        <div class="login" ><div id="imgLogin"><img src="common/imgs/bt_zhuxiao.png" onclick="gotologin()" /></div></div>
    </div>             
    <div id="leftmenu">         
         <div class="data">
                    
            <div class="menu">
<!--            	<div class="head"> 	-->
<!--                </div>-->
        <%
       
        List<T0003_Ad_Competence> list=SessionUtil.getMenu(session);
        //判断父节点是否重复
        HashMap map = new HashMap();
        %> 
       <%
       T0003_Ad_Competence module;
       if(list!=null){
       for(int i=0;i<list.size();i++){
         module=list.get(i);
         if(!map.containsKey(module.getId())){
		%>
			<a href="#" id=<%="Menu_"+module.getId() %>><%=module.getTitle() %></a>
			<%
			map.put(module.getId(),module.getId());
			System.out.println("Menu_"+module.getId());
		}
		 %>
			<div class="selectitem"> 
 		<%
 		HashMap sonMap = new HashMap();
 		for(T0003_Ad_Competence sonModule : module.getSeList()){ //secMenu
 		 if(!sonMap.containsKey(sonModule.getId())){
 		%>
 			 <a href="#" id="<%="menu_"+sonModule.getFather() %>" onclick="gotourl(this)" url="<%=sonModule.getLink() %>" class="category"><%=sonModule.getTitle() %></a>
 			<%
 			
 				sonMap.put(sonModule.getId(),sonModule.getId());
 				}
 			}
 			%>
 			</div>
			<!-- 三级菜单   -->
 			<%
 		}
 		}
 		%>
            </div>
         </div>
    </div>
	<!--  菜单 end -->
    <div class="content">
		<iframe  align="middle" width="100%" 
			name="frameaif" id="frameaif" src="main.jsp" 
			frameborder="no" border="0" marginwidth="0" marginheight="0"
			scrolling="no" allowtransparency="yes"></iframe>		
	</div>
</body>
</html>
