<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    
    <title>Error</title>
	<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />

  
  <script>
 		var i=5;  
		function daore(){
		 	$("#daoss").text(i);  
   		 	if(i>0){
       			setTimeout("daore()",1000);  
    		}
    		else{
       			window.location.href="/cinemamanage/login.jsp";
     		}
   			i--;
  		}
  </script>
    </head>
  <body onload="daore()" style="background-image: url('/cinemamanage/common/imgs/body_bg.jpg')">
  		
  		<div style="margin: 0 auto;text-align: center;">
  			<div><img style="float: none" src="common/imgs/errorico.png" ></img></div>
  			<div class="altxt">
  			<%
  		String code =(String)request.getParameter("errorCode");
      	if("0010".equals(code)){
      	    %>
      	    	用户或密码错误,登录失败!
      	    <%
      	}
      	if("0404".equals(code)){
      		%>
      			没有找到页面,可能已不存在!
      		<%
      	}
      	if("0500".equals(code)){
      		%>
      			出错了...
      		<%
      	}
    		 %>
  			</div>
  			<div class="retxt">
  				系统将在[<label id="daoss"></label>]秒后自动<a href='/cinemamanage/main.jsp' class="retxt"  style="text-decoration: underline; color:#008BFF">跳转</a>
  			</div>
  		</div>
  </body>
</html>
