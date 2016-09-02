package cn.cinema.manage.action.orderform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.action.cmanage.Cmanage;
import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.entity.manage.T0905_logrecord;
import cn.cinema.manage.entity.manage.Vo_orderform;
import cn.cinema.manage.entity.sys.T0001_Ad_User;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 订单查询
 * @author ducl
 * 
 *
 */
public class OrderSelect {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(OrderSelect.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 订单表实体
	 */
	private Vo_orderform orderform = new Vo_orderform();
	
	private T0300_UserOrder userorder = new T0300_UserOrder();
	
	private T0905_logrecord logrecord;
	public T0904_City city;
	public List<T0904_City> lcity;
	public T0100_PlaceInfo placeinfo;
	public List<T0100_PlaceInfo> lplaceinfo;
	
	public String citynum;		//已选择的cityno
	public String vipno;		//会员卡号
	public String cinemalinkid;	//cinemalinkid
	public Integer fid;
	
	/**
	 * 订单编号
	 */
	public String orderno;
	
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 页数
	 */
	private Integer pageNo;
	
	/**
	 * 操作返回
	 */
	private boolean result = false;
	
	/**
	 * 查询订单列表
	 * @return 
	 */
	public  String queryOrderformList(){
		try {
			PageList orderformlist= bs.findPage("select_userorder_list", "select_userorder_count", userorder, pageNo, pageSize);
			SessionUtil.setPageList(request, orderformlist);
			request.setAttribute("list", orderformlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询订单列表异常:");
			e.printStackTrace();
		}
        return "queryOrderformResult";
	}
	
	/**
	 * 查看订单交易明细信息
	 * @return
	 */
	public String userOrderDetail(){
		try {
			userorder.setOrder_no(orderno);
			//获取影票订单信息
			List ticketList = bs.queryForList("select_orderform_list", userorder);
			StringBuffer buf = new StringBuffer();
			if(ticketList!=null && ticketList.size()>0){
				orderform = (Vo_orderform)ticketList.get(0);
				String seat = orderform.getSeatinfo();
				if(StringUtils.isNotBlank(seat)){
					String[] arr1 = seat.split(",");
					for(int i =0; i< arr1.length ;i++){
						String arr2 = arr1[i];
						String[] arr3 = arr2.split("_");
						buf.append(arr3[0]+"排"+arr3[1]+"列"+"<br/>");
					}
				}
			}
			orderform.setSeatinfo(buf.toString());
			request.setAttribute("orderDetail", orderform);
			
			//获取卖品订单信息
			List goodsList = bs.queryForList("select_orderform_goods",userorder);
			request.setAttribute("goodsList", goodsList);
			
			//获取活动信息
			List activityList = bs.queryForList("select_orderform_activity", userorder);
			request.setAttribute("activityList", activityList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查看订单交易明细信息异常:");
			e.printStackTrace();
		}
		return "userOrderDetail";
	}
	
	/**
	 * 查看购票详情
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String orderformDetail(){
		List<Vo_orderform> list = bs.queryForList("select_orderform_detail",orderform);
		if(list.size()==0 || list.get(0)==null)
			return null;
		Vo_orderform order = list.get(0);
		
		Integer ticketsnum = order.getTicketsnum();
		
		//处理座位信息，分割方式   4:07|4:08   ： 4排07 ，4排08
		if(("00".equals(order.getSeatinfo()))){
			order.setSeatinfo("自动选坐");
		}else if(ticketsnum>1 && !("00".equals(order.getSeatinfo()))){
			String seatstr = "<table>";
			String strSeat = order.getSeatinfo();
			String [] s = strSeat.split("\\|");			
			for(int i=0;i<s.length;i++){
				String [] a = s[i].split(":");
				for(int k=1;k<a.length;k= k+2){
					String c = "<tr><td>"+a[0]+"排 "+a[1]+"</td></tr>";
					seatstr += c;
				}
			}
			seatstr+="</table>";
			order.setSeatinfo(seatstr);
		}else if(ticketsnum==1 && !("00".equals(order.getSeatinfo()))){
			String str = order.getSeatinfo();
			String [] a = str.split(":");
			String c = a[0]+"排 "+a[1];
			order.setSeatinfo(c);
		}
		
		//处理观影日期
		String showdate = order.getShowdate();
		SimpleDateFormat spm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat spm2 = new SimpleDateFormat("MM月dd日");
		try {
			Date date = spm.parse(showdate);
			order.setShowdate(spm2.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//处理观影时间
		String oldshowtime = order.getShowtime();
		if(oldshowtime.length()==3){
			oldshowtime="0"+oldshowtime;
		}
		order.setShowtime((oldshowtime.substring(0, 2)+":"+oldshowtime.substring(2)));
	    request.setAttribute("orderform", order);
		return "orderformDetail";
	}
	
	/**
	 * 领取订单操作
	 * @return
	 */
	public String receiveOp(){
		try {
			T0001_Ad_User newusers = (T0001_Ad_User)request.getSession().getAttribute("users");
			userorder.setOper_id(newusers.getId());
			userorder.setOrder_no(orderno);
			bs.update("update_userorder_receive", userorder);
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("领取订单操作异常:");
			e.printStackTrace();
		}
		return "json";
	}

	public T0904_City getCity() {
		return city;
	}

	public Vo_orderform getOrderform() {
		return orderform;
	}

	public void setOrderform(Vo_orderform orderform) {
		this.orderform = orderform;
	}

	public void setCity(T0904_City city) {
		this.city = city;
	}

	public List<T0904_City> getLcity() {
		return lcity;
	}

	public void setLcity(List<T0904_City> lcity) {
		this.lcity = lcity;
	}

	public String getCitynum() {
		return citynum;
	}

	public void setCityno(String citynum) {
		this.citynum = citynum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public void setCitynum(String citynum) {
		this.citynum = citynum;
	}

	public T0905_logrecord getLogrecord() {
		return logrecord;
	}

	public void setLogrecord(T0905_logrecord logrecord) {
		this.logrecord = logrecord;
	}

	public String getVipno() {
		return vipno;
	}

	public void setVipno(String vipno) {
		this.vipno = vipno;
	}

	public String getCinemalinkid() {
		return cinemalinkid;
	}

	public void setCinemalinkid(String cinemalinkid) {
		this.cinemalinkid = cinemalinkid;
	}

	public T0100_PlaceInfo getPlaceinfo() {
		return placeinfo;
	}

	public void setPlaceinfo(T0100_PlaceInfo placeinfo) {
		this.placeinfo = placeinfo;
	}

	public List<T0100_PlaceInfo> getLplaceinfo() {
		return lplaceinfo;
	}

	public void setLplaceinfo(List<T0100_PlaceInfo> lplaceinfo) {
		this.lplaceinfo = lplaceinfo;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public T0300_UserOrder getUserorder() {
		return userorder;
	}

	public void setUserorder(T0300_UserOrder userorder) {
		this.userorder = userorder;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
