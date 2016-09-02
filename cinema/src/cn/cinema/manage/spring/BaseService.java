package cn.cinema.manage.spring;

import java.util.List;

import cn.cinema.manage.util.PageList;


public interface BaseService {
   /**
    * 查询list集合 无参数
    */
	List queryForList(String sql);
	/**
	 * 查询List集合
	 * @param sql iBATIS xml中select的id
	 *  @param obj 查询参数
	 */
	List queryForList(String sql,Object obj) ;
	
	/**
	 * 查询单条数据
	 * @param sql iBATIS xml中select的id
	 * @param obj 查询参数
	 */
	Object queryForObject(String sql,Object obj);
	
	/**
	 * insert 方法
	 * @param sql iBATIS xml中select的id
	 * @param obj 查询参数
	 */
	void save(String sql,Object obj);
	
	/**
	 * delete删除方法 返回主键
	 * @param sql iBATIS xml中select的id
	 * @param obj 查询参数
	 */
	Object delete(String sql,Object obj);
	
	/**
	 * 查询数据库时间
	 */
	String queryForSysDate(String formatdate);
	/**
	 * update 修改方法
	 * @param sql iBATIS xml中select的id
	 * @param obj 查询参数
	 */
	void update(String sql ,Object obj);
	/**
	 * 分页
	 */
	 PageList findPage(String querySql, String countSql,Object obj, Integer page, Integer numPerPage);
	
	 /**
	  * 批量添加
	  * @param sql
	  * @param objList
	  * @return
	  */
	 int saveAll(String sql, List objList);
	 
	 /**
	  * insert 方法 返回主键
	  * @param sql iBATIS xml中select的id
	  * @param obj 查询参数
	  */
	 Object saveObject(String sql , Object obj);
}
