<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta http-equiv="pragma" content="no-cache"></meta>
		<meta http-equiv="cache-control" content="no-cache"></meta>
		<meta http-equiv="expires" content="0">
		</meta>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
		<meta http-equiv="description" content="This is my page"></meta>
	<script>
	   function cancleDiv() {
	var str = parent.$(".boxy-modal-blackout").length;
	if(str!=1){
	// 父页面DIV层移除
	parent.$(".boxy-modal-blackout").eq(str-1).remove();
	// 父页面DIV遮罩移除
	parent.$(".boxy-wrapper").eq(str-1).remove();
	}else{
		// 父页面DIV层移除
		parent.$(".boxy-modal-blackout").eq(0).remove();
		// 父页面DIV遮罩移除
		parent.$(".boxy-wrapper").eq(0).remove();
	}
}
	</script>
		

	
		
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<form action="/cinema/filmmanage/filmmanage!details.action" method="post" id="editForm">
			<input type="hidden" name="filmmessage.id" value="${filmmessage.id }" />
			<div style="width: 18px"></div>
			<div style="width: 100%; height: 100%;" align="center">
				<table style="width: 90%;">
					<tr style="height: 25px" align="left">
						<td width="15%">
							电影编号:
						</td>
						<td width="36%">
							<input type="text" 
								value="${filmmessage.filmno }" readonly="readonly" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td width="13%">
							影片名称:
						</td>
						<td width="36%">
							<input type="text" readonly="readonly"
								value="${filmmessage.filmname }" />
						</td>
					</tr>
<!--					<tr style="height: 25px" align="left">-->
<!--						<td>-->
<!--							影片短名称:-->
<!--						</td>-->
<!--						<td>-->
<!--							<div class="font-style">-->
<!--								<input type="text" readonly="readonly"-->
<!--									value="${filmmessage.filmename }" />-->
<!--							</div>-->
<!--						</td>-->
<!--					</tr>-->
					<tr style="height: 25px" align="left">
						<td>
							导演:  
						</td>
						<td>
							<div class="font-style">
								<input type="text" readonly="readonly"
									value="${filmmessage.direct}" />
							</div>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							演员:
						</td>
						<td>
							<div class="font-style">
								<input type="text" readonly="readonly"
									value="${filmmessage.player}" />
							</div>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							编剧:
						</td>
						<td>
							<div class="font-style">
								<input type="text" readonly="readonly"
									value="${filmmessage.writer }" />
							</div>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							评分:
						</td>
						<td>
							<div class="font-style">
								<input type="text" readonly="readonly"
									value="${filmmessage.grade }" />
							</div>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							影片类型:
						</td>
						<td>
							<input type="text" readonly="readonly"
									value="${filmmessage.genrename }" />
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							版本类型:
						</td>
						<td>
							<div class="font-style">
							<c:if test="${filmmessage.version_type==1}">
								<input type="text" readonly="readonly" value="IMAX"/>
						    </c:if>	
						    <c:if test="${filmmessage.version_type==2}">
								<input type="text" readonly="readonly" value="2D"/>
						    </c:if>	
						    <c:if test="${filmmessage.version_type==3}">
								<input type="text" readonly="readonly" value="3D"/>
						    </c:if>			
							</div>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							上映时间:
						</td>
						<td>
							<input type="text" readonly="readonly" value="${filmmessage.showtime }">
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							国家:
						</td>
						<td>
							<input type="text" readonly="readonly" value="${filmmessage.nationname}">
						</td>
					</tr>
					<tr style="height: 25px" align="left">

						<td>
							影片简介:
						</td>
						<td colspan="3">
							<textarea readonly="readonly" cols="45" rows="5" />${filmmessage.filmsis }</textarea>
						</td>
					</tr>
					<tr style="height: 25px" align="left">
						<td>
							剧情简介:
						</td>
						<td colspan="3">
							<textarea readonly="readonly" cols="45" rows="5" />${filmmessage.getshow}</textarea>
						</td>
					</tr>
<!--					<tr style="height: 25px" align="left">-->
<!--						<td>-->
<!--							影片横版:-->
<!--						</td>-->
<!--						<td>-->
<!--					      		<div id="previews">-->
<!--					      	  		<img id="img" src="<%=imgPath%>${dzs}"  width="80px" height="60"/>-->
<!--					      	  	</div>-->
<!--						</td>-->
<!--					</tr>-->
				
					<tr style="height: 25px" align="left">
						<td>
							影片竖版:
						</td>
						<td>
					      		<div id="preview">
					      	  		<img id="img" src="<%=imgPath%>${dz}"  width="80px" height="60"/>
					      	  	</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
