package cn.cinema.pojo;

/**
 * Cinema影院
 * 
 * @author tangfx. 
 * 
 */
public class Cinema {
	
	private String id;		
	private String name;		
	private String linkid;		
	private String local;		
	private String hallcount;		
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getHallcount() {
		return hallcount;
	}
	public void setHallcount(String hallcount) {
		this.hallcount = hallcount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
