package cn.cinema.manage.entity.reportform;

@SuppressWarnings("serial")
public class T2001AppCount implements java.io.Serializable
{

    // Fields

    private String app_id;
    private String app_type;
    private String add_time;
    private String count;
    
    //客串属性
    private String startdate;
    private String enddate;
    
    
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
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String appId) {
		app_id = appId;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String appType) {
		app_type = appType;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String addTime) {
		add_time = addTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
