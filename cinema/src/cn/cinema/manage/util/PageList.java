package cn.cinema.manage.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页显示信息类。
 * 
 * @author bzyg
 */
public class PageList {

	/** 每页显示记录数 */
	private long numPerPage = 20;

	/** 当前页数 */
	private long page = 1;

	/** 总页数 */
	private long pagesum = 1;

	/** 总记录数 */
	private long objectsum = 0;

	/** 当前页开始记录序号 */
	private long begin;

	/** 当前页结束记录序号 */
	private long end;

	/** 记录信息集合 */
	private List objects = new ArrayList();
	/**
	 * 分页计算.
	 */
	public void count(){
	    //计算总页数.
		if(objectsum%numPerPage==0){
			pagesum = objectsum/numPerPage;
		}else{
			pagesum = objectsum/numPerPage+1;
		}
		
		if(page<0){
			page=0;
		}else if(page>pagesum){
			page=pagesum;
		}
		//查询结束记录位置.
		end = page*numPerPage;
		if(end>objectsum){
			end = objectsum;
		}
		//计算开始位置.
		begin = (page-1)*numPerPage;
		
		if(begin<0){
			begin=0;
		}
	}

	public long getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(long numPerPage) {
		this.numPerPage = numPerPage;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPagesum() {
		return pagesum;
	}

	public void setPagesum(long pagesum) {
		this.pagesum = pagesum;
	}

	public long getObjectsum() {
		return objectsum;
	}

	public void setObjectsum(long objectsum) {
		this.objectsum = objectsum;
	}

	public long getBegin() {
		return begin;
	}

	public void setBegin(long begin) {
		this.begin = begin;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
	}

	

}
