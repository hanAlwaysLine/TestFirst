package cn.cinema.manage.action.orderform;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.goods.T0300OrderList;
import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.entity.manage.T0905_logrecord;
import cn.cinema.manage.entity.manage.Vo_orderform;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 订单管理
 * @author ducl
 * 
 *
 */
public class Orderform {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Orderform.class);
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
	public String startDate;
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String endDate;
	
	
	
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
						buf.append(arr3[0]+"排"+arr3[1]+"座"+"<br/>");
					}
				}
			}
			orderform.setSeatinfo(buf.toString());
			request.setAttribute("orderDetail", orderform);
			
			//获取卖品订单信息
			List goodsList = bs.queryForList("select_orderform_goods",userorder);
			request.setAttribute("goodsList", goodsList);
			
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
	 * 综合查询统计订单  包含  影票  卖品列表
	 * @return 
	 */
	public  String orderfilmgoodsResult(){
		try {
			PageList orderformlist= bs.findPage("select_countorderformlist", "select_countorderformlist_count", userorder, pageNo, pageSize);
			SessionUtil.setPageList(request, orderformlist);
			request.setAttribute("list", orderformlist);
		} catch (Exception e) {
			logger.error("综合查询统计订单:");
			e.printStackTrace();
		}
        return "orderfilmgoodsResult";
	}
	
	/**
	 * 导出账户
	 * @return
	 */
	public void madeExport(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String strnow = sf.format(new Date());
		if(("".equals(startDate)||startDate==null)&&("".equals(endDate)||endDate==null)){
			userorder.setStartDate(strnow);
			userorder.setEndDate(strnow);
		}else{
			userorder.setStartDate(startDate);
			userorder.setEndDate(endDate);
		}
		List<Map<String ,Object>> orderformlist= (List<Map<String ,Object>>)bs.queryForList("select_countorderformlist",userorder);
		userorder = null;
        Date date=new Date();//获取当前时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        try {
                        //生成Excel文件
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
			         + dateFormat.format(date) + "Excel.xls");
			 
			 OutputStream os=response.getOutputStream(); //创建数据流 
			 WritableWorkbook workBook = Workbook.createWorkbook(os); //生成名为"第一页"的工作表，参数0表示这是第一页   
			 WritableSheet sheet=workBook.createSheet("sheet", 0);
			 
			 //导出列名
			 Label col1 = new Label(0, 1, "订单号");
			 Label col2 = new Label(1, 1, "订单总价");
			 Label col3 = new Label(2, 1, "影票总价");
			 Label col4 = new Label(3, 1, "活动id");
			 Label col5 = new Label(4, 1, "活动价");
			 Label col6 = new Label(5, 1, "活动名称");
			 Label col7 = new Label(6, 1, "排期应用号");
			 Label col8 = new Label(7, 1, "影片编码");
			 Label col9 = new Label(8, 1, "影片名称");
			 Label col10 = new Label(9, 1, "排期号");
			 Label col11 = new Label(10, 1, "影厅编码");
			 Label col12 = new Label(11, 1, "影厅名称");
			 Label col13 = new Label(12, 1, "影票结算总价");
			 Label col14 = new Label(13, 1, "影票结算价");
			 Label col15 = new Label(14, 1, "影票数量");
			 Label col16 = new Label(15, 1, "卖品价格");
			 Label col17 = new Label(16, 1, "卖品数量");
			 
			 Label col18 = new Label(0, 0,  "统计订单价格明细"); //账号
			 for(int i=1;i<orderformlist.size()+1;i++){
				 Map<String ,Object> map =(Map<String ,Object>)orderformlist.get(i-1);
				 String orderno = String.valueOf(map.get("ORDER_NO"));
				 T0300_UserOrder uo = new T0300_UserOrder();
				 uo.setOrder_no(orderno);
				 List<T0300OrderList> list  = (List<T0300OrderList>)bs.queryForList("select_orderform_goods",uo);
				 StringBuffer sb = new StringBuffer("(");
				 for(T0300OrderList  obj :list){
					 sb.append(obj.getGOODS_NAME()+"*");
					 sb.append(obj.getGOODS_COUNT()+"*");
					 sb.append(obj.getGOODS_PRICE());
				 }
				 sb.append(")");
				 Label row1 = new Label(0, i+1, map.get("ORDER_NO") + ""); //订单号
			     Label row2 = new Label(1, i+1, map.get("ORDER_PRICE") + ""); //订单总价
			     Label row3 = new Label(2, i+1, map.get("COUNTPRICE") + ""); //影票总价
			     Label row4 = new Label(3, i+1, map.get("ACTIVITY_ID") + ""); //活动id
			     Label row5 = new Label(4, i+1, map.get("ACTIVEPRICE") + ""); //活动价
			     Label row6 = new Label(5, i+1, map.get("ACTIVITY_NAME") + ""); //活动名称
			     Label row7 = new Label(6, i+1, map.get("FEATUREAPPNO") + ""); //排期应用号
			     Label row8 = new Label(7, i+1, map.get("FILMNO") + ""); //影片编码
			     Label row9 = new Label(8, i+1, map.get("FILMNAME") + ""); //影片名称
			     Label row10 = new Label(9, i+1, map.get("FEATURENO") + ""); //排期号
			     Label row11 = new Label(10, i+1, map.get("HALLNO") + ""); //影厅编码
			     Label row12 = new Label(11, i+1, map.get("HALLNAME") + ""); //影厅名称
			     Label row13 = new Label(12, i+1, map.get("APPTOALPRICE") + ""); //影票结算总价
			     Label row14 = new Label(13, i+1, map.get("APPPRIC") + ""); //影票结算价
			     Label row15 = new Label(14, i+1, map.get("TICKETSNUM") + ""); //影票数量
			     Label row16 = new Label(15, i+1, map.get("GOODTOTALPRICE") + ""); //卖品价格
			     Label row17 = new Label(16, i+1, map.get("GOODTOTALS") + sb.toString()); //卖品数量
			     //填入每一列的值
			     sheet.addCell(row1);
			     sheet.addCell(row2);
			     sheet.addCell(row3);
			     sheet.addCell(row4);
			     sheet.addCell(row5);
			     sheet.addCell(row6);
			     sheet.addCell(row7);
			     sheet.addCell(row8);
			     sheet.addCell(row9);
			     sheet.addCell(row10);
			     sheet.addCell(row11);
			     sheet.addCell(row12);
			     sheet.addCell(row13);
			     sheet.addCell(row14);
			     sheet.addCell(row15);
			     sheet.addCell(row16);
			     sheet.addCell(row17);
			 }
			 
			 //添加列名
			 sheet.addCell(col1);
			 sheet.addCell(col2);
			 sheet.addCell(col3);
			 sheet.addCell(col4);
			 sheet.addCell(col5);
			 sheet.addCell(col6);
			 sheet.addCell(col7);
			 sheet.addCell(col8);
			 sheet.addCell(col9);
			 sheet.addCell(col10);
			 sheet.addCell(col11);
			 sheet.addCell(col12);
			 sheet.addCell(col13);
			 sheet.addCell(col14);
			 sheet.addCell(col15);
			 sheet.addCell(col16);
			 sheet.addCell(col17);
			 sheet.addCell(col18);
			 
			 workBook.write();
			 workBook.close();
		} catch (Exception e) {
			logger.error("导出账户异常");
			e.printStackTrace();
		}
	}
	

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
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
}
