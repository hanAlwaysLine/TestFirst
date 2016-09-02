package cn.cinema.manage.iface;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.util.DateUtil;


public class ClientAction {
	
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(ClientAction.class);

	/**
	 * 接口对象
	 */
	private IClientService cinemaService;

	private String appcode;  //应用编码
	private String validatekey;  //md5加密key
	private Integer pageNo;    //页码
	private Integer pageSize;  //每页显示条数
	private String filmno;  //影片编码
	private String filmtype; //影片类型  1,热映,2即将上映
	private String cityno;  //城市编码
	private String placeno;  //影院编码
	private String cinemano; //影院编码
	private String featuredate;  //排期日期
	private String featureappno; //排期应用编码
	private String userId;  //客户端用户id
	private String partnerId;  //合作商流水号
	private String cardId;  //会员卡卡号或账户号，账户号前加$
	private String mobilePhone;  //手机号
	private String passWord;  //会员卡密码
	private String oldPrice;  //预算：原始价格=排期价格乘以票数, 支付：原始价格=预算价格乘以票数，折扣传10
	private String tracePrice;  //交易费用，即交易手续费，无手续费传0
	private String discount;  //折扣，范围：0~10，0：影院规则，1-10：1折-10折
	private String featureNo;   //排期号
	private String filmNo;  //影片编号
	private String ticketNum;  //票数，预算传1
	private String traceMemo;  //备注
	private String recvmobileno; //锁座接口二维码手机号
	private String seatinfo;   //座位信息,json格式
	private String userName;  //会员用户名
	private String deductMoney;  //中心预算后的单张票价
	private String orderNo;   //订单号
	
	
	
	/**
	 * 获取软件版本信息
	 */
	public void getVersion() {
		String str = cinemaService.getVersion(appcode, validatekey);
		outPrint(str);
	}
	
	/**
	 * 获取影片详细信息
	 */
	public void getFilmInfo() {
		String str = cinemaService.getFilmInfo(appcode, validatekey, filmno);
		outPrint(str);
	}
	
	/**
	 * 获取影片列表
	 */
	public void getFilmList() {
		String str = cinemaService.getFilmList(appcode, validatekey, pageNo, pageSize);
		outPrint(str);
	}
	
	/**
	 * 获取热映影片列表
	 */
	public void getHotFilmList(){
		String str = cinemaService.getHotFilmList(appcode, validatekey, filmtype, cityno, placeno);
		outPrint(str);
	}
	
	/**
	 * 根据影片编码影院编码获取排期
	 */
	public void getFilmFeature(){
		String str = cinemaService.getFilmFeature(appcode, validatekey, filmno, cinemano, featuredate);
		outPrint(str);
	}
	
	/**
	 *  通过影院获取某天的排期
	 */
	public void getFeatureByCinema(){
		String str = cinemaService.getFeatureByCinema(appcode, validatekey, cinemano, featuredate);
		outPrint(str);
	}
	
	/**
	 * 根据排期获取座位图
	 */
	public void getPlanSiteState(){
		String str = cinemaService.getPlanSiteState(appcode, validatekey, featureappno);
		outPrint(str);
	}
	
	/**
	 * 预算
	 */
	public void goBudget(){
		placeno = "gzxxx";
		partnerId = this.getSerialNum(appcode);
		cardId = "00000366";  //会员号
		mobilePhone = "18500376349";
		passWord = "111111";    //密码
		oldPrice = "80";
		tracePrice = "0";
		discount = "0";
		featureNo = "0000106992";
		filmNo = "051200722014";
		ticketNum = "1";
		traceMemo = "电影名";
		
		
		String str = cinemaService.goBudget(appcode, validatekey, userId, placeno, partnerId, cardId, 
				mobilePhone, passWord, oldPrice, tracePrice, discount, featureNo, filmNo, ticketNum, traceMemo);
		outPrint(str);
	}
	
	/**
	 * 锁座
	 */
	public void RealCheck(){
//		featureappno = "77168451";
		recvmobileno = "18500376349";
		deductMoney = "48";
		
		String str = cinemaService.RealCheck(appcode, validatekey, userId, featureappno, recvmobileno, seatinfo, deductMoney);
		outPrint(str);
	}
	
