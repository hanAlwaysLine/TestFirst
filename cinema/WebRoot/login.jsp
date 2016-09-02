<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎登录银谷影城管理后台</title>
      <link rel="shortcut icon" href="common/imgs/favicon.ico" /> 
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0">    </meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js" ></script>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery.json-2.2.min.js" ></script>
	<script type="text/javascript" src="<%=basePath %>login.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
   <form action="index.jsp" id="login" method="post" class="form_bk"><br/>
   <input type="text" name="users.name" id="name"  size="16" class="input1" />
   <input type="password" name="users.password" id="password"  size="16" class="input2" />
   <input type="button" id="btnLogin" value="" onclick="login();" class="input3"/>
   <font color="red" id="tip" class="font1"></font>
   </form> 
  </body>
</html>
