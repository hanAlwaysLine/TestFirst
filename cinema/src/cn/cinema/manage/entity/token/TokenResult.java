package cn.cinema.manage.entity.token;

public class TokenResult {

	private String ResultCode;
	private String TokenID;//
	private String Token;//令牌
	private String TimeOut;//生命周期
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getTokenID() {
		return TokenID;
	}
	public void setTokenID(String tokenID) {
		TokenID = tokenID;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getTimeOut() {
		return TimeOut;
	}
	public void setTimeOut(String timeOut) {
		TimeOut = timeOut;
	}
	
	
	
	

	
}
