package cn.cinema.manage.util;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.cinema.manage.spring.BaseService;

public class getWebService extends SqlMapClientDaoSupport {
	private BaseService bs = ServiceLocator.getBaseService();
	 public Object getWebService(Object[] message, String method, String url,
			    String nameSpace,String[] parameter) 
	 {
		 Logger logger = Logger.getLogger(getWebService.class);
		    Service service = new Service();
		    Object obj = null;
		    
			    try {
					Call call = (Call) service.createCall();
					call.setTargetEndpointAddress(new java.net.URL(url));
					call.setOperationName(new QName(nameSpace, method));
					for(int i=0;i<parameter.length;i++)
					{
						call.addParameter(new QName(nameSpace, parameter[i]),
								org.apache.axis.encoding.XMLType.XSD_STRING,
								javax.xml.rpc.ParameterMode.IN);
					}
				
//					call.addParameter(new QName(nameSpace, parameter[1]),
//					org.apache.axis.encoding.XMLType.XSD_STRING,
//					javax.xml.rpc.ParameterMode.IN);
					call.setUseSOAPAction(true);
					call.setReturnType(org.apache.axis.Constants.XSD_STRING);
					call.setSOAPActionURI(nameSpace+method);
					//设置超时时间
					call.setTimeout(10000);
					obj = call.invoke(message);
			    } catch (Exception e) {
					logger.info("getWebService调用异常！");
					e.printStackTrace();
				}
			  
		   
		    return obj;
	    } 
}
