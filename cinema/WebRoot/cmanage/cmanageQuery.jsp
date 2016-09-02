<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
    
    <title>影院管理</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>cmanage/cmanageQuery.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <div class="par_tit" style="*width:99%;">影院管理</div>	
  <form action="/cinema/manage/cmanage!queryCmanage.action" id="queryForm" name="queryForm" 
   method="post" >
         <input type="hidden" id="pageNo" name="pageNo" value="1"/>
   <input type="hidden" id="pageSize" name="pageSize" value="10"/>
   <div class="qu_div" style="*margin-left:8px;*width:813px;">
 	 <ul>
		<div>
			<span>影院名称：</span>
			<span>
			<input type="text" name="placeinfo.placename" class="qu_inp1" id="placename"/>
			</span>
			<span>地方：</span>
			<span>
			 <select name="placeinfo.cityno">
			    <option value="">全部</option>
			  	<s:iterator value="#request.lcity" id="city" status="i" >
                    <option value="<s:property value="#city.Id"/>"><s:property value="#city.cityName"/></option>			  	
			  	</s:iterator>
			 </select>
			
			</span>
			<span>状态:</span> 
			<span>
			<select name="placeinfo.usable" class="qu_inp1" style="width: 80px">
				<option value="" selected="selected">全部</option>
				<option value="1">已发布</option>
				<option value="0">未发布</option>
			</select>
			</span>
			<input type="button" value="" class="qu_inp2" style="width: 65px" onclick="sub()"/>
			<input type="button" value="" onclick="goAdd()" class="qu_inp3" style="width: 65px"/>
		</div>
	</ul>
  </div>
  <div style="height: 5px;"></div>
		<div style="width: 100%">
			<iframe allowTransparency="true" name="result" id="iframe" 
				width="100%" frameborder="0" scrolling="no">
			</iframe>
		</div>
  </form>
  </body>
</html>
