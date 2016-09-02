package cn.cinema.manage.entity.app;

import java.util.List;

import cn.cinema.manage.entity.ticket.T0300_Orderform;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;

public class Order {
	private String resultcode;
	private String resultmsg;
	private List<T0300_UserOrder> userorder;
	private T0300_Orderform orderform;
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
	public List<T0300_UserOrder> getUserorder() {
		return userorder;
	}
	public void setUserorder(List<T0300_UserOrder> userorder) {
		this.userorder = userorder;
	}
	public T0300_Orderform getOrderform() {
		return orderform;
	}
	public void setOrderform(T0300_Orderform orderform) {
		this.orderform = orderform;
	}
	
}
