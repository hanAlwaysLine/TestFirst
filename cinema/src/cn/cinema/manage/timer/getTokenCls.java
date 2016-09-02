package cn.cinema.manage.timer;

import java.net.URLEncoder;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import cn.cinema.manage.util.Md5;


public class getTokenCls {
	public static String main(String[] args,String payurl,String paynameSpace,String pAppCode,String checkKey) throws Exception {
		
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(payurl);
        call.setOperationName(new QName(paynameSpace, "GetToken"));// 1.命名空间 2.方法名
		// 参数设置
		call.addParameter("pAppCode", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("pVerifyInfo", XMLType.XSD_STRING, ParameterMode.IN);
		Md5 md5 = new Md5();
		String pVerifyInfo = md5.getMD5ofStr1(URLEncoder.encode(pAppCode.toLowerCase() + checkKey, "UTF-8")).substring(8, 24).toLowerCase();
		// 返回类型设置
		call.setReturnType(XMLType.XSD_STRING);
		String result = (String) call.invoke(new Object[] {pAppCode,pVerifyInfo });
		System.out.println(result);	
		
		return result;
	}
}
