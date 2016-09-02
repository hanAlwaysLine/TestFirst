package cn.cinema.manage.timer;
import java.util.Date;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import cn.cinema.manage.util.CPO;

/**
 * TODO
 * 
 * @ClassName FeatureSeatStatetimer
 * @Description TODO
 */
public class WeixinToken extends TimerTask{
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(WeixinToken.class);
	
	 private   String weixin_appID;
	 private  String weixin_appsecret;

	
	@Override
	public void run() {
			try {
				logger.info("开始获得微信TOKEN"+new Date());
				HttpClient clinet =new HttpClient();
				String uri="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9abd6d3519cdec28&secret=e2d87aa799640bcb3f2656877b351fb4";
				PostMethod post=new PostMethod(uri);
				
				clinet.executeMethod(post);
				String str=	post.getResponseBodyAsString();
				logger.info("---->"+str);
				JSONObject obj=JSONObject.fromObject(str);
				CPO.weixinToken=obj.getString("access_token");
			} catch (Exception e) {
				logger.info("获得微信TOKEN");
				logger.info(e);
				e.printStackTrace();
			}
	}


	public void setWeixin_appID(String weixinAppID) {
		weixin_appID = weixinAppID;
	}


	public void setWeixin_appsecret(String weixinAppsecret) {
		weixin_appsecret = weixinAppsecret;
	}
	
	
	}
	

