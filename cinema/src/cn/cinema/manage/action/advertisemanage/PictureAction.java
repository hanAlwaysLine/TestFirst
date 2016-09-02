package cn.cinema.manage.action.advertisemanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.advertisemanage.T0406Picture;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 广告管理
 * 
 * @author ducl
 * 
 */
public class PictureAction {

	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(PictureAction.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();

	/**
	 * 内置对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();

	private HttpServletResponse response = ServletActionContext.getResponse();

	private HttpSession session = request.getSession();

	/**
	 * 图片管理实体
	 */
	T0406Picture picture = new T0406Picture();

	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 页数
	 */
	private Integer pageNo;

	/**
	 * 文件对象
	 */
	private File upload;

	private String uploadContentType;

	private String uploadFileName;
	
	/**
	 * 原图片地址
	 */
	private String oldPictureUrl;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 操作结果
	 */
	private boolean result = true;
	
	/**
	 * json参数
	 */
	private String jsonString;

	/********************************* 方法 ********************************/

	/**
	 * 查询图片信息
	 * 
	 * @return
	 */
	public String findPicList() {
		try {
			PageList pictureList = bs.findPage("select_picture_info",
					"select_picture_infocount", picture, pageNo, pageSize);
			SessionUtil.setPageList(request, pictureList);
			request.setAttribute("pictureList", pictureList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询图片信息:");
			e.printStackTrace();
		}
		return "findPicList";
	}

	/**
	 * 添加图片信息
	 * 
	 * @return
	 */
	public String addPic() {
		try {
			if(!"".equals(uploadFileName)&&uploadFileName!=null){
				picture.setPictureurl(copyImgFile());
			}
			bs.save("insert_picture_info", picture);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加图片成功");
			request.setAttribute("msg", "添加成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "添加失败!");
			logger.error("添加图片信息异常:");
			e.printStackTrace();
		}
		return "addPic";
	}
	
	/**
	 * 根据ID获取图片信息
	 * @return
	 */
	public String findInfoById(){
		try {
			picture.setId(Integer.parseInt(id));
			picture = (T0406Picture)bs.queryForObject("select_picture_infoById", picture);
			this.oldPictureUrl = picture.getPictureurl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findInfoById";
	}
	
	/**
	 * 修改图片信息
	 * @return
	 */
	public String editPic(){
		try {
			if(!"".equals(uploadFileName)&&uploadFileName!=null){
				picture.setPictureurl(copyImgFile());
				//清除原图片
				clearPicture(oldPictureUrl);
			}else{
				picture.setPictureurl(oldPictureUrl);
			}
			bs.update("update_picture_Info", picture);
			request.setAttribute("msg", "修改成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "修改图片成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "修改失败!");
			logger.error("修改图片信息异常:");
			e.printStackTrace();
		}
		return "editPic";
	}

	/**
	 * 删除图片信息
	 * @return
	 */
	public String delPic(){
		try {
			//删除信息
			picture.setId(Integer.parseInt(id));
			picture = (T0406Picture)bs.queryForObject("select_picture_infoById", picture);
			
			bs.delete("delete_picture_info", picture);
			
			//删除图片
			clearPicture(picture.getPictureurl());
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "删除图片成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = false;
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "删除图片失败");
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 把选中的图片复制到指定的文件夹中
	 * 
	 * @throws IOException
	 */
	public String copyImgFile() throws IOException {
		// 指定文件目标路径
		String serverPath = CPO.webRootPath;
		String mkpath = serverPath.substring(0, serverPath.indexOf("cinema"))
				+ "upload" + File.separator + "picture";
		File img = new File(mkpath);
		if (!img.exists()) {
			img.mkdir();
		}
		String copyImgPath = mkpath+ File.separator+ upload.toString().substring(upload.toString().lastIndexOf(File.separator.toString()) + 1,upload.toString().lastIndexOf("."));
		uploadFileName = uploadFileName.substring(uploadFileName
				.lastIndexOf("."), uploadFileName.length());
		copyImgPath += uploadFileName;
		File out = new File(copyImgPath);

		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(upload));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(out));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("复制图片文件失败：" + e.getMessage());
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
		copyImgPath = "/"+copyImgPath.substring(copyImgPath.indexOf("upload"),
				copyImgPath.length());
		return copyImgPath;
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
	 * 判断图片名称是否存在
	 */
	public void isNotExist(){
		try {
			String result = "true";
			JSONObject jsonObj = JSONObject.fromObject(jsonString);
			String picturename = jsonObj.getString("picturename");
			String oldpicturename = jsonObj.getString("oldpicturename");
			//判断缩写名称是否与原名称相同
			if(!oldpicturename.equals(picturename)){
				picture.setPicturename(picturename);
				List pictureList = bs.queryForList("select_picture_infoByName", picture);
				if(pictureList.size()>0){
					result = "false";
				}
			}
		
			response.getWriter().write(result);
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("判断图片名称是否存在异常:");
			e.printStackTrace();
		}
	}

	/********************************* 封装 ********************************/
	public T0406Picture getPicture() {
		return picture;
	}

	public void setPicture(T0406Picture picture) {
		this.picture = picture;
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

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getOldPictureUrl() {
		return oldPictureUrl;
	}

	public void setOldPictureUrl(String oldPictureUrl) {
		this.oldPictureUrl = oldPictureUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
}
