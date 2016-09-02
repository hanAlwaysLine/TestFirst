package cn.cinema.manage.action.filmmanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.action.cmanage.Cmanage;
import cn.cinema.manage.entity.filmmanage.T0301_NEWS;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

public class FilmInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Cmanage.class);
	/**
	 * 获取读取properties工具类
	 */
	private Messages messages = (Messages) ServiceLocator
			.getService("messages");

	public List<T0904_City> lcity;
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
	 * 删除主键
	 */
	private Integer id;
	/**
	 * 判断删除是否成功
	 */
	private Boolean flag;
	/**
	 * 上传图片
	 */
	private File uploadimg;
	private String uploadimgFileName;
	/**
	 * 咨询管理主体
	 */
	private T0301_NEWS t0301_news;
	
	//================  以上为声明     ================================
	/**
	 * 资讯管理
	 * 
	 * @return
	 */
	public String filminformation() {
		PageList pagelist = bs.findPage("selectall_filminformation",
				"selectcount_filminformation", t0301_news, pageNo, pageSize);
		SessionUtil.setPageList(request, pagelist);
		request.setAttribute("list", pagelist);
		return "filminformation";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String addfilminformation() {
		// 查询所属地区列表
		String aero = messages.getString("area");
		lcity = bs.queryForList("select_city", aero);
		return "addfilminformation";
	}

	/**
	 * 添加资讯管理
	 * 
	 * @throws IOException
	 */
	public String savefilminformation() throws IOException {
		File filesimg = getUploadimg();
		String filename = "";// 图片文件的名字
		try {
			if (filesimg != null) {
				if (!"".equals(getUploadimgFileName())) {
					java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
							"yyyyMMddHHmmss ");
					Date date = new Date();
					filename = sf.format(date);
					// 1.文件的名字
					filename = "img" + filename.trim() + ".jpg";
					// 2.存储路径改
					String dz = "D:\\Tomcat 6.0\\webapps\\hymanage\\upload\\"
							+ filename;					
					session.setAttribute("fileimagedz", dz);
					FileOutputStream fos = new FileOutputStream(dz);
					FileInputStream fis = new FileInputStream(filesimg);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				}
			}
			t0301_news.setImage(filename);
			bs.save("save_filminformation", t0301_news);
			request.setAttribute("msg", "添加成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "添加影视资讯成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "添加影视资讯失败，异常：" + e.getMessage());
		}
		return "savefilminformation";
	}

	/**
	 * 更新资讯管理
	 * 
	 * @return
	 */
	public String updatefilminformation() {
		try {
			File filesimg = getUploadimg();
			String filename = "";
			if (filesimg != null) {
				if (!"".equals(getUploadimgFileName())) {
					java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
							"yyyyMMddHHmmss ");
					Date date = new Date();
					filename = sf.format(date);
					// 1.文件的名字
					filename = "img" + filename.trim() + ".jpg";
					// 2.存储路径改
					String dz = "D:\\Tomcat 6.0\\webapps\\hymanage\\upload\\"
							+ filename;
					System.out
							.println("==============================dz:" + dz);
					session.setAttribute("fileimagedz", dz);
					FileOutputStream fos = new FileOutputStream(dz);
					FileInputStream fis = new FileInputStream(filesimg);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				}
			}
			t0301_news.setImage(filename);
			bs.update("update_t0301_news", t0301_news);
			request.setAttribute("msg", "更新成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "更新影视资讯成功");
		} catch (Exception e) {
			request.setAttribute("msg", "更新失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "更新影视资讯失败，异常：" + e.getMessage());
		}

		return "updatefilminformation";
	}

	/**
	 * 删除资讯
	 * 
	 * @return
	 */
	public String delfilminformation() {
		try {
			bs.delete("del_filminformation", this.id);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "删除咨询成功");
			this.flag = true;
		} catch (Exception e) {
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除咨询失败，异常：" + e.getMessage());
			this.flag = false;
		}
		return "delfilminformation";
	}

	/**
	 * 资讯明细
	 * 
	 * @return
	 */
	public String editfilminformation() {
		t0301_news = (T0301_NEWS) bs.queryForObject(
				"select_editfilminformation", this.id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String straddtime = format.format(t0301_news.getAddtime());
		String filename = t0301_news.getImage();
		// 存储路径改
		String dz = "D:\\Tomcat 6.0\\webapps\\hymanage\\upload\\" + filename;
		
		session.setAttribute("fileimagedz", dz);
		session.setAttribute("addtime1", straddtime);
		request.setAttribute("t0301_news", t0301_news);
		return "editfilminformation";
	}

	/**
	 * 查看资讯
	 * 
	 * @return
	 */
	public String seefilminformation() {
		t0301_news = (T0301_NEWS) bs.queryForObject(
				"select_editfilminformation", this.id);
		String filename = t0301_news.getImage();
		// 存储路径改
		String dz = "D:\\Tomcat 6.0\\webapps\\hymanage\\upload\\" + filename;
	
		session.setAttribute("fileimagedz", dz);
		request.setAttribute("t0301_news", t0301_news);
		return "seefilminformation";
	}
	//================== 以下为SET get方法  =========================

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public List<T0904_City> getLcity() {
		return lcity;
	}

	public void setLcity(List<T0904_City> lcity) {
		this.lcity = lcity;
	}

	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public File getUploadimg() {
		return uploadimg;
	}

	public void setUploadimg(File uploadimg) {
		this.uploadimg = uploadimg;
	}

	public String getUploadimgFileName() {
		return uploadimgFileName;
	}

	public void setUploadimgFileName(String uploadimgFileName) {
		this.uploadimgFileName = uploadimgFileName;
	}

	public T0301_NEWS getT0301_news() {
		return t0301_news;
	}

	public void setT0301_news(T0301_NEWS t0301News) {
		t0301_news = t0301News;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
