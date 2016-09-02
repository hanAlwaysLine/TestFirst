package cn.cinema.manage.timer;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.entity.manage.T0103_HallInfo;
import cn.cinema.manage.entity.token.TokenResult;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.XmlPojoUtil;
import cn.cinema.manage.util.getWebService;

public class Halltimer extends TimerTask{
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(FeatureSeatStatetimer.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	
	private getWebService getWebService = new getWebService();
	
	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	@Override
	public void run() {
		try{
		T0103_HallInfo placehallInfo=new T0103_HallInfo();
	    String result = null;
	    
	    String pAppCode = "TEST";
	    String pCinemaID = "gzxxx";
		String checkKey = "12345678";
		String payurl = Messages.getString("url");
		String paynameSpace = Messages.getString("namespace");
		
		String xmlToken = getTokenCls.main(new String[1],payurl,paynameSpace, pAppCode, checkKey);
		TokenResult  tn = (TokenResult) XmlPojoUtil.createPojo(xmlToken, "token.TokenResult");
		String paramers=pAppCode+pCinemaID+tn.getTokenID()+tn.getToken();
		Md5 md5 = new Md5();
		String pVerifyInfo = md5.getMD5ofStr1(URLEncoder.encode(paramers.toLowerCase() + checkKey, "UTF-8")).substring(8,24).toLowerCase();
		
		String url=Messages.getString("url");//url地址
		String []parameter={"pAppCode","pCinemaID","TID","pVerifyInfo"};
		Object []message={pAppCode,pCinemaID,tn.getTokenID(),pVerifyInfo};
		
	    result = (String) getWebService.getWebService(message, "GetHall", url, paynameSpace, parameter);
	    logger.info("---------开始同步影厅------");
	    Document document = null;
		document = DocumentHelper.parseText(result);
		Element employees = document.getRootElement();	 
		List<Element> list = employees.elements("Halls");	
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			List wtnodes = element.elements("Hall");
			for (Iterator it = wtnodes.iterator(); it.hasNext();){
				Element elm = (Element) it.next();
        		placehallInfo.setHallname(elm.elementText("HallName"));
        		placehallInfo.setHallno(elm.elementText("HallNo"));
//        		placehallInfo.setPlaceno(pCinemaID);
        		Map map=new HashMap();
        		map.put("hallno", elm.attributeValue("id"));
        		map.put("placeno", pCinemaID);
        		Integer hallcount=	(Integer) bs.queryForObject("select_allHall", map);
        		placehallInfo.setHallseat(Integer.parseInt(elm.attributeValue("seatcount")));
        		if(hallcount.equals(0)){
        			bs.save("insert_hall", placehallInfo);
        		}else{
        			bs.update("update_hall", placehallInfo);
        		}
        	
			}
		}
		logger.info("---------影厅同步完毕------");
		}catch(Exception e){
			logger.info("getWebService调用异常！");
		}

	}
	
	
}
