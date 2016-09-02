package cn.cinema.manage.iface.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.cinema.manage.entity.app.CardPay;
import cn.cinema.manage.entity.app.CardPayReturn;
import cn.cinema.manage.entity.app.FeatureInfos;
import cn.cinema.manage.entity.app.Order;
import cn.cinema.manage.entity.app.PlanSiteStates;
import cn.cinema.manage.entity.app.SeatInfoBean;
import cn.cinema.manage.entity.app.Users;
import cn.cinema.manage.entity.filmmanage.T0111_FILMMESSAGE;
import cn.cinema.manage.entity.manage.PlanSiteState;
import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.entity.sys.T0907_Version;
import cn.cinema.manage.entity.sys.T1000_Users;
import cn.cinema.manage.entity.ticket.T0300_Orderform;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.iface.AppCode;
import cn.cinema.manage.iface.CallMain;
import cn.cinema.manage.iface.IClientService;
import cn.cinema.manage.iface.StatusCode;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.spring.ClientService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.DateUtil;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;

public class ClientServiceImpl implements IClientService {

	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(ClientServiceImpl.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	
	/**
	 * 数据操作类
	 */
	private ClientService cs = (ClientService) ServiceLocator.getService("clientService");
	
	/**
	 * MD5加密
	 */
	Md5 md5 = new Md5();
	
	/*----------卖票接口   卖票流程，先预算，  --中心锁座，生成订单，然后支付，支付成功后调中心出票---------*/
	
	
	/**
	 * 获取订单列表
	 */
	public String getOrderList(String appcode, String validatekey, String userId, String orderNo){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getOrderList获取订单列表  parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",userId:"+userId+",orderNo:"+orderNo+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, userId, orderNo)){
				Order order = new Order();
				if(StringUtils.isBlank(userId)){
					order.setResultcode(StatusCode.c_1002);
					order.setResultmsg(StatusCode.d_1002);
				}else{
					//查询订单主表
					T0300_UserOrder userOrder = new T0300_UserOrder();
					userOrder.setUser_id(userId);
					userOrder.setOrder_no(orderNo);
			    	List<T0300_UserOrder> userOrderList = bs.queryForList("clilent_query_userorder", userOrder);
			    	order.setUserorder(userOrderList);
			    	//查询订单详细信息
					if(!StringUtils.isBlank(orderNo)){
						T0300_Orderform orderFrom = new T0300_Orderform();
				    	orderFrom.setOrderno(orderNo);
				    	List<T0300_Orderform> orderFormList = bs.queryForList("clilent_query_orderfrom", orderFrom);
				    	if(orderFormList.size()>0){
				    		order.setOrderform(orderFormList.get(0));
				    	}
					}
					order.setResultcode(StatusCode.c_0000);
					order.setResultmsg(StatusCode.d_0000);
				}
				JSONObject jo = JSONObject.fromObject(order);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 会员登录
	 */
	public String userLogin(String appcode, String validatekey, String mobilePhone, String passWord){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",userLogin会员登录  parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",mobilePhone:"+mobilePhone+",passWord:"+passWord+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			Users users = new Users();
			if(StringUtils.isBlank(passWord) || StringUtils.isBlank(mobilePhone)){
				users.setResultcode(StatusCode.c_1002);
				users.setResultmsg(StatusCode.d_1002);
			}else{
				T1000_Users t1000 = new T1000_Users();
				t1000.setUser_mobile(mobilePhone);
				t1000.setUser_pass(passWord);
				List<T1000_Users> t1000List = bs.queryForList("client_query_user", t1000);
				if(t1000List.size()>0){
					users.setResultcode(StatusCode.c_0000);
					users.setResultmsg(StatusCode.d_0000);
					users.setUserinfo(t1000List.get(0));
				}else{
					users.setResultcode(StatusCode.c_2007);
					users.setResultmsg(StatusCode.d_2007);
				}
			}
			JSONObject jo = JSONObject.fromObject(users);
			sResult.append(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 会员注册
	 */
	public String userRegister(String appcode, String validatekey, String userName, String passWord, String mobilePhone){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",userRegister会员注册 parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",userName:"+userName+",passWord:"+passWord+",mobilePhone:"+mobilePhone+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			Users users = new Users();
			if(StringUtils.isBlank(passWord) || StringUtils.isBlank(mobilePhone)){
				users.setResultcode(StatusCode.c_1002);
				users.setResultmsg(StatusCode.d_1002);
			}else{
				//注册 判断用户名是否可用 和手机号是否已经注册过。
				T1000_Users t1000 = new T1000_Users();
				t1000.setUser_mobile(mobilePhone);
				List<T1000_Users> t1000List = bs.queryForList("client_query_user", t1000);
				if(t1000List.size()>0){
					users.setResultcode(StatusCode.c_2006);
					users.setResultmsg(StatusCode.d_2006);
				}else{
					t1000.setUser_name(userName);
					t1000.setUser_pass(passWord);
					t1000.setUser_mobile(mobilePhone);
					t1000.setUser_app(AppCode.A.getCode(appcode));
					bs.save("client_insert_user", t1000);
					users.setUserinfo(t1000);
					users.setResultcode(StatusCode.c_0000);
					users.setResultmsg(StatusCode.d_0000);
				}
			}
			JSONObject jo = JSONObject.fromObject(users);
			sResult.append(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	
	
	/**
	 * 扣费
	 */
	public String cardPay(String appcode, String validatekey, String userId, String orderNo,
			String placeNo, String partnerId, String cardId, String mobilePhone, String passWord,
			String oldPrice, String tracePrice, String discount, String featureNo, 
			String filmNo, String ticketNum, String traceMemo){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",cardPay 扣费  parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",userId: "+userId+",orderNo:"+orderNo+",placeNo :"+placeNo+",partnerId:"+partnerId+",cardId:"+cardId+",mobilePhone:"+mobilePhone+",passWord:"+passWord+",oldPrice:"+oldPrice+
				",tracePrice:"+tracePrice+",discount:"+discount+",featureNo:"+featureNo+",filmNo:"+filmNo+",ticketNum:"+ticketNum+",traceMemo:"+traceMemo+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, userId, orderNo, placeNo, partnerId, cardId, mobilePhone, passWord, oldPrice, tracePrice, discount, featureNo, filmNo, ticketNum, traceMemo)){
				CardPay cp = new CardPay();
				CardPayReturn cpr = new CardPayReturn();
				if(StringUtils.isBlank(orderNo)){
					return returnmsg("2"); 
				}else{
					String result = CallMain.invokePay("cardPay", true ,placeNo, partnerId, cardId,
							mobilePhone, passWord, "01", oldPrice, tracePrice, discount, featureNo, filmNo,
							ticketNum, traceMemo);
					logger.info("扣费中心返回报文："+result);
					
					//数据处理
					Document doc = null;
					try {
						doc = DocumentHelper.parseText(result);
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					Element rootElt = doc.getRootElement(); // 获取根节点
				    Element eleCode = rootElt.element("ResultCode");
				    Element eleMsg = rootElt.element("ResultMsg");
				    Element eleDeductMoney = rootElt.element("DeductMoney");
				    Element eleGroundTradeNo = rootElt.element("GroundTradeNo");
				    Element eleTradeResultStatus = rootElt.element("TradeResultStatus");
				    
				    //扣费成功
				    if("0".equals(eleCode.getText())){
				    	cpr.setDeductMoney(eleDeductMoney.getText());
				    	cpr.setGroundTradeNo(eleGroundTradeNo.getText());
				    	cpr.setTradeResultStatus(eleTradeResultStatus.getText());
				    	
				    	//获取到订单信息
				    	T0300_UserOrder userOrder = new T0300_UserOrder();
				    	userOrder.setOrder_no(orderNo);
				    	List<T0300_UserOrder> userOrderList = bs.queryForList("clilent_query_userorder", userOrder);
				    	if(userOrderList.size()>0){
				    		userOrder = userOrderList.get(0);
				    	}else{
				    		return returnmsg("2");
				    	}

				    	T0300_Orderform orderFrom = new T0300_Orderform();
				    	orderFrom.setOrderno(orderNo);
				    	List<T0300_Orderform> orderFormList = bs.queryForList("clilent_query_orderfrom", orderFrom);
				    	if(orderFormList.size()>0){
				    		orderFrom = orderFormList.get(0);
				    	}else{
				    		return returnmsg("2");
				    	}
				    	//中心出票接口
						String sellResult = CallMain.invoke("SellTicket", 
								orderFrom.getFeatureappno(),  // 排期应用号
								orderFrom.getCenter_no(),  //中心锁座时返回的订单号
								"",  //取票密码
								userOrder.getOrder_price(),  //要支付的余额
								orderFrom.getPay_type(),	//付费类型
								"",	//接收二唯码手机号码
								"",	//接收二唯码的方式
								"0",	//支付结果
								"false",	//是否由满天星负责扣费
								"false",	//是否由满天星负责发送二维码
								"",		//支付手机号码，若需要满天星支付，填支付的手机号码
								"0",	//0全额支付1预定金方式
								"0",	//商城已经支付的金额
								"",		//满天星发送二唯码的模板编号
								"",		//影院会员卡支付交易流水号
								CPO.getpTokenID());		//令牌id
						logger.info("出票中心返回报文："+sellResult);
						
						
						//数据处理
						Document doc1 = null;
						try {
							doc1 = DocumentHelper.parseText(sellResult);
						} catch (DocumentException e) {
							e.printStackTrace();
						}
						Element rootElt1 = doc1.getRootElement(); // 获取根节点
					    Element eleCode1 = rootElt1.element("ResultCode");
					    
					    
					    //出票成功 
					    if("0".equals(eleCode1.getText())){
					    	cp.setResultcode(StatusCode.c_0000);
					    	cp.setResultmsg(StatusCode.c_0000);
					    }else{
					    	cp.setResultcode(StatusCode.c_6011);
					    	cp.setResultmsg(StatusCode.d_6011);
					    }
					    cpr.setResultCode(eleCode1.getText());
					    //TODO 根据结果修改订单信息和状态
				    }else{
				    	cp.setResultcode(StatusCode.c_0009);
				    	cp.setResultmsg(StatusCode.d_0009);
				    	cpr.setResultCode(eleCode.getText());
				    	cpr.setResultMsg(eleMsg.getText());
				    }
				}
		    	cp.setCardPayReturn(cpr);
			    JSONObject jo = JSONObject.fromObject(cp);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 锁座 
	 */
	public String RealCheck(String appcode, String validatekey, String userId, String featureappno, String recvmobileno, String seatinfo, String deductMoney){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",RealCheck锁座 parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",userId:"+userId+",featureappno:"+featureappno+",recvmobileno:"+recvmobileno+",seatinfo:"+seatinfo+",deductMoney:"+deductMoney+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, userId, featureappno, recvmobileno, seatinfo, deductMoney)){
				String orderNo = String.valueOf(bs.queryForList("client_query_order_seq").get(0)); //获取订单表序列值  主表使用
				String serialNum = String.valueOf(bs.queryForList("client_query_order_seq").get(0));   //从表主键

				// 构造座位集合
				List<SeatInfoBean> sList = new ArrayList<SeatInfoBean>();
				JSONArray jsonArray = JSONArray.fromObject(seatinfo);
				sList = jsonArray.toList(jsonArray, SeatInfoBean.class);
				
				//去中心锁座
				String result = CallMain.RealCheck(featureappno, serialNum, recvmobileno, sList);
				logger.info("锁座中心返回报文："+result);
				
				Document doc = null;
				try {
					doc = DocumentHelper.parseText(result);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				Element rootElt = doc.getRootElement(); // 获取根节点
			    Element eleCode = rootElt.element("ResultCode");
			    Element eleOrder = rootElt.element("OrderNo");

			    Order order = new Order();
			    if("0".equals(eleCode.getText())){
			    	//根据排期应用号获取排期信息
					T0201_FEATURE_APP t0201 = new T0201_FEATURE_APP();
					t0201.setFeatureappno(Integer.parseInt(featureappno));
					List<T0201_FEATURE_APP> t0201List = bs.queryForList("client_query_film_feature", t0201);
					if(t0201List.size()>0){
						t0201 = t0201List.get(0);
					}else{
						return returnmsg("2");
					}
					
					//预算预算后的单价
					double price = Double.parseDouble(deductMoney);
					//总金额
					double sumPrice = price * sList.size();
					
//					/*******************生成订单 */
					T0300_UserOrder userOrder = new T0300_UserOrder();    //主表
					userOrder.setOrder_no(orderNo);
					userOrder.setUser_id(userId);
					userOrder.setOrder_time(DateUtil.getDate());
					userOrder.setOrder_price(String.valueOf(sumPrice));
					userOrder.setOrder_type("1");
					userOrder.setPay_type("0");
					userOrder.setAppcode(AppCode.A.getCode(appcode));
					
					T0300_Orderform orderForm = new T0300_Orderform();        //从表
					orderForm.setFid(serialNum);
					orderForm.setFeatureappno(String.valueOf(t0201.getFeatureappno()));
					orderForm.setOrderno(orderNo);   //userorder  主键
					orderForm.setPlaceid(t0201.getPlaceno());
					orderForm.setTicketsnum(String.valueOf(sList.size()));
					orderForm.setSeatinfo(seatinfo);
					orderForm.setFilmno(t0201.getFilmno());
					orderForm.setHallno(t0201.getHallno());
					orderForm.setShowdate(t0201.getFeaturedate());
					orderForm.setShowtime(t0201.getFeaturetime());
					orderForm.setMessage("");
					orderForm.setResult_lock("");  //锁座  0表示成功，非0失败，非0数字为错误代码。
					orderForm.setSeats_pay("");   //座位列表 支付返回的
					orderForm.setResult_pay("0");   //0,未支付,1,已支付已出票,2已支付未出票,3,已退款.
					orderForm.setState("0");          //订单状态（1：已删除，0：未删除）
					orderForm.setPrice(String.valueOf(price));
					orderForm.setCountprice(String.valueOf(sumPrice));
					orderForm.setPay_type("");    //支付状态,1,会员卡支付,2支付宝支付,3微信支付
					orderForm.setCenter_no(eleOrder.getText());  //锁座成功后中心返回的订单编码
					cs.saveOrder("client_insert_userorder", userOrder, "client_insert_orderform", orderForm);
					
					//锁座成功生成订单 如果锁座失败则直接返回
					order.setResultcode(StatusCode.c_0000);
					order.setResultmsg(StatusCode.d_0000);
					List<T0300_UserOrder> userOrderList = new ArrayList<T0300_UserOrder>();
					userOrderList.add(userOrder);
					order.setUserorder(userOrderList);
					order.setOrderform(orderForm);
			    }else{
			    	order.setResultcode(StatusCode.c_5101);
					order.setResultmsg(StatusCode.d_5101);
			    }
				JSONObject jo = JSONObject.fromObject(order);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 预算
	 */
	public String goBudget(String appcode, String validatekey, String userId,
						String placeNo, String partnerId, String cardId, String mobilePhone, String passWord,
						String oldPrice, String tracePrice, String discount, String featureNo, 
						String filmNo, String ticketNum, String traceMemo){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",goBudget预算  parameter{appCode : " + appcode + ", validatekey:" + validatekey  + 
				",userId: "+userId+",placeNo :"+placeNo+",partnerId:"+partnerId+",cardId:"+cardId+",mobilePhone:"+mobilePhone+",passWord:"+passWord+",oldPrice:"+oldPrice+
				",tracePrice:"+tracePrice+",discount:"+discount+",featureNo:"+featureNo+",filmNo:"+filmNo+",ticketNum:"+ticketNum+",traceMemo:"+traceMemo+" }");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, userId, placeNo, partnerId, cardId, mobilePhone, passWord, oldPrice, tracePrice, discount, featureNo, filmNo, ticketNum, traceMemo)){
				CardPay cp = new CardPay();
				String result = CallMain.invokePay("cardPay", true ,placeNo, partnerId, cardId,
						mobilePhone, passWord, "02", oldPrice, tracePrice, discount, featureNo, filmNo,
						ticketNum, traceMemo);
				logger.info("预算中心返回报文："+result);
				// 预算成功后锁座，锁座成功生成订单
				
				//数据处理
				Document doc = null;
				try {
					doc = DocumentHelper.parseText(result);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				Element rootElt = doc.getRootElement(); // 获取根节点
			    Element eleCode = rootElt.element("ResultCode");
			    Element eleMsg = rootElt.element("ResultMsg");
			    Element eleDeductMoney = rootElt.element("DeductMoney");
			    Element eleGroundTradeNo = rootElt.element("GroundTradeNo");
			    Element eleTradeResultStatus = rootElt.element("TradeResultStatus");
			    
			    CardPayReturn cpr = new CardPayReturn();
			    //获取成功
			    if("0".equals(eleCode.getText())){
			    	cpr.setDeductMoney(eleDeductMoney.getText());
			    	cpr.setGroundTradeNo(eleGroundTradeNo.getText());
			    	cpr.setTradeResultStatus(eleTradeResultStatus.getText());
			    	cp.setResultcode(StatusCode.c_0000);
			    	cp.setResultmsg(StatusCode.d_0000);
			    }else{
			    	cp.setResultcode(StatusCode.c_0009);
			    	cp.setResultmsg(StatusCode.d_0009);
			    }
			    cpr.setResultCode(eleCode.getText());
		    	cpr.setResultMsg(eleMsg.getText());
		    	cp.setCardPayReturn(cpr);
			    JSONObject jo = JSONObject.fromObject(cp);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 根据排期获取座位图
	 */
	public String getPlanSiteState(String appcode, String validatekey, String featureappno){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getPlanSiteState根据排期获取座位图  parameter{appCode : " + appcode + ", validatekey:" + validatekey  + ", featureappno:"+featureappno+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, featureappno)){
				PlanSiteStates pss = new PlanSiteStates();
				if(StringUtils.isBlank(featureappno)){
					pss.setResultcode(StatusCode.c_1002);
					pss.setResultmsg(StatusCode.d_1002);
				}else{
					//去中心获取座位图
					String result = CallMain.invoke("GetPlanSiteState", featureappno, CPO.getpTokenID());
					logger.info("获取座位图中心返回报文："+result);
					//数据处理
					Document doc = null;
					try {
						doc = DocumentHelper.parseText(result);
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					
					Element rootElt = doc.getRootElement(); // 获取根节点
				    Element eleCode = rootElt.element("ResultCode");
				    //获取成功
				    if("0".equals(eleCode.getText())){
						List pssEleList = rootElt.element("PlanSiteStates").elements();
						List<PlanSiteState> pssList = new ArrayList<PlanSiteState>();
						
						//最小座位行
						int minrow = -1;
						//最大座位行
						int maxrow = -1;
						//最小座位列
						int mincol = -1;
						//最大座位列
						int maxcol = -1;
						
						//相对屏幕
						int mingraphrow = -1;
						int mingraphcol = -1;
						int maxgraphrow = -1;
						int maxgraphcol = -1;
						
						
						for(int i = 0 ; i < pssEleList.size() ; i ++){
							Element e=(Element)pssEleList.get(i);
							PlanSiteState planSiteState = new PlanSiteState();
							//座位行
							String row = e.elementText("SeatRow");
							//座位列
							String col = e.elementText("SeatCol");
							//屏幕行
							String graphRow = e.elementText("GraphRow");
							//屏幕列
							String graphCol = e.elementText("GraphCol");
							
							int i_row = Integer.parseInt(row);
							int i_col = Integer.parseInt(col);
							int i_graphRow = Integer.parseInt(graphRow);
							int i_graphCol = Integer.parseInt(graphCol);
							
							if(minrow == -1 || i_row < minrow)minrow = i_row;
							if(maxrow == -1 || i_row > maxrow)maxrow = i_row;
							if(mincol == -1 || i_col < mincol)mincol = i_col;
							if(maxcol == -1 || i_col > maxcol)maxcol = i_col;
							
							if(mingraphrow == -1 || i_graphRow < mingraphrow)mingraphrow = i_graphRow;
							if(maxgraphrow == -1 || i_graphRow > maxgraphrow)maxgraphrow = i_graphRow;
							if(mingraphcol == -1 || i_graphCol < mingraphcol)mingraphcol = i_graphCol;
							if(maxgraphcol == -1 || i_graphCol > maxgraphcol)maxgraphcol = i_graphCol;
							
							planSiteState.setSeatno(e.elementText("SeatNo"));
							planSiteState.setGraphcol(graphCol);
							planSiteState.setGraphrow(graphRow);
							planSiteState.setSeatcol(col);
							planSiteState.setSeatrow(row);
							planSiteState.setSeatstate(e.elementText("SeatState"));
							planSiteState.setSeatpieceno(e.elementText("SeatPieceNo"));
							planSiteState.setSeatpiecename(e.elementText("SeatPieceName"));
							pssList.add(planSiteState);
						}
						pss.setResultcode(StatusCode.c_0000);
						pss.setResultmsg(StatusCode.d_0000);
						pss.setMinrow(String.valueOf(minrow));
						pss.setMaxrow(String.valueOf(maxrow));
						pss.setMincol(String.valueOf(mincol));
						pss.setMaxcol(String.valueOf(maxcol));
						pss.setMingraphrow(String.valueOf(mingraphrow));
						pss.setMaxgraphrow(String.valueOf(maxgraphrow));
						pss.setMingraphcol(String.valueOf(mingraphcol));
						pss.setMaxgraphcol(String.valueOf(maxgraphcol));
						pss.setPlansitestate(pssList);
				    }else{
				    	pss.setResultcode(StatusCode.c_0009);
						pss.setResultmsg(StatusCode.d_0009);
				    }
				}
				
				JSONObject jo = JSONObject.fromObject(pss);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}

	/**
	 * 通过影院获取某天的排期
	 */
	public String getFeatureByCinema(String appcode, String validatekey, String cinemano, String featuredate){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getFilmFeature根据影片编码影院编码获取排期   parameter{appCode : " + appcode + ", validatekey:" + validatekey  + ", cinemano:"+cinemano+", featuredate:"+featuredate+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, cinemano, featuredate)){
				FeatureInfos fi = new FeatureInfos();
				if(StringUtils.isBlank(cinemano) || StringUtils.isBlank(featuredate)){
					fi.setResultcode(StatusCode.c_1002);
					fi.setResultmsg(StatusCode.d_1002);
				}else{
					T0201_FEATURE_APP t0201 = new T0201_FEATURE_APP();
					t0201.setPlaceno(cinemano);
					t0201.setFeaturedate(featuredate);
					List<T0201_FEATURE_APP> t0201List = bs.queryForList("client_query_film_feature", t0201);
					fi.setResultcode(StatusCode.c_0000);
					fi.setResultmsg(StatusCode.d_0000);
					fi.setFeatureinfo(t0201List);
				}
				JSONObject jo = JSONObject.fromObject(fi);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 *  根据影片编码影院编码获取排期
	 */
	public String getFilmFeature(String appcode, String validatekey, String filmno, String cinemano, String featuredate){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getFilmFeature根据影片编码影院编码获取排期   parameter{appCode : " + appcode + ", validatekey:" + validatekey  + ", filmno:"+filmno+", cinemano:"+cinemano+", featuredate:"+featuredate+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, filmno, cinemano, featuredate)){
				FeatureInfos fi = new FeatureInfos();
				if(StringUtils.isBlank(filmno) || StringUtils.isBlank(cinemano) || StringUtils.isBlank(featuredate)){
					fi.setResultcode(StatusCode.c_1002);
					fi.setResultmsg(StatusCode.d_1002);
				}else{
					T0201_FEATURE_APP t0201 = new T0201_FEATURE_APP();
					t0201.setFilmno(filmno);
					t0201.setPlaceno(cinemano);
					t0201.setFeaturedate(featuredate);
					List<T0201_FEATURE_APP> t0201List = bs.queryForList("client_query_film_feature", t0201);
					fi.setResultcode(StatusCode.c_0000);
					fi.setResultmsg(StatusCode.d_0000);
					fi.setFeatureinfo(t0201List);
				}
				JSONObject jo = JSONObject.fromObject(fi);
				sResult.append(jo.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		
		return sResult.toString();
	}
	
	/**
	 * 获取热映影片列表
	 */
	public String getHotFilmList(String appcode, String validatekey, String filmtype, String cityno ,String placeno){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getHotFilmList获取热映影片列表   parameter{appcode : " + appcode + ", validatekey:" + validatekey + ", filmtype:"+filmtype+", cityno:"+cityno+", placeno:"+placeno+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, filmtype, cityno, placeno)){
				T0111_FILMMESSAGE t0111 = new T0111_FILMMESSAGE();
				t0111.setRelease(filmtype);
				List<T0111_FILMMESSAGE> t0111List = bs.queryForList("client_query_film_list", t0111);
				JSONArray jsonArray = JSONArray.fromObject(t0111List);
				sResult.append(jsonArray.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/**
	 * 获取影片列表
	 */
	public String getFilmList(String appcode, String validatekey, int pageNo, int pageSize){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getFilmList获取影片列表   parameter{appcode : " + appcode + ", validatekey:" + validatekey + ", pageNo:"+pageNo+", pageSize:"+pageSize+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, String.valueOf(pageNo), String.valueOf(pageSize))){
				T0111_FILMMESSAGE t0111 = new T0111_FILMMESSAGE();
				//设置参数
				PageList filmList=bs.findPage("client_query_film_list", "client_query_film_list_count", t0111, pageNo, pageSize);
				JSONArray jsonArray = JSONArray.fromObject(filmList);
				sResult.append(jsonArray.toString());
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		
		return sResult.toString();
	}
	
	/**
	 * 影片详细信息
	 */
	public String getFilmInfo(String appcode, String validatekey, String filmno){
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getFilmInfo获取影片详细信息   parameter{appcode : " + appcode + ", validatekey:" + validatekey + ", filmno:"+filmno+"}");
		StringBuffer sResult = new StringBuffer();
		try {
			if(Md5Check(appcode, validatekey, filmno)){
				T0111_FILMMESSAGE t0111 = new T0111_FILMMESSAGE();
				t0111.setFilmno(String.valueOf(filmno));
				List<T0111_FILMMESSAGE> t0111List = bs.queryForList("client_query_film_info", t0111);
				if(t0111List.size()>0){
					JSONObject jsonObject = JSONObject.fromObject(t0111List.get(0));//转换成json			
					sResult.append(jsonObject);
				}else{
					return returnmsg("8");
				}
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	
	/**
	 * 获取软件版本
	 */
	public String getVersion(String appcode,String validatekey) {
		logger.info("线程id："+Thread.currentThread().getId()+",开始时间线："+System.currentTimeMillis()+",getVersion获取软件版本   parameter{appCode : " + appcode + ", validatekey:" + validatekey  + "}");
		StringBuffer sResult = new StringBuffer();
		try {
			//md5校验
			if(Md5Check(appcode, validatekey)){
			
				List <T0907_Version> versionList = null;
				T0907_Version version = new T0907_Version();
				version.setVersion_type(appcode.toLowerCase());
				versionList = bs.queryForList("client_query_version", version);
//				if("cinema_Ios".equals(appcode)){
//					versionList = bs.queryForList("client_query_version", version);
//				}else if("cinema_Android".equals(appcode)){
//					versionList = bs.queryForList("client_query_version", version);
//				}else{
//					sResult.append(returnmsg("2"));	
//				}
				if(versionList.size()>0){
					JSONObject jsonObject = JSONObject.fromObject(versionList.get(0));//转换成json			
					sResult.append(jsonObject);
				}else{
					return returnmsg("8");
				}
			}else{
				return returnmsg("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnmsg("4");
		}
		return sResult.toString();
	}
	
	/*-------------------------辅助方法---------------------------*/
	
	/**
	 * md5参数校验.
	 * @param values 参数数组.
	 * @return 校验结果.
	 */
	private boolean Md5Check(String ...values){
		String appcode = values[0];//应用名
		String validatekey = values[1];//加密串
		boolean checkstate = false;//校验状态
		checkstate = true;
		StringBuffer sValue = new StringBuffer();	//参数
		if(values.length>2){
			//循环拼接参数
			for(int i=2;i<values.length;i++){
				sValue = sValue.append(values[i]);
			}
			try {
				if(md5.getMD5ofStr((URLEncoder.encode(appcode+sValue.toString()+ (appcode +"_Password"),"utf-8")).toLowerCase()).toLowerCase().equals(validatekey)){
					checkstate = true;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			try {
//				if(validatekey.equals(md5.getMD5ofStr((URLEncoder.encode(OtherConfig.getkeyvalue(appcode)+OtherConfig.getkeyvalue(appcode+"_Password"),"utf-8")).toLowerCase()).toLowerCase())){
//					checkstate = true;
//				}
				if(md5.getMD5ofStr((URLEncoder.encode(appcode+(appcode+"_Password"),"utf-8")).toLowerCase()).toLowerCase().equals(validatekey)){
					checkstate = true;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return checkstate;	

	}

	/**
	 * 状态信息返回报文
	 * @param msgcode 状态编码
	 * @return json报文
	 */
	private String returnmsg(String msgcode){
		String msginfo = null;
		if("0".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_0000+"\",\"resultmsg\":\""+StatusCode.d_0000+"\"}";
		}else if("1".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_0001+"\",\"resultmsg\":\""+StatusCode.d_0001+"\"}";
		}else if("2".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_1002+"\",\"resultmsg\":\""+StatusCode.d_1002+"\"}";
		}else if("4".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_0004+"\",\"resultmsg\":\""+StatusCode.d_0004+"\"}";
		}else if("5".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_2005+"\",\"resultmsg\":\""+StatusCode.d_2005+"\"}";
		}else if("6".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_2006+"\",\"resultmsg\":\""+StatusCode.c_2006+"\"}";
		}else if("7".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_2007+"\",\"resultmsg\":\""+StatusCode.c_2007+"\"}";
		}else if("8".equals(msgcode)){
			msginfo ="{\"resultcode\": \""+StatusCode.c_0009+"\",\"resultmsg\":\""+StatusCode.c_0009+"\"}";
		}
		
		return msginfo;
	}
	
}
