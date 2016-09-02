<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
		<script type="text/javascript">
			var UPLOAD_PATH = "<%=basePath%>";
			var imgPath = "<%=imgPath%>";
		</script>
		<script type="text/javascript"
			src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>goods/goodsEdit.js"></script>
		<script type="text/javascript" src="<%=basePath %>common/js/My97DatePicker/WdatePicker.js"></script>	
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
			
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/validate.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath %>common/css/page.css" />
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/fileprogress.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/handlers.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/swfupload/js/swfupload.queue.js"></script>
		<script type="text/javascript">
		$(document).ready(
			function() {
	setframeifHeight('add');
			});
		
	</script>	
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
		<form action="/cinema/goods/goods!updHall.action"
			method="post" name="editForm" id="editForm" enctype="multipart/form-data">
			<input type="hidden" name="goods.GOODS_PIC" id="GOODS_PIC" value="${goods.GOODS_PIC}"/>
			<input type="hidden" name="oldPicUrl" value="${goods.GOODS_PIC}"/>
			<input type="hidden" name="goods.GOODS_ID" value="${goods.GOODS_ID }"/>
			<input type="hidden" id="oldgoodsname" value="${goods.GOODS_NAME}"/>
			<input type="hidden" id="msg" value="${msg}"/>
			<div style="width: 10px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
				    <tr style="height: 25px" align="left">
						<td width="20%">
							卖品名称:
						</td>
						<td colspan="2" align="left" width="80%">
							<input name="goods.GOODS_NAME" id="GOODS_NAME" type="text" value="${goods.GOODS_NAME }" />
						</td>                     
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							价格:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="goods.GOODS_PRICE" id="GOODS_PRICE"  value="${goods.GOODS_PRICE }" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							分类:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="goods.GOODS_TYPE" id="GOODS_TYPE" value="${goods.GOODS_TYPE }"  />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							是否促销物品:
						</td>
						<td colspan="2" align="left">
						<select name="goods.IS_DISCOUNT" id="IS_DISCOUNT">
							<option value="1" <s:if test="#goods.IS_DISCOUNT==1">selected</s:if> >是</option>
							<option value="0" <s:if test="#goods.IS_DISCOUNT==0">selected</s:if> >否</option>
						</select>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							可用数量:
						</td>
						<td colspan="2" align="left">
						    <input type="text" name="goods.GOODS_COUNT" id="GOODS_COUNT"  value="${goods.GOODS_COUNT }" />
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td colspan="2" align="left">
							<textarea rows="5" cols="19" name="goods.GOODS_MEMO">${goods.GOODS_MEMO}</textarea>
						</td>
					</tr>
		 			   <tr align="left">
							<td>
							    <input name="text" id="upload_images" type="button" />
							</td>
						    <td>
							   <img src="<%=imgPath%>${goods.GOODS_PIC}" id="img" width="130px;" height="100px;"/>
							  <!-- 进度条 -->
							  <div style="width:130px;position: absolute;display:none" id="upload_progresse" div-css="10">
    						  <div id="upload_progress_bge"  style="background: #8BABCE; width: 20%; height: 20px; float:left; margin-top: 35px;"></div>
    						  </div>
						    </td>
						    <td align="left"><font color="red">*规格尺寸（198*158px）</font></td>
					   </tr>
					<tr>
						<td colspan="3">
							<hr />
						</td>
					</tr>
					<tr align="center">
						<td colspan="3">
							<input type="button" onclick="doEdit()" class="word_ft3" />
							<input type="button" onclick="cancleDiv()" class="word_ft5" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
