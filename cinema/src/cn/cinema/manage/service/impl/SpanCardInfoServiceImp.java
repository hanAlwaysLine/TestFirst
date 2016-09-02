package cn.cinema.manage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.dao.BaseDao;
import cn.cinema.manage.entity.Activitymanage.T0905_LOGRECORD;
import cn.cinema.manage.service.SpanCardInfoService;



public class SpanCardInfoServiceImp implements SpanCardInfoService {
	public BaseDao dao;
	/**
	 *日志 
	 */
	private Logger logger = Logger.getLogger(SpanCardInfoServiceImp.class);
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
	public String SpanCardInfo(String xml) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			Document dc = DocumentHelper.parseText(xml);
			Element root = dc.getRootElement();
			String cardId = root.elementTextTrim("CardId");//会员卡号
			//每张票的原始价格
			T0905_LOGRECORD logrecord = new T0905_LOGRECORD();
			logrecord.setCARDFACADECD(cardId);
			 List listAcc = dao.queryForList("query_T0905_LOGRECORD", logrecord);
			 if(listAcc.size()!=0){
				 //转换实体
				 T0905_LOGRECORD account = (T0905_LOGRECORD) listAcc.get(0);
				 result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	 			 "<CardInfoReturn>" +
	 			 "<ResultCode>1</ResultCode>" +
	 			 "<CARDFACADECD>"+account.getCARDFACADECD()+"</CARDFACADECD>" +
	 			"<IDCARD>"+account.getIDCARD()+"</IDCARD>" +
	 			"<APPCODE>"+account.getAPPCODE()+"</APPCODE>" +
	 			"<MOBILEPHONE>"+account.getMOBILEPHONE()+"</MOBILEPHONE>" +
	 			"<USERID>"+account.getUSERID()+"</USERID>" +
	 			"<ADDRESS>"+account.getADDRESS()+"</ADDRESS>" +
	 			"<BIRTHDATE>"+account.getBIRTHDATE()+"</BIRTHDATE>" +
	 			"<MAILADDRESS>"+account.getMAILADDRESS()+"</MAILADDRESS>" +
	 			"<CONTACTPHONE>"+account.getCONTACTPHONE()+"</CONTACTPHONE>" +
	 			"<SEX>"+account.getSEX()+"</SEX>" +
	 			"<REGISTERDT>"+account.getREGISTERDT()+"</REGISTERDT>" +
	 			 "</CardInfoReturn>";
				return result;
				//支付
			 }else{
				 //会员不存在
				 result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	 			 "<CardInfoReturn>" +
	 			 "<ResultCode>1</ResultCode>" +
	 			 "<ResultMsg>会员不存在，用户名或密码错误</ResultMsg>" +
	 			 "</CardInfoReturn>";
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
 			 "<CardInfoReturn>" +
 			 "<ResultCode>1</ResultCode>" +
 			 "<ResultMsg>发生异常</ResultMsg>" +
 			 "</CardInfoReturn>";
		}
		return result;
	}

}
