package cn.cinema.manage.timer;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0111_FILMMESSAGE;
import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.entity.token.TokenResult;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.XmlPojoUtil;
import cn.cinema.manage.util.getWebService;
import cn.cinema.pojo.CinemaPlan;
import cn.cinema.pojo.T0201_feature_app;

/**
 * TODO
 * 
 * @ClassName FilmFeatureUtil
 * @Description TODO
 * @author 汤凤欣
 * @date 2012-11-16
 */
public class FilmFeatureUtil{
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(FilmFeatureUtil.class);
	// 跨影院  单影院 表示
	private String institution_id = Messages.getString("institution_id");
	// 应用号
	private String app_Code = Messages.getString("appcode");
	// 中心地址
	private String endpoint = Messages.getString("cinemamainurl");
	// 中心命名空间
	private String main_NS = Messages.getString("mainnamespace");
	// 加密key
	private String PCheckKey = Messages.getString("pcheckkey");
	//影院编码
	private String cinemaid = Messages.getString("cinemaid");
	String pCinemaVer = Messages.getString("appcode");
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
	 
		public void run() {
			logger.info("开始同步排期"+new Date());
			try {
				addFeature();
				logger.info("结束同步排期"+new Date());
			} catch (Exception e) {
				logger.info("排期更新失败");
				logger.info(e);
				e.printStackTrace();
			}
		}
		public String getCinemaPlan(String cinemaID,String pCinemaVer,String dates){
			String result="";
			try {
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName(new QName(main_NS, "GetCinemaPlan"));// 1.命名空间 2.方法名
				// 参数设置
				call.addParameter("pAppCode", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pCinemaID", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pPlanDate", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pVerifyInfo", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pTokenID", XMLType.XSD_STRING, ParameterMode.IN);
				// 返回类型设置
				call.setReturnType(XMLType.XSD_STRING);
				
				String pAppCode = app_Code;
				String cheKey = PCheckKey;
				if("V01".equals(pCinemaVer)){
					pAppCode = app_Code;
					cheKey = PCheckKey;
				}
				
				String pCinemaID=cinemaID;
				String pPlanDate=dates;
				String pTokenID=CPO.pTokenID;
				String paramers=pAppCode +pCinemaID+pPlanDate+pTokenID+CPO.Token;
				
				Md5 md5 = new Md5();
				String pVerifyInfo = md5.getMD5ofStr(URLEncoder.encode(paramers.toLowerCase() + cheKey, "UTF-8"))
				.substring(8, 24).toLowerCase();
				result = (String) call.invoke(new Object[] { pAppCode , pCinemaID  , pPlanDate,pTokenID,pVerifyInfo });
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
	// 更新排期
	public void addFeature() throws ParseException {

		 try {
			String pCinemaID="";
			 Date date=new Date();//取时间
			 Calendar calendar = new GregorianCalendar();
			 calendar.setTime(date);
			 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String dateString = formatter.format(date);
			 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
			 String hdateString = formatter.format(date);
			 String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			if("kyy".equals(institution_id))
			{
				List list=bs.queryForList("city_cinemaall");
				for(int i=0;i<list.size();i++)
				{
					pCinemaID = list.get(i).toString();
					String result= this.getCinemaPlan(list.get(i).toString(),pCinemaVer,dates);//今天排期
					String mresult= this.getCinemaPlan(list.get(i).toString(),pCinemaVer,dateString);//明天排期
					String hresult= this.getCinemaPlan(list.get(i).toString(),pCinemaVer,hdateString);//后天排期
					Document doc = null;
					try {
						doc = DocumentHelper.parseText(result);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Element rootElt = doc.getRootElement(); // 获取根节点
				    Element MEsendCount = rootElt.element("ResultCode");
				    if("0".equals(MEsendCount.getText())){
				    	logger.info("影院编码："+pCinemaID+"获取相排期成功");
				    	 List cinemaPlans = rootElt.element("CinemaPlans").elements();
				    	 int y=cinemaPlans.size();
				    	 for(int j=0;j<y;j++){
				    		 Element cinemaPlan=(Element)cinemaPlans.get(j);
				    		 T0201_feature_app featureapp=new T0201_feature_app();
				    		 String featureappno=cinemaPlan.elementText("FeatureAppNo");
				    		 featureapp.setFeatureappno(featureappno);
				    		 featureapp.setFeatureno(cinemaPlan.elementText("FeatureNo"));
				    		 featureapp.setPlacename(cinemaPlan.elementText("PlaceName"));
				    		 featureapp.setFeaturedate(cinemaPlan.elementText("FeatureDate"));
				    		 featureapp.setFeaturetime(cinemaPlan.elementText("FeatureTime"));
				    		 featureapp.setPlaceno(cinemaPlan.elementText("PlaceNo"));
				    		 featureapp.setHallno(cinemaPlan.elementText("HallNo"));
				    		 featureapp.setFilmno(cinemaPlan.elementText("FilmNo"));
				    		 featureapp.setFilmname(cinemaPlan.elementText("FilmName"));
				    		 featureapp.setApppric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//销售价
				    		 featureapp.setAndroidpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//客户端价格
				    		 featureapp.setWinxinpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//微信价格
				    		 featureapp.setWebsitepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//网站价格
				    		 featureapp.setBalancepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//结算价   同步时==销售价
				    		 featureapp.setStandpric(Double.parseDouble(cinemaPlan.elementText("StandPric")));//原时间 （影院价）
				    		 featureapp.setOriginalapprice(Double.parseDouble(cinemaPlan.elementText("AppPric")));//原始结算价 
				    		 featureapp.setTotalseats(cinemaPlan.elementText("HallSeats"));
				    		 featureapp.setRemainingseats(cinemaPlan.elementText("AvailableSeats"));
				    		 featureapp.setUsesign(cinemaPlan.elementText("UseSign"));
				    		 featureapp.setSetclose(cinemaPlan.elementText("SetClose"));
				    		 featureapp.setHallname(cinemaPlan.elementText("HallName"));
				    		 featureapp.setCopylanguage(cinemaPlan.elementText("CopyLanguage"));
				    		 featureapp.setCopytype(cinemaPlan.elementText("CopyType"));
				    		 featureapp.setFlag("1");
				    		 featureapp.setStatus("0");//开售状态 0 未开售
				    		 bs.delete("delete_featureapp", featureapp);
				    		 bs.save("insert_featureapp", featureapp);
				    	 }
				    }
				  Document mdoc = null;
				  try {
				    	mdoc = DocumentHelper.parseText(mresult);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					Element mrootElt = mdoc.getRootElement(); // 获取根节点	
					Element mMEsendCount = mrootElt.element("ResultCode");
					if("0".equals(mMEsendCount.getText())){
						logger.info("影院编码："+pCinemaID+"获取相排期成功");
				    	 List cinemaPlans = mrootElt.element("CinemaPlans").elements();
				    	 int y=cinemaPlans.size();
				    	 for(int j=0;j<y;j++){
				    		 Element cinemaPlan=(Element)cinemaPlans.get(j);
				    		 T0201_feature_app featureapp=new T0201_feature_app();
				    		 String featureappno=cinemaPlan.elementText("FeatureAppNo");
				    		 featureapp.setFeatureappno(featureappno);
				    		 featureapp.setFeatureno(cinemaPlan.elementText("FeatureNo"));
				    		 featureapp.setPlacename(cinemaPlan.elementText("PlaceName"));
				    		 featureapp.setFeaturedate(cinemaPlan.elementText("FeatureDate"));
				    		 featureapp.setFeaturetime(cinemaPlan.elementText("FeatureTime"));
				    		 featureapp.setPlaceno(cinemaPlan.elementText("PlaceNo"));
				    		 featureapp.setHallno(cinemaPlan.elementText("HallNo"));
				    		 featureapp.setFilmno(cinemaPlan.elementText("FilmNo"));
				    		 featureapp.setFilmname(cinemaPlan.elementText("FilmName"));
				    		 featureapp.setApppric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//销售价
				    		 featureapp.setAndroidpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//客户端价格
				    		 featureapp.setWinxinpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//微信价格
				    		 featureapp.setWebsitepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//网站价格
				    		 featureapp.setBalancepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//结算价   同步时==销售价
				    		 featureapp.setStandpric(Double.parseDouble(cinemaPlan.elementText("StandPric")));//原时间 （影院价）
				    		 featureapp.setOriginalapprice(Double.parseDouble(cinemaPlan.elementText("AppPric")));//原始结算价 
				    		 featureapp.setTotalseats(cinemaPlan.elementText("HallSeats"));
				    		 featureapp.setRemainingseats(cinemaPlan.elementText("AvailableSeats"));
				    		 featureapp.setUsesign(cinemaPlan.elementText("UseSign"));
				    		 featureapp.setSetclose(cinemaPlan.elementText("SetClose"));
				    		 featureapp.setHallname(cinemaPlan.elementText("HallName"));
				    		 featureapp.setCopylanguage(cinemaPlan.elementText("CopyLanguage"));
				    		 featureapp.setCopytype(cinemaPlan.elementText("CopyType"));
				    		 featureapp.setFlag("2");
				    		 featureapp.setStatus("0");//开售状态 0 未开售
				    		 bs.delete("delete_featureapp", featureapp);
				    		 bs.save("insert_featureapp", featureapp);
				    	 }
					}
					Document hdoc = null;
					   try {
					    	hdoc = DocumentHelper.parseText(hresult);
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Element hrootElt = hdoc.getRootElement(); // 获取根节点
						Element hMEsendCount = hrootElt.element("ResultCode");
					  if("0".equals(hMEsendCount.getText())){
						  logger.info("影院编码："+pCinemaID+"获取相排期成功");
					    	 List cinemaPlans = hrootElt.element("CinemaPlans").elements();
					    	 int y=cinemaPlans.size();
					    	 for(int j=0;j<y;j++){
					    		 Element cinemaPlan=(Element)cinemaPlans.get(j);
					    		 T0201_feature_app featureapp=new T0201_feature_app();
					    		 String featureappno=cinemaPlan.elementText("FeatureAppNo");
					    		 featureapp.setFeatureappno(featureappno);
					    		 featureapp.setFeatureno(cinemaPlan.elementText("FeatureNo"));
					    		 featureapp.setPlacename(cinemaPlan.elementText("PlaceName"));
					    		 featureapp.setFeaturedate(cinemaPlan.elementText("FeatureDate"));
					    		 featureapp.setFeaturetime(cinemaPlan.elementText("FeatureTime"));
					    		 featureapp.setPlaceno(cinemaPlan.elementText("PlaceNo"));
					    		 featureapp.setHallno(cinemaPlan.elementText("HallNo"));
					    		 featureapp.setFilmno(cinemaPlan.elementText("FilmNo"));
					    		 featureapp.setFilmname(cinemaPlan.elementText("FilmName"));
					    		 featureapp.setApppric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//销售价
					    		 featureapp.setAndroidpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//客户端价格
					    		 featureapp.setWinxinpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//微信价格
					    		 featureapp.setWebsitepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//网站价格
					    		 featureapp.setBalancepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//结算价   同步时==销售价
					    		 featureapp.setStandpric(Double.parseDouble(cinemaPlan.elementText("StandPric")));//原时间 （影院价）
					    		 featureapp.setOriginalapprice(Double.parseDouble(cinemaPlan.elementText("AppPric")));//原始结算价 
					    		 featureapp.setTotalseats(cinemaPlan.elementText("HallSeats"));
					    		 featureapp.setRemainingseats(cinemaPlan.elementText("AvailableSeats"));
					    		 featureapp.setUsesign(cinemaPlan.elementText("UseSign"));
					    		 featureapp.setSetclose(cinemaPlan.elementText("SetClose"));
					    		 featureapp.setHallname(cinemaPlan.elementText("HallName"));
					    		 featureapp.setCopylanguage(cinemaPlan.elementText("CopyLanguage"));
					    		 featureapp.setCopytype(cinemaPlan.elementText("CopyType"));
					    		 featureapp.setFlag("3");
					    		 featureapp.setStatus("0");//开售状态 0 未开售
					    		 bs.delete("delete_featureapp", featureapp);
					    		 bs.save("insert_featureapp", featureapp);
					    	 }
					  }
				}
//				Date now = new Date() ;
//				SimpleDateFormat formatters = new SimpleDateFormat("HH:mm") ;
//				CPO.setOuttimers(now);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("同步排期异常:");
			e.printStackTrace();
		}

		 

	}
	
	//获得HHMM格式的排期时间
	private String getFormatShowTime(String time) {
		String showTime = "0000";
		showTime = showTime+time;
		showTime = showTime.substring(showTime.length()-4);
		return showTime;
	}

	
	
	//
	private T0201_FEATURE_APP getFeatureInfo(CinemaPlan show, T0100_PlaceInfo place,
			T0111_FILMMESSAGE film) {
		T0201_FEATURE_APP feature = new T0201_FEATURE_APP();
		try {
			feature.setFeaturedate(show.getFeatureDate());
			feature.setFeatureno(show.getFeatureNo());
			feature.setFeaturetime(show.getFeatureTime().replace(":", ""));
			feature.setFilmname(show.getFilmName());
			feature.setFilmname1(show.getFilmName());
			feature.setFilmno(show.getFilmNo());
			feature.setHallno(show.getHallNo());
			feature.setHallname(show.getHallName());
			feature.setPlacename(place.getPlacename());
			feature.setPlaceno(place.getPlaceno());
			feature.setCopylanguage(show.getCopyLanguage());
			Date adddate = new Date();
			feature.setAdddatetime(adddate);
			feature.setSetclose(show.getSetClose());
			
			feature.setFeatureappno(Integer.parseInt(show.getFeatureAppNo()));
			feature.setUsesign(show.getUseSign());
			feature.setStandpric(Double.valueOf(show.getStandPric()));
//			feature.setAppcode("");
			feature.setApppric(Double.valueOf(show.getAppPric()));
//			feature.setBalancepric(Double.valueOf(show.getStandPric()));
//			feature.setCorrelated("");
//			feature.setFeatureappseatid("");
			feature.setRemainingseats(show.getAvailableSeats());
//			feature.setSectionid("");
//			feature.setSefeatureno("");
//			feature.setTopcount("");
			feature.setTotalseats(show.getHallSeats());
		} catch (NumberFormatException e) {
			logger.info("构造排期实体异常 :"+e);
			e.printStackTrace();
		} 
		return feature;
	}
	
	//获取影厅名
	private String getHallName(String hallId,String placeno) {
		String hallName = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hallId", hallId);
		map.put("placeno", placeno);
		hallName = (String) bs.queryForObject("getHallName", map);
		return hallName;
	}

	//获取火凤凰排期信息
	private List<CinemaPlan> qryShow(String cinemaId, String showDate) {
		cinemaId = "gzxxx";
		List<CinemaPlan> showList = new ArrayList<CinemaPlan>();
		String result = null;
		try {
			String pAppCode = "TEST";
			String checkKey = "12345678";
			String paynameSpace = Messages.getString("namespace");
			String url=Messages.getString("url");//url地址
			
			String xmlToken = getTokenCls.main(new String[1],url,paynameSpace, pAppCode, checkKey);
			TokenResult  tn = (TokenResult) XmlPojoUtil.createPojo(xmlToken, "token.TokenResult");
			
			String paramers=pAppCode+cinemaId+showDate+tn.getTokenID()+tn.getToken();
			Md5 md5 = new Md5();
			String pVerifyInfo = md5.getMD5ofStr1(URLEncoder.encode(paramers.toLowerCase() + checkKey, "UTF-8")).substring(8,24).toLowerCase();
			
			
			String []parameter={"pAppCode","pCinemaID","pPlanDate","pTokenID","pVerifyInfo"};
			Object []message={pAppCode,cinemaId,showDate,tn.getTokenID(),pVerifyInfo};
			
			for (int i = 0; i < message.length; i++) {
				logger.info(parameter[i]+" : "+message[i]);
			}
			long begintime = System.currentTimeMillis();
			logger.info("当前时间 :"+new Date());
			result = (String) getWebService.getWebService(message, "GetCinemaPlan", url, paynameSpace, parameter);
			long endtime = System.currentTimeMillis(); 
			logger.info("当前时间 :"+new Date());
			logger.info("GetCinemaPlan接口调用耗时 :"+(endtime-begintime)+"");
			logger.info("【GetCinemaPlan】 result : "+result);
			Document document = null;
			document = DocumentHelper.parseText(result);
			Element employees = document.getRootElement();			
			List<Element> list = employees.elements("CinemaPlans");		
			for (int i = 0; i < list.size(); i++) {
				Element element1 = list.get(i);
				List<Element> lists = element1.elements("CinemaPlan");
				for (int j = 0; j < lists.size(); j++) {
					Element element = lists.get(j);
					CinemaPlan show = new CinemaPlan();
					show.setFeatureAppNo(element.elementText("FeatureAppNo"));
					show.setFeatureNo(element.elementText("FeatureNo"));
					show.setHallName(element.elementText("HallName"));
					show.setPlaceName(element.elementText("PlaceName"));
					show.setFeatureDate(element.elementText("FeatureDate"));
					show.setFeatureTime(element.elementText("FeatureTime"));
					show.setTotalTime(element.elementText("TotalTime"));
					show.setFilmNo(element.elementText("FilmNo"));
					show.setFilmName(element.elementText("FilmName"));
					show.setHallNo(element.elementText("HallNo"));
					show.setPlaceNo(element.elementText("PlaceNo"));
					show.setAppPric(element.elementText("AppPric"));
					show.setStandPric(element.elementText("StandPric"));
					show.setUseSign(element.elementText("UseSign"));
					show.setSetClose(element.elementText("SetClose"));
					show.setCopyType(element.elementText("CopyType"));
					show.setCopyLanguage(element.elementText("CopyLanguage"));
					show.setAvailableSeats(element.elementText("AvailableSeats"));
					show.setHallSeats(element.elementText("HallSeats"));
					showList.add(show);
				}
//				cinema.setId(element.attributeValue("id"));
//				<show hallId="4" seqNo="086085348" showSeqNo="1" date="2012-11-19"
//					time="1025" updateLevel="0" updateType="I">
//					<film id="00110340201201" name="寒战" language="国语" title="寒战"
//						duration="105" imax="0" dimensional="2D" />
//					<price lowest="0">
//						<section id="01" standard="50" name="普通区" />
//					</price>
//					<through flag="N"></through>
//				</show>
				
			}
		} catch (Exception e) {
			logger.info("接口调用异常【qryShow】 ："+e);
//			Map parameterMap = new HashMap(); 
//			parameterMap.put("time", new  Date());
//			parameterMap.put("name", "qryShow");
//			parameterMap.put("result", result);
//			parameterMap.put("exception", e.toString());
//			bs.save("insertErrorLog", parameterMap);
			e.printStackTrace();
		}
		return showList;
	}


}
