package cn.cinema.manage.action.weixin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.SHA1;
import cn.cinema.manage.util.ServiceLocator;

/**
 * 微信
 * 
 * @author yangdx
 * 
 */
public class Weixin {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Weixin.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();
	//微信加密签名
	private String signature;
	//时间戳
	private String timestamp;
	//随机数
	private String nonce;
	//随机字符串.
	private String echostr;
	private String  token="cinema";
	
	//用户授权后 返回code
	private String code;
	//用户授权后 返回状态
	private String state;
	public void valWeixin(){
		logger.info("请求信息,signature:"+signature);
		logger.info("请求信息,timestamp:"+timestamp);
		logger.info("请求信息,nonce:"+nonce);
		logger.info("请求信息,echostr:"+echostr);
		String[] param=new String[]{token,timestamp,nonce};
		Arrays.sort(param);
		
		StringBuffer sb=new StringBuffer();
		for(String str:param){
			sb.append(str);
		}
		
		logger.info("paramStr:"+sb.toString());
		String sha1str=SHA1.SHA1_STR(sb.toString());
		logger.info("sha1str:"+sha1str+"----"+signature);
		try {
			if(sha1str.equals(signature)){
				response.getWriter().write(echostr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//用户授权后 重定向地址方法
	public void redWexin(){
		logger.info("微信返回,code,code:"+code);
		logger.info("微信返回,state:"+state);
		
		
	}
	

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public static void main(String[] args) {
		HttpClient clinet =new HttpClient();
		String uri="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9abd6d3519cdec28&secret=e2d87aa799640bcb3f2656877b351fb4";
		PostMethod post=new PostMethod(uri);
		try {
				clinet.executeMethod(post);
		String str=		post.getResponseBodyAsString();
		System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}

