package cn.cinema.manage.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.sys.T0001_Ad_User;
import cn.cinema.manage.entity.sys.T0002_Ad_Role;
import cn.cinema.manage.entity.sys.T0003_Ad_Competence;
import cn.cinema.manage.entity.sys.T1000_Users;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.Md5;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;

public class Login {
	/**
	 * 操作日志
	 */
	private Logger logger = Logger.getLogger(Login.class);
	/**
	 * 操作类
	 */
	private BaseService bs = ServiceLocator.getBaseService();
	/**
	 * 用户实体对象
	 */
	private T0001_Ad_User users;
	private T1000_Users user;
	private String name;
	public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    /**
	 * 登陆接收串
	 */
	private String userStr;
	/**
	 * json返回是否存在用户
	 */
	private Boolean isSuccess;
	/**
	 * json返回用户是否有权限
	 */
	private Boolean isLoginGrant;
	/**
	 * request对象
	 */
	private HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * session对象
	 */
	private HttpSession session = request.getSession();
	/**
	 * 登陆验证用户
	 * @return "success"
	 */
	/**
	 * 跳转到修改密码页
	 * by: cq20120817
	 */
	public String showuserpass()
	{
		users = SessionUtil.getEmp(session);
		return "showuserpassword";
	}
	
	/**
	 * 登陆者修改密码
	 * by: cq20120817
	 */
	
	public String pudateuserpass()
	{
	 	Md5 md5 = new Md5();
	 	T0001_Ad_User user=new T0001_Ad_User();
	 	user = SessionUtil.getEmp(session);
		
	 	String md5Str = md5.getMD5ofStr(CPO.userPassPri + users.getPassword());
		try {
			if(!md5Str.equals(user.getPassword()))
			{
				
				users.setPassword(md5Str);
				bs.update("login.update",users );
			}
			logger.info("用户id["+SessionUtil.getEmp(session).getId()+"]更新用户成功,操作用户名为：["+user.getName()+"]");
			ServletActionContext.getRequest().setAttribute("msg","更新成功!" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("用户id["+SessionUtil.getEmp(session).getId()+"]更新用户失败，异常信息："+e.getMessage());
			ServletActionContext.getRequest().setAttribute("msg","更新失败!" );
			e.printStackTrace();
		}
		
		return "showuserpassword";
	}
  public String login(){
	  JSONObject obj = JSONObject.fromObject(userStr);
	  users = (T0001_Ad_User) JSONObject.toBean(obj, T0001_Ad_User.class);
	// 加密密码.
	  	Md5 md5 = new Md5();
		// 用户MD5加密后的字符串.
	  String md5Str = md5.getMD5ofStr(CPO.userPassPri + users.getPassword());	
	  HashMap<String , String > map = new HashMap<String, String> ();
	  map.put("name", users.getName());
	  map.put("password", md5Str);
	  T0001_Ad_User newusers =   (T0001_Ad_User) bs.queryForObject("select_user", map);
	  if(newusers!=null){
		  session.setAttribute("usersname", newusers.getName());
		  session.setAttribute("users", newusers);
		  SessionUtil.setEmp(session, newusers);
		  logger.info("用户id:["+newusers.getId()+"]登陆系统");
		  this.isSuccess=true;
	  }else{
		  this.isSuccess=false;
	  }
	return "success";
  }
  /**
   * 验证权限
   * @return isLoginGrant
   */
  @SuppressWarnings("unchecked")
public String loginGrant(){
	  //存放所有一级二级目录
	  HashMap map = new HashMap();
	  List <T0003_Ad_Competence>allList = new ArrayList<T0003_Ad_Competence>();
	  users = (T0001_Ad_User) session.getAttribute("users");
	  List<T0002_Ad_Role> role=  bs.queryForList("select_grant", users);
	  if(role != null){
		 this.isLoginGrant=true;
		 String a = "''";
		 for(T0002_Ad_Role tr : role){
			 a+=",'',"+tr.getId();
		 }
		 for(T0002_Ad_Role r : role){
		 List <T0003_Ad_Competence>powerList=  bs.queryForList("select_menu", r);
		 for(T0003_Ad_Competence temp : powerList){
			temp.setRoleId(a);
			List <T0003_Ad_Competence>sonList =  bs.queryForList("select_menu_son", temp);
			if(sonList != null){
			temp.setSeList(sonList);
			}
			allList.add(temp);
		 }
		 }
		session.setAttribute("allList", allList);
		SessionUtil.setMenu(session, allList);
	  }else{
		  this.isLoginGrant=false;
	  }
	return "success";
  }
  
  public String loginAllGrant(){
	return userStr;
	  
  }
	public T1000_Users getUser() {
		return user;
	}
	public void setUser(T1000_Users user) {
		this.user = user;
	}
	public String getUserStr() {
		return userStr;
	}
	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Boolean getIsLoginGrant() {
		return isLoginGrant;
	}
	public void setIsLoginGrant(Boolean isLoginGrant) {
		this.isLoginGrant = isLoginGrant;
	}
	public T0001_Ad_User getUsers() {
		return users;
	}
	public void setUsers(T0001_Ad_User users) {
		this.users = users;
	}
	
  
}
