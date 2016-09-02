package cn.cinema.manage.entity.sys;

public class T0502_Placesysmod {
	private String fid;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	private String cinemaid;
	private String moduleid;
	private String usable;
	private String expiredflag;
	private String moduletype;
	private String tmusable;
	
	public String getModuletype() {
		return moduletype;
	}
	public void setModuletype(String moduletype) {
		this.moduletype = moduletype;
	}
	public String getTmusable() {
		return tmusable;
	}
	public void setTmusable(String tmusable) {
		this.tmusable = tmusable;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getUsable() {
		return usable;
	}
	public void setUsable(String usable) {
		this.usable = usable;
	}
	public String getExpiredflag() {
		return expiredflag;
	}
	public void setExpiredflag(String expiredflag) {
		this.expiredflag = expiredflag;
	}
	public void setCinemaid(String cinemaid) {
		this.cinemaid = cinemaid;
	}
	public String getCinemaid() {
		return cinemaid;
	}
	
}
