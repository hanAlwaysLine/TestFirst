package cn.cinema.manage.action.reportform;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.ServiceLocator;

/**
 * 订单统计报表
 * @author ducl
 */
public class OrderStatisticsAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(OrderStatisticsAction.class);
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
	 * 报表实体
	 */
	private T0300_UserOrder userorder = new T0300_UserOrder();
	
	/**
	 * 报表折线图像数据
	 */
	private String line;
	
	/**
	 * 开始日期
	 */
	private String startdate;
	
	/**
	 * 结束日期
	 */
	private String enddate;
	
	/**
	 * 订单类型
	 */
	private String ordertype;
	
	/**
	 * 渠道类型
	 */
	private String appcode;
	
	/**
	 * json类型信息
	 */
	private String jsonInfo;
	
	/***********************************方法***********************************/
	
	/**
	 * 查询订单统计报表
	 */
	@SuppressWarnings("unchecked")
	public String findOrdStaticInfo(){
		try {
			userorder.setStartDate(startdate);
			userorder.setEndDate(enddate);
			userorder.setOrder_type(ordertype);
			userorder.setAppcode(appcode);
			
			//查询网站数据信息
			userorder.setAppcode("1");
			List<T0300_UserOrder> webList = bs.queryForList("select_orderstatistics_Info", userorder);
			
			//查询android数据信息
			userorder.setAppcode("2");
			List<T0300_UserOrder> androidList = bs.queryForList("select_orderstatistics_Info", userorder);
			
			//查询IOS数据信息
			userorder.setAppcode("3");
			List<T0300_UserOrder> iosList = bs.queryForList("select_orderstatistics_Info", userorder);
			
			//查询微信数据信息
			userorder.setAppcode("4");
			List<T0300_UserOrder> microMsgList = bs.queryForList("select_orderstatistics_Info", userorder);
			
			//查询全部数据信息
			List<T0300_UserOrder> allList = bs.queryForList("select_orderstatistics_all", userorder);
			
			//拼接数据串
			String webStr = "[1,0],";
			String androidStr = "[1,0],";
			String iosStr = "[1,0],";
			String microMsgStr = "[1,0],";
			String allStr = "[1,0],";
			
			for(T0300_UserOrder ac : webList){
				int day = Integer.parseInt(ac.getOrder_time());
				webStr += "["+day+","+ac.getOrder_price()+"],";
			}
			
			for(T0300_UserOrder ac : androidList){
				int day = Integer.parseInt(ac.getOrder_time());
				androidStr += "["+day+","+ac.getOrder_price()+"],";
			}
			
			for(T0300_UserOrder ac : iosList){
				int day = Integer.parseInt(ac.getOrder_time());
				iosStr += "["+day+","+ac.getOrder_price()+"],";
			}
			
			for(T0300_UserOrder ac : microMsgList){
				int day = Integer.parseInt(ac.getOrder_time());
				microMsgStr += "["+day+","+ac.getOrder_price()+"],";
			}
			
			for(T0300_UserOrder ac : allList){
				int day = Integer.parseInt(ac.getOrder_time());
				allStr += "["+day+","+ac.getOrder_price()+"],";
			}
			
			webStr = "["+webStr.substring(0, webStr.length()-1)+"]";
			androidStr = "["+androidStr.substring(0, androidStr.length()-1)+"]";
			iosStr = "["+iosStr.substring(0, iosStr.length()-1)+"]";
			microMsgStr = "["+microMsgStr.substring(0, microMsgStr.length()-1)+"]";
			allStr = "["+allStr.substring(0, allStr.length()-1)+"]";
			
			line = webStr+"_"+androidStr+"_"+iosStr+"_"+microMsgStr+"_"+allStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询订单统计报表异常:");
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 订单统计报表导出
	 */
	public void showExcel(){
		if(jsonInfo!=null&&!jsonInfo.equals("")){
			userorder = (T0300_UserOrder)JSONObject.toBean(JSONObject.fromObject(jsonInfo), T0300_UserOrder.class);
		}
		
		List<T0300_UserOrder> couponListLike = bs.queryForList("select_orderstatistics_excel",userorder); //获取数据列表
        //获取当前日期
		String nowDate = bs.queryForSysDate(CPO.YYYY_MM_DD);
		
		Date date=new Date();//获取当前时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        try {
                        //生成Excel文件
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
			         + dateFormat.format(date) + "Excel.xls");
			 
			 OutputStream os=response.getOutputStream(); //创建数据流 
			 WritableWorkbook workBook = Workbook.createWorkbook(os); //生成名为"第一页"的工作表，参数0表示这是第一页   
			 
			 //添加边框样式
			 WritableCellFormat borderFormat = new WritableCellFormat();
			 borderFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			 borderFormat.setAlignment(jxl.format.Alignment.CENTRE);
			 
			 WritableCellFormat format1 = new WritableCellFormat();
			 format1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			 format1.setAlignment(jxl.format.Alignment.LEFT);
			 
			 
			 WritableSheet sheet = workBook.createSheet("第一页", 0);
			 sheet.mergeCells(0, 0, 9, 0);
			 sheet.mergeCells(0, couponListLike.size()+2, 9, couponListLike.size()+2);
			 Label col0 = new Label(0, 0, "银谷影城管理系统"+nowDate+"订单统计报表", format1);
			 //导出列名
			 Label col1 = new Label(0, 1, "订单编号", borderFormat);
			 Label col2 = new Label(1, 1, "会员名称", borderFormat);
			 Label col3 = new Label(2, 1, "订单时间", borderFormat);
			 Label col4 = new Label(3, 1, "订单总额", borderFormat);
			 Label col5 = new Label(4, 1, "订单类型", borderFormat);
			 Label col6 = new Label(5, 1, "支付状态", borderFormat);
			 Label col7 = new Label(6, 1, "渠道", borderFormat);
			 Label col8 = new Label(7, 1, "领取状态", borderFormat);
			 Label col9 = new Label(8, 1, "领取时间", borderFormat);
			 Label col10 = new Label(9, 1, "操作员", borderFormat);
			 //获取当前登录用户
			 String usersname = request.getSession().getAttribute("usersname")+"";
			 Label col11 = new Label(0, couponListLike.size()+2, "银谷影城管理系统"+nowDate+",操作员:"+usersname, format1);
			 
			 for(int i=2;i<couponListLike.size()+2;i++){
				 userorder =(T0300_UserOrder)couponListLike.get(i-2);
				 Label row1 = new Label(0, i, userorder.getOrder_no() + "", borderFormat); //订单编号
			     Label row2 = new Label(1, i, userorder.getUser_name() + "", borderFormat); //会员名称
			     Label row3 = new Label(2, i, userorder.getOrder_time() + "", borderFormat); //订单时间
			     Label row4 = new Label(3, i, userorder.getOrder_price() + "", borderFormat); //订单总额
			     Label row5 = new Label(4, i, userorder.getOrder_type() + "", borderFormat); //订单类型
			     Label row6 = new Label(5, i, userorder.getPay_status() + "", borderFormat); //支付状态
			     Label row7 = new Label(6, i, userorder.getAppcode() + "", borderFormat); //渠道
			     Label row8 = new Label(7, i, userorder.getReceive_status() + "", borderFormat); //领取状态
			     Label row9 = new Label(8, i, userorder.getReceive_time() + "", borderFormat); //领取时间
			     Label row10 = new Label(9, i, userorder.getName() + "", borderFormat); //操作员
			     
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
			 }
			 
			 //添加列名
			 sheet.addCell(col0);
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
			 
			 workBook.write();
			 workBook.close();
		} catch (Exception e) {
			logger.error("订单统计报表导出异常:");
			e.printStackTrace();
		}
	}
	/***********************************封装***********************************/

	public String getLine() {
		return line;
	}


	public T0300_UserOrder getUserorder() {
		return userorder;
	}


	public void setUserorder(T0300_UserOrder userorder) {
		this.userorder = userorder;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public String getOrdertype() {
		return ordertype;
	}


	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}


	public String getAppcode() {
		return appcode;
	}


	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	public String getJsonInfo() {
		return jsonInfo;
	}

	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}
	
}
