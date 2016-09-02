package cn.cinema.pojo;

/**
 * 排期管理模型
 * 
 * @author tangfx. 
 * 
 */
public class FeatureForm {
	private String featureappno;
	private String featureno; //计划号
	private String adddate;		//更新时间
	private String featuredate;		//上映时间
	private String cityname;		//城市名称
	private String cityno;		//城市编码
	private String placename;		//影院名称
	private String placeno;		//影院编码
	private String filmno;		//电影编码
	private String filmname;		//电影名称
	private String features;		//场馆排期
	private String appcode;//应用编码
	private String balancepric;//和影院结算票价
	private String hallno;//影厅编号
	private String hallname;//影厅名称
	private String featuretime;//开始时间
	private String androidpric;//客户端结算价
	private String winxinpric;//微信价格
	private Double standpric;
	private Double apppric;
	private String setclose;
	private String usesign;
	private String totaltime;
	private String remainingseats;
	private String totalseats;
	private String cinemaname;
	private String copytype;
	private String copylanguage;
	private String flag;
	private String websitepric;//网站价格
	private String updatetime;//最后修改时间
	private Double originalapprice;
	private String status;
	private String useflag;
	//追加属性
	private String starttime;
	private String endtime;

	
	
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	public String getFeaturedate() {
		return featuredate;
	}
	public void setFeaturedate(String featuredate) {
		this.featuredate = featuredate;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public String getPlaceno() {
		return placeno;
	}
	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}
	public String getFilmno() {
		return filmno;
	}
	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}
	public String getFilmname() {
		return filmname;
	}
	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getFeatureno() {
		return featureno;
	}
	public void setFeatureno(String featureno) {
		this.featureno = featureno;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	public String getBalancepric() {
		return balancepric;
	}
	public void setBalancepric(String balancepric) {
		this.balancepric = balancepric;
	}
	public String getHallno() {
		return hallno;
	}
	public void setHallno(String hallno) {
		this.hallno = hallno;
	}
	public String getHallname() {
		return hallname;
	}
	public void setHallname(String hallname) {
		this.hallname = hallname;
	}
	public String getFeaturetime() {
		return featuretime;
	}
	public void setFeaturetime(String featuretime) {
		this.featuretime = featuretime;
	}
	public String getAndroidpric() {
		return androidpric;
	}
	public void setAndroidpric(String androidpric) {
		this.androidpric = androidpric;
	}
	public String getWinxinpric() {
		return winxinpric;
	}
	public void setWinxinpric(String winxinpric) {
		this.winxinpric = winxinpric;
	}
	public String getFeatureappno() {
		return featureappno;
	}
	public void setFeatureappno(String featureappno) {
		this.featureappno = featureappno;
	}
	public Double getStandpric() {
		return standpric;
	}
	public void setStandpric(Double standpric) {
		this.standpric = standpric;
	}
	public Double getApppric() {
		return apppric;
	}
	public void setApppric(Double apppric) {
		this.apppric = apppric;
	}
	public String getSetclose() {
		return setclose;
	}
	public void setSetclose(String setclose) {
		this.setclose = setclose;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(String totaltime) {
		this.totaltime = totaltime;
	}
	public String getRemainingseats() {
		return remainingseats;
	}
	public void setRemainingseats(String remainingseats) {
		this.remainingseats = remainingseats;
	}
	public String getTotalseats() {
		return totalseats;
	}
	public void setTotalseats(String totalseats) {
		this.totalseats = totalseats;
	}
	public String getCinemaname() {
		return cinemaname;
	}
	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}
	public String getCopytype() {
		return copytype;
	}
	public void setCopytype(String copytype) {
		this.copytype = copytype;
	}
	public String getCopylanguage() {
		return copylanguage;
	}
	public void setCopylanguage(String copylanguage) {
		this.copylanguage = copylanguage;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getWebsitepric() {
		return websitepric;
	}
	public void setWebsitepric(String websitepric) {
		this.websitepric = websitepric;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public void setOriginalapprice(Double originalapprice) {
		this.originalapprice = originalapprice;
	}
	public Double getOriginalapprice() {
		return originalapprice;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setUseflag(String useflag) {
		this.useflag = useflag;
	}
	public String getUseflag() {
		return useflag;
	}
}
