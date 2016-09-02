package cn.cinema.manage.entity.sys;

import java.util.ArrayList;
import java.util.List;

public class T1001_Menu {

	private String menuid;
	private String fmenuid;
	private String menuname;
	private String menuurl;
	private String menuseq;
	private String menudesc;
	private String roleId;
	
	private List <T1001_Menu>seList = new ArrayList<T1001_Menu>();
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getFmenuid() {
		return fmenuid;
	}
	public void setFmenuid(String fmenuid) {
		this.fmenuid = fmenuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	public String getMenuseq() {
		return menuseq;
	}
	public void setMenuseq(String menuseq) {
		this.menuseq = menuseq;
	}
	public String getMenudesc() {
		return menudesc;
	}
	public void setMenudesc(String menudesc) {
		this.menudesc = menudesc;
	}
	public List<T1001_Menu> getSeList() {
		return seList;
	}
	public void setSeList(List<T1001_Menu> seList) {
		this.seList = seList;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
