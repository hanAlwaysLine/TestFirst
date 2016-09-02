package cn.cinema.manage.entity.manage;

import java.sql.Clob;

public class T0103_HallInfo {
	
	private String hallno;
	private String hallname;
	private String hallename;
	private Integer hallseat;
	private String halltext;

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

	public String getHallename() {
		return hallename;
	}

	public void setHallename(String hallename) {
		this.hallename = hallename;
	}

	public Integer getHallseat() {
		return hallseat;
	}

	public void setHallseat(Integer hallseat) {
		this.hallseat = hallseat;
	}

	public String getHalltext() {
		return halltext;
	}

	public void setHalltext(String halltext) {
		this.halltext = halltext;
	}
}
