package cn.cinema.manage.iface;

public enum AppCode {

	//应用编码定义
	ANDROID,
	IOS,
	WEIXIN;
	
	public static class A{
		public static String getCode(String appCode){
			if(AppCode.ANDROID.toString().equals(appCode))return "2";
			else if(AppCode.IOS.toString().equals(appCode))return "3";
			else if(AppCode.WEIXIN.toString().equals(appCode))return "4";
			else return "0";
		}
	}
}
