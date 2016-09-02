package cn.cinema.manage.entity.goods;

/**
 * T0300OrderList entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class T0300OrderList implements java.io.Serializable {

	private Integer ORDERLIST_ID;
	private String ORDER_NO;
	private Integer GOODS_ID;
	private Integer GOODS_COUNT;
	private Integer GOODS_PRICE;
	private Integer GOODS_COUNTPRICE;
	
	/*************客串属性***********/
	private String GOODS_NAME; //商品名称
	
	public Integer getORDERLIST_ID() {
		return ORDERLIST_ID;
	}
	public void setORDERLIST_ID(Integer oRDERLISTID) {
		ORDERLIST_ID = oRDERLISTID;
	}
	public String getORDER_NO() {
		return ORDER_NO;
	}
	public void setORDER_NO(String oRDERNO) {
		ORDER_NO = oRDERNO;
	}
	public Integer getGOODS_ID() {
		return GOODS_ID;
	}
	public void setGOODS_ID(Integer gOODSID) {
		GOODS_ID = gOODSID;
	}
	public Integer getGOODS_COUNT() {
		return GOODS_COUNT;
	}
	public void setGOODS_COUNT(Integer gOODSCOUNT) {
		GOODS_COUNT = gOODSCOUNT;
	}
	public Integer getGOODS_PRICE() {
		return GOODS_PRICE;
	}
	public void setGOODS_PRICE(Integer gOODSPRICE) {
		GOODS_PRICE = gOODSPRICE;
	}
	public Integer getGOODS_COUNTPRICE() {
		return GOODS_COUNTPRICE;
	}
	public void setGOODS_COUNTPRICE(Integer gOODSCOUNTPRICE) {
		GOODS_COUNTPRICE = gOODSCOUNTPRICE;
	}
	public String getGOODS_NAME() {
		return GOODS_NAME;
	}
	public void setGOODS_NAME(String gOODSNAME) {
		GOODS_NAME = gOODSNAME;
	}
}
