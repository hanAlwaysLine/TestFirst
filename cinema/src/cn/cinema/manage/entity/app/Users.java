package cn.cinema.manage.entity.app;

import cn.cinema.manage.entity.sys.T1000_Users;

public class Users {

	private String resultcode;
	private String resultmsg;
	private T1000_Users userinfo;
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public T1000_Users getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(T1000_Users userinfo) {
		this.userinfo = userinfo;
	}
	
}
