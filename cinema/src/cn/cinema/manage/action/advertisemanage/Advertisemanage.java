package cn.cinema.manage.action.advertisemanage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.Activitymanage.T0403Activity;
import cn.cinema.manage.entity.advertisemanage.T0405Advertisement;
import cn.cinema.manage.entity.filmmanage.T0111_FILMMESSAGE;
import cn.cinema.manage.entity.manage.T0100_PlaceInfo;
import cn.cinema.manage.entity.manage.T0904_City;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 广告管理
 * 
 */
public class Advertisemanage {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Advertisemanage.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	private T0904_City city;
	private List<T0904_City> listall;
	private T0111_FILMMESSAGE filmmessage;
	public T0403Activity activity;

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

	private String type;
	/**
	 * 判断删除是否成功
	 */
	private Boolean modifySuc;
	/**
	 * 上传图片
	 */
	private File uploadimg;
	private String uploadimgFileName;

	/**
	 * 获取读取properties工具类
	 */
	private Messages messages = (Messages) ServiceLocator
			.getService("messages");

	/**
	 * 广告管理实体
	 */
	public T0405Advertisement advertise;

	public String jsonString;

	public String a_city;
	public Integer advertisement_type;

	public String getA_city() {
		return a_city;
	}

	public void setA_city(String aCity) {
		a_city = aCity;
	}

	public Integer getAdvertisement_type() {
		return advertisement_type;
	}

