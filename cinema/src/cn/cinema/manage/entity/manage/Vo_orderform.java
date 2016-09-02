package cn.cinema.manage.entity.manage;

import java.util.Date;

public class Vo_orderform {
	private Integer fid;					//主键（Fid）
	private String filmname;				//电影名称
	private String placename;				//影院名称
	private Integer ticketsnum;				//票数
	private String seatinfo;				//座位信息
	private String hallname;				//影厅名称
	private String cinemalinkid;			//cinemalinkid
	private String startDate;				//开始时间
	private String endDate;					//结束时间
	private String orderno;					//订单编号
	private String showdate;				//观影日期
	private String showtime;				//观影时间
	private String price;					//单价
	private String cityno;					//城市编码
	private String placeid;					//影院编号
	private String countprice;				//总价
	private String result_pay;				//支付状态 0,未支付,1,已支付已出票,2已支付未出票,3,已退款.
	private Date orderdate;
	
	public String getPlaceid() {
		return placeid;
	}
	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}
	public String getFilmname() {
		return filmname;
	}
	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public Integer getTicketsnum() {
		return ticketsnum;
	}
	public void setTicketsnum(Integer ticketsnum) {
		this.ticketsnum = ticketsnum;
	}
	public String getSeatinfo() {
		return seatinfo;
	}
	public void setSeatinfo(String seatinfo) {
		this.seatinfo = seatinfo;
	}
	public String getCountprice() {
		return countprice;
	}
	public void setCountprice(String countprice) {
		this.countprice = countprice;
	}
	public String getHallname() {
		return hallname;
	}
	public void setHallname(String hallname) {
		this.hallname = hallname;
	}
	public String getCinemalinkid() {
		return cinemalinkid;
	}
	public void setCinemalinkid(String cinemalinkid) {
		this.cinemalinkid = cinemalinkid;
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
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	public String getResult_pay() {
		return result_pay;
	}
	public void setResult_pay(String resultPay) {
		result_pay = resultPay;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
}
