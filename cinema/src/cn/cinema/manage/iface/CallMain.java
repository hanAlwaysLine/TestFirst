package cn.cinema.manage.iface;

import java.net.URLEncoder;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import com.thoughtworks.xstream.XStream;
import cn.cinema.manage.entity.app.RealCheckSeatStateParameter;
import cn.cinema.manage.entity.app.SeatInfoBean;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;

public class CallMain {

	// 应用号
	private static String app_Code = Messages.getString("appcode");
	// 中心地址
	private static String endpoint = Messages.getString("cinemamainurl");
	// 中心命名空间
	private static String main_NS = Messages.getString("mainnamespace");
	// 加密key
	private static String PCheckKey = Messages.getString("pcheckkey");
	
	
	// 合作商编码
	private static String app_Code_pay = Messages.getString("payappcode");
	// 交易地址
	private static String endpoint_pay = Messages.getString("cinemapayurl");
	// 交易命名空间
	private static String main_NS_pay = Messages.getString("cinemapaynamespace");
	// 交易加密key
	private static String PCheckKey_pay = Messages.getString("paycheckkey");
	
	/**
	 * 调用中心接口
	 * @param methodName  调用方法名
	 * @param params 参数
	 * @return
	 */
	public static String invoke(String methodName, String... params){
		String result = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(new QName(main_NS, methodName));
			Md5 md5val = new Md5();
			
			Object[] oArray = new Object[params.length+2];
			oArray[0] = app_Code;
			String ps = "";
			for(int i = 0; i < params.length ; i ++){
				oArray[i+1] = params[i];
				ps += params[i];
			}
			String pVerifyInfo = md5val.getMD5ofStr(URLEncoder.encode((app_Code + ps + CPO.getToken() + PCheckKey).toLowerCase(), "utf-8")).substring(8, 24).toLowerCase();
			
			oArray[params.length+1] = pVerifyInfo;
			result = (String) call.invoke(oArray);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 交易类接口调用
	 * @param methodName 调用方法名
	 * @param params 参数
	 * @return
	 */
	public static String invokePay(String methodName, boolean isMemo, String... params){
		String result = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint_pay);
			call.setOperationName(new QName(main_NS_pay, methodName));
			Md5 md5val = new Md5();
			
			Object[] oArray = new Object[params.length+2];
			oArray[0] = app_Code_pay;
			String ps = "";
			for(int i = 0; i < params.length ; i ++){
				oArray[i+1] = params[i];
				if(isMemo){
					if(i < params.length-1){
						ps += params[i];
					}
				}else{
					ps += params[i];
				}
			}
			String pVerifyInfo = md5val.getMD5ofStr(URLEncoder.encode((app_Code_pay + ps + PCheckKey_pay).toLowerCase(), "utf-8")).substring(8, 24).toLowerCase();
			
			oArray[params.length+1] = pVerifyInfo;
			result = (String) call.invoke(oArray);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 锁座
	 * @param featureappno  排期应用号
	 * @param serialNum 流水号
	 * @param recvmobileno 手机号
	 * @param seatinfo 座位信息
	 * @return
	 */
	public static String RealCheck(String featureappno, String serialNum, String recvmobileno, List<SeatInfoBean> sList){
		String result = "";
		try {
			RealCheckSeatStateParameter rssp = new RealCheckSeatStateParameter();
			rssp.setAppCode(app_Code);
			rssp.setFeatureAppNo(featureappno);
			rssp.setSerialNum(serialNum);
			rssp.setPayType("00");
			rssp.setRecvMobilePhone(recvmobileno);
			rssp.setTokenID(CPO.getpTokenID());
			rssp.setSeatInfos(sList);
			// 组成加密串
			String VerifyInfo = rssp.getAppCode() + rssp.getFeatureAppNo()
					+ rssp.getSerialNum() + sList.size() + rssp.getPayType()
					+ rssp.getRecvMobilePhone()+rssp.getTokenID();
			System.out.println(VerifyInfo);
			Md5 md5val = new Md5();
			// 加密
			String pVerifyInfo = md5val.getMD5ofStr(URLEncoder.encode((VerifyInfo +CPO.Token+ PCheckKey).toLowerCase(),"UTF-8")).substring(8, 24).toLowerCase();

			rssp.setVerifyInfo(pVerifyInfo);
			XStream xStream = new XStream();
			xStream.alias("RealCheckSeatStateParameter",
					RealCheckSeatStateParameter.class);
			xStream.alias("SeatInfo", SeatInfoBean.class);
			// 锁坐接口所需参数xml串
			String xmlString = "<?xml version=\"1.0\"?>" + xStream.toXML(rssp);
			System.out.println(xmlString);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(new QName(main_NS, "LiveRealCheckSeatState"));
			// 调用锁坐接口
			result = (String) call.invoke(new Object[] { xmlString });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
