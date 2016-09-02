package cn.cinema.manage.timer;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimerTask;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.entity.filmmanage.T0111_FILMMESSAGE;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.pojo.T0201_feature_app;

/**
 * 按时间同步排期
 * @author Administrator
 *
 */
public class JinTaskApp extends TimerTask{
	BaseService baseService  =ServiceLocator.getBaseService();

	// 跨影院  单影院 表示
	private Logger logger = Logger.getLogger(JinTaskApp.class);
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
			logger.error("按时间同步排期异常:");
			e.printStackTrace();
		}
		return result;
	}
	
	public void run() {
		 try {
			 int i = 0;
			 String pCinemaID = cinemaid;
			 while(i<=9){
				 Date date=new Date();//取时间
				 Calendar calendar = new GregorianCalendar();
				 calendar.setTime(date);
				 calendar.add(calendar.DATE,i);
				 date=calendar.getTime();
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				 String dateString = formatter.format(date);
				 String result= this.getCinemaPlan(cinemaid,pCinemaVer,dateString);//获取排期
				 Document doc = DocumentHelper.parseText(result);
				 Element rootElt = doc.getRootElement(); // 获取根节点
				 Element MEsendCount = rootElt.element("ResultCode");
				    //排期信息集合
				 List<T0201_feature_app> featureAppList = new ArrayList<T0201_feature_app>();
				 
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
			    		 featureapp.setBalancepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//结算价   同步时==销售价
			    		 featureapp.setAndroidpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//客户端价格
			    		 featureapp.setWinxinpric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//微信价格
			    		 featureapp.setWebsitepric(Double.parseDouble(cinemaPlan.elementText("AppPric")));//网站价格
			    		 featureapp.setStandpric(Double.parseDouble(cinemaPlan.elementText("StandPric")));//原时间 （影院价）
			    		 featureapp.setOriginalapprice(Double.parseDouble(cinemaPlan.elementText("AppPric")));//原始结算价 
			    		 featureapp.setStatus("0");//开售状态 0 未开售
			    		 featureapp.setTotalseats(cinemaPlan.elementText("HallSeats"));
			    		 featureapp.setRemainingseats(cinemaPlan.elementText("AvailableSeats"));
			    		 featureapp.setUsesign(cinemaPlan.elementText("UseSign"));
			    		 featureapp.setSetclose(cinemaPlan.elementText("SetClose"));
			    		 featureapp.setHallname(cinemaPlan.elementText("HallName"));
			    		 featureapp.setCopylanguage(cinemaPlan.elementText("CopyLanguage"));
			    		 featureapp.setCopytype(cinemaPlan.elementText("CopyType"));
			    		 featureapp.setFlag("1");
			    		 int feaResInt = Integer.parseInt(baseService.queryForObject("feature_select_feaNumByNo", featureapp.getFeatureappno()).toString());
			    		 if(feaResInt>0){
			    			 //更新排期状态
			    			 logger.info("更新同步排期状态-"+dateString+",排期应用编号为:"+featureapp.getFeatureappno()+",usersign="+featureapp.getUsesign()+",setclose="+featureapp.getSetclose());
				    		 baseService.update("feature_update_setclose", featureapp);
			    		 }else{
			    			 //把要添加的排期信息添加到集合中
			    			 logger.info("同步排期-"+dateString+"  排期应用编号为:"+featureapp.getFeatureappno()+",影片名称:"+featureapp.getFilmname());
			    			 featureAppList.add(featureapp);
			    		 }
			    	 }
			    	 //添加今天排期信息
			    	 baseService.saveAll("insert_featureapp", featureAppList);
			    	 //添加影片信息
			    	 addFilmInfo(featureAppList);
				    }
				 ++i;
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("按时间同步排期异常:");
			e.printStackTrace();
		}
	}
	
	/**
	 * 自动添加影片信息
	 */
	public void addFilmInfo(List<T0201_feature_app> faList){
		try {
			for(T0201_feature_app fa : faList){
				T0111_FILMMESSAGE film = new T0111_FILMMESSAGE();
				film.setFilmno(fa.getFilmno());
				film.setFilmname(fa.getFilmname());
				film.setFilmtime("0");
				film.setDirect("省略");
				film.setPlayer("省略");
				film.setWriter("省略");
				film.setGrade(1.0);
				film.setFilmsis("省略");
				film.setRelease("2");
				film.setGenreno("0");
				film.setShowtime(fa.getFeaturedate());
				film.setImage("/cinema/common/Images/notimg.jpg");
				//判断影片是否存在
				int filmResInt = Integer.parseInt(baseService.queryForObject("select_filmmanage_count", film).toString());
				if(filmResInt<=0){
					baseService.save("insert_filmmessage", film);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("按时间同步排期-自动添加影片信息异常:");
			e.printStackTrace();
		}
	}
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
}
