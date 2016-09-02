package cn.cinema.manage.entity.app;

public class CardPay {

	private String resultcode;
	private String resultmsg;
	private CardPayReturn cardPayReturn;
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
	public CardPayReturn getCardPayReturn() {
		return cardPayReturn;
	}
	public void setCardPayReturn(CardPayReturn cardPayReturn) {
		this.cardPayReturn = cardPayReturn;
	}
}
