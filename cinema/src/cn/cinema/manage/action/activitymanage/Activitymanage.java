package cn.cinema.manage.action.activitymanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ibatis.common.jdbc.SimpleDataSource;

import cn.cinema.manage.entity.Activitymanage.T0403Activity;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;
import cn.cinema.pojo.FeatureForm;

/**
 * 活动管理
 * 
 */
public class Activitymanage {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Activitymanage.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
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
	private Boolean modifySuc;
	/**
	 * 上传图片
	 */
	private File uploadimg;
	private File file;

	/**
	 * 获取读取properties工具类
	 */
	private Messages messages = (Messages) ServiceLocator
			.getService("messages");
	
	/**
	 * 回调提示信息
	 */
	private String resultMsg;

	/**
	 * 活动管理实体
	 */
	public T0403Activity activity;

	public String jsonString;

	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();
	
	/**
	 * 原图片信息
	 */
	public String oldPicUrl;

	/*******************************************方法******************************************/
	
	// 查询列表
	public String activityList() {

		PageList activitylist = bs.findPage("select_activity",
				"select_activitycount", activity, pageNo, pageSize);
		SessionUtil.setPageList(request, activitylist);
		request.setAttribute("activitylist", activitylist);
		return "queryactivity";

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
	public String saveActivity() {

		try {
			Date date = new Date();
			activity.setAddtime(date);
			bs.save("insert_activity", activity);
			request.setAttribute("msg", "添加成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加活动成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加失败!");
//			logger.error("用户id:" + SessionUtil.getEmp(session).getId()
//					+ "新增活动失败，异常：" + e.getMessage());
		}
		return "addactivity";
	}

	/**
	 * 根据用户id删除活动
	 * 
	 * @return
	 */
	public String delActivity() {

		try {
			T0403Activity t = new T0403Activity();
			t.setActivity_id(id);
			activity = (T0403Activity) bs.queryForObject("query_activity", t);
			bs.update("delete_activity", t);
			//bs.delete("delete_activity_advertise", t);
			//bs.delete("delete_activity", t);
			logger.info("用户id[" + SessionUtil.getEmp(session).getId()
					+ "]删除活动成功,操作活动名为[" + activity.getActivity_name() + "]");
			clearPicture(activity.getActimg());
			modifySuc = true;
		} catch (Exception e) {
//			logger.error("用户id[" + SessionUtil.getEmp(session).getId()
//					+ "]删除活动失败，异常信息：" + e.getMessage());
			modifySuc = false;
		}
		return "delete";
	}

	/**
	 * 根据用户id发布活动
	 * 
	 * @return
	 */
	public String relActivity() {
		try {
			T0403Activity t = new T0403Activity();
			t.setActivity_id(id);
			activity = (T0403Activity) bs.queryForObject("query_activity", t);
			bs.update("release_activity", t);
			logger.info("用户id[" + SessionUtil.getEmp(session).getId()
					+ "]发布活动成功,操作活动名为[" + activity.getActivity_name() + "]");
			modifySuc = true;
		} catch (Exception e) {
			logger.error("用户id[" + SessionUtil.getEmp(session).getId()
					+ "]发布活动失败，异常信息：" + e.getMessage());
			modifySuc = false;
		}
		return "release";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	public String goedit() {

		T0403Activity t = new T0403Activity();
		t.setActivity_id(id);
		activity = (T0403Activity) bs.queryForObject("query_activity", t);
		String uploadimgFileName = activity.getActimg();// 图片的名字
		// 存储路径改
		String dz = "upload/" + uploadimgFileName;
		request.setAttribute("fileimagedz", dz);
		request.setAttribute("activity", activity);
		return "goedit";
	}

	/**
	 * 修改活动信息
	 * 
	 * @return
	 */
	public String updActivity() {
		try {
			bs.update("update_activity", activity);
			request.setAttribute("msg", "修改活动信息成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改活动信息成功");
			//清除原图片信息
			if(!oldPicUrl.equals(activity.getActimg())){
				clearPicture(oldPicUrl);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "修改活动信息失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改活动信息失败，异常：" + e.getMessage());
		}
		return "update";
	}
	
	/**
	 * 校验排期时间是否符合活动要求
	 * @return
	 */
	public String checkFeatureNo(){
		try {
			resultMsg="";
			JSONObject jsonObj = JSONObject.fromObject(jsonString);
			String feano = jsonObj.getString("feano"); //排期号
			String starttime = jsonObj.getString("starttime"); //活动开始时间
			String endtime = jsonObj.getString("endtime"); //活动结束时间
			//判断排气编号是否为空
			if(!"".equals(feano)&&feano!=null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//判断排期是否存在
				FeatureForm featureForm = new FeatureForm();
				featureForm.setFeatureappno(feano);
				featureForm =  (FeatureForm) bs.queryForObject("select_feature", featureForm);
				if(featureForm!=null){
					long featuredatetime = dateFormat.parse(featureForm.getFeaturedate()+" "+featureForm.getFeaturetime()+":00").getTime();
					long starttimelong = dateFormat.parse(starttime).getTime();
					long endtimelong = dateFormat.parse(endtime).getTime();
					if(featuredatetime<starttimelong||featuredatetime>endtimelong){
						resultMsg = "添加排期号的放映时间不在活动时间内!";
					}
				}else{
					resultMsg = "此排期编号不存在!";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("校验排期时间是否符合活动要求异常:");
			e.printStackTrace();
		}
		
		
		return "json";
	}

	public void upload() {
		try {
			String filename = "";
			File filesimg = getFile();
			String webPath = CPO.webRootPath.substring(0, CPO.webRootPath.indexOf("cinema"));
			String imgactivity = webPath + File.separator
					+ "upload//activity" + File.separator;
			
			File img = new File(imgactivity);
			if (!img.exists()) {
				img.mkdirs();
			}
			if (filesimg != null) {

				// 1.文件的名字
				filename = UUID.randomUUID().toString().replaceAll("-", "")
						+ ".jpg";
				// 2.存储路径改
				String path = imgactivity + filename;
				FileOutputStream fos = new FileOutputStream(path);
				FileInputStream fis = new FileInputStream(filesimg);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			}
			ServletActionContext.getResponse().getWriter().print(
					"/upload/activity/" + filename);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 清除无用图片
	 */
	public void clearPicture(String url){
		try {
			String serverPath = CPO.webRootPath;
			String imgPath = serverPath.substring(0, serverPath.indexOf("cinema")-1)+url;
			File imgFile = new File(imgPath);
			if(imgFile.exists()){
				imgFile.delete();
			}
		} catch (Exception e) {
			logger.error("清除无用图片异常:");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*******************************************封装******************************************/
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public T0403Activity getActivity() {
		return activity;
	}

	public void setActivity(T0403Activity activity) {
		this.activity = activity;
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

	public Boolean getModifySuc() {
		return modifySuc;
	}

	public void setModifySuc(Boolean modifySuc) {
		this.modifySuc = modifySuc;
	}

	public File getUploadimg() {
		return uploadimg;
	}

	public void setUploadimg(File uploadimg) {
		this.uploadimg = uploadimg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getOldPicUrl() {
		return oldPicUrl;
	}

	public void setOldPicUrl(String oldPicUrl) {
		this.oldPicUrl = oldPicUrl;
	}
	
}
