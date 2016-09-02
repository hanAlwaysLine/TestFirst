package cn.cinema.manage.action.advertisemanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.Activitymanage.T0404UploadHtml;
import cn.cinema.manage.entity.advertisemanage.T0406Picture;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 上传页面
 * 
 * @author ducl
 * 
 */
public class UploadHtmlAction {

	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(UploadHtmlAction.class);
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
	 * 上传页面实体
	 */
	T0404UploadHtml uploadHtml = new T0404UploadHtml();

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
	 * 查询上传页面信息
	 * 
	 * @return
	 */
	public String findHtmlList() {
		try {
			PageList uploadHtmlList = bs.findPage("uploadhtml_select_info",
					"uploadhtml_select_count", uploadHtml, pageNo, pageSize);
			SessionUtil.setPageList(request, uploadHtmlList);
			request.setAttribute("uploadHtmlList", uploadHtmlList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询上传页面信息异常:");
			e.printStackTrace();
		}
		return "findHtmlList";
	}

	/**
	 * 添加上传页面信息
	 * 
	 * @return
	 */
	public String addHtml() {
		try {
			if(!"".equals(uploadFileName)&&uploadFileName!=null){
				uploadHtml.setHtmlurl(copyImgFile());
			}
			bs.save("uploadhtml_insert_info", uploadHtml);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "上传页面信息");
			request.setAttribute("msg", "添加成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "添加失败!");
			logger.error("添加图片信息异常:");
			e.printStackTrace();
		}
		return "addHtml";
	}
	
	/**
	 * 根据ID获取上传页面信息
	 * @return
	 */
	public String findInfoById(){
		try {
			uploadHtml.setHtmlid(id);
			uploadHtml = (T0404UploadHtml)bs.queryForObject("uploadhtml_select_info", uploadHtml);
			this.oldPictureUrl = uploadHtml.getHtmlurl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findInfoById";
	}
	
	/**
	 * 修改上传页面信息
	 * @return
	 */
	public String editHtml(){
		try {
			if(!"".equals(uploadFileName)&&uploadFileName!=null){
				uploadHtml.setHtmlurl(copyImgFile());
				//清除原图片
//				clearPicture(oldPictureUrl);
			}else{
				uploadHtml.setHtmlurl(oldPictureUrl);
			}
			bs.update("uploadhtml_update_info", uploadHtml);
			request.setAttribute("msg", "修改成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "修改上传页面信息成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "修改上传页面信息失败!");
			logger.error("修改图片信息异常:");
			e.printStackTrace();
		}
		return "editHtml";
	}

	/**
	 * 删除文件信息
	 * @return
	 */
	public String delHtml(){
		try {
			//删除信息
			uploadHtml.setHtmlid(id);
			uploadHtml = (T0404UploadHtml)bs.queryForObject("uploadhtml_select_info", uploadHtml);
			
			bs.delete("uploadhtml_delete_info", uploadHtml);
			
			//删除文件
			clearFile(uploadHtml);
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "删除上传页面信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = false;
			logger.info("用户id" + SessionUtil.getEmp(session).getId()+ "删除上传页面信息失败");
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 把选中的页面压缩包复制到指定的文件夹中
	 * 
	 * @throws IOException
	 */
	public String copyImgFile() throws IOException {
		boolean op = true;
		// 指定文件目标路径
		String serverPath = CPO.webRootPath;
		String mkpath = serverPath.substring(0, serverPath.indexOf("cinema")) + "upload/html";
		File img = new File(mkpath);
		if (!img.exists()) {
			img.mkdir();
		}
		//拼接文件名
		String copyImgPath = mkpath+ File.separator+ uploadFileName;
		File out = new File(copyImgPath);

		//上传文件
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
			op = false;
			logger.error("把选中的页面压缩包复制到指定的文件夹中上传异常:");
			 e.printStackTrace();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
		
		// 解压zip文件
		// 定义输入输出流对象
		InputStream input = null;
		OutputStream output = null;
		// 创建zip文件对象
		ZipFile zipFile = null;
		try {
			// 创建zip文件对象
			zipFile = new ZipFile(out);
			// 得到zip文件条目枚举对象
			Enumeration zipEnum = zipFile.entries();

			// 循环读取条目
			while (zipEnum.hasMoreElements()) {
				// 得到当前条目
				ZipEntry entry = (ZipEntry) zipEnum.nextElement();
				String entryName = new String(entry.getName().getBytes("ISO-8859-1"), "UTF-8");
				//解压后文件路径
				String outPathStr = mkpath + "/" + entryName;
				File outPathFile = new File(outPathStr);
				
				//判断路径是否存在
				if(entryName.indexOf('.') <= 0){
					if(!outPathFile.exists()){
						outPathFile.mkdirs();
					}
				}
				//判断是否文件夹
				if(outPathFile.isDirectory()){
					continue;
				}
				
				//解压文件
				input = zipFile.getInputStream(entry);
				output = new FileOutputStream(new File(outPathStr));
				byte[] buffer = new byte[1024 * 8];
				int readLen = 0;
				while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1) {
					output.write(buffer, 0, readLen);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			op = false;
			logger.error("把选中的页面压缩包复制到指定的文件夹中解压异常:");
			e.printStackTrace();
		}finally {
			// 关闭流
			if (input != null)
				input.close();
			if (output != null)
				output.close();
				output.flush();
			if(zipFile != null)
				zipFile.close();
		}
		
		if(op){
			out.delete();
		}
		//拼接上传页面路径
		String nameStr = uploadFileName.substring(0, uploadFileName.indexOf('.'));
		copyImgPath = "/"+mkpath.substring(mkpath.indexOf("upload"),mkpath.length())+"/"+nameStr+File.separator+uploadHtml.getHtmlname();
		return copyImgPath;
	}
	
	/**
	 * 清除无用文件
	 */
	public void clearFile(T0404UploadHtml uh){
		try {
			String serverPath = CPO.webRootPath;
			String filmPath = uh.getHtmlurl().substring(0, uh.getHtmlurl().indexOf(uh.getHtmlname()));
			String imgPath = serverPath.substring(0, serverPath.indexOf("cinema")-1)+filmPath;
			File imgFile = new File(imgPath);
			if(imgFile.exists()){
				deleteDir(imgFile);
			}
			logger.info("删除无用页面文件成功!");
		} catch (Exception e) {
			logger.error("清除无用文件异常:");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 */
	public void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                deleteDir(new File(dir, children[i]));
            }
        }
        // 目录此时为空，可以删除
        dir.delete();
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
				uploadHtml.setHtmlname(picturename);
				List pictureList = bs.queryForList("select_picture_infoByName", uploadHtml);
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

	public T0404UploadHtml getUploadHtml() {
		return uploadHtml;
	}

	public void setUploadHtml(T0404UploadHtml uploadHtml) {
		this.uploadHtml = uploadHtml;
	}
	
}
