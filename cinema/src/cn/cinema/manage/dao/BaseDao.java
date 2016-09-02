package cn.cinema.manage.dao;

import java.util.List;

import cn.cinema.manage.util.PageList;


/**
 * DAO 接口.
 * @author yangdx.
 *
 */
public interface BaseDao { 
	/**
	 * 查询集合 无参数
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
	 * update 修改方法
	 * @param sql iBATIS xml中select的id
	 * @param obj 查询参数
	 */
	void update(String sql ,Object obj);
	/**
	 * 分页
	 * @param sql iBATIS xml中select的id
	 */
	PageList findPage(String querySql, String countSql,Object obj,int page,int numPerPage);
	 /**
	  * insert 方法 返回对象类型
	  * @param sql iBATIS xml中select的id
	  * @param obj 查询参数
	  */
	 Object saveObject(String sql , Object obj);
	 
	 /**
	  * 批量添加
	  * @param sql
	  * @param objList
	  * @return
	  */
	 int saveAll(String sql, List objList);
}
