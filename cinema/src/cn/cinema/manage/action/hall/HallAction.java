package cn.cinema.manage.action.hall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.manage.T0103_HallInfo;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 影厅管理
 * 
 */
public class HallAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(HallAction.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 页数
	 */
	private Integer pageNo;

	/**
	 * 判断删除是否成功
	 */
	private Boolean modifySuc;

	/**
	 * 活动管理实体
	 */
	public T0103_HallInfo hall = new T0103_HallInfo();

	public String jsonString;
	
	/**
	 * 富文本内容
	 */
	private String ueDitorContent;

	/**
	 * 影厅编号
	 */
	private String hallno;
	
	

	/************************************* 方法 *************************************/
	// 查询列表
	public String hallList() {
		try {
			PageList halllist = bs.findPage("select_hall", "select_hallcount",
					hall, pageNo, pageSize);
			SessionUtil.setPageList(request, halllist);
			request.setAttribute("halllist", halllist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryhall";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @return
	 */
	public String addactivity() {
		return "addactivity";
	}

	/**
	 * 保存新增活动
	 * 
	 * @return
	 */
	public String savehall() {

		try {
			bs.save("insert_hall", hall);
			request.setAttribute("msg", "添加成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加活动成功");

		} catch (Exception e) {
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id:" + SessionUtil.getEmp(session).getId()
					+ "新增活动失败，异常：" + e.getMessage());
		}
		return "addhall";
	}
	
	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	public String goedit() {
		try {
			hall.setHallno(hallno);
			hall = (T0103_HallInfo) bs.queryForObject("query_hall_byid", hall);
			request.setAttribute("hall", hall);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "goedit";
	}

	/**
	 * 修改影厅
	 * 
	 * @return
	 */
	public String updHall() {
		try {
			bs.update("update_hallinfo", hall);
			request.setAttribute("msg", "修改影厅信息成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影厅信息成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改影厅信息失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影厅信息失败，异常：" + e.getMessage());
		}
		return "update";
	}
	
	 /**
	 * 根据用户id删除活动
	 * @return
	 */
	public String delActivity() {
		try {
			hall.setHallno(hallno);
			bs.delete("del_hallinfo", hall);
			modifySuc = true;
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除影厅信息成功");
		} catch (Exception e) {
			modifySuc = false;
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除影厅信息成功");
		}
		return "delete";
	}

	
	/**
	 * 判断影厅名称是否存在
	 */
	public void isNotExist(){
		try {
			String result = "true";
			JSONObject jsonObj = JSONObject.fromObject(jsonString);
			String hallname = jsonObj.getString("hallname");
			String oldhallname = jsonObj.getString("oldhallname");
//			System.out.println("---------参数:" + jsonObj.toString());
			//判断缩写名称是否与原名称相同
			if(!oldhallname.equals(hallname)){
				hall.setHallname(hallname);
				List hallList = bs.queryForList("query_hall_byname", hall);
				if(hallList.size()>0){
					result = "false";
				}
			}
		
			response.getWriter().write(result);
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("判断影厅名称是否存在异常:");
			e.printStackTrace();
		}
	}
	/************************************* 封装 *************************************/
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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

	public Boolean getModifySuc() {
		return modifySuc;
	}

	public void setModifySuc(Boolean modifySuc) {
		this.modifySuc = modifySuc;
	}

	public String getUeDitorContent() {
		return ueDitorContent;
	}

	public void setUeDitorContent(String ueDitorContent) {
		this.ueDitorContent = ueDitorContent;
	}

	public String getHallno() {
		return hallno;
	}

	public void setHallno(String hallno) {
		this.hallno = hallno;
	}
	
}
