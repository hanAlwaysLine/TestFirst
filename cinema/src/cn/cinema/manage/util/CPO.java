package cn.cinema.manage.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author yangdx
 *
 */
public class CPO {
 //影院系统使用期限
 public final static int EXPIRE_DATE=1;
 /*
  * 用戶名前綴.用戶MD5加密使用
  */
 public static String userPassPri ;
 /**
  * 发KEY加密前缀
  */
 public final static String key = "cinemaManageKey";
 
 public static String YYYY_MM_DD = "YYYY-MM-DD";

 public static String YYYY_MM_DD_HH24_mi_ss = "YYYY-MM-DD HH24:MI:SS";

 public static String HH24_mi_ss = "HH24:MI:SS";
 //服务器路径
 public static String serverUrl;
 //服务器路径
 public static String serverUploadUrl;
 //工作目录路径
 public static String webRootPath;

 public static String pTokenID;
 
 public static String Token;
 //微信TOKEN
 public static String weixinToken;
 
 public static Map<String ,String> warmap = new HashMap<String ,String >();

 
 public  void setWebRootPath(String webRootPath)
 {
     CPO.webRootPath = webRootPath;
 }
public  void setServerUrl(String serverUrl) {
	CPO.serverUrl = serverUrl;
}

public  void setServerUploadUrl(String serverUploadUrl) {
	CPO.serverUploadUrl = serverUploadUrl;
}
public static String getpTokenID() {
	return pTokenID;
}
public static void setpTokenID(String pTokenID) {
	CPO.pTokenID = pTokenID;
}
public static String getToken() {
	return Token;
}
public static void setToken(String token) {
	Token = token;
}


}
