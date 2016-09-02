<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"></base>
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>	
	<script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>filmmanage/fimewarnmanageAdd.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/validate.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/newMainIframeDiv_2.css"/>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var msg = "${requestScope.msg}"
		var img="";
		if (msg != null && "" != msg) {
			if(msg.indexOf("失败")>0)
			{
				img="<img src='/cinema/common/Images/cha.png' border='0' align='absmiddle'/>" 
			}
			else
			{
				img="<img src='/cinema/common/Images/gou.png' border='0' align='absmiddle'/>";
			}
			parent.parent.susAlert(img+msg);//显示成功与失败的图片
			
			var boxy_1 = parent.$(".boxy-wrapper").eq(0);
			var divIframe = $(boxy_1).find("iframe");
			var formCopy = divIframe[0].contentWindow.document.forms["editfimeForm"];
			formCopy.submit();

			parent.$(".boxy-modal-blackout").eq(1).remove();
			parent.$(".boxy-wrapper").eq(1).remove();
			
		}
	</script>
  </head>
  
  <body>
  <form action="/cinema/filmmanage/filmmanage!savefimewarn.action" method="post" enctype="multipart/form-data" name="addForm" id="addForm">
  <input type="hidden" name="fimewarn.film_d" value='<%=request.getParameter("id") %>'/>
  <input type="hidden" name="type" value="" id="ty"/>
  <div style="width: 10px"></div>
  <div style="width: 100%;height: 100%;" align="center">
  <table border="1" style="width: 90%" class="">
	   <tr style="height: 25px" align=left>
	  	<td>
	  		视频:
	  	</td>
	  	<td>
        	<input name="uploadimg" type="file" id="uploadimg"/>           
  	     </td>
  	     <td>
  	     	<font color="red">*视频格式为flv|mp4且小于100MB</font>
  	     </td>
	  </tr>
	  <tr style="height: 25px" align="left">
	  	<td>
	  		预览图:
	  	</td>
	  	<td>
        	<input name="uploadimgs" type="file" id="uploadimgs"/>           
  	     </td>
  	     <td><font color="red">*图片尺寸为90*126</font></td>
	  </tr>
      <tr>
      	<td>
      		<div id="preview">
      	  	</div>
   	    </td>
      </tr>	  
	  <tr align="center">
	  	<td colspan="4">
	  	<input type="button" value="" class="mms_bt1" onclick="checktype();" />
	  	<input type="button" value="" onclick="cancleDiv();" class="word_ft5"/>
	  	</td>
	  </tr>
  </table>
  </div>
  </form>
  </body>
</html>
