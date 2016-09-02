package cn.cinema.manage.entity.reportform;

/**
 * 对账报表实体
 * 
 * @author Administrator
 * 
 */
public class Reconciliation {

	private String order_no;
	private String featureappno;
	private String filmname;
	private String hallname;
	private String featuredate;
	private String ticketsnum;
	private String apppric;
	private String order_price;
	private String pay_type;
	private String order_time;
	private String appcode;
	private String receive_status;
	private String receive_time;

	private String startdate;
	private String enddate;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String orderNo) {
		order_no = orderNo;
	}

	public String getFeatureappno() {
		return featureappno;
	}

	public void setFeatureappno(String featureappno) {
		this.featureappno = featureappno;
	}

	public String getFilmname() {
		return filmname;
	}

	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}

	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public String getApppric() {
		return apppric;
	}

	public void setApppric(String apppric) {
		this.apppric = apppric;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String orderPrice) {
		order_price = orderPrice;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String payType) {
		pay_type = payType;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String orderTime) {
		order_time = orderTime;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	public String getReceive_status() {
		return receive_status;
	}

	public void setReceive_status(String receiveStatus) {
		receive_status = receiveStatus;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receiveTime) {
		receive_time = receiveTime;
	}

	public String getTicketsnum() {
		return ticketsnum;
	}

	public void setTicketsnum(String ticketsnum) {
		this.ticketsnum = ticketsnum;
	}

	public String getFeaturedate() {
		return featuredate;
	}

	public void setFeaturedate(String featuredate) {
		this.featuredate = featuredate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
