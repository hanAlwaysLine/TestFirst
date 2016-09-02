package cn.cinema.manage.timer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.entity.token.TokenResult;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.XmlPojoUtil;
import cn.cinema.manage.util.getWebService;

/**
 * TODO
 * 
 * @ClassName FeatureSeatStatetimer
 * @Description TODO
 * @author 汤凤欣
 * @date 2012-12-12
 */
public class FeatureSeatStatetimer extends TimerTask{
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(FeatureSeatStatetimer.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	
	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	private getWebService getWebService = new getWebService();
	
	@Override
	public void run() {
			try {
				logger.info("开始同步排期剩余座位数"+new Date());
				//查询未过期排期
				Date date = new Date();
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = df.format(date);
				List<T0201_FEATURE_APP> featureList = bs.queryForList("selectFeatureState",dateStr);
				for (int i = 0; i < featureList.size(); i++) {
					try {
						T0201_FEATURE_APP feature = featureList.get(i);
						//如果排期总座位数为空 查总座位数
						if ( feature.getTotalseats() == null || "".equals(feature.getTotalseats()) || "0".equals(feature.getTotalseats())) {
							int totalseats = this.qryTotalseats(feature);
							if (totalseats > 0) {
								feature.setTotalseats(Integer.toString(totalseats));
							}
						}
						//查询剩余座位数
						if ( feature.getTotalseats() != null && !"".equals(feature.getTotalseats()) && !"0".equals(feature.getTotalseats())) {
							int remainingseats = this.qryRemainingseats(feature);
							logger.info("剩余座位数 :"+remainingseats);
							feature.setRemainingseats(Integer.toString(remainingseats));
							//执行修改
							bs.update("updateFeatureSeats", feature);
						}
					} catch (Exception e) {
						logger.info("同步座位失败");
						e.printStackTrace();
					}
				}
				logger.info("结束同步排期剩余座位数"+new Date());
			} catch (Exception e) {
				logger.info("同步排期剩余座位数异常");
				logger.info(e);
				e.printStackTrace();
			}
	}

	//根据排期获得剩余座位数
	private int qryRemainingseats(T0201_FEATURE_APP feature) throws Exception {
		//剩余座位数
		int remainingseats = 0;
		try {
			String pAppCode = "TEST";
			String checkKey = "12345678";
			String paynameSpace = Messages.getString("namespace");
			String url=Messages.getString("url");//url地址
			
			String xmlToken = getTokenCls.main(new String[1],url,paynameSpace, pAppCode, checkKey);
			TokenResult  tn = (TokenResult) XmlPojoUtil.createPojo(xmlToken, "token.TokenResult");
			
			String paramers=pAppCode+feature.getFeatureappno()+tn.getTokenID()+tn.getToken();
			
			Md5 md5 = new Md5();
			String pVerifyInfo = md5.getMD5ofStr1(URLEncoder.encode(paramers.toLowerCase() + checkKey, "UTF-8")).substring(8,24).toLowerCase();
			
			String []parameter={"pAppCode","pFeatureAppNo","pTokenID","pVerifyInfo"};
			Object []message={pAppCode,feature.getFeatureappno(),tn.getTokenID(),pVerifyInfo};
			
			for (int i = 0; i < message.length; i++) {
				logger.info(parameter[i]+" : "+message[i]);
			}
			long begintime = System.currentTimeMillis();
			logger.info("当前时间 :"+new Date());
			String result = (String) getWebService.getWebService(message, "GetPlanSiteStatistic", url, paynameSpace, parameter);
			long endtime = System.currentTimeMillis(); 
			logger.info("当前时间 :"+new Date());
			logger.info("GetPlanSiteStatistic接口调用耗时 :"+(endtime-begintime)+"");
			logger.info("GetPlanSiteStatistic返回 : "+result);
			Document document = null;
			document = DocumentHelper.parseText(result);
			Element rootEle = document.getRootElement();
			remainingseats =  Integer.parseInt(rootEle.elementText("SeatToSellAmount"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return remainingseats;
	}

	//根据排期获得总座位数
	private int qryTotalseats(T0201_FEATURE_APP feature) {
		int totalseats = 0;
		try {
			String pAppCode = "TEST";
			String checkKey = "12345678";
			String paynameSpace = Messages.getString("namespace");
			String url=Messages.getString("url");//url地址
			
			String xmlToken = getTokenCls.main(new String[1],url,paynameSpace, pAppCode, checkKey);
			TokenResult  tn = (TokenResult) XmlPojoUtil.createPojo(xmlToken, "token.TokenResult");
			
			String paramers=pAppCode+feature.getFeatureappno()+tn.getTokenID()+tn.getToken();
			
			Md5 md5 = new Md5();
			String pVerifyInfo = md5.getMD5ofStr1(URLEncoder.encode(paramers.toLowerCase() + checkKey, "UTF-8")).substring(8,24).toLowerCase();
			
			String []parameter={"pAppCode","pFeatureAppNo","pTokenID","pVerifyInfo"};
			Object []message={pAppCode,feature.getFeatureappno(),tn.getTokenID(),pVerifyInfo};
			
			for (int i = 0; i < message.length; i++) {
				logger.info(parameter[i]+" : "+message[i]);
			}
			long begintime = System.currentTimeMillis();
			logger.info("当前时间 :"+new Date());
			String result = (String) getWebService.getWebService(message, "GetPlanSiteStatistic", url, paynameSpace, parameter);
			long endtime = System.currentTimeMillis(); 
			logger.info("当前时间 :"+new Date());
			logger.info("GetPlanSiteStatistic接口调用耗时 :"+(endtime-begintime)+"");
			logger.info("GetPlanSiteStatistic返回 : "+result);
			Document document = null;
			document = DocumentHelper.parseText(result);
			Element rootEle = document.getRootElement();
			totalseats =  Integer.parseInt(rootEle.elementText("TotalSeatAmount"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return totalseats;
	}
}
