package cn.cinema.manage.action.reportform;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.reportform.Reconciliation;
import cn.cinema.manage.entity.reportform.T2001AppCount;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.ServiceLocator;

/**
 * 对账报表
 * @author ducl
 */
public class ReconciliAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(ReconciliAction.class);
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
	private Reconciliation reconciliation = new Reconciliation();
	
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
	 * 查询对账报表
	 */
	@SuppressWarnings("unchecked")
	public String findOrderInfo(){
		try {
			List<Reconciliation> rcList = bs.queryForList("select_reconciliation_info", reconciliation);
			request.setAttribute("rcList", rcList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询对账报表异常:");
			e.printStackTrace();
		}
		return "findOrderInfo";
	}
	
	
	/**
	 * 对账报表导出
	 */
	public void showExcel(){
		if(jsonInfo!=null&&!jsonInfo.equals("")){
			reconciliation = (Reconciliation)JSONObject.toBean(JSONObject.fromObject(jsonInfo), Reconciliation.class);
		}
		List<Reconciliation> rcList = bs.queryForList("select_reconciliation_info",reconciliation); //获取数据列表
        
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
			 borderFormat.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 borderFormat.setAlignment(jxl.format.Alignment.CENTRE);
			 
			 WritableCellFormat format1 = new WritableCellFormat();
			 format1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			 format1.setAlignment(Alignment.LEFT);
			 
			 
			 WritableSheet sheet = workBook.createSheet("第一页", 0);
			 sheet.mergeCells(0, 0, 10, 0);
			 sheet.mergeCells(0, rcList.size()+2, 10, rcList.size()+2);
			 Label col0 = new Label(0, 0, "银谷影城管理系统"+nowDate+"对账报表", format1);
			 
			 //导出列名
			 Label col1 = new Label(0, 1, "订单号", borderFormat);
			 Label col2 = new Label(1, 1, "排期编号", borderFormat);
			 Label col3 = new Label(2, 1, "影片名称", borderFormat);
			 Label col4 = new Label(3, 1, "影厅名称", borderFormat);
			 Label col5 = new Label(4, 1, "放映日期", borderFormat);
			 Label col6 = new Label(5, 1, "单价", borderFormat);
			 Label col7 = new Label(6, 1, "票数", borderFormat);
			 Label col8 = new Label(7, 1, "结算总额", borderFormat);
			 Label col9 = new Label(8, 1, "支付类型", borderFormat);
			 Label col10 = new Label(9, 1, "订单时间", borderFormat);
			 Label col11 = new Label(10, 1, "渠道", borderFormat);
			//获取当前登录用户
			 String usersname = request.getSession().getAttribute("usersname")+"";
			 Label col12 = new Label(0, rcList.size()+2, "银谷影城管理系统"+nowDate+",操作员:"+usersname, format1);
			 
			 for(int i=2;i<rcList.size()+2;i++){
				 reconciliation = (Reconciliation)rcList.get(i-2);
				 Label row1; 
			     Label row2;
			     Label row3; 
			     Label row4; 
			     Label row5; 
			     Label row6; 
			     Label row7; 
			     Label row8; 
			     Label row9; 
			     Label row10; 
			     Label row11; 
				 if("-1".equals(reconciliation.getOrder_no())){
					 row1 = new Label(0, i, reconciliation.getFilmname() + "", borderFormat); 
				     row2 = new Label(1, i, "", borderFormat);
				     row3 = new Label(2, i, "", borderFormat); 
				     row4 = new Label(3, i, "", borderFormat); 
				     row5 = new Label(4, i, "", borderFormat); 
				     row6 = new Label(5, i, "", borderFormat); 
				     row7 = new Label(6, i, reconciliation.getTicketsnum() + "", borderFormat); 
				     row8 = new Label(7, i, reconciliation.getOrder_price() + "", borderFormat); 
				     row9 = new Label(8, i, "", borderFormat); 
				     row10 = new Label(9, i, "", borderFormat); 
				     row11 = new Label(10, i, "", borderFormat); 
				 }else{
					 row1 = new Label(0, i, reconciliation.getOrder_no() + "", borderFormat); 
				     row2 = new Label(1, i, reconciliation.getFeatureappno() + "", borderFormat);
				     row3 = new Label(2, i, reconciliation.getFilmname() + "", borderFormat); 
				     row4 = new Label(3, i, reconciliation.getHallname() + "", borderFormat); 
				     row5 = new Label(4, i, reconciliation.getFeaturedate() + "", borderFormat); 
				     row6 = new Label(5, i, reconciliation.getApppric() + "", borderFormat); 
				     row7 = new Label(6, i, reconciliation.getTicketsnum() + "", borderFormat); 
				     row8 = new Label(7, i, reconciliation.getOrder_price() + "", borderFormat); 
				     row9 = new Label(8, i, reconciliation.getPay_type() + "", borderFormat); 
				     row10 = new Label(9, i, reconciliation.getOrder_time() + "", borderFormat); 
				     row11 = new Label(10, i, reconciliation.getAppcode() + "", borderFormat); 
				 }
				 
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
			 sheet.addCell(col12);
			 
			 workBook.write();
			 workBook.close();
		} catch (Exception e) {
			logger.error("对账报表导出异常:");
			e.printStackTrace();
		}
	}
	
	/***********************************封装***********************************/

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


	public Reconciliation getReconciliation() {
		return reconciliation;
	}


	public void setReconciliation(Reconciliation reconciliation) {
		this.reconciliation = reconciliation;
	}
	
	
}
