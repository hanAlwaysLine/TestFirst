package cn.cinema.manage.action.goods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.goods.T0200Goods;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 活动管理
 * 
 */
public class GoodsAction {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(GoodsAction.class);
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
	 * 活动管理实体
	 */
	public T0200Goods goods;

	public String jsonString;
	
	/**
	 * 原图片信息
	 */
	public String oldPicUrl;

	
	public String getOldPicUrl() {
		return oldPicUrl;
	}

	public void setOldPicUrl(String oldPicUrl) {
		this.oldPicUrl = oldPicUrl;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public T0200Goods getGoods() {
		return goods;
	}

	public void setGoods(T0200Goods goods) {
		this.goods = goods;
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

	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();

	// 查询列表
	public String goodsList() {

		PageList goodslist = bs.findPage("select_goods", "select_goodscount",
				goods, pageNo, pageSize);
		SessionUtil.setPageList(request, goodslist);
		request.setAttribute("goodslist", goodslist);
		return "queryhall";

	}

	/**
	 * 跳转添加页面
	 * 
	 * @return
	 */
	public String addgoods() {
		return "addgoods";
	}

	/**
	 * 保存新增卖品
	 * 
	 * @return
	 */
	public String savegoods() {

		try {
			bs.save("insert_goods", goods);
			request.setAttribute("msg", "添加成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加卖品成功");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id:" + SessionUtil.getEmp(session).getId()
					+ "新增活动失败，异常：" + e.getMessage());
		}
		return "update";
	}

	//
	// /**
	// * 根据用户id删除卖品
	// * @return
	// */s
	public String delActivity() {
		try {
			T0200Goods t = new T0200Goods();
			t.setGOODS_ID(id);
			goods = (T0200Goods) bs.queryForObject("query_goods", t);
			
			//删除图片数据
			bs.delete("del_goods", t);
			//清除原图片
			clearPicture(goods.getGOODS_PIC());
			modifySuc = true;
		} catch (Exception e) {
			modifySuc = false;
		}
		return "delete";
	}

	//
	// /**
	// * 根据用户id发布活动
	// * @return
	// */
	// public String relActivity() {
	// try {
	// T0403Activity t = new T0403Activity();
	// t.setActivity_id(id);
	// activity=(T0403Activity) bs.queryForObject("query_activity",t);
	// bs.update("release_activity", t);
	// logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]发布活动成功,操作活动名为["+activity.getActivity_name()+"]");
	// modifySuc = true;
	// } catch (Exception e) {
	// logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]发布活动失败，异常信息："+e.getMessage());
	// modifySuc = false;
	// }
	// return "release";
	// }
	//
	//
	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	public String goedit() {
		try {
			T0200Goods t = new T0200Goods();
			t.setGOODS_ID(id);
			goods = (T0200Goods) bs.queryForObject("query_goods", t);
			String uploadimgFileName = goods.getGOODS_PIC();// 图片的名字
			// 存储路径改
			String dz = "upload/" + uploadimgFileName;
			request.setAttribute("fileimagedz", dz);
			request.setAttribute("goods", goods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("跳转修改页面异常:");
			e.printStackTrace();
		}
		return "goedit";
	}

	/**
	 * 修改卖品信息
	 * 
	 * @return
	 */
	public String updHall() {
		try {
			bs.update("update_goods", goods);
			request.setAttribute("msg", "修改卖品信息成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改卖品信息成功");
			//清除原图片信息
			if(!oldPicUrl.equals(goods.getGOODS_PIC())){
				clearPicture(oldPicUrl);
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", "修改卖品信息失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影厅信息失败，异常：" + e.getMessage());
		}
		return "update";
	}

	/**
	 * 上传图片
	 */
	public void upload() {
		try {
			String filename = "";
			File filesimg = getFile();
			String webPath = CPO.webRootPath.substring(0, CPO.webRootPath.indexOf("cinema"));
			String imgactivity = webPath + File.separator
					+ "upload//goods" + File.separator;

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
					"/upload/goods/" + filename);

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
	

	/**
	 * 判断卖品名称是否存在
	 */
	public void isNotExist(){
		try {
			String result = "true";
			JSONObject jsonObj = JSONObject.fromObject(jsonString);
			String goodsname = jsonObj.getString("goodsname");
			String oldgoodsname = jsonObj.getString("oldgoodsname");
			//判断缩写名称是否与原名称相同
			if(!oldgoodsname.equals(goodsname)){
				goods.setGOODS_NAME(goodsname);
				List pictureList = bs.queryForList("query_goods_ByName", goods);
				if(pictureList.size()>0){
					result = "false";
				}
			}
		
			response.getWriter().write(result);
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("判断卖品名称是否存在异常:");
			e.printStackTrace();
		}
	}

}
