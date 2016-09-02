package cn.cinema.manage.entity.reportform;

@SuppressWarnings("serial")
public class MemberNum implements java.io.Serializable {

	// Fields

	private String usertime;
	private String count;
	private String startdate;
	private String enddate;
	private String user_app;

	public String getUsertime() {
		return usertime;
	}

	public void setUsertime(String usertime) {
		this.usertime = usertime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getUser_app() {
		return user_app;
	}

	public void setUser_app(String userApp) {
		user_app = userApp;
	}

}
