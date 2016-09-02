package cn.cinema.manage.action.sys;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.sys.T0001_Ad_User;
import cn.cinema.manage.entity.sys.T0002_Ad_Role;
import cn.cinema.manage.entity.sys.T0004_Ad_Userrole;
import cn.cinema.manage.entity.sys.T1000_Users;
import cn.cinema.manage.entity.sys.T1005_Userrole;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

public class User {
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * 日志操作
	 */
	private Logger logger = Logger.getLogger(User.class);
	/**
	 * 用户实体对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpSession session = request.getSession();
	private T0001_Ad_User users;
	private T0002_Ad_Role role ;
	private T1005_Userrole urole;
	private List<T0001_Ad_User> list;
	private List<T0002_Ad_Role> rlist;
	private List<T1005_Userrole> urlist;
	private String userid;
	private boolean modifySuc;
	private String roleid;
	private String loginname;
	private T1005_Userrole userRole;
	private JSONArray arr;
	private String password;
	private String id;
	 /**
	  * 页数pageSize
	  */
	 private Integer pageSize;
	 /**
	  * 每页数量
	  */
	 private Integer pageNo;
	 
    /**
     * 查询用户列表
     * @return
     */
	public String userList() {
		  if(!"".equals(users.getName())){
			  users.setName(users.getName().trim());
		  }
		  PageList list = (PageList) bs.findPage("user.selAll", "select_user_count", users, pageNo, pageSize);
		  SessionUtil.setPageList(request, list);
		  request.setAttribute("list", list);
		  return "success";
     }
	
	/**
	 * 跳转到添加也页面
	 */
	public String goAddUser(){
		rlist = bs.queryForList("role.select", rlist);
		
		return "goAdd";
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	public String addUser(){
		try{
		// 加密密码.
	  	Md5 md5 = new Md5();
		// 用户MD5加密后的字符串.
		String md5Str = md5.getMD5ofStr(CPO.userPassPri + users.getPassword());
		users.setPassword(md5Str);
		String uid = (String) bs.saveObject("user.insert", users);
		//为用户添加角色
//		String rold ="24";
		if(!"".equals(roleid)&&roleid!=null){
			//String roleId = role.getId();
			//String[] id = roleId.split(",");
			//for (int i = 0; i < id.length; i++) {
				T0004_Ad_Userrole ur = new T0004_Ad_Userrole();
				ur.setUser_id(uid);
				ur.setRole_id(roleid);
				bs.save("jus.insert", ur);	
			//}
		}
		logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]新增用户成功,操作用户名为：["+users.getName()+"]");
		ServletActionContext.getRequest().setAttribute("msg","添加成功!" );
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]新增用户失败，异常信息："+e.getMessage());
		ServletActionContext.getRequest().setAttribute("msg","添加失败!" );
		}
		
		return "add";
	}
	
	/**
	 * 根据用户id删除用户
	 * @return
	 */
	public String delUser(){
		
		try {
			T0001_Ad_User u = new T0001_Ad_User();
			u.setId(id);
			bs.delete("user.delete", u);
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]删除用户成功,操作用户ID为：["+id+"]");
			modifySuc=true;
		} catch (Exception e) {
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]删除用户失败，异常信息："+e.getMessage());
			modifySuc=false;
		}
		return "delete";
	}
	
	/**
	 * 跳转到修改也页面
	 */
	public String goUpdateUser(){
		rlist = bs.queryForList("role.select", rlist);
		users = (T0001_Ad_User) bs.queryForList("user.selectOne", userid).get(0);
		return "goUpdate";
	}
	/**
	 * 更新用户信息
	 * @return
	 */
	public String updateUser(){
		try{
			bs.delete("jse.delete", users);
			//为用户添加角色
			T0004_Ad_Userrole ur=new T0004_Ad_Userrole();
				ur.setUser_id(users.getId());
				ur.setRole_id(roleid);
				bs.save("jus.insert", ur);	
			if(password.equals(users.getPassword())){
				users.setPassword("");
			}else{
				// 加密密码.
			  	Md5 md5 = new Md5();
				// 用户MD5加密后的字符串.
				String md5Str = md5.getMD5ofStr(CPO.userPassPri + users.getPassword());
				users.setPassword(md5Str);
			}
			bs.save("user.update", users);
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]更新用户成功,操作用户名为：["+users.getName()+"]");
			ServletActionContext.getRequest().setAttribute("msg","更新成功!" );
			}catch (Exception e) {
			e.printStackTrace();
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]更新用户失败，异常信息："+e.getMessage());
			ServletActionContext.getRequest().setAttribute("msg","更新失败!" );
			}
		return "update";
	}
	
	/**
	 * 验证登录用户名是否存在
	 * @throws IOException 
	 */
	public String addYz() throws IOException{
	    
	    T0001_Ad_User user=new T0001_Ad_User();
	    if (id!=null)
        {
	        user.setId(id);
        }
	    
	    System.out.println(id+"id++++++++");
	    System.out.println(loginname+"name++++++++");
	    user.setName(loginname);
		list = bs.queryForList("select_user_loginname",user);
		String result = "false";
		if(list.size()==0){
			result = "true";
		}else{
			result = "false";
		}
		response.getWriter().write(result);
		response.getWriter().close();
		return null;
	}
	/**
	 * 用户详细信息
	 * @return
	 * @throws IOException 
	 */
	public String userDetails() throws IOException{
		T1000_Users u = new T1000_Users();
		u.setUserid(userid);
		rlist = bs.queryForList("role.select", rlist);
		users = (T0001_Ad_User) bs.queryForList("user.selAll",u).get(0); 
		return "toDetailsUser";
	}
	//checkbox的回显
	public String checkUser() throws IOException {
		T1005_Userrole ur = new T1005_Userrole();
		ur.setUserid(userid);
		urlist = bs.queryForList("select_jse", ur);
		arr = JSONArray.fromObject(urlist);
		response.getWriter().write(arr.toString());
		return null;
	}

	public BaseService getBs() {
		return bs;
	}


	public void setBs(BaseService bs) {
		this.bs = bs;
	}

	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean isModifySuc() {
		return modifySuc;
	}
	public void setModifySuc(boolean modifySuc) {
		this.modifySuc = modifySuc;
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





	public T1005_Userrole getUrole() {
		return urole;
	}

	public void setUrole(T1005_Userrole urole) {
		this.urole = urole;
	}




	public List<T0001_Ad_User> getList()
    {
        return list;
    }

    public void setList(List<T0001_Ad_User> list)
    {
        this.list = list;
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

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public T1005_Userrole getUserRole() {
		return userRole;
	}

	public void setUserRole(T1005_Userrole userRole) {
		this.userRole = userRole;
	}

	public List<T1005_Userrole> getUrlist() {
		return urlist;
	}

	public void setUrlist(List<T1005_Userrole> urlist) {
		this.urlist = urlist;
	}

	public JSONArray getArr() {
		return arr;
	}

	public void setArr(JSONArray arr) {
		this.arr = arr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public T0001_Ad_User getUsers() {
		return users;
	}

	public void setUsers(T0001_Ad_User users) {
		this.users = users;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
