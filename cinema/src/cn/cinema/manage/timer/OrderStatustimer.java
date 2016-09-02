/**
 * 
 */
package cn.cinema.manage.timer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.getWebService;
import cn.cinema.pojo.T0300_ORDERFORM;

/**
 * TODO
 * 
 * @ClassName GetOrderStatustimer
 * @Description TODO
 * @author 汤凤欣
 * @date 2012-12-10
 */
public class OrderStatustimer extends TimerTask {
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(OrderStatustimer.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	
	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	private getWebService getWebService = new getWebService();
	
	@Override
	public void run() {
		try {
			
			logger.info("开始同步订单取票状态"+new Date());
			//获取本地库 支付成功且 未取票的订单
			List<T0300_ORDERFORM> orderList = bs.queryForList("queryOrderList");
			if (orderList.size()==0) {
				logger.info("无未取票订单");
			}
			for (int i = 0; i < orderList.size(); i++) {
				T0300_ORDERFORM order = orderList.get(i);
				//如果订单不是已取票 调用接口 查询订单取票状态
//			System.out.println("订单取票标识符"+order.getPrintedflg());
				logger.info("订单号为 ："+order.getOrderno()+" 的订单取票状态"+order.getPrintedflg());
				if (!"Y".equals(order.getPrintedflg())) {
					logger.info("获取订单号 ："+order.getOrderno()+"的取票状态");
					String printedflg = this.qryOrder(order);
					//如果订单已取票修改订单取票标识符
					if ("Y".equals(printedflg)) {
						logger.info("修改订单编号 "+order.getOrderno()+" 的取票标识为Y");
						bs.update("updateOrderflg", order);
					}
				}
			}
			logger.info("结束同步订单取票状态"+new Date());
			
		} catch (Exception e) {
			logger.info("同步订单取票状态异常！");
			logger.info(e);
			e.printStackTrace();
		}
	}

	//根据本地订单编号获取火凤凰取票标识
	private String qryOrder(T0300_ORDERFORM order) {
		Md5 md5 = new Md5();
		String payuserId = Messages.getString("userId");
		String payuserPass = Messages.getString("userPass");
		String payurl = Messages.getString("url");
		String paynameSpace = Messages.getString("namespace");
		String checkValue = "";
		try {
			checkValue = md5.getMD5ofStr(URLEncoder.encode("userId="+payuserId+"&userPass="
					+payuserPass+"&orderNo="+order.getOrderno()+"&ticketCount="
					+order.getTicketsnum()+"&cinemaId="+order.getPlaceid()+"&cinemaLinkId="+order.getCinemalinkid()+"&randKey="+"12345678","utf-8").toLowerCase()).toLowerCase();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String []parameter={"userId","userPass","orderNo","ticketCount",
				"checkValue","cinemaId","cinemaLinkId","randKey"};
		Object []message= {payuserId, payuserPass, order.getOrderno(), order.getTicketsnum(),
				checkValue, order.getPlaceid(), order.getCinemalinkid(),"12345678"};
		for (int i = 0; i < message.length; i++) {
			logger.info(parameter[i]+" : "+message[i]);
		}
		long begintime = System.currentTimeMillis();
		logger.info("当前时间 :"+new Date());
		String qryOrder = (String) getWebService.getWebService(message, "qryOrder", payurl, paynameSpace, parameter);
		long endtime = System.currentTimeMillis(); 
		logger.info("当前时间 :"+new Date());
		logger.info("qryOrder接口调用耗时 :"+(endtime-begintime)+"");
		logger.info("qryOrder 接口返回 ："+qryOrder);
		if ( qryOrder==null ) {
			return "";
		}
		Document document = null;
		String printedFlg = "";
		try {
			document = DocumentHelper.parseText((String) qryOrder);
			Element rootEle = document.getRootElement();
			Iterator iter = rootEle.elementIterator("seats");
			while (iter.hasNext()) {
				 Element recordEle = (Element) iter.next();
				 Iterator seatIt = recordEle.elementIterator("seat");
				 while (seatIt.hasNext()) {
					Element seatEle = (Element) seatIt.next();
	//				System.out.println(seatEle.attributeValue("printedFlg"));
					printedFlg = seatEle.attributeValue("printedFlg");
				}
			}
		} catch (DocumentException e) {
			logger.info("查询订单状态失败");
//			Map parameterMap = new HashMap(); 
//			parameterMap.put("time", new  Date());
//			parameterMap.put("name", "qryOrder");
//			parameterMap.put("result", qryOrder);
//			parameterMap.put("exception", e.toString());
//			bs.save("insertErrorLog", parameterMap);
			e.printStackTrace();
		}
		return printedFlg;
	}
}
