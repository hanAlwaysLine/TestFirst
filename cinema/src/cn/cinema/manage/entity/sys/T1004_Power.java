package cn.cinema.manage.entity.sys;
/**
 * 用户权限表
 * @author yangdx
 *
 */
public class T1004_Power {
private String powerid;
private String powertype;
private String menufunctionid;
private String roleid;
public String getPowerid() {
	return powerid;
}
public void setPowerid(String powerid) {
	this.powerid = powerid;
}
public String getPowertype() {
	return powertype;
}
public void setPowertype(String powertype) {
	this.powertype = powertype;
}
public String getMenufunctionid() {
	return menufunctionid;
}
public void setMenufunctionid(String menufunctionid) {
	this.menufunctionid = menufunctionid;
}
public String getRoleid() {
	return roleid;
}
public void setRoleid(String roleid) {
	this.roleid = roleid;
}

}
