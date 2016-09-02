<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/cinemamanage" prefix="cinemamanage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>common/js/jquery.json-2.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>common/js/comm.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>featuremanage/featuremanageResult.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>common/css/page.css" />
		<style>
#apdiv {
	color: #212121;
	position: absolute;
	word-wrap: break-word;
	background-color: #DBE9C5;
	line-height: 20px;
	padding: 5px;
	font-size: 12px;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
}
</style>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<body>
	<div id="loading" style="width: 200px; margin-left: 30%; margin-top: 170px; position: fixed !important;
        position: absolute; z-index: 999; display: none">
        <div class="UpdateProgress">
            <img src="<%=basePath%>common/imgs/loading.gif" />
        </div>
    </div>
	 <div class="qu_div" style="*margin-left:8px;*width:813px;">
		<div>
			<table>
				<tr>
					<td>
						<span>数字价格：</span>
					</td>
					<td>
						<input type="text" id="m2price" name="mprice"  class="qu_inp1" onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')" />
					</td>
					
					<td>
						<span>3d价格：</span>
						<input type="text" id="m3price" name="mprice"  class="qu_inp1" onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')" />
					</td>
					
					<td>
						<span>微信价格：</span>
					</td>
					<td>
						<input type="text" id="mwprice" name="mwprice"  class="qu_inp1" onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')"/>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<span>价格类型：</span>
					</td>
					<td>
						<span><select name="priceflag" id="priceflag">
								<option value="0">百分比</option>
								<option value="1">底价</option>
							  </select>
						</span>
					</td>
					<td>
						<span><input type="button"  onclick="modifyprce();" value="更改价格" style="width: 55px"/></span>
						<span><input type="button"  onclick="updateStatus(1);" value="开售" style="width: 45px"/></span>
						<span><input type="button"  onclick="updateStatus(0);" value="停售" style="width: 45px"/></span>
					</td>
				</tr>
			</table>
	<div style="color: red;margin-left: 25px;margin-top:10px;font-size: 14px;">温馨提示:价格最终会上报给专资平台!</div>
			</div></div>
	<input type="hidden" id="sysDate" value="${sysDate}"/>
		<table class="data_table">
			<tr>
				<th width="4%">
					<input type="checkbox"  id="maina" name="maina" onclick="toggleCheck()"/>
				</th>
				<th width="10%">
					排期应用编码
				</th>
				<th width="10%">
					排期编码
				</th>
				<th width="12%">
					影片编码
				</th>
				<th width="10%">
					影片名称
				</th>
				<th width="10%">
					放映时间
				</th>
				<th width="4%">
					影厅
				</th>
				<th width="8%">
					结算价格
				</th>
<%--				<th width="10%">--%>
<%--					客户端价格--%>
<%--				</th>--%>
				<th width="8%">
					微信价格
				</th>
				<th width="5%">
					原始结算价
				</th>
<%--				<th width="10%">--%>
<%--					网站价格--%>
<%--				</th>--%>
				<th width="5%">
					是否可用
				</th>
				<th width="5%">
					开售状态
				</th>
				<th width="3%">
					类型
				</th>
				<th width="10%">
					更新时间
				</th>
			</tr>
			<s:iterator id="featuremanage" value="#request.list.objects"
				status="sat"><div> 
				</div><tr align="center">
					<td>
						<input type="checkbox"  name="modifyprice" value="${featuremanage.featureappno}" />
					</td>
					<td>
						<input type="hidden" id="feadate_${featuremanage.featureappno}" name="input_feadate" value="${featuremanage.featuredate } ${featuremanage.featuretime }"/>
						${featuremanage.featureappno}
					</td>
					<td>
						${featuremanage.featureno}
					</td>
					<td>
						${featuremanage.filmno }
					</td>
					<td>
						${featuremanage.filmname }
					</td>
					<td>
						${featuremanage.featuredate }
						${featuremanage.featuretime }
					</td>
					<td>
						${featuremanage.hallname }
					</td>
					<td>
						<s:if test="#featuremanage.usesign==0 && #featuremanage.setclose==1">
<%--							<s:if test="#featuremanage.setclose==1">--%>
								<input type="text" " id="${ featuremanage.featureappno}"
							value="${featuremanage.apppric}"
							name="${featuremanage.apppric}"
							onkeyup="if(isNaN(value))execCommand('undo')"
							onafterpaste="if(isNaN(value))execCommand('undo')"
							onchange="wapprice(this)" size="5" style="Width:50px"/>
<%--							</s:if>--%>
						</s:if>
						<s:else>
							${featuremanage.apppric}
						</s:else>
					</td>
<%--					<td>--%>
<%--						<input type="text" id="${ featuremanage.featureappno}"--%>
<%--							value="${featuremanage.androidpric}"--%>
<%--							onkeyup="if(isNaN(value))execCommand('undo')"--%>
<%--							onafterpaste="if(isNaN(value))execCommand('undo')"--%>
<%--							onchange="aprice(this)" size="5" />--%>
<%--					</td>--%>
					<td>
						<s:if test="#featuremanage.usesign==0 && #featuremanage.setclose==1">
								<input type="text" id="${featuremanage.featureappno}"
									value="${featuremanage.winxinpric}"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									onchange="wprice(this)" size="5" style="Width:50px"/>
						</s:if>
						<s:else>
							${featuremanage.winxinpric}
						</s:else>
					</td>
					<td>
						${featuremanage.originalapprice}
					</td>
<%--					<td>--%>
<%--						<input type="text" id="${ featuremanage.featureappno}"--%>
<%--							value="${featuremanage.websitepric}"--%>
<%--							onkeyup="if (isNaN(value))execCommand('undo')" --%>
<%--							onafterpaste="if(isNaN(value))execCommand('undo')"--%>
<%--							onchange="wsrice(this)" size="5" />--%>
<%--					</td>--%>
					<!--				<div onmouseover="showcon(this,event)" onmouseout="closecon(this)"-->
					<!--							style="height:28px;line-height:28px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">-->
					<!--							${featuremanage.features }-->
					<!--				</div>-->
					<td>
						<s:if test="#featuremanage.usesign==0 && #featuremanage.setclose==1">
								可用
						</s:if>
						<s:else>
								不可用
						</s:else>
					</td>
					<td>
						<s:if test="#featuremanage.status==1">
								开售
						</s:if>
						<s:else>
								未开售
						</s:else>
					</td>
					<td>
						${featuremanage.copytype}
					</td>
					<td>
						${featuremanage.updatetime}
					</td>
				</tr>
			</s:iterator>
		</table>
		<div style="height: 5px; clear: both;"></div>
		<div id="page">
			<cinemamanage:pages formId="queryForm" className="button_page" />
			<div style="height: 20px; clear: both;"></div>
		</div>
		<div id="apdiv" style="display: none;"></div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	</body>

</html>
