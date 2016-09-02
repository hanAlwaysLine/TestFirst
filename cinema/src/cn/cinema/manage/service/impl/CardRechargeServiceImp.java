package cn.cinema.manage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.transaction.annotation.Transactional;

import cn.cinema.manage.dao.BaseDao;
import cn.cinema.manage.entity.Activitymanage.T0905_LOGRECORD;
import cn.cinema.manage.service.CardRechargeService;



public class CardRechargeServiceImp implements CardRechargeService{
	public BaseDao dao;

	private Logger  logger = Logger.getLogger(CardRechargeServiceImp.class);
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
	private String result;
	@SuppressWarnings("unchecked")
	@Transactional
	public String recharge(String str) {
		// TODO Auto-generated method stub
			try{
			String cardNo = "";
			Document dc = DocumentHelper.parseText(str);
			Element root = dc.getRootElement();
			String serialnumber = root.elementTextTrim("Serialnumber");//中心流水号
			String partnerCode = root.elementTextTrim("PartnerCode");//合作商代码
			String price = root.elementTextTrim("Price");
			String cardId = root.elementTextTrim("CardId");
			String password = root.elementTextTrim("PassWord");
			String cinemaName = root.elementTextTrim("Cinemaname");
			String userName = root.elementTextTrim("UserName");
			String traceMemo = root.elementTextTrim("TraceMemo");
			String tempCardId = cardId.substring(0,1);
			
			//获取影院编号
			String cinemaId = "获取影院编号";
			
			Double score = 0.00;
			//星是卡号
			T0905_LOGRECORD logrecord = new T0905_LOGRECORD();
			logrecord.setCARDFACADECD(cardId);
			 List listAcc = dao.queryForList("query_T0905_LOGRECORD", logrecord);
			if(listAcc.size()!=0){
				 T0905_LOGRECORD account = (T0905_LOGRECORD) listAcc.get(0);
				 account.setBALANCE(Double.valueOf(account.getBALANCE())+Math.abs(Double.valueOf(price))+"");
				 	//修改会员信息
				 	dao.update("update_T0905_LOGRECORD", account);
				 	result="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
					"<CardRechargeReturn>" +
					"<ResultCode>0</ResultCode>" +
					"<ResultMsg>会员充值成功</ResultMsg>" +
					"<Balance>"+Double.valueOf(account.getBALANCE())+Math.abs(Double.valueOf(price))+"</Balance>" +
					"<Score></Score>" +
					"</CardRechargeReturn>";
				}else{
					//无会员信息 充值失败
					result="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
							"<CardRechargeReturn>" +
							"<ResultCode>1</ResultCode>" +
							"<ResultMsg>会员不存在或密码不正确</ResultMsg>" +
							"<Balance></Balance>" +
							"<Score></Score>" +
							"</CardRechargeReturn>";
				}
			}catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
//				result = "{\"flag\":\"back\",\"results\":\"0200\",\"cinemaID\":\""+cinemaId+"\"}";
//				// TODO: handle exception
				result="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
				"<CardRechargeReturn>" +
				"<ResultCode>1</ResultCode>" +
				"<ResultMsg>发生异常</ResultMsg>" +
				"<Balance></Balance>" +
				"<Score></Score>" +
				"</CardRechargeReturn>";
			}
			return result;
	}

}
