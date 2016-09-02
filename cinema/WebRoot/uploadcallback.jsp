<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     
    <title>My JSP 'uploadcallback.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
	var msg = "${requestScope.tipMessage}";
	  if(msg!="")
		   {
		   		if(msg.substring(0,1)==0){
		   			parent.parent.closeWait();
		   			parent.parent.susAlert(msg.substring(2),0);
		   			parent.parent.$("#frameaif").attr("src","mms/mmsQuery.jsp");
			   	}else if(msg.substring(0,1)==1){
			   		parent.parent.closeWait();
			   		parent.parent.susAlert(msg.substring(2),1);
			   		parent.parent.$("#frameaif").attr("src","mms/mmsQuery.jsp");
				}else if(msg.substring(0,1)==2 || msg.substring(0,1)==3){
					parent.parent.closeWait();
					parent.parent.boxyAlert(msg.substring(2),2);
				}
		   }
</script>
  <body>
  </body>
</html>
