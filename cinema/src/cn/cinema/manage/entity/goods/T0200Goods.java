package cn.cinema.manage.entity.goods;

/**
 * T0200Goods entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class T0200Goods implements java.io.Serializable {

	private Integer GOODS_ID;
	private String GOODS_NAME;
	private Double GOODS_PRICE;
	private String GOODS_TYPE;
	private String GOODS_PIC;
	private String IS_DISCOUNT;
	private Integer GOODS_COUNT;
	private String GOODS_MEMO;

	public Integer getGOODS_ID() {
		return GOODS_ID;
	}

	public void setGOODS_ID(Integer gOODSID) {
		GOODS_ID = gOODSID;
	}

	public String getGOODS_NAME() {
		return GOODS_NAME;
	}

	public void setGOODS_NAME(String gOODSNAME) {
		GOODS_NAME = gOODSNAME;
	}

	public Double getGOODS_PRICE() {
		return GOODS_PRICE;
	}

	public void setGOODS_PRICE(Double gOODSPRICE) {
		GOODS_PRICE = gOODSPRICE;
	}

	public String getGOODS_TYPE() {
		return GOODS_TYPE;
	}

	public void setGOODS_TYPE(String gOODSTYPE) {
		GOODS_TYPE = gOODSTYPE;
	}

	public String getIS_DISCOUNT() {
		return IS_DISCOUNT;
	}

	public void setIS_DISCOUNT(String iSDISCOUNT) {
		IS_DISCOUNT = iSDISCOUNT;
	}

	public Integer getGOODS_COUNT() {
		return GOODS_COUNT;
	}

	public void setGOODS_COUNT(Integer gOODSCOUNT) {
		GOODS_COUNT = gOODSCOUNT;
	}

	public String getGOODS_PIC() {
		return GOODS_PIC;
	}

	public void setGOODS_PIC(String gOODSPIC) {
		GOODS_PIC = gOODSPIC;
	}

	public String getGOODS_MEMO() {
		return GOODS_MEMO;
	}

	public void setGOODS_MEMO(String gOODSMEMO) {
		GOODS_MEMO = gOODSMEMO;
	}

}
