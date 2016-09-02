package cn.cinema.manage.entity.sys;
/**
 * 用户权限表
 * @author yangdx
 *
 */
public class T0005_Ad_Comprole {
private String id;//id
private String competence_id;//权限id
private String role_id;//角色id
private String addtime;//创建时间
private String state;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCompetence_id() {
	return competence_id;
}
public void setCompetence_id(String competenceId) {
	competence_id = competenceId;
}
public String getRole_id() {
	return role_id;
}
public void setRole_id(String roleId) {
	role_id = roleId;
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

}
