package cn.cinema.manage.action.featuremanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.service.FeatureService;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.timer.JinTaskApp;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;
import cn.cinema.pojo.FeatureForm;

/**
 * 排期管理
 * 
 * @author 汤凤欣
 * 
 */
public class Featuremanage {

	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Featuremanage.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	
	/**
	 * 操作类
	 */
	private FeatureService featureservice = ServiceLocator.getFeatureService();
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 页数
	 */
	private Integer pageNo;

	/**
	 * 城市列表
	 */
	private List<T0904_City> cityList;

	/**
	 * 影院列表
	 */
	private List<T0100_PlaceInfo> placeList;

	/**
	 * 排期管理主体
	 */
	private FeatureForm featureForm;

	private String jsonString;
	private String cinemano;

	/**
	 * -1昨天0今天1明天
	 */
	private String datetime;

	private String placeno;
	private String cityno;
	private String filmno;
	private String filmname;
	private String jsonid;
	private String jsonpr;
	private String jsonwp;
	private String jsonwx;
	private String jsonap;
	private String jsonflag;
	private String json3d;
	private String json2d;
	private Boolean modifySuc;
	private Boolean exitsorder;
	
	/**
	 * 前台地址
	 */
	private String cinemawurl;

	
	public Boolean getExitsorder() {
		return exitsorder;
	}

	public void setExitsorder(Boolean exitsorder) {
		this.exitsorder = exitsorder;
	}

