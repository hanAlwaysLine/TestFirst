package cn.cinema.manage.action.cmanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 影院管理
 * 
 * @author yangdx
 * 
 */
public class Cmanage {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Cmanage.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession();
	/**
	 * 影院管理实体
	 */
	public T0100_PlaceInfo placeinfo;// 影院信息表
	public T0904_City city;
	public List<T0904_City> lcity;
	private String placeno;
	
	/**
	 * 删除主键 id
	 */
	private Integer id;
	/**
	 * 影院的名字
	 */
	private String placename;
	/**
	 * 判断是否成功
	 */
	private Boolean flag;
	/**
	 * 判断是否更新成功
	 */
	private Boolean uFlag;
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 页数
	 */
	private Integer pageNo;
	/**
	 * 发KEY 接值串
	 */
	private String placeStr;

	private String moduleList;
	/**
	 * 上传图片
	 */
	private File file;

	/**
	 * 查询影院.
	 * 
	 * @return queryCmanage.
	 */
	@SuppressWarnings("unchecked")
	public String queryCmanage() {
		if (placeinfo != null) {
			if("".equals(placeinfo.getPlacename())){
				placeinfo.setPlacename(null);
			}else{
				placeinfo.setPlacename(placeinfo.getPlacename().trim());
			}
			PageList list = bs.findPage("select_cmanage",
					"select_cmanage_count", placeinfo, pageNo, pageSize);
			SessionUtil.setPageList(request, list);
			request.setAttribute("list", list);
			return "queryCmanageResult";
		} else {
			lcity = bs.queryForList("select_city");
			return "queryCmanage";
		}
	}

	/**
	 * 影院添加
	 */
	@SuppressWarnings("unchecked")
	public String saveCmanage() {
		try {
			bs.save("insert_cmanage", placeinfo);
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "添加影院成功");
			request.setAttribute("msg", "添加成功!");
			lcity = bs.queryForList("select_city");
		} catch (Exception e) {
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()+ "添加影院失败，异常：" + e.getMessage());
			// TODO: handle exception
		}
		return "queryCmanage";
	}
	/**
	 * 跳转添加页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addcmanage(){
		lcity = bs.queryForList("select_city");
		return "saveCmanage";
		
	}

	/**
	 * 删除影院
	 */
	public String delCmanage() {
		try {
			bs.delete("delete_cmanage", this.id);
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "删除影院成功");
			this.flag = true;
		} catch (Exception e) {
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除影院失败，异常：" + e.getMessage());
			this.flag = false;
			// TODO: handle exception
		}
		return "delCmanage";
	}

	/**
	 * 发布影院信息
	 */
	public String updateUsable() {
		try {
			bs.delete("update_usable", this.id);
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "发布影院成功");
			this.uFlag = true;
		} catch (Exception e) {
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "发布影院失败，异常：" + e.getMessage());
			this.uFlag = false;
			// TODO: handle exception
		}
		return "updateUsable";
	}
	
	
	/**
	 * 查看明细.
	 * 
	 * @return showDetail.
	 */
	@SuppressWarnings("unchecked")
	public String goDetailedinformation() {
		lcity = bs.queryForList("select_city");
		placeinfo = (T0100_PlaceInfo) bs.queryForObject("select_placeinfo",
				this.id);
		if(!"".equals(placeinfo.getImage())){
			String img = "/upload"+placeinfo.getImage();
			placeinfo.setImage(img);
		}
		if(!"".equals(placeinfo.getMappic())){
			String img = "/upload"+placeinfo.getMappic();
			placeinfo.setMappic(img);
		}
		request.setAttribute("placeinfo", placeinfo);
		return "goDetailedinformation";
	}

	/**
	 * 修改影院信息
	 * @return
	 */
	public String updateDetailedinformation1() {
		try {
			bs.update("update_placeinfo", placeinfo);
			request.setAttribute("msg", "修改影院成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影院明细成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改影院失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除影院失败，异常：" + e.getMessage());
		}
		return "updateDetailedinformation1";
	}

	/**
	 * 影院信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String goedit() {
		lcity = bs.queryForList("select_city");
		placeinfo = (T0100_PlaceInfo) bs.queryForObject("select_placeinfo",
				this.id);
		request.setAttribute("placeinfo", placeinfo);
		return "goedit";
	}

	/**
	 * 判断影院编号是否存在
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String isExist(){
		List list = bs.queryForList("select_cmanage_isExist", placeno);
		if(list.size()>0){
			flag=false;
		}else{
			flag=true;
		}
		return "isExist";
	}
	
	
	public void uploadIMG(String url){
		try {
			String filename = "";
			File filesimg = getFile();
			String imgcmanage =	CPO.webRootPath+File.separator+"upload//"+url+ File.separator;
			if (filesimg != null) {
				// 1.文件的名字
				filename = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
				// 2.存储路径改
				String path = imgcmanage + filename;
				FileOutputStream fos = new FileOutputStream(path);
				FileInputStream fis = new FileInputStream(filesimg);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			}
			ServletActionContext.getResponse().getWriter().print("/"+url+"/"+filename);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void upload(){
		uploadIMG("cmanage");
	}
	
	public void uploadMappic(){
		uploadIMG("mappic");
	}
	
	public Boolean getFlag() {
		return flag;
	}

	public T0100_PlaceInfo getPlaceinfo() {
		return placeinfo;
	}

	public void setPlaceinfo(T0100_PlaceInfo placeinfo) {
		this.placeinfo = placeinfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
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

	public String getPlaceStr() {
		return placeStr;
	}

	public void setPlaceStr(String placeStr) {
		this.placeStr = placeStr;
	}

	public String getModuleList() {
		return moduleList;
	}

	public void setModuleList(String moduleList) {
		this.moduleList = moduleList;
	}

	public T0904_City getCity() {
		return city;
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
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getPlaceno() {
		return placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

	public Boolean getuFlag() {
		return uFlag;
	}

	public void setuFlag(Boolean uFlag) {
		this.uFlag = uFlag;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
