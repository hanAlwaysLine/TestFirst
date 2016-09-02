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
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0">    </meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/newMainIframeDiv_2.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css" />
	<script type="text/javascript" src="<%=basePath %>common/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
	<script type="text/javascript" src="<%=basePath%>cmanage/cmanageEdit.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/newMainIframeDiv_2.css"/>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>common/css/page.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="/cinema/manage/cmanage!updateDetailedinformation.action" method="post" name="editForm" id="editForm">
  <div style="width: 10px"></div>
  <div style="width: 100%;height: 100%;" align="center">
   <table style="width: 90%;">
	 <tr style="height: 25px" align="left">
			<td width="13%">
				城市名称:
			</td>
			<td colspan="3" align="left">
				<select name="placeinfo.cityno" disabled="disabled">
					<option value="">
						全部
					</option>
					<s:iterator value="#request.lcity" id="city" status="i">
						<option value="<s:property value="#city.Id"/>" 
							<s:if test="%{#city.Id==#request.placeinfo.cityno}">selected</s:if>
						>
						
							<s:property value="#city.cityName" />
						</option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院ID:
			</td>
			<td colspan="3" align="left">
			<input name="placeinfo.placeno" type="text" value="${placeinfo.placeno }" disabled="disabled"/>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院名称:
			</td>
			<td width="21%" align="left">
				<input name="placeinfo.placename" type="text" value="${placeinfo.placename }" disabled="disabled"/>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				CINEMALINKID:
			</td>
			<td colspan="3" align="left">
				<input name="placeinfo.cinemalinkid" id="cmad" value="${placeinfo.cinemalinkid }" type="text" disabled="disabled"/>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
		  	<td>
		  		电话:
		  	</td>
		  	<td align="left"><input name="placeinfo.phone" value="${placeinfo.phone}" type="text" disabled="disabled"/></td>
		 	</tr>
		<!-- tr style="height: 25px">
			<td>
				经纬度:
			</td>
			<td align="left">
				<input name="placeinfo.postion" value="${placeinfo.postion }" type="text" disabled="disabled"/>
			</td>
		</tr> -->
		<tr style="height: 25px" align="left">
			<td>
				微博地址:
			</td>
			<td align="left">
				<input name="placeinfo.weibolink" value="${placeinfo.weibolink }" type="text" disabled="disabled"/>
			</td>
		</tr>
		<tr align="left">
			<td>图片:</td>
			<td><div title="图片" ><img src="<%=basePath%>${placeinfo.image }" width="120px" height="110px"/></div></td>
		</tr>
		<tr align="left">
			<td>地图图片:</td>
			<td><div title="地图图片" ><img src="<%=basePath%>${placeinfo.mappic }" width="120px" height="110px"/></div></td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院详情:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.information" cols="42" rows="9" disabled="disabled">${placeinfo.information}</textarea>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				营业时间:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.business_hours" cols="42" rows="4" disabled="disabled">${placeinfo.business_hours }</textarea>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				交通资讯:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.route" cols="42" rows="4" disabled="disabled">${placeinfo.route}</textarea>
			</td>
		</tr>
		<tr style="height: 25px" align="left">
			<td>
				影院地址:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.address" cols="42" rows="4" disabled="disabled">${placeinfo.address}</textarea>
			</td>
		</tr>
			<tr style="height: 25px" align="left">
			<td>
				会员提示信息:
			</td>
			<td colspan="3" align="left">
				<textarea name="placeinfo.vipalert" cols="42" rows="3" disabled="disabled">${placeinfo.vipalert}</textarea>
			</td>
		</tr>
	  <tr>
	  	<td colspan="4">
	  		<hr />
	  	</td>
	  </tr>
	  <tr align="center">
	  	<td colspan="2">
	  	<input type="button" onClick="cancleDiv()" class="word_ft5" style="height:26px;width:63px;border:none;margin-left:5px;cursor: pointer;"/>
	  	</td>
	  </tr>
  </table>
  </div>
  </form>
  </body>
</html>
