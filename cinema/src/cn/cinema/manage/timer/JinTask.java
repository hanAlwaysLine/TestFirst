package cn.cinema.manage.timer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.TimerTask;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;


public class JinTask extends TimerTask {
	// 应用号
	private String app_Code = Messages.getString("appcode");
	// 中心地址
	private String endpoint = Messages.getString("cinemamainurl");
	// 中心命名空间
	private String main_NS = Messages.getString("mainnamespace");
	// 加密key
	private String PCheckKey = Messages.getString("pcheckkey");
 public void run() {
     //可以定义自己要处理的方法
		
			try {
				String result = "";
				String GetPlanSite_MName = "GetToken";
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName(new QName(main_NS, GetPlanSite_MName));
				Md5 md5val = new Md5();
				result = (String) call.invoke(new Object[] {
						app_Code,
						md5val.getMD5ofStr(URLEncoder.encode((app_Code+ PCheckKey).toLowerCase(), "utf-8"))
								.substring(8, 24).toLowerCase() });
				System.out.println(result+"-----------");
				Document doc = null;
				 try {
					doc = DocumentHelper.parseText(result);
					 Element rootElt = doc.getRootElement(); // 获取根节点
					 Element MEsendResult = rootElt.element("ResultCode");
					 if("0".equals(MEsendResult.getText()))
					 {
						 CPO.setpTokenID(rootElt.element("TokenID").getText());
						 CPO.setToken(rootElt.element("Token").getText());
					 }
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
  }

 

}