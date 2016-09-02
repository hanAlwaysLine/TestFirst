package cn.cinema.manage.action.filmmanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.action.cmanage.Cmanage;
import cn.cinema.manage.action.featuremanage.Featuremanage;
import cn.cinema.manage.entity.filmmanage.T0111_FILMMESSAGE;
import cn.cinema.manage.entity.filmmanage.T0112_GENREMESSAGE;
import cn.cinema.manage.entity.filmmanage.T0113_FIMEWARN;
import cn.cinema.manage.entity.filmmanage.T0903_NATION;
import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

/**
 * 影片管理
 */
public class Filmmanage {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Cmanage.class);
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

	private Integer id;
	/**
	 * 片花表中与电影关联字段
	 */
	private String film_d;
	
	private String iframeSrc;
	/**
	 * 判断删除是否成功
	 */
	private Boolean flag;
	private String type;// 上传文件类型
	private List<T0112_GENREMESSAGE> genremessageList;
	private List<T0903_NATION> nationList;
	/**
	 * 上传图片
	 */
	private File uploadimgs;
	private File uploadimg;
	private T0111_FILMMESSAGE filmmessage;
	private T0113_FIMEWARN fimewarn;
	/**
	 * 电影名称
	 */
	private String filmnameStr;
	private Messages messages = (Messages) ServiceLocator
			.getService("messages");
	String filename = "";// 文件的名字
	String filenames = "";
	public String filmno; // 影片编号
	private File file;
	
	/**
	 * 前台地址
	 */
	private String cinemawurl;
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();

	private void selList() {
		genremessageList = bs.queryForList("select_genremessage");
		nationList = bs.queryForList("selece_nation");
		request.setAttribute("genremessageList", genremessageList);
		request.setAttribute("nationList", nationList);
	}

	/**
	 * 查询电影
	 */
	public String queryfilmmanage() {
		try {
			PageList list = bs.findPage("select_filmmanage",
					"select_filmmanage_count", filmmessage, pageNo, pageSize);
			SessionUtil.setPageList(request, list);
			request.setAttribute("list", list);
			
			//获取前台地址信息
			Properties pro = new Properties();
			pro.load(Featuremanage.class.getClassLoader().getResourceAsStream("init.properties"));
			cinemawurl = pro.getProperty("cinemawurl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "queryfilmmanage";
	}

	/**
	 * 详细信息
	 */
	@SuppressWarnings("unchecked")
	public String details() {
		try {
			filmmessage = (T0111_FILMMESSAGE) bs.queryForObject("query_film",
					this.id);
			String uploadimgFileName = (String) filmmessage.getImage();// 图片的名字
			String uploadimgFileNames = (String) filmmessage.getImages();// 图片的名字
			System.out.println(uploadimgFileNames);
//			String dz = "upload" + uploadimgFileName;
//			String dzs = "upload" + uploadimgFileNames;
			request.setAttribute("dz", uploadimgFileName);
			request.setAttribute("dzs", uploadimgFileNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "details";
	}

	/**
	 * 删除影片
	 */
	public String delfilm() {
		try {
			T0111_FILMMESSAGE oldfilmmessage = (T0111_FILMMESSAGE) bs.queryForObject("edit_film",
					this.id);
			bs.delete("del_advertise", this.id);
			bs.delete("del_film", this.id);
			//清除无用文件
			clearFile(oldfilmmessage.getImage());
			clearFile(oldfilmmessage.getImages());
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "删除影片成功");
			this.flag = true;
		} catch (Exception e) {
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除影片失败，异常：" + e.getMessage());
			this.flag = false;
		}
		return "delfilm";
	}

	/**
	 * 进入添加影片页面
	 */
	public String addfilm() {
		try {
			selList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "savefilm";
	}

	/**
	 * 添加影片
	 */
	public String savefilm() {
		try {
			filmmessage.setRelease("0");
			bs.save("insert_filmmessage", filmmessage);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加影片成功");
			request.setAttribute("msg", "添加成功!");
			this.flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "添加影片失败，异常：" + e.getMessage());
			this.flag = false;
		}
		return "requery";
	}

	/**
	 * 跳转修改页
	 */
	public String editfilm() {
		filmmessage = (T0111_FILMMESSAGE) bs.queryForObject("edit_film",
				this.id);
		request.setAttribute("filmmessage", filmmessage);
		selList();
		return "editfilm";
	}

	/**
	 * 影片修改
	 */
	public String updatefilm() {
		try {
			T0111_FILMMESSAGE oldfilmmessage = (T0111_FILMMESSAGE) bs.queryForObject("edit_film",
					filmmessage.getId());
			bs.update("update_film", filmmessage);
			//清除无用文件
			if(!"".equals(filmmessage.getImage())&&filmmessage.getImage()!=null){
				clearFile(oldfilmmessage.getImage());
			}
			if(!"".equals(filmmessage.getImages())&&filmmessage.getImages()!=null){
				clearFile(oldfilmmessage.getImages());
			}
			
			request.setAttribute("msg", "修改成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "修改影片成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改影片失败，异常：" + e.getMessage());
		}
		return "updatefilm";
	}

	// ==============================================================
	/**
	 * 片花管理
	 */
	public String editfimewarn() {
		List list = (List) bs.queryForList("select_filmfimewarn", this.id);
		request.setAttribute("list", list);
		return "editfimewarn";
	}

	/**
	 * 添加片花
	 */
	public String savefimewarn() {
		try {
			if (uploadimg != null) {
				filename = saveFile(uploadimg, "trailer", type);
			}
			if (uploadimgs != null) {
				filenames = saveFile(uploadimgs, "trailer", "jpg");
			}
			fimewarn.setWarnpic("/upload/trailer/" + filename);
			fimewarn.setPrepic("/upload/trailer/" + filenames);
			fimewarn.setWarntype(2);
			bs.save("insert_fimewarn", fimewarn);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加片花成功");
			request.setAttribute("msg", "添加片花成功!");
			this.flag = true;
		} catch (Exception e) {
			request.setAttribute("msg", "添加片花失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "添加片花失败，异常：" + e.getMessage());
			this.flag = false;
		}
		return "savefimewarn";
	}

	/**
	 * 删除片花
	 * 
	 * @return
	 */
	public String delfimewarn() {
		try {
			fimewarn = (T0113_FIMEWARN) bs.queryForObject("select_fimestills",
					this.id);			
			bs.delete("del_fimewarn", this.id);
			//清除无用文件
			clearFile("/upload/"+fimewarn.getWarnpic());
			clearFile("/upload/"+fimewarn.getPrepic());
			request.setAttribute("msg", "删除成功!");
			this.film_d = this.film_d + ",true";// 影片编号+操作状态
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "删除片花成功");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			this.film_d = this.film_d + ",flase";
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除片花失败，异常：" + e.getMessage());
		}
		return "delfimewarn";
	}

	/**
	 * 片花详细
	 */
	public String goEditfimewarn() {
		fimewarn = (T0113_FIMEWARN) bs.queryForObject("select_fimewarn",
				this.id);
		filmnameStr = fimewarn.getPrepic();// 图片的名字
		// 存储路径改
		String dz = "upload" + filmnameStr;
		request.setAttribute("fileimagedz", dz);
		return "goEditfimewarn";
	}

	/**
	 * 更新片花
	 */
	public String goUpdatefimewarn() {
		try {
			if (uploadimg != null) {
				filename = saveFile(uploadimg, "trailer", type);
				if (filename != "") {
					fimewarn.setWarnpic("/upload/trailer/" + filename);
					clearFile("/upload/trailer/" + filename);
				} else {
					fimewarn.setWarnpic("");
				}
			}
			if (uploadimgs != null) {
				filenames = saveFile(uploadimgs, "trailer", "jpg");
				if (filenames != "") {
					fimewarn.setPrepic("/upload/trailer/" + filenames);
					clearFile("/upload/trailer/" + filename);
				} else {
					fimewarn.setPrepic("");
				}
			}

			bs.update("update_fimewarn", fimewarn);
			request.setAttribute("msg", "修改片花成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "修改片花成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改片花失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改片花失败，异常：" + e.getMessage());
		}
		return "goEditfimewarn";
	}

	// ==============================================================
	/**
	 * 剧照管理
	 * 
	 * @return
	 */
	public String stillsfimewarn() {
		try {
			List list = (List) bs
					.queryForList("select_filmfimestills", this.id);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fimestill";
	}

	/**
	 * 添加剧照
	 * 
	 * @return
	 */
	public String stillfime() {
		try {
			fimewarn.setWarntype(1);
			session.removeAttribute("war");
			fimewarn.setWarnpic_cut(CPO.warmap.get(fimewarn.getFilm_d()));
			bs.save("insert_fimestills", fimewarn);
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "添加剧照成功");
			request.setAttribute("msg", "添加剧照成功!");
			this.flag = true;
		} catch (Exception e) {
			request.setAttribute("msg", "添加剧照失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "添加剧照失败，异常：" + e.getMessage());
			this.flag = false;
		}
		return "savefimestill";
	}

	/**
	 * 剧照修改页
	 */
	public String goEditfimestills() {
		fimewarn = (T0113_FIMEWARN) bs.queryForObject("select_fimestills",
				this.id);
		filmnameStr = fimewarn.getWarnpic();// 图片的名字
		// 存储路径改
		String dz = "upload" + filmnameStr;
		request.setAttribute("stillimagedz", dz);
		return "goUpdatefimestills";
	}

	/**
	 * 更新剧照
	 * 
	 * @return
	 */
	public String goUpdatefimestills() {
		try {
			T0113_FIMEWARN fimeUpPic = (T0113_FIMEWARN)bs.queryForObject("select_fimestills", fimewarn.getId());
			if("".equals(session.getAttribute("war"))||session.getAttribute("war")==null){
				fimewarn.setWarnpic_cut("");
			}else{
				fimewarn.setWarnpic_cut(session.getAttribute("war").toString());
			}
			
			bs.update("update_fimestills", fimewarn);
			//清除无用文件
			if(!"".equals(fimewarn.getWarnpic())&&fimewarn.getWarnpic()!=null){
				clearFile(fimeUpPic.getWarnpic());
			}
			if(!"".equals(fimewarn.getWarnpic_cut())&&fimewarn.getWarnpic_cut()!=null){
				clearFile(fimeUpPic.getWarnpic_cut());
			}			
			request.setAttribute("msg", "修改剧照成功!");
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "修改剧照成功");
		} catch (Exception e) {
			request.setAttribute("msg", "修改剧照失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改片花剧照，异常：" + e.getMessage());
		}
		return "updatefimestills";
	}

	/**
	 * 删除剧照
	 */
	public String delfimestills() {
		try {
			fimewarn = (T0113_FIMEWARN) bs.queryForObject("select_fimestills",
					this.id);
			bs.delete("del_fimestills", this.id);
			//清除无用文件
			clearFile(fimewarn.getWarnpic());
			clearFile(fimewarn.getWarnpic_cut());
			request.setAttribute("msg", "删除成功!");
			this.film_d = this.film_d + ",true";// 影片编号+操作状态
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "删除剧照成功");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			this.film_d = this.film_d + ",flase";
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "删除剧照失败，异常：" + e.getMessage());
		}
		return "delfimestills";
	}

	// ==============================================================
	/**
	 * 位置设置
	 */
	public String goPosition() {
		try {
			String ids = request.getParameter("id");// 获得前台传来的ID值
			String position = request.getParameter("position");// 获得前台传来的位置值
			int twoposition = Integer.valueOf(position);
			List filmmessageList = bs.queryForList("select_Position", ids);
			T0111_FILMMESSAGE filmmessage = (T0111_FILMMESSAGE) filmmessageList
					.get(0);
			int oneposition = 0;
			if (filmmessage.getPosition() != null)
				oneposition = filmmessage.getPosition(); // 改动前的位置信息
			filmmessage.setPosition(twoposition);
			bs.update("update_Postion", filmmessage);
			if (oneposition > twoposition && oneposition != 0) {
				for (int i = twoposition + 1; i < 6; i++) {
					ids = this.rePosition(i, i - 1, ids);
				}
			} else if (oneposition < twoposition && oneposition != 0) {
				for (int i = twoposition - 1; i > 0; i--) {
					ids = this.rePosition(i, i + 1, ids);
				}
			} else {
				for (int i = twoposition + 1; i < 11; i++) {
					if (i == 10) {
						bs.update("update_Postion_others", ids);
					} else {
						ids = this.rePosition(i, i - 1, ids);
					}

				}
			}
			request.setAttribute("msg", "修改位置成功!");
			logger.info("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改位置信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改位置信息失败!");
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "修改位置信息失败，异常：" + e.getMessage());
		}
		return "rePosition";
	}

	@SuppressWarnings("unchecked")
	public String rePosition(int oneposition, int twoposition, String id) {
		String retval = "";
		try {
			retval = "";
			Map maps = new HashMap();
			maps.put("twoposition", twoposition);
			maps.put("id", id);
			List filmmessageList = bs.queryForList("select_Position_id", maps);
			T0111_FILMMESSAGE filmmessage = new T0111_FILMMESSAGE();
			if (filmmessageList.size() > 0) {
				filmmessage = (T0111_FILMMESSAGE) filmmessageList.get(0);
				retval = filmmessage.getId().toString();
				Map map = new HashMap();
				map.put("oneposition", oneposition);
				map.put("twoposition", twoposition);
				map.put("id", id);
				bs.update("update_Postion_other", map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

	// ==============================================================
	/**
	 * 发布状态
	 */
	@SuppressWarnings("unchecked")
	public String gorelease() {
		try {
			String ids = request.getParameter("id");
			List list = bs.queryForList("select_release", ids);
			T0201_FEATURE_APP t0201_feature_app = new T0201_FEATURE_APP();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());// 当前系统时间
			Map map = new HashMap();
			map.put("id", ids);
			if (list.size() > 0) {
				t0201_feature_app = (T0201_FEATURE_APP) list.get(0);
				if (t0201_feature_app.getFeaturedate() == null) {
					map.put("release", "1");
				} else {
					int i = sdf.parse(t0201_feature_app.getFeaturedate())
							.compareTo(sdf.parse(time));
					if (i > 0) {
						map.put("release", "1");
					} else {
						map.put("release", "2");
					}
				}
				bs.update("update_release", map);
			}
			logger
					.info("用户id" + SessionUtil.getEmp(session).getId()
							+ "发布影院成功");
			this.film_d = this.film_d + ",true";
		} catch (Exception e) {
			logger.error("用户id" + SessionUtil.getEmp(session).getId()
					+ "发布影院失败，异常：" + e.getMessage());
			this.film_d = this.film_d + ",false";
		}
		return "gorelease";
	}

	// ==============================================================
	public String saveFile(File file, String path, String type) {
		String filename = UUID.randomUUID().toString().replaceAll("-", "")
				+ "." + type;// 避免产生同名图片
		// 2.存储路径改
		String webPath = CPO.webRootPath.substring(0, CPO.webRootPath.indexOf("cinema"));
		String imgfilm = webPath + File.separator + "upload"+"//" + path + File.separator;// 读取init.properties文件里的path路径
		//判断文件是否存在
		File filePath = new File(imgfilm);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		String dz =imgfilm + filename;
		session.setAttribute("fileimagedz", dz);
		try {
			FileOutputStream fos = new FileOutputStream(dz);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filename;
	}

	public void uploadone() {
		String pianhua = request.getParameter("pianhua");
		uploads("filmpic", pianhua,this.id);
	}

	public void uploadtwo() {
		String pianhua = request.getParameter("pianhua");
		uploads("still", pianhua,this.id);
	}

	public void uploads(String url, String pianhua,Integer id) {
		try {
			String filename = "";
			File filesimg = getFile();
			String webPath = CPO.webRootPath.substring(0, CPO.webRootPath.indexOf("cinema"));
			String imgactivity = webPath + File.separator
					+ "upload" + "/" +url+File.separator;
			File imgFile = new File(imgactivity);
			if(!imgFile.exists()){
				imgFile.mkdirs();
			}
			if (filesimg != null) {

				// 1.文件的名字
				filename = UUID.randomUUID().toString().replaceAll("-", "")
						+ ".jpg";
				// 2.存储路径改
				String path = imgactivity + filename;
				System.out.println("path:" + path);
				FileOutputStream fos = new FileOutputStream(path);
				FileInputStream fis = new FileInputStream(filesimg);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				// 按比例截图
				if ("1".equals(pianhua)) {
					int h = 0;
					int w = 0;
					int x = 0;
					int y = 0;
					// OperateImage image = new OperateImage(35,0,484,629);
					// OperateImage image = new OperateImage(152,0,182,237);
					OperateImage image = new OperateImage(x, y, w, h);
					// 获取原图片的高和宽
					int yh = image.getImgHeight(filesimg);
					int yw = image.getImgWidth(filesimg);
					System.out.println("高：" + yh + ",宽：" + yw);
					double bl = (yw * 1.0) / (yh * 1.0);
					System.out.println("比例：" + bl);
					// 判断该图片是属于纵向还是横向 h>w（纵向）h<w（横向）
					if (yh > yw) {
						if (bl > 0.77) {
							h = yh;
							w = (int) (0.77 * yh);
							System.out.println("宽：" + w);
							y = 0;
							x = yw / 2 - w / 2;
							System.out.println("X轴：" + x);
						} else {
							w = yw;
							h = (int) (yw / 0.77);
							x = 0;
							y = yh / 2 - h / 2;
						}
					} else {
						h = yh;
						w = (int) (0.77 * yh);
						System.out.println("宽：" + w);
						y = 0;
						x = yw / 2 - w / 2;
						System.out.println("X轴：" + x);
					}
					image.setHeight(h);
					image.setWidth(w);
					image.setX(x);
					image.setY(y);
					image.setSrcpath(path);
					image.setSubpath(imgactivity
							+ UUID.randomUUID().toString().replaceAll("-", "")
							+ "_CUT" + ".jpg");
					image.setLastdir("jpg");
					String war = image.cut();
					if (war != null) {
						String[] w1 = war.split("still");
						war = "/upload/still/" + w1[1].substring(1);
					}
					
					CPO.warmap.put(String.valueOf(id), war);
					session.setAttribute("war", war);
					System.out.println("session="+session);
					System.out.println("sessionwar="+session.getAttribute("war"));
				}

			}
			ServletActionContext.getResponse().getWriter().print(
					"/upload/" + url + "/" + filename);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public String preview() {
		filmmessage = (T0111_FILMMESSAGE) bs.queryForObject("edit_film",
				this.id);
		request.setAttribute("filmmessage", filmmessage);
		return "preview";
	}

	/**
	 * 添加影片信息时验证影片编号
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String addFilmmanageValidate() throws IOException {
		try {
			T0111_FILMMESSAGE filmmes = new T0111_FILMMESSAGE();
			filmmes.setFilmno(filmno);
			List list = bs.queryForList("select_filmmanage_filmno", filmmes);
			String result = "false";
			if (list.size() == 0) {
				result = "true";
				System.out.println(result);
			} else {
				result = "false";
				System.out.println(result);
			}
			response.getWriter().write(result);
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("添加影片信息时验证影片编号异常:");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 清除无用文件
	 */
	public void clearFile(String url){
		try {
			String serverPath = CPO.webRootPath;
			String imgPath = serverPath.substring(0, serverPath.indexOf("cinema")-1)+url;
			File imgFile = new File(imgPath);
			if(imgFile.exists()){
				imgFile.delete();
			}
		} catch (Exception e) {
			logger.error("影片管理-清除无用文件异常:");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public List<T0112_GENREMESSAGE> getGenremessageList() {
		return genremessageList;
	}

	public void setGenremessageList(List<T0112_GENREMESSAGE> genremessageList) {
		this.genremessageList = genremessageList;
	}

	public File getUploadimgs() {
		return uploadimgs;
	}

	public void setUploadimgs(File uploadimgs) {
		this.uploadimgs = uploadimgs;
	}

	public File getUploadimg() {
		return uploadimg;
	}

	public void setUploadimg(File uploadimg) {
		this.uploadimg = uploadimg;
	}

	public T0111_FILMMESSAGE getFilmmessage() {
		return filmmessage;
	}

	public void setFilmmessage(T0111_FILMMESSAGE filmmessage) {
		this.filmmessage = filmmessage;
	}

	public T0113_FIMEWARN getFimewarn() {
		return fimewarn;
	}

	public void setFimewarn(T0113_FIMEWARN fimewarn) {
		this.fimewarn = fimewarn;
	}

	public String getFilmnameStr() {
		return filmnameStr;
	}

	public void setFilmnameStr(String filmnameStr) {
		this.filmnameStr = filmnameStr;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getFilm_d() {
		return film_d;
	}

	public void setFilm_d(String filmD) {
		film_d = filmD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilmno() {
		return filmno;
	}

	public void setFilmno(String filmno) {
		this.filmno = filmno;
	}

	public void setCinemawurl(String cinemawurl) {
		this.cinemawurl = cinemawurl;
	}

	public String getCinemawurl() {
		return cinemawurl;
	}

	public void setIframeSrc(String iframeSrc) {
		this.iframeSrc = iframeSrc;
	}

	public String getIframeSrc() {
		return iframeSrc;
	}
	
}
