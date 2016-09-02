package cn.cinema.manage.action.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.sys.T0002_Ad_Role;
import cn.cinema.manage.entity.sys.T0003_Ad_Competence;
import cn.cinema.manage.entity.sys.T0005_Ad_Comprole;
import cn.cinema.manage.entity.sys.T1000_Users;
import cn.cinema.manage.entity.sys.T1001_Menu;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

public class Role {

	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * 日志操作
	 */
	private Logger logger = Logger.getLogger(Role.class);
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();
	/**
	 * 页数pageSize
	 */
	private Integer pageSize;
	/**
	 * 每页数量
	 */
	private Integer pageNo;

	private List<T0002_Ad_Role> list;
	private T0002_Ad_Role role;
	private String id;
	private List<T0002_Ad_Role> rlist;
	private T0005_Ad_Comprole power;
	private T1001_Menu menu;
	private List<T0005_Ad_Comprole> plists;
	private String rolename;
	private JSONArray arr;
	/**
	 * 判断
	 */
	private Boolean modifySuc;

	/**
	 * 用户实体对象
	 */
	private T1000_Users user;

	/**
	 * 查询角色列表 分页 pageNo pageSize
	 * 
	 * @return
	 */
	public String queryRole() {
		try {
			PageList list = bs.findPage("role_select", "select_role_count",
					role, pageNo, pageSize);
			SessionUtil.setPageList(request, list);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 跳转到添加页面
	 */
	public String goAddRole() {

		return "goAdd";
	}

	/**
	 * 新增角色
	 * 
	 * @return
	 */
	public String addRole() {
		try {
			T0002_Ad_Role r = new T0002_Ad_Role();
			r.setTitle(role.getTitle());
			bs.save("role_insert", r);
			
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]新增角色成功,操作角色名为["+role.getTitle()+"]");
			ServletActionContext.getRequest().setAttribute("msg", "添加成功!");
		} catch (Exception e) {
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]新增角色失败，异常信息："+e.getMessage());
			ServletActionContext.getRequest().setAttribute("msg", "添加失败!");
		}

		return "add";
	}

