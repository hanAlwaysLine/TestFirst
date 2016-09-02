package cn.cinema.manage.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.transaction.annotation.Transactional;

import cn.cinema.manage.dao.BaseDao;
import cn.cinema.manage.entity.Activitymanage.T0905_LOGRECORD;
import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.service.CardPayService;


public class CardPayServiceImp implements CardPayService{
	public BaseDao dao;
	/**
	 *日志 
	 */
	private Logger logger = Logger.getLogger(CardPayServiceImp.class);
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	private String result;
	
	@Transactional
	public String cardPay(String xml) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			Document dc = DocumentHelper.parseText(xml);
			Element root = dc.getRootElement();
			String serialnumber = root.elementTextTrim("Serialnumber");//中心流水号
			String partnerCode = root.elementTextTrim("PartnerCode");//合作商代码
			String oldPrice = root.elementTextTrim("OldPrice");//原始价格
			String tracePrice = root.elementTextTrim("TracePrice");//手续费
			String discount = root.elementTextTrim("Discount");//折扣
			String cardId = root.elementTextTrim("CardId");//会员卡号
			String passWord = root.elementTextTrim("PassWord");//会员卡密码
			String cinemaname = root.elementTextTrim("Cinemaname");//影院名称
			String filmNo = root.elementTextTrim("FilmNo");//影片编码
			String ticketNum = root.elementTextTrim("TicketNum");//票数
			String featureNo = root.elementTextTrim("FeatureNo");//排期号
			String userName = root.elementTextTrim("UserName");//操作员
			String traceMemo = root.elementTextTrim("TraceMemo");//备注
			//两位的交易类型号 01支付 02预算
			String traceTypeNo = root.elementTextTrim("Type");
			String fprice="0";//折后金额
			String oldfeatureprice = ""; //原始排期价格
			
			//获取影院编号
			String cinemaId = "获取影院编号";
			
			//每张票的原始价格
			String eachTicPrice = Double.toString(Double.parseDouble(oldPrice)/Double.parseDouble(ticketNum));
			T0905_LOGRECORD logrecord = new T0905_LOGRECORD();
			logrecord.setCARDFACADECD(cardId);
			 List listAcc = dao.queryForList("query_T0905_LOGRECORD", logrecord);
			 
			 if(listAcc.size()!=0){
				 //转换实体
				 T0905_LOGRECORD account = (T0905_LOGRECORD) listAcc.get(0);
				//支付
			 	if("01".equals(traceTypeNo)){
			 		//获取排期价格sql
			 		T0201_FEATURE_APP  featureAPP = new T0201_FEATURE_APP();
			 		featureAPP.setFeatureno(featureNo);
					List featrue_list = dao.queryForList("query_T0201_FEATURE_APP",featureAPP);
					if(featrue_list.size()>0){
						oldfeatureprice = featrue_list.get(0).toString();
					}
					
					DecimalFormat df = new DecimalFormat("0.00");
					Double truediscount= Math.abs((Double.parseDouble(oldPrice)/(Double.parseDouble(oldfeatureprice)*Integer.parseInt(ticketNum))*10)); 
					
			 		//交易总金额
				 	Double allPrice = Math.abs(Double.parseDouble(oldPrice))+Math.abs(Double.parseDouble(tracePrice));
				 	
				 	//获取客户交易规则信息
//				 	List<Double> resultList = GetAccTraceRule.getPriceAndScore(account.getLevelno(), allPrice,"01","1");
//					score = resultList.get(0);
					
			 		logger.info("总金额:"+Math.abs(allPrice)+"手续费:"+Math.abs(Double.parseDouble(tracePrice))+"数量"+ticketNum);	
			 		if(allPrice > Double.parseDouble(account.getBALANCE().toString())){
						logger.error("会员卡余额不足--网售支付失败！");
						result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
			 			 "<CardPayReturn>" +
			 			 "<ResultCode>1</ResultCode>" +
			 			 "<ResultMsg>会员卡余额不足</ResultMsg>" +
			 			 "<GroundTradeNo></GroundTradeNo>" +
			 			 "<TradeResultStatus></TradeResultStatus>" +
			 			 "<DeductMoney></DeductMoney>" +
			 			 "</CardPayReturn>";
						return result;
					}
			 	account.setBALANCE(Double.valueOf(account.getBALANCE())-Math.abs(allPrice)+"");
			 	//修改会员信息
			 	dao.update("update_T0905_LOGRECORD", account);
			 	
					 	logger.info("会员卡支付成功！交易号："+serialnumber+"折扣："+df.format(truediscount));
					 	result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
					 			 "<CardPayReturn>" +
					 			 "<ResultCode>0</ResultCode>" +
					 			 "<ResultMsg>支付成功</ResultMsg>" +
					 			 "<GroundTradeNo>"+serialnumber+"</GroundTradeNo>" +
					 			 "<TradeResultStatus>0</TradeResultStatus>" +
					 			 "<DeductMoney>"+allPrice+"</DeductMoney>" +
					 			 "</CardPayReturn>";
			 	}
			 }else{
				 //会员不存在
				 result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
	 			 "<CardPayReturn>" +
	 			 "<ResultCode>1</ResultCode>" +
	 			 "<ResultMsg>会员不存在，用户名或密码错误</ResultMsg>" +
	 			 "<GroundTradeNo></GroundTradeNo>" +
	 			 "<TradeResultStatus></TradeResultStatus>" +
	 			 "<DeductMoney></DeductMoney>" +
	 			 "</CardPayReturn>";
			 }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
 			 "<CardPayReturn>" +
 			 "<ResultCode>1</ResultCode>" +
 			 "<ResultMsg>发生异常</ResultMsg>" +
 			 "<GroundTradeNo></GroundTradeNo>" +
 			 "<TradeResultStatus></TradeResultStatus>" +
 			 "<DeductMoney></DeductMoney>" +
 			 "</CardPayReturn>";
		}
		return result;
	}

}