	public String getFilmname() {
		return filmname;
	}

	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}

	public String getCityno() {
		return cityno;
	}

	public void setCityno(String cityno) {
		this.cityno = cityno;
	}

	public String getPlaceno() {
		return placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

	public String getCinemano() {
		return cinemano;
	}

	public void setCinemano(String cinemano) {
		this.cinemano = cinemano;
	}

	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();

	// ==============================================================
	/**
	 * 进入查询页面
	 * 
	 * @return
	 */
	public String query() {
		if (!"".equals(filmno) && filmno != null) {
			featureForm = (FeatureForm) bs.queryForObject("select_filmname",
					filmno);
		}
		// 城市列表
		// 查询影院排期
		return "query";
	}

	/**
	 * AJAX 根据城市编码获得影院列表
	 * 
	 * @return
	 */
	public void queryPlaceList() {
		try {
			List<T0100_PlaceInfo> t0100list = (List) bs.queryForList(
					"getCityPlaceList", jsonString);

			JSONArray jsonlist = JSONArray.fromObject(t0100list);
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Pragma", "No-Cache");
			response.setHeader("Cache-Control", "No-Cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(jsonlist.toString());
		} catch (IOException e) {
			logger.info(e);
			e.printStackTrace();
		}
	}

	/**
	 * 查询排期
	 * 
	 * @return
	 */
	public String queryfeature() {
		try {
			// logger.info("======"+featureForm.getPlaceno());
			// logger.info("======"+cinemano);
			// 分页排期
			if (!"".equals(cinemano) && cinemano != null
					&& "".equals(featureForm.getPlaceno())) {
				// logger.info("++++++");
				featureForm.setPlaceno(cinemano);
			}
			cinemano = "";
			if(featureForm.getStarttime()==null && featureForm.getEndtime()==null){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				featureForm.setStarttime(sf.format(new Date()));
			}
			
			featureForm.setFilmname(featureForm.getFilmname().trim());
			
			PageList list = bs.findPage("select_feature", "select_feature_count",
					featureForm, pageNo, pageSize);
			
			//获取当前系统时间
			String sysDate = bs.queryForList("select_nowSysDate").get(0).toString();
			sysDate = sysDate.substring(0, 16);
			
			SessionUtil.setPageList(request, list);
			request.setAttribute("list", list);
			request.setAttribute("sysDate", sysDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询排期异常:");
			e.printStackTrace();
		}
		return "queryfeature";
	}

	/**
	 * 更新排期
	 * 
	 * @return
	 */
	public void addfeature() {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			JinTaskApp jta = new JinTaskApp();
			jta.run();
			out.print("排期更新成功");
		} catch (Exception e) {
			logger.info("排期更新失败");
			e.printStackTrace();
		}
	}

	/**
	 * 更新影院价格
	 * 
	 * @return
	 */
	public String updatebprice() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		Map map = new HashMap();
		map.put("balancepric", jsonpr);
		map.put("featureappno", jsonid);
		map.put("updatetime", dates);
		try {
			bs.update("update_balancepric", map);
			modifySuc = true;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "将影院价格修改为" + jsonpr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modifySuc = false;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改影院价格失败");
		}
		return "update";
	}

	/**
	 * 更新客户端结算价
	 * 
	 * @return
	 */
	public String updateandroidpric() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		Map map = new HashMap();
		map.put("androidpric", jsonap);
		map.put("featureappno", jsonid);
		map.put("updatetime", dates);
		try {
			bs.update("update_androidpric", map);
			modifySuc = true;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "将客户端结算价修改为" + jsonap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modifySuc = false;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改客户端结算价失败");
		}
		return "update";
	}

	/**
	 * 更新微信价格
	 * 
	 * @return
	 */
	public String updatewinxinpric() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		Map map = new HashMap();
		map.put("winxinpric", jsonwp);
		map.put("featureappno", jsonid);
		map.put("updatetime", dates);
		try {
			bs.update("update_winxinpric", map);
			modifySuc = true;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "将微信价格修改为" + jsonwp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modifySuc = false;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改微信价格失败");
		}
		return "update";
	}

	/**
	 * 更新网站价格
	 * 
	 * @return
	 */
	public String updatewebsitepric() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		Map map = new HashMap();
		map.put("websitepric", jsonwp);
		map.put("featureappno", jsonid);
		map.put("updatetime", dates);
		try {
			bs.update("update_websitepric", map);
			modifySuc = true;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "将网站价格修改为" + jsonwp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modifySuc = false;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改网站价格失败");
		}
		return "update";
	}
	
	
	
	/**
	 * 更新结算价格价格
	 * @return
	 * @throws Exception 
	 */
	public String updateapppric() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		boolean flag = featureservice.SetFeaturePrice(jsonid, jsonwp);
		if(!flag){
			modifySuc = false;
			logger.error("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改中心结算价格失败" +"排映应用号："+jsonid);
			return "update";
		}
		
		Map map = new HashMap();
		map.put("apppric", jsonwp);
		map.put("featureappno", jsonid);
		map.put("updatetime", dates);
		try {
			bs.update("update_apppric", map);
			modifySuc = true;
			logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "将结算价格修改为:" + jsonwp   +" 原价格为:"+jsonpr);
		} catch (Exception e) {
			e.printStackTrace();
			modifySuc = false;
			logger.error("用户" + SessionUtil.getEmp(session).getId() + "在"
					+ dates + "修改结算价格失败");
		}
		return "update";
	}
	
	
	/**
	 * 更新结算价格价格
	 * @return
	 * @throws Exception 
	 */
	public String updateappprics() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		String[] arrayapp = null;
		try{
			 arrayapp =  jsonid.split(",");
			 if(arrayapp==null||arrayapp.length<=0){
				 return "update";
			 }
		}catch (Exception e) {
			e.printStackTrace();
			return "update";
		}
		String modifyprice = "0";
		String modifweixinyprice = "0";
		String basicprice = "";
		
		//如价格为空 视为0元处理
		if(StringUtils.isBlank(json3d)){
			json3d= "0";
		}
		
		if(StringUtils.isBlank(json2d)){
			json2d= "0";
		}
		if(StringUtils.isBlank(jsonwx)){
			jsonwx= "0";
		}
		
		
		//循环更新
		for(int i=0;i<arrayapp.length;i++){
			
			String[] arrayappprice = arrayapp[i].split("_");
			String appno = arrayappprice[0];
			
			T0201_FEATURE_APP app = (T0201_FEATURE_APP)bs.queryForObject("selectfeaturenobyno", appno);
			if(app==null){
				continue;
			}
			String copy=app.getCopytype();
			Double appprice = app.getOriginalapprice(); 
			if("3D".equals(copy)){
				basicprice = json3d;
			}else{
				basicprice = json2d;
			}
			try{
				
				BigDecimal big = new BigDecimal(0);
				//百分比 jsonpr结算价 apprice 当jsonflag=0时jsonwp为百分比  其他为固定底价  jsonwx微信价格 
				if("0".equals(jsonflag)){
					modifyprice =String.valueOf(appprice*Double.valueOf(basicprice)/100);
					modifweixinyprice = String.valueOf(appprice*Double.valueOf(basicprice)/100+Double.valueOf(jsonwx));
				}
				//固定底价
				else{
					modifyprice = basicprice;
					modifweixinyprice = String.valueOf(Double.valueOf(basicprice) + Double.valueOf(jsonwx));
				}
				//四舍五入 
				modifyprice = String.valueOf(new BigDecimal(modifyprice).setScale(0, BigDecimal.ROUND_HALF_UP));
				modifweixinyprice = String.valueOf(new BigDecimal(modifweixinyprice).setScale(0, BigDecimal.ROUND_HALF_UP));
				
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("计算价格失败排映应用号："+jsonid +" json3d="+json3d  +" json2d="+json2d +"jsonwx="+jsonwx);
				continue;
			}
			
			
			boolean flag = featureservice.SetFeaturePrice(appno, modifyprice);
			if(!flag){
				modifySuc = false;
				logger.error("用户" + SessionUtil.getEmp(session).getId() + "在"
						+ dates + "修改中心结算价格失败" +"排映应用号："+jsonid);
				continue;
			}
			Map map = new HashMap();
			map.put("apppric", modifyprice);
			map.put("winxinpric", modifweixinyprice);
			map.put("featureappno", appno);
			map.put("updatetime", dates);
			try {
				bs.update("update_apppricwxpric", map);
				logger.info("排期应用号为:" + appno + "在"+ dates + "将结算价格修改为:" + modifyprice  + "将微信价格修改为:" + modifweixinyprice   +" 原结算价格为:"+appprice);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("排期应用号为:" + appno + "在"
						+ dates + "修改排期号"+appno+"结算价格失败");
			}
		}
		return "update";
	}
	
	/**
	 * 更新开售状态
	 * @return
	 * @throws Exception 
	 */
	public String updatestatus() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dates = df.format(new Date());
		
		String[] arrayapp =  jsonid.split(",");
		Map map = new HashMap();
		map.put("status", jsonflag);
		map.put("updatetime", dates);
		//循环更新
		for(int i=0;i<arrayapp.length;i++){
			map.put("featureappno", arrayapp[i]);
			try {
				bs.update("update_featurestatus", map);
				logger.info("用户" + SessionUtil.getEmp(session).getId() + "在"
						+ dates + "更新开售状态:" + jsonflag);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("用户" + SessionUtil.getEmp(session).getId() + "在"
						+ dates + "更新开售状态失败");
			}
		}
		
		
		return "update";
	}
	
	/**
	 * 验证是否存在订单
	 * @return
	 */
	public String queryorderbyappno(){
		String count = (String)bs.queryForObject("select_existsorder", jsonid);
		exitsorder =  true;
		if(Integer.parseInt(count)<1){
			exitsorder = false;
		}
		return "querycount";
	}
	
	
	// ============================== SET GET 方法
	// ==============================================]

	public Logger getLogger() {
		return logger;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T0904_City> getCityList() {
		return cityList;
	}

	public void setCityList(List<T0904_City> cityList) {
		this.cityList = cityList;
	}

	public List<T0100_PlaceInfo> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<T0100_PlaceInfo> placeList) {
		this.placeList = placeList;
	}

	public FeatureForm getFeatureForm() {
		return featureForm;
	}

	public void setFeatureForm(FeatureForm featureForm) {
		this.featureForm = featureForm;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getFilmno() {
		return filmno;
	}

	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}

	public String getJsonid() {
		return jsonid;
	}

	public void setJsonid(String jsonid) {
		this.jsonid = jsonid;
	}

	public String getJsonpr() {
		return jsonpr;
	}

	public void setJsonpr(String jsonpr) {
		this.jsonpr = jsonpr;
	}

	public Boolean getModifySuc() {
		return modifySuc;
	}

	public void setModifySuc(Boolean modifySuc) {
		this.modifySuc = modifySuc;
	}

	public String getJsonwp() {
		return jsonwp;
	}

	public void setJsonwp(String jsonwp) {
		this.jsonwp = jsonwp;
	}

	public String getJsonap() {
		return jsonap;
	}

	public void setJsonap(String jsonap) {
		this.jsonap = jsonap;
	}

	public void setCinemawurl(String cinemawurl) {
		this.cinemawurl = cinemawurl;
	}

	public String getCinemawurl() {
		return cinemawurl;
	}

	public void setFeatureservice(FeatureService featureservice) {
		this.featureservice = featureservice;
	}

	public FeatureService getFeatureservice() {
		return featureservice;
	}

	public void setJsonflag(String jsonflag) {
		this.jsonflag = jsonflag;
	}

	public String getJsonflag() {
		return jsonflag;
	}

	public void setJsonwx(String jsonwx) {
		this.jsonwx = jsonwx;
	}

	public String getJsonwx() {
		return jsonwx;
	}

	public void setJson2d(String json2d) {
		this.json2d = json2d;
	}

	public String getJson2d() {
		return json2d;
	}

	public void setJson3d(String json3d) {
		this.json3d = json3d;
	}

	public String getJson3d() {
		return json3d;
	}

	
	
}
