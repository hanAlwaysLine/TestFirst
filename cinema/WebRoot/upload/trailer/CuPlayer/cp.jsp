<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mvPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
String N_path = mvPath+request.getParameter("warnpic");

%>
<script type="text/javascript" src="Images/swfobject.js"></script>
<div id="CuPlayer" > <strong>酷播迷你(CuPlayerMiniV2.2) 提示：您的Flash Player版本过低，请<a href="http://www.CuPlayer.com/" >点此进行播放器升级</a>！</strong> </div>
<script type=text/javascript>
var w_path = "<%=N_path%>"
var so = new SWFObject("CuPlayerMiniV22_Black_S.swf","CuPlayer","525","320","9","#000000");
so.addParam("allowfullscreen","true");
so.addParam("allowscriptaccess","always");
so.addParam("wmode","opaque");
so.addParam("quality","high");
so.addParam("salign","lt");	  
so.addVariable("CuPlayerFile",w_path);
so.addVariable("CuPlayerImage","Images/flashChangfa2.jpg");
so.addVariable("CuPlayerLogo","Images/mylogo.png");
so.addVariable("CuPlayerShowImage","true");
so.addVariable("CuPlayerWidth","525");
so.addVariable("CuPlayerHeight","320");
so.addVariable("CuPlayerAutoPlay","true");
so.addVariable("CuPlayerAutoRepeat","false");
so.addVariable("CuPlayerShowControl","true");
so.addVariable("CuPlayerAutoHideControl","false");
so.addVariable("CuPlayerAutoHideTime","6");
so.addVariable("CuPlayerVolume","80");
so.write("CuPlayer");
</script>