	/**
	 * 根据用户id删除角色
	 * 
	 * @return
	 */
	public String delRole() {

		try {
			T0002_Ad_Role r = new T0002_Ad_Role();
			r.setId(id);
			role=(T0002_Ad_Role) bs.queryForObject("role_query",r);
			bs.delete("role_delete", r);
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]删除角色成功,操作角色名为["+role.getTitle()+"]");
			modifySuc = true;
		} catch (Exception e) {
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]删除角色失败，异常信息："+e.getMessage());
			modifySuc = false;
		}
		return "delete";
	}

	/**
	 * 跳转到修改页面
	 */
	public String goUpdateRole() {
		role = (T0002_Ad_Role) bs.queryForList("role_selectOne", id).get(0);

		return "goUpdate";
	}

	/**
	 * 更新角色信息
	 * 
	 * @return
	 */
	public String updateRole() {
		try {
			bs.save("role_update", role);
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]删除角色成功,操作角色名为["+role.getTitle()+"]");
			ServletActionContext.getRequest().setAttribute("msg", "更新成功!");
		} catch (Exception e) {
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]更新角色失败，异常信息："+e.getMessage());
			ServletActionContext.getRequest().setAttribute("msg", "更新失败!");
		}
		return "update";
	}

	/**
	 * 跳转到授权页面
	 */
	public String goSqRole() {
		try {
			// 授权的角色
			role = (T0002_Ad_Role) bs.queryForList("role_selectOne", id)
					.get(0);
			// 存放所有一级二级目录（存放T1001_Menu实体）
			List<T0003_Ad_Competence> allList = new ArrayList<T0003_Ad_Competence>();
			// 查询所有一级目录
			List<T0003_Ad_Competence> meun = bs.queryForList("select_f_menu");
			// 循环 迭代一级目录 查找 当前一级目录下的所有二级目录，并且放入T1001 实体中的集合当中
			for (T0003_Ad_Competence m : meun) {
				List<T0003_Ad_Competence> sonList = bs.queryForList("select_s_menu", m);
				if (sonList != null) {
					m.setSeList(sonList);
				}
				allList.add(m);
			}
			request.setAttribute("allList", allList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSq";
	}
    /**
     * 权限的回显
     * @return
     * @throws IOException
     */
	public String checkRole() throws IOException {
		plists = bs.queryForList("select_qxian", role);
		arr = JSONArray.fromObject(plists);
		response.getWriter().write(arr.toString());
		return null;
	}

	/**
	 * 授权
	 * 
	 * @return
	 */
	public String sqRole() {
		try {
			List<T0005_Ad_Comprole> menuList = new ArrayList<T0005_Ad_Comprole>();
			String menuId = power.getCompetence_id();
			String[] id = menuId.split(",");
			for (int i = 0; i < id.length; i++) {
				T0005_Ad_Comprole po = new T0005_Ad_Comprole();
				po.setCompetence_id(id[i].trim());
				po.setRole_id(role.getId());
				menuList.add(po);
			}
			bs.delete("qxian_delete", role);
			bs.saveAll("quan_insert", menuList);
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]授权成功,操作角色名为["+role.getTitle()+"],权限为：["+menuList.toString()+"]");
			ServletActionContext.getRequest().setAttribute("msg", "授权成功!");
		} catch (Exception e) {
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]授权失败");
			ServletActionContext.getRequest().setAttribute("msg", "授权失败!");
		}
		return "sqAdd";
	}

	/**
	 * 验证角色名是否存在
	 * 
	 * @throws IOException
	 */
	public String addYz() throws IOException {
		role.setTitle(rolename);
		list = bs.queryForList("select_role_loginname", role);
		String result = "false";
		if (list.size() == 0) {
			result = "true";
		} else {
			result = "false";
		}
		response.getWriter().println(result);
		response.getWriter().close();
		return null;
	}
	
	/**
	 * 角色详细信息
	 * @return
	 */
	public String roleDetails(){
		T0002_Ad_Role r = new T0002_Ad_Role();
		r.setTitle(id);
		role = (T0002_Ad_Role) bs.queryForList("role_select",r).get(0); 
		return "toDetailsRole";
	}
	
	public BaseService getBs() {
		return bs;
	}

	public void setBs(BaseService bs) {
		this.bs = bs;
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






	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getModifySuc() {
		return modifySuc;
	}

	public void setModifySuc(Boolean modifySuc) {
		this.modifySuc = modifySuc;
	}



	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
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

	public List<T0002_Ad_Role> getList() {
		return list;
	}

	public void setList(List<T0002_Ad_Role> list) {
		this.list = list;
	}

	public T0002_Ad_Role getRole() {
		return role;
	}

	public void setRole(T0002_Ad_Role role) {
		this.role = role;
	}

	public List<T0002_Ad_Role> getRlist() {
		return rlist;
	}

	public void setRlist(List<T0002_Ad_Role> rlist) {
		this.rlist = rlist;
	}

	public T1000_Users getUser() {
		return user;
	}

	public void setUser(T1000_Users user) {
		this.user = user;
	}



	public T0005_Ad_Comprole getPower() {
		return power;
	}

	public void setPower(T0005_Ad_Comprole power) {
		this.power = power;
	}

	public T1001_Menu getMenu() {
		return menu;
	}

	public void setMenu(T1001_Menu menu) {
		this.menu = menu;
	}





	public List<T0005_Ad_Comprole> getPlists() {
		return plists;
	}

	public void setPlists(List<T0005_Ad_Comprole> plists) {
		this.plists = plists;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public JSONArray getArr() {
		return arr;
	}

	public void setArr(JSONArray arr) {
		this.arr = arr;
	}

}
