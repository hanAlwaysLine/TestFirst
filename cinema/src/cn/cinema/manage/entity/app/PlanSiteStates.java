package cn.cinema.manage.entity.app;

import java.util.List;

import cn.cinema.manage.entity.manage.PlanSiteState;

public class PlanSiteStates {

	private String resultcode;
	private String resultmsg;
	private String minrow;
	private String mincol;
	private String maxrow;
	private String maxcol;
	private String mingraphrow;
	private String mingraphcol;
	private String maxgraphrow;
	private String maxgraphcol;
	private List<PlanSiteState> plansitestate;
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
	public String getMinrow() {
		return minrow;
	}
	public void setMinrow(String minrow) {
		this.minrow = minrow;
	}
	public String getMincol() {
		return mincol;
	}
	public void setMincol(String mincol) {
		this.mincol = mincol;
	}
	public String getMaxrow() {
		return maxrow;
	}
	public void setMaxrow(String maxrow) {
		this.maxrow = maxrow;
	}
	public String getMaxcol() {
		return maxcol;
	}
	public void setMaxcol(String maxcol) {
		this.maxcol = maxcol;
	}
	public List<PlanSiteState> getPlansitestate() {
		return plansitestate;
	}
	public void setPlansitestate(List<PlanSiteState> plansitestate) {
		this.plansitestate = plansitestate;
	}
	public String getMingraphrow() {
		return mingraphrow;
	}
	public void setMingraphrow(String mingraphrow) {
		this.mingraphrow = mingraphrow;
	}
	public String getMingraphcol() {
		return mingraphcol;
	}
	public void setMingraphcol(String mingraphcol) {
		this.mingraphcol = mingraphcol;
	}
	public String getMaxgraphrow() {
		return maxgraphrow;
	}
	public void setMaxgraphrow(String maxgraphrow) {
		this.maxgraphrow = maxgraphrow;
	}
	public String getMaxgraphcol() {
		return maxgraphcol;
	}
	public void setMaxgraphcol(String maxgraphcol) {
		this.maxgraphcol = maxgraphcol;
	}
	
}
