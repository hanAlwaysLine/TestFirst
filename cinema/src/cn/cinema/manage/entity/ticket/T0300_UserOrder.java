package cn.cinema.manage.entity.ticket;

public class T0300_UserOrder {

	private String order_no; // 订单编号
	private String user_id; // 用户编号
	private String order_time; // 订单时间
	private String order_price; // 订单总价
	private String order_type; // 1,影片订单,2卖品订单,3综合订单
	private String pay_type; // 支付类型: 1,会员卡支付,2微信支付,3淘宝支付
	private String appcode; // 渠道(web,ios),1,网站,2,ANDORID,3,IOS,4,微信
	private String activity_id; // 活动ID
	private String receive_status; // 领取状态 0,未领取,1已领取
	private String receive_time; // 领取时间
	private String oper_id; // 操作员ID.t0001_ad_user.ID
	private String pay_cardno; //支付会员卡号
	private String pay_tranno; //网银支付流水号
	private String pay_status; //支付状态,0,待支付,1已支付

	/************* 客串属性 ***********/
	private String user_name; // 用户名称
	private String startDate; // 开始时间
	private String endDate; // 结束时间
	private String count; // 数量
	private String user_mobile; // 手机号
	private String name; // 操作员

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String orderNo) {
		order_no = orderNo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String orderTime) {
		order_time = orderTime;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String orderPrice) {
		order_price = orderPrice;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String orderType) {
		order_type = orderType;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String payType) {
		pay_type = payType;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receiveTime) {
		receive_time = receiveTime;
	}

	public String getOper_id() {
		return oper_id;
	}

	public void setOper_id(String operId) {
		oper_id = operId;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String userMobile) {
		user_mobile = userMobile;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activityId) {
		activity_id = activityId;
	}

	public String getReceive_status() {
		return receive_status;
	}

	public void setReceive_status(String receiveStatus) {
		receive_status = receiveStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPay_cardno() {
		return pay_cardno;
	}

	public void setPay_cardno(String payCardno) {
		pay_cardno = payCardno;
	}

	public String getPay_tranno() {
		return pay_tranno;
	}

	public void setPay_tranno(String payTranno) {
		pay_tranno = payTranno;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String payStatus) {
		pay_status = payStatus;
	}

}
