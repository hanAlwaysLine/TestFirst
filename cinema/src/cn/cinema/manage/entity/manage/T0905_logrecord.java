package cn.cinema.manage.entity.manage;

import java.util.Date;

public class T0905_logrecord {
	private String id;      			//ID
    private String cardfacadecd;		//会员卡号
    private String idcard;      		//身份证号码
    private String appcode;   			//应用编码
    private String mobilephone;			//手机号码
    private String userid;				//卡用户名
    private String address;				//地址
    private String birthdate;			//生日 格式（0000-00-00）
    private String mailaddress;			//e-mail
    private String contactphone;      	//联系电话
    private String sex;      			//性别
    private String registerdt;      	//登记日期 格式（0000-00-00 00:00:00）
    private String cardname;      		//证件名称
    private String avaiflg;      		//卡是否有效 y：有效；n：无效
    private String balance;      		//可用余额
    private String type;      			//卡类型 c：充值型；b：非充值型； 
    private String cumulationmarking;	//累积积分
    private String trademarking;		//换领积分
    private String addmoneytm;			//充值次数
    private String invalidationdate;	//会员卡到期时间 格式（0000-00-00 00:00:00）
    private String firstdate;      		//优惠期结束时间 格式（0000-00-00 00:00:00）
    private String lastdate;      		//优惠期结束时间 格式（0000-00-00 00:00:00）
    private String consumeupgradetime;  //会员卡已消费次数
    private String saledate;      		//发卡时间 格式（0000-00-00 00:00:00）
    private String state;      			//会员卡状态 n：正常；r：报失；g：换卡；c：消卡；t：过期；u：用完
    private String gradeid;     		//会员卡等级id
    private String gradedesc;      		//会员卡等级描述
    private Date addtime;      			//添加时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardfacadecd() {
		return cardfacadecd;
	}
	public void setCardfacadecd(String cardfacadecd) {
		this.cardfacadecd = cardfacadecd;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRegisterdt() {
		return registerdt;
	}
	public void setRegisterdt(String registerdt) {
		this.registerdt = registerdt;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getAvaiflg() {
		return avaiflg;
	}
	public void setAvaiflg(String avaiflg) {
		this.avaiflg = avaiflg;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCumulationmarking() {
		return cumulationmarking;
	}
	public void setCumulationmarking(String cumulationmarking) {
		this.cumulationmarking = cumulationmarking;
	}
	public String getTrademarking() {
		return trademarking;
	}
	public void setTrademarking(String trademarking) {
		this.trademarking = trademarking;
	}
	public String getAddmoneytm() {
		return addmoneytm;
	}
	public void setAddmoneytm(String addmoneytm) {
		this.addmoneytm = addmoneytm;
	}
	public String getInvalidationdate() {
		return invalidationdate;
	}
	public void setInvalidationdate(String invalidationdate) {
		this.invalidationdate = invalidationdate;
	}
	public String getFirstdate() {
		return firstdate;
	}
	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getConsumeupgradetime() {
		return consumeupgradetime;
	}
	public void setConsumeupgradetime(String consumeupgradetime) {
		this.consumeupgradetime = consumeupgradetime;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getGradedesc() {
		return gradedesc;
	}
	public void setGradedesc(String gradedesc) {
		this.gradedesc = gradedesc;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
