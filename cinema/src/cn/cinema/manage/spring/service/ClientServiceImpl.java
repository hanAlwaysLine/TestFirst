package cn.cinema.manage.spring.service;

import org.springframework.transaction.annotation.Transactional;

import cn.cinema.manage.dao.BaseDao;
import cn.cinema.manage.entity.ticket.T0300_Orderform;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.ClientService;

public class ClientServiceImpl implements ClientService{

	public BaseDao dao;
	
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void saveOrder(String userOrderSql, T0300_UserOrder userOrder, String orderFromSql, T0300_Orderform orderFrom) {
		dao.save(userOrderSql, userOrder);
		dao.save(orderFromSql, orderFrom);
	}
	
}