	public void setAdvertisement_type(Integer advertisementType) {
		advertisement_type = advertisementType;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public T0111_FILMMESSAGE getFilmmessage() {
		return filmmessage;
	}

	public void setFilmmessage(T0111_FILMMESSAGE filmmessage) {
		this.filmmessage = filmmessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public T0904_City getCity() {
		return city;
	}

	public void setCity(T0904_City city) {
		this.city = city;
	}

	public List<T0904_City> getListall() {
		return listall;
	}

	public void setListall(List<T0904_City> listall) {
		this.listall = listall;
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

	public T0405Advertisement getAdvertise() {
		return advertise;
	}

	public void setAdvertise(T0405Advertisement advertise) {
		this.advertise = advertise;
	}

	public T0403Activity getActivity() {
		return activity;
	}

	public void setActivity(T0403Activity activity) {
		this.activity = activity;
	}

	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selList() {

		if (type.equals("1")) {
			PageList filmmsglist = bs.findPage("select_filmmsg",
					"select_filmmsgcount", advertise, pageNo, pageSize);
			SessionUtil.setPageList(request, filmmsglist);
			request.setAttribute("filmmsglist", filmmsglist);
			return "querycarouse";
		} else if (type.equals("2")) {
			PageList admsglist = bs.findPage("select_admsg",
					"select_admsgcount", advertise, pageNo, pageSize);
			SessionUtil.setPageList(request, admsglist);
			request.setAttribute("admsglist", admsglist);
			return "queryadchart";
		}

		else if (type.equals("3")) {
			PageList actmsglist = bs.findPage("select_actmsg",
					"select_actmsgcount", advertise, pageNo, pageSize);
			SessionUtil.setPageList(request, actmsglist);
			request.setAttribute("actmsglist", actmsglist);
			return "queryactchart";
		}
		return null;
	}

	/**
	 * 跳转添加页面
	 * 
	 * @return
	 */
	public String addadvertise() {
		if (type.equals("1")) {
			return "addcarouse";
		} else if (type.equals("2")) {
			return "addadchart";
		} else {
			return "addactchart";
		}

	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String saveadvertise() {
		try {
			bs.save("insert_advertise", advertise);
			request.setAttribute("msg", "添加成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "添加广告成功");
		} catch (Exception e) {
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id:" + SessionUtil.getEmp(session).getId()
					+ "新增广告失败，异常：" + e.getMessage());
		}
		if (advertise.getAdvertisement_type() == 1) {
			return "savecarouse";
		} else if (advertise.getAdvertisement_type() == 21
				|| advertise.getAdvertisement_type() == 22
				|| advertise.getAdvertisement_type() == 23) {
			return "saveadchart";
		} else {
			return "saveactchart";
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deladvertise() {
		try {
			T0405Advertisement t = new T0405Advertisement();
			t.setAdvertise_id(id);
			bs.delete("delete_advertise", t);
			logger.info("用户id[" + SessionUtil.getEmp(session).getId()
					+ "]删除广告成功");
			modifySuc = true;
		} catch (Exception e) {
			logger.error("用户id[" + SessionUtil.getEmp(session).getId()
					+ "]删除广告失败，异常信息：" + e.getMessage());
			modifySuc = false;
		}
		return "delete";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	public String goedit() {

		T0405Advertisement t = new T0405Advertisement();
		t.setAdvertise_id(id);
		advertise = (T0405Advertisement) bs
				.queryForObject("query_advertise", t);
		request.setAttribute("advertise", advertise);
		if (type.equals("1")) {
			return "editcarouse";
		} else if (type.equals("2")) {
			return "editadchart";
		} else {
			return "editactchart";
		}

	}

	/**
	 * 修改广告信息
	 * 
	 * @return
	 */
	public String editadvertise() {

		try {
			bs.update("update_advertise", advertise);
			request.setAttribute("msg", "修改广告信息成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改广告信息成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改广告信息失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改广告信息失败，异常：" + e.getMessage());
		}
		if (advertise.getAdvertisement_type() == 1) {
			return "editcarouse";
		} else if (advertise.getAdvertisement_type() == 21
				|| advertise.getAdvertisement_type() == 22
				|| advertise.getAdvertisement_type() == 23) {
			return "editadchart";
		} else if (advertise.getAdvertisement_type() == 31
				|| advertise.getAdvertisement_type() == 32) {
			return "editactchart";
		}
		return null;
	}

	/**
	 * 广告图和活动图的位置设置
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String posadvertise() {
		Integer pos = advertise.getPosition();// 页面传来的位置
		// System.out.println(pos);
		// city
		// type

		advertise = (T0405Advertisement) bs.queryForObject("sel_advertise",
				advertise);
		Integer advertisement_type = advertise.getAdvertisement_type(); // type
		Map map = new HashMap();
		// map.put("advertisement_type",
		// advertisement_type.toString().substring(0, 1));//区分要修改广告还是活动图的位置
		map.put("advertisement_type", advertisement_type + "");// 区分要修改广告还是活动图的位置
		map.put("cityno", advertise.getCityno() + "");// 城市
		if (advertise.getPosition() != null)// 数据库里的位置不为NULL
		{
			Integer position = advertise.getPosition();
			try {
				if (position > pos) {
					for (int i = position - 1; i >= pos; i--)// 必须倒着改
					{
						map.put("position", i + 1);// 要改到的位置
						map.put("prepos", i); // 原来的位置
						bs.update("update_Position_Other", map);
					}
					advertise.setPosition(pos);
					bs.update("update_Position", advertise);
				} else if (position < pos) {
					for (int i = position + 1; i <= pos; i++) {
						map.put("position", i - 1);// 要改到的位置
						map.put("prepos", i); // 原来的位置
						bs.update("update_Position_Other", map);
					}
					advertise.setPosition(pos);
					bs.update("update_Position", advertise);
				}
				request.setAttribute("msg", "修改位置成功!");
				logger.info("用户id" + SessionUtil.getEmp(session).getId()
						+ "修改位置信息成功");
			} catch (Exception e) {
				request.setAttribute("msg", "修改位置信息失败!");
				logger.error("用户id" + SessionUtil.getEmp(session).getId()
						+ "修改位置信息失败，异常：" + e.getMessage());
			}

		} else {// 数据库里的位置为NULL

			try {
				for (int i = 5; i >= pos; i--) // 必须倒着改
				{
					if (i == 5) {
						map.put("position", null);
						map.put("prepos", i);
						bs.update("update_Position_Other", map);
						continue;
					}
					map.put("position", i + 1);// 要改到的位置
					map.put("prepos", i); // 原来的位置
					bs.update("update_Position_Other", map);
				}
				advertise.setPosition(pos);
				bs.update("update_Position", advertise);
				request.setAttribute("msg", "修改位置成功!");
				logger.info("用户id" + SessionUtil.getEmp(session).getId()
						+ "修改位置信息成功");
			} catch (Exception e) {
				request.setAttribute("msg", "修改位置信息失败!");
				logger.error("用户id" + SessionUtil.getEmp(session).getId()
						+ "修改位置信息失败，异常：" + e.getMessage());
			}

		}
		if (advertisement_type == 21 || advertisement_type == 22
				|| advertisement_type == 23) {
			return "adposition";
		} else {
			return "actposition";
		}
	}

	/**
	 * 校验影片ID
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String validat() {
		try {
			T0111_FILMMESSAGE filmmessage = new T0111_FILMMESSAGE();
			filmmessage.setId(Integer.parseInt(jsonString.trim()));
			List list = bs.queryForList("queryfsg", filmmessage);
			List list_adver = bs.queryForList("sum_advertisement");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Pragma", "No-Cache");
			response.setHeader("Cache-Control", "No-Cache");
			response.setDateHeader("Expires", 0);
			// 判断影片是否合格
			if (list.size() == 0) {
				try {
					// 影片不存在
					response.getWriter().write("F");
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (list != null && list.size() > 0) {
				// 获得影片信息
				filmmessage = (T0111_FILMMESSAGE) list.get(0);
				// 判断影片是否发布
				if (filmmessage.getRelease().equals("0")) {
					try {
						// 影片未发布，返回E（用于前台回调函数判断）
						response.getWriter().write("E");
						return null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					if (list_adver.size() < 6) {
						try {
							// 影片已发布
							response.getWriter().write("T");
							return null;
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							// 首页轮播图超过6张
							response.getWriter().write("C");
							return null;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("校验影片ID异常:");
			e.printStackTrace();
		}
		/*
		 * if(list_adver != null && list_adver.size() > 0 && list_adver.size() <
		 * 6){ if (list!=null && list.size()>0){ //获得影片信息 filmmessage =
		 * (T0111_FILMMESSAGE) list.get(0); //判断影片是否发布
		 * if(filmmessage.getRelease().equals("0")){ try {
		 * //影片未发布，返回E（用于前台回调函数判断） response.getWriter().write("E"); return null;
		 * }catch (IOException e){ e.printStackTrace(); } }else{ try { //影片已发布
		 * response.getWriter().write("T"); return null; }catch (IOException e){
		 * e.printStackTrace(); } } }else { try{ //影片不存在
		 * response.getWriter().write("F"); return null; } catch (IOException
		 * e){ e.printStackTrace(); } } }else{ if(list_adver.size() >= 5){ try{
		 * //首页轮播图超过6张 response.getWriter().write("C"); return null; } catch
		 * (IOException e){ e.printStackTrace(); } } if(list.size()==0){ try{
		 * //影片不存在 response.getWriter().write("F"); return null; } catch
		 * (IOException e){ e.printStackTrace(); } }
		 * 
		 * }
		 */
		return null;
	}

	public String advervalidat() {
		T0403Activity activity = new T0403Activity();
		activity.setActivity_id(Integer.parseInt(jsonString));
		activity = (T0403Activity) bs.queryForObject("query_act", activity);
		if (activity == null) {
			try {
				response.getWriter().write("F");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("T");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * else { if (type.equals("2")){ if
		 * (!(activity.getImgtype()==21||activity
		 * .getImgtype()==22||activity.getImgtype()==23)) { try {
		 * response.getWriter().write("E"); } catch (IOException e){
		 * e.printStackTrace(); } }else { try { response.getWriter().write("T");
		 * } catch (IOException e){ e.printStackTrace(); } } }else { if
		 * (!(activity.getImgtype()==31||activity.getImgtype()==32)) { try {
		 * response.getWriter().write("E"); } catch (IOException e){
		 * e.printStackTrace(); } }else { try { response.getWriter().write("T");
		 * } catch (IOException e){ e.printStackTrace(); } } } }
		 */
		return null;
	}

	public String editwarn() {

		filmmessage = (T0111_FILMMESSAGE) bs.queryForObject("edit_film",
				this.id);
		request.setAttribute("filmmessage", filmmessage);
		return "editwarn";
	}

	public String doeditwarn() {
		try {
			bs.update("update_filmimage", filmmessage);
			request.setAttribute("msg", "修改成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "修改影片成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影片失败，异常：" + e.getMessage());
		}
		return "editwarn";
	}

	public String editactwarn() {
		activity = (T0403Activity) bs
				.queryForObject("query_activity", activity);
		request.setAttribute("activity", activity);
		request.setAttribute("advertisement_type", advertisement_type);
		return "editactwarn";
	}

	public String doeditactwarn() {
		try {
			bs.update("update_actimg", activity);
			request.setAttribute("msg", "修改成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId() + "修改成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改失败，异常：" + e.getMessage());
		}
		return "editactwarn";
	}

	public String editactimg() {
		activity = (T0403Activity) bs
				.queryForObject("query_activity", activity);
		request.setAttribute("activity", activity);
		// System.out.println("================:"+advertisement_type);
		request.setAttribute("advertisement_type", advertisement_type);
		return "editactimg";
	}

	public String doeditactimg() {
		try {
			bs.update("update_actimg", activity);
			request.setAttribute("msg", "修改成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId() + "修改成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改失败，异常：" + e.getMessage());
		}
		return "editactimg";
	}

	public String count_advert() {
		T0405Advertisement advertisement = new T0405Advertisement();
		advertisement.setCityno(a_city);
		advertisement.setAdvertisement_type(advertisement_type);
		List list = bs.queryForList("count_advertisement", advertisement);
		if (list == null || list.size() == 0 || list.size() < 5) {
			try {
				// 返回成功
				response.getWriter().write("Y");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				// 返回失败
				response.getWriter().write("N");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
