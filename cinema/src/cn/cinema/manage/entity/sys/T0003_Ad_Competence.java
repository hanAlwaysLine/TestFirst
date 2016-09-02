package cn.cinema.manage.entity.sys;

import java.util.ArrayList;
import java.util.List;

public class T0003_Ad_Competence {
	private String id;//id
	private String father;//父ie
	private String title;//名称
	private String link;//url
	private String addtime;//添加时间
	private String state;//状态
	private String logo;
	private String recommended;
	private String roleId;
	
	private List <T0003_Ad_Competence>seList = new ArrayList<T0003_Ad_Competence>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRecommended() {
		return recommended;
	}
	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}
	public List<T0003_Ad_Competence> getSeList() {
		return seList;
	}
	public void setSeList(List<T0003_Ad_Competence> seList) {
		this.seList = seList;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
