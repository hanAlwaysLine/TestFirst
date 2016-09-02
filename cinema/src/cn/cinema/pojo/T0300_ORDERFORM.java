package cn.cinema.pojo;

import java.util.Date;

public class T0300_ORDERFORM {
	
	private Integer fid;	//integer	fid
	private String featureappno;	//number(10)	总排期号
	private String orderno;	//varchar2(20)	订单编号
	private String appcode;	//varchar2(10)	渠道(web,ios)
	private String vipno;	//varchar2(50)	会员卡号
	private String placeid;	//varchar2(20)	影院编码
	private Date orderdate;	//date	订购日期
	private String ticketsnum;	//number(4)	票数
	private String seatinfo;	//varchar2(60)	座位信息
	private String filmno;	//varchar2(20)	影片编号
	private String hallno;	//varchar2(2)	影厅编号
	private String cityno;	//varchar2(10)	城市编码
	private String username;	//varchar2(50)	姓名
	private String cinemalinkid;	//影院连接编码
	private String sectionid;	//影厅场区编号
	private String showseqno;	//场次内部编号
	private String showdate;	//场次日期
	private String showtime;	//场次时间
	private String result;	//状态编码
	private String message;	//状态描述
	private String seats;	//锁座  如果锁定失败，该节点内保存引起失败的座位信息。如果正常锁定成功，则返回正常锁定座位列表。（如果提交的座位列表内只含情侣座中的一个座位，则节点内额外保存锁定的另一个情侣座座位信息。）
	private String result_lock;	//锁座  0表示成功，非0失败，非0数字为错误代码。
	private String message_lock;	//锁座  错误描述
	private String tickettypelist;	//影票名称列表
	private String pricelist;	//影票价格列表
	private String feelist;	//影票手续费列表
	private String disticketcount;	//折扣总票数
	private String bookingid;	//火凤凰系统订单号
	private String confirmationid;	//火凤凰系统取票号
	private String seats_pay;	//座位列表 支付返回的
	private String result_pay;	//支付 状态编码
	private String message_pay;	//支付 状态描述
	
	private String price;	//单张票价
	private String countprice;	//总价
	private String printedflg;	//取票状态(Y为已打票，N为未打票)
	
	public String getPrintedflg() {
		return printedflg;
	}
	public void setPrintedflg(String printedflg) {
		this.printedflg = printedflg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCountprice() {
		return countprice;
	}
	public void setCountprice(String countprice) {
		this.countprice = countprice;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFeatureappno() {
		return featureappno;
	}
	public void setFeatureappno(String featureappno) {
		this.featureappno = featureappno;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	public String getVipno() {
		return vipno;
	}
	public void setVipno(String vipno) {
		this.vipno = vipno;
	}
	public String getPlaceid() {
		return placeid;
	}
	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getTicketsnum() {
		return ticketsnum;
	}
	public void setTicketsnum(String ticketsnum) {
		this.ticketsnum = ticketsnum;
	}
	public String getSeatinfo() {
		return seatinfo;
	}
	public void setSeatinfo(String seatinfo) {
		this.seatinfo = seatinfo;
	}
	public String getFilmno() {
		return filmno;
	}
	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}
	public String getHallno() {
		return hallno;
	}
	public void setHallno(String hallno) {
		this.hallno = hallno;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCinemalinkid() {
		return cinemalinkid;
	}
	public void setCinemalinkid(String cinemalinkid) {
		this.cinemalinkid = cinemalinkid;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getShowseqno() {
		return showseqno;
	}
	public void setShowseqno(String showseqno) {
		this.showseqno = showseqno;
	}
	public String getShowdate() {
		return showdate;
	}
	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getResult_lock() {
		return result_lock;
	}
	public void setResult_lock(String resultLock) {
		result_lock = resultLock;
	}
	public String getMessage_lock() {
		return message_lock;
	}
	public void setMessage_lock(String messageLock) {
		message_lock = messageLock;
	}
	public String getTickettypelist() {
		return tickettypelist;
	}
	public void setTickettypelist(String tickettypelist) {
		this.tickettypelist = tickettypelist;
	}
	public String getPricelist() {
		return pricelist;
	}
	public void setPricelist(String pricelist) {
		this.pricelist = pricelist;
	}
	public String getFeelist() {
		return feelist;
	}
	public void setFeelist(String feelist) {
		this.feelist = feelist;
	}
	public String getDisticketcount() {
		return disticketcount;
	}
	public void setDisticketcount(String disticketcount) {
		this.disticketcount = disticketcount;
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getConfirmationid() {
		return confirmationid;
	}
	public void setConfirmationid(String confirmationid) {
		this.confirmationid = confirmationid;
	}
	public String getSeats_pay() {
		return seats_pay;
	}
	public void setSeats_pay(String seatsPay) {
		seats_pay = seatsPay;
	}
	public String getResult_pay() {
		return result_pay;
	}
	public void setResult_pay(String resultPay) {
		result_pay = resultPay;
	}
	public String getMessage_pay() {
		return message_pay;
	}
	public void setMessage_pay(String messagePay) {
		message_pay = messagePay;
	}
	
}
