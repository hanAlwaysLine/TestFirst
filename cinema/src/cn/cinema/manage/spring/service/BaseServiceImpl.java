package cn.cinema.manage.spring.service;

import java.util.List;

import cn.cinema.manage.dao.BaseDao;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;

public class BaseServiceImpl implements BaseService{
	public BaseDao dao;
	
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
	public List queryForList(String sql) {
		// TODO Auto-generated method stub
		return dao.queryForList(sql);
	}
	
	public Object delete(String sql, Object obj) {
		// TODO Auto-generated method stub
		return dao.delete(sql, obj);
	}
	
	
	@SuppressWarnings("unchecked")
	public List queryForList(String sql, Object obj) {
		// TODO Auto-generated method stub
		return dao.queryForList(sql, obj);
	}

	public Object queryForObject(String sql, Object obj) {
		// TODO Auto-generated method stub
		return dao.queryForObject(sql, obj);
	}

	public void save(String sql, Object obj) {
		// TODO Auto-generated method stub
		dao.save(sql, obj);
	}

	public String queryForSysDate(String formatdate) {
		String sysDate = (String) dao.queryForObject("select_sysDate", formatdate);
		return sysDate;
	}

	public void update(String sql, Object obj) {
		// TODO Auto-generated method stub
		dao.update(sql, obj);
	}

	public PageList findPage(String querySql, String countSql, Object obj,
			Integer page, Integer numPerPage) {
		// TODO Auto-generated method stub
	        return dao.findPage(querySql, countSql,obj, page, numPerPage);
	}

	public int saveAll(String sql, List objList) {
		// TODO Auto-generated method stub
		return dao.saveAll(sql, objList);
	}

	public Object saveObject(String sql, Object obj) {
		// TODO Auto-generated method stub
		return dao.saveObject(sql, obj);
	}

}
