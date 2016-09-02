package cn.cinema.manage.entity.Activitymanage;

import java.util.Date;

/**
 * T0403Activity entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class T0403Activity implements java.io.Serializable {

	// Fields

	private Integer activity_id;
	private String title;
	private String cityno;
	private String cinemaname;
	private Integer activestate;
	private String cont;
	private String actimg;
	private Integer imgtype;
	private Integer act_type;
	private String activity_name;
	private String actimg_h;
	private String featureno;
	private String seatcount;
	private String price; // 活动金额
	private String appcode; //渠道
	private String count; //活动数量

	// 追加属性
	private Date starttime;
	private Date endtime;
	private Date addtime;

	// Property accessors

	public String getTitle() {
		return this.title;
	}

	public String getActimg_h() {
		return actimg_h;
	}

	public void setActimg_h(String actimgH) {
		actimg_h = actimgH;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCityno() {
		return cityno;
	}

	public void setCityno(String cityno) {
		this.cityno = cityno;
	}

	public String getCinemaname() {
		return this.cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}

	public Integer getActivestate() {
		return activestate;
	}

	public void setActivestate(Integer activestate) {
		this.activestate = activestate;
	}

	public String getCont() {
		return this.cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getActimg() {
		return this.actimg;
	}

	public void setActimg(String actimg) {
		this.actimg = actimg;
	}

	public Integer getImgtype() {
		return imgtype;
	}

	public void setImgtype(Integer imgtype) {
		this.imgtype = imgtype;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activityId) {
		activity_id = activityId;
	}

	public Integer getAct_type() {
		return act_type;
	}

	public void setAct_type(Integer actType) {
		act_type = actType;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activityName) {
		activity_name = activityName;
	}

	public String getFeatureno() {
		return featureno;
	}

	public void setFeatureno(String featureno) {
		this.featureno = featureno;
	}

	public String getSeatcount() {
		return seatcount;
	}

	public void setSeatcount(String seatcount) {
		this.seatcount = seatcount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
