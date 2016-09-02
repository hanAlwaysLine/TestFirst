package cn.cinema.manage.service.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.action.featuremanage.Featuremanage;
import cn.cinema.manage.service.FeatureService;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;

public class FeatureServiceImp implements FeatureService {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Featuremanage.class);
		
	public boolean SetFeaturePrice(String appno,String pAppPric) throws Exception{
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(Messages.getString("cinemamainurl"));
		call.setOperationName(new QName("http://webservice.main.cmts.cn", "SetFeaturePrice"));// 1.命名空间 2.方法名
		// 参数设置
		call.addParameter("pAppCode", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("pFeatureAppNo", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("pAppPric", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("pBalancePric", XMLType.XSD_DOUBLE, ParameterMode.IN);
		call.addParameter("pTokenID", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("pVerifyInfo", XMLType.XSD_DOUBLE, ParameterMode.IN);
		// 返回类型设置
		call.setReturnType(XMLType.XSD_STRING);
		String pBalancePric= pAppPric;
		String paramers=Messages.getString("appcode") + appno + pAppPric + pBalancePric;
		Md5 md5 = new Md5();
		String pVerifyInfo = md5.getMD5ofStr(URLEncoder.encode(paramers.toLowerCase() +"1829"+"abcdef"+ Messages.getString("pcheckkey"), "GB2312"))
		.substring(8, 24).toLowerCase();
		String result = (String) call.invoke(new Object[] { Messages.getString("appcode") ,  appno  , pAppPric , pBalancePric ,"1829", pVerifyInfo });
		
		Document dc = DocumentHelper.parseText(result);
		Element root = dc.getRootElement();
		Element e = root.element("ResultCode");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
	    String ResultCode=e.getText();
		if("0".equals(ResultCode)){
			return true;
		}else{
			logger.error("时间:"+ dates +"排期应用号:"+appno +"结算价:"+pAppPric+ "修改中心结算价格失败"+"ResultCode="+ResultCode);
		}
		return false;
	}

}
