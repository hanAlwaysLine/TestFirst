package cn.cinema.manage.entity.app;

import java.util.List;

public class RealCheckSeatStateParameter {

	private String AppCode;
	private String FeatureAppNo;
	private String SerialNum;
	private String PayType;
	private String RecvMobilePhone;
	private String TokenID;
	private String VerifyInfo;
	private List<SeatInfoBean> SeatInfos;
	
	public String getAppCode() {
		return AppCode;
	}
	public void setAppCode(String appCode) {
		AppCode = appCode;
	}
	public String getFeatureAppNo() {
		return FeatureAppNo;
	}
	public void setFeatureAppNo(String featureAppNo) {
		FeatureAppNo = featureAppNo;
	}
	public String getSerialNum() {
		return SerialNum;
	}
	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}
	public String getPayType() {
		return PayType;
	}
	public void setPayType(String payType) {
		PayType = payType;
	}
	public String getRecvMobilePhone() {
		return RecvMobilePhone;
	}
	public void setRecvMobilePhone(String recvMobilePhone) {
		RecvMobilePhone = recvMobilePhone;
	}
	public String getTokenID() {
		return TokenID;
	}
	public void setTokenID(String tokenID) {
		TokenID = tokenID;
	}
	public List<SeatInfoBean> getSeatInfos() {
		return SeatInfos;
	}
	public void setSeatInfos(List<SeatInfoBean> seatInfos) {
		SeatInfos = seatInfos;
	}
	public String getVerifyInfo() {
		return VerifyInfo;
	}
	public void setVerifyInfo(String verifyInfo) {
		VerifyInfo = verifyInfo;
	}
	
}
