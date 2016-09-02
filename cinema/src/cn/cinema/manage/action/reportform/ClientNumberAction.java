package cn.cinema.manage.action.reportform;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
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

import cn.cinema.manage.entity.reportform.MemberNum;
import cn.cinema.manage.entity.reportform.T2001AppCount;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.ServiceLocator;

/**
 * 客户端数量统计报表
 * @author ducl
 */
public class ClientNumberAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(ClientNumberAction.class);
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
	private T2001AppCount appCount = new T2001AppCount();
	
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
	 * json类型信息
	 */
	private String jsonInfo;
	
	/***********************************方法***********************************/
	
	/**
	 * 查询客户端数量统计报表
	 */
	@SuppressWarnings("unchecked")
	public String findClientInfo(){
		try {
			appCount.setStartdate(startdate);
			appCount.setEnddate(enddate);
			
			//查询android数据信息
			List<T2001AppCount> androidList = bs.queryForList("select_clientnum_Android", appCount);
			//查询IOS数据信息
			List<T2001AppCount> iosList = bs.queryForList("select_clientnum_IOS", appCount);
			//查询全部数据信息
			List<T2001AppCount> sumList = bs.queryForList("select_clientnum_sum", appCount);

			//拼接数据串
			String androidStr = "[1,0],";
			String iosStr = "[1,0],";
			String sumStr = "[1,0],";
			for(T2001AppCount ac : androidList){
				int day = Integer.parseInt(ac.getAdd_time());
				androidStr += "["+day+","+ac.getCount()+"],";
			}
			
			for(T2001AppCount ac : iosList){
				int day = Integer.parseInt(ac.getAdd_time());
				iosStr += "["+day+","+ac.getCount()+"],";
			}
			
			for(T2001AppCount ac : sumList){
				int day = Integer.parseInt(ac.getAdd_time());
				sumStr += "["+day+","+ac.getCount()+"],";
			}
			
			androidStr = "["+androidStr.substring(0, androidStr.length()-1)+"]";
			iosStr = "["+iosStr.substring(0, iosStr.length()-1)+"]";
			sumStr = "["+sumStr.substring(0, sumStr.length()-1)+"]";
			
			line = androidStr+"_"+iosStr+"_"+sumStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询客户端数量统计报表异常:");
			e.printStackTrace();
		}
		return "json";
	}
	
	
	/**
	 * 客户端数量统计报表导出
	 */
	public void showExcel(){
		if(jsonInfo!=null&&!jsonInfo.equals("")){
			appCount = (T2001AppCount)JSONObject.toBean(JSONObject.fromObject(jsonInfo), T2001AppCount.class);
		}
		List<T2001AppCount> appCountList = bs.queryForList("select_clientnum_excel",appCount); //获取数据列表
        
		 //获取当前数据库日期
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
			 sheet.mergeCells(0, 0, 2, 0);
			 sheet.mergeCells(0, appCountList.size()+2, 2, appCountList.size()+2);
			 Label col0 = new Label(0, 0, "银谷影城管理系统"+nowDate+"客户端数量统计报表", format1);
			 
			 //导出列名
			 Label col1 = new Label(0, 1, "客户端名称", borderFormat);
			 Label col2 = new Label(1, 1, "数量", borderFormat);
			 Label col3 = new Label(2, 1, "添加日期", borderFormat);
			//获取当前登录用户
			 String usersname = request.getSession().getAttribute("usersname")+"";
			 Label col4 = new Label(0, appCountList.size()+2, "银谷影城管理系统"+nowDate+",操作员:"+usersname, format1);
			 
			 for(int i=2;i<appCountList.size()+2;i++){
				 appCount = (T2001AppCount)appCountList.get(i-2);
				 Label row1 = new Label(0, i, appCount.getApp_type() + "", borderFormat); 
			     Label row2 = new Label(1, i, appCount.getCount() + "", borderFormat);
			     Label row3 = new Label(2, i, appCount.getAdd_time() + "", borderFormat); 
			     
			     //填入每一列的值
			     sheet.addCell(row1);
			     sheet.addCell(row2);
			     sheet.addCell(row3);
			 }
			 
			 //添加列名
			 sheet.addCell(col0);
			 sheet.addCell(col1);
			 sheet.addCell(col2);
			 sheet.addCell(col3);
			 sheet.addCell(col4);
			 
			 workBook.write();
			 workBook.close();
		} catch (Exception e) {
			logger.error("客户端数量统计报表导出异常:");
			e.printStackTrace();
		}
	}
	
	/***********************************封装***********************************/
	public T2001AppCount getAppCount() {
		return appCount;
	}

	public void setAppCount(T2001AppCount appCount) {
		this.appCount = appCount;
	}


	public String getLine() {
		return line;
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


	public String getJsonInfo() {
		return jsonInfo;
	}


	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}
	
}
