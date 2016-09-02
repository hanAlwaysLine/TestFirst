<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    
    <title>角色管理</title>
 
	<script type="text/javascript" src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>role/roleQuery.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/boxy/js/jquery.boxy.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/js/jquery.metadata.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/page.css" />

  </head>
  
  <body>
  	  <div class="par_tit" style="*width:99%;">角色管理</div>	
  <form action="/cinema/sys/role!queryRole.action" id="queryForm" name="queryForm" target="result" method="post">
     <input type="hidden" id="pageNo" name="pageNo" value="1"/>
     <input type="hidden" id="pageSize" name="pageSize" value="10"/>
  <div class="qu_div" style="*margin-left:8px;*width:813px;">
 	 <ul>
		<div>
			<span>角色名:</span>
			<span><input type="text" id="title" name="role.title"/></span>
			<span><input type="submit" value="" class="qu_inp2"/></span>
			<span><input type="button" class="qu_inp3" onclick="getAdd()"/></span>
		</div>
	</ul>
  </div>
  <div style="height: 5px;"></div>
		<div style="height: 600px;">
			<iframe allowTransparency="true" name="result" id="result"
				width="100%" height="600" frameborder="0" scrolling="auto">
			</iframe>
		</div>
  </form>
  </body>
</html>
