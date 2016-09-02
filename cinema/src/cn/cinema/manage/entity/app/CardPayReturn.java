package cn.cinema.manage.entity.app;

public class CardPayReturn {

	private String ResultCode;
	private String ResultMsg;
	private String GroundTradeNo;
	private String TradeResultStatus;
	private String DeductMoney;
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getResultMsg() {
		return ResultMsg;
	}
	public void setResultMsg(String resultMsg) {
		ResultMsg = resultMsg;
	}
	public String getGroundTradeNo() {
		return GroundTradeNo;
	}
	public void setGroundTradeNo(String groundTradeNo) {
		GroundTradeNo = groundTradeNo;
	}
	public String getTradeResultStatus() {
		return TradeResultStatus;
	}
	public void setTradeResultStatus(String tradeResultStatus) {
		TradeResultStatus = tradeResultStatus;
	}
	public String getDeductMoney() {
		return DeductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		DeductMoney = deductMoney;
	}
	
}
