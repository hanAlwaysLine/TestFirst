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
import cn.cinema.manage.entity.sys.T1000_Users;
import cn.cinema.manage.entity.ticket.T0300_UserOrder;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.ServiceLocator;

/**
 * 会员数量统计报表
 * @author ducl
 */
public class MemberNumberAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(MemberNumberAction.class);
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
	private MemberNum memberNum = new MemberNum();
	
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
	 * 会员数量统计报表
	 */
	@SuppressWarnings("unchecked")
	public String findMemInfo(){
		try {
			memberNum.setStartdate(startdate);
			memberNum.setEnddate(enddate);
			
			//查询新增会员数量
			List<MemberNum> newAddList = bs.queryForList("select_membernum_newadd", memberNum);

			//拼接数据串
			String newAddStr = "";
			String allStr = "";
			
			int sumcount = 0;
			for(MemberNum mn : newAddList){
				int day = Integer.parseInt(mn.getUsertime());
				newAddStr += "["+day+","+mn.getCount()+"],";
				//拼接总数
				sumcount += Integer.parseInt(mn.getCount());
				allStr += "["+day+","+sumcount+"],";
			}
			
			
			newAddStr = "["+newAddStr.substring(0, newAddStr.length()-1)+"]";
			allStr = "["+allStr.substring(0, allStr.length()-1)+"]";
			
			line = newAddStr+"_"+allStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询客户端数量统计报表异常:");
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 会员数量统计报表导出
	 */
	public void showExcel(){
		if(jsonInfo!=null&&!jsonInfo.equals("")){
			memberNum = (MemberNum)JSONObject.toBean(JSONObject.fromObject(jsonInfo), MemberNum.class);
		}
		List<T1000_Users> userList = bs.queryForList("select_membernum_excel",memberNum); //获取数据列表
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
			 sheet.mergeCells(0, 0, 6, 0);
			 sheet.mergeCells(0, userList.size()+2, 6, userList.size()+2);
			 Label col0 = new Label(0, 0, "银谷影城管理系统"+nowDate+"会员数量统计报表", format1);
			 
			 //导出列名
			 Label col1 = new Label(0, 1, "会员ID", borderFormat);
			 Label col2 = new Label(1, 1, "会员姓名", borderFormat);
			 Label col3 = new Label(2, 1, "手机号", borderFormat);
			 Label col4 = new Label(3, 1, "会员卡卡号", borderFormat);
			 Label col5 = new Label(4, 1, "会员注册时间", borderFormat);
			 Label col6 = new Label(5, 1, "微信号", borderFormat);
			 Label col7 = new Label(6, 1, "注册渠道", borderFormat);
			 //获取当前登录用户
			 String usersname = request.getSession().getAttribute("usersname")+"";
			 Label col8 = new Label(0, userList.size()+2, "银谷影城管理系统"+nowDate+",操作员:"+usersname, format1);
			 
			 for(int i=2;i<userList.size()+2;i++){
				 T1000_Users user =(T1000_Users)userList.get(i-2);
				 Label row1 = new Label(0, i, user.getUser_id() + "", borderFormat);
			     Label row2 = new Label(1, i, user.getUser_name() + "", borderFormat);
			     Label row3 = new Label(2, i, user.getUser_mobile() + "", borderFormat);
			     Label row4 = new Label(3, i, user.getCardcode() + "", borderFormat);
			     Label row5 = new Label(4, i, user.getUser_time() + "", borderFormat);
			     Label row6 = new Label(5, i, user.getWeixin_code() + "", borderFormat);
			     Label row7 = new Label(6, i, user.getUser_app() + "", borderFormat);
			     
			     //填入每一列的值
			     sheet.addCell(row1);
			     sheet.addCell(row2);
			     sheet.addCell(row3);
			     sheet.addCell(row4);
			     sheet.addCell(row5);
			     sheet.addCell(row6);
			     sheet.addCell(row7);
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
			 
			 workBook.write();
			 workBook.close();
		} catch (Exception e) {
			logger.error("会员数量统计报表导出异常:");
			e.printStackTrace();
		}
	}
	
	/***********************************封装***********************************/


	public MemberNum getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(MemberNum memberNum) {
		this.memberNum = memberNum;
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