	/**
	 * 扣费
	 */
	public void cardPay(){
		placeno = "gzxxx";
		partnerId = this.getSerialNum(appcode);
		cardId = "00000366";  //会员号
		mobilePhone = "18500376349";
		passWord = "111111";    //密码
		oldPrice = "240";
		tracePrice = "0";
		discount = "0";
		featureNo = "0000198693";
		filmNo = "051100692014";
		ticketNum = "3";
		traceMemo = "电影名";
		orderNo = "10982";
		String str = cinemaService.cardPay(appcode, validatekey, userId, orderNo, placeno, partnerId, cardId, 
				mobilePhone, passWord, oldPrice, tracePrice, discount, featureNo, filmNo, ticketNum, traceMemo);
		outPrint(str);
	}
	
	/**
	 * 会员注册
	 */
	public void userRegister(){
		String str = cinemaService.userRegister(appcode, validatekey, userName, passWord, mobilePhone);
		outPrint(str);
	}
	
	/**
	 * 会员登录
	 */
	public void userLogin(){
		String str = cinemaService.userLogin(appcode, validatekey, mobilePhone, passWord);
		outPrint(str);
	}
	
	/**
	 * 获取订单列表
	 */
	public void getOrderList(){
		String str = cinemaService.getOrderList(appcode, validatekey, userId, orderNo);
		outPrint(str);
	}
	
	public static void main(String[] args) {
//		SeatInfoBean sib = new SeatInfoBean();
//		sib.setHandlingfee("0");
//		sib.setSeatNo("123");
//		sib.setTicketPrice("80");
//		
//		JSONObject jo = JSONObject.fromObject(sib);
		
		//[{"handlingfee":"0","seatNo":"111","ticketPrice":"80"},{"handlingfee":"1","seatNo":"222","ticketPrice":"81"},{"handlingfee":"2","seatNo":"333","ticketPrice":"82"}]
		
//		System.out.println(String.valueOf(AppCode.MON));
		System.out.println(new ClientAction().getSerialNum("IOS"));
		
	}
	
	//-------------------------------------------------------

	//接口注入方法
	public void setCinemaService(IClientService cinemaService) {
		this.cinemaService = cinemaService;
	}

	//获取response对象
	private HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0);
		return response;
	}
	
	//输出
	private void outPrint(String content) {
		try {
			logger.info("线程id："+Thread.currentThread().getId()+",结束时间线："+System.currentTimeMillis()+",请求报文结果："+content);
			getResponse().getWriter().write(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//生成流水号， 日期+应用编码+随机数
	private String getSerialNum(String appcode){
		String d = DateUtil.getDateStr();
		String a = AppCode.A.getCode(appcode);
		Random rd = new Random();
		String r = new String();
		for(int i = 0 ; i < 10 ; i ++){
			r += rd.nextInt(10);
		}
		return d + a + r;
	}
	
	/*********************以下为参数get,set方法*********************/
	
	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	public String getValidatekey() {
		return validatekey;
	}

	public void setValidatekey(String validatekey) {
		this.validatekey = validatekey;
	}

	public String getFilmno() {
		return filmno;
	}

	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getFilmtype() {
		return filmtype;
	}

	public void setFilmtype(String filmtype) {
		this.filmtype = filmtype;
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

	public String getFeaturedate() {
		return featuredate;
	}

	public void setFeaturedate(String featuredate) {
		this.featuredate = featuredate;
	}

	public String getCinemano() {
		return cinemano;
	}

	public void setCinemano(String cinemano) {
		this.cinemano = cinemano;
	}

	public String getFeatureappno() {
		return featureappno;
	}

	public void setFeatureappno(String featureappno) {
		this.featureappno = featureappno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getTracePrice() {
		return tracePrice;
	}

	public void setTracePrice(String tracePrice) {
		this.tracePrice = tracePrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getFeatureNo() {
		return featureNo;
	}

	public void setFeatureNo(String featureNo) {
		this.featureNo = featureNo;
	}

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getTraceMemo() {
		return traceMemo;
	}

	public void setTraceMemo(String traceMemo) {
		this.traceMemo = traceMemo;
	}

	public String getRecvmobileno() {
		return recvmobileno;
	}

	public void setRecvmobileno(String recvmobileno) {
		this.recvmobileno = recvmobileno;
	}

	public String getSeatinfo() {
		return seatinfo;
	}

	public void setSeatinfo(String seatinfo) {
		this.seatinfo = seatinfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
