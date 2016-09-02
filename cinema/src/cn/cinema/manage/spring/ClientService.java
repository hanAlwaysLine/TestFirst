package cn.cinema.manage.spring;

import cn.cinema.manage.entity.ticket.T0300_Orderform;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;

public interface ClientService{

	void saveOrder(String userOrderSql, T0300_UserOrder userOrder, String orderFromSql, T0300_Orderform orderFrom);
}
