package cn.cinema.manage.action.filmmanage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.action.cmanage.Cmanage;
import cn.cinema.manage.entity.filmmanage.T0112_GENREMESSAGE;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;
/**
 * 影片管理
 * @author zhaochunyu
 *
 */
public class Filmtypemanage {
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
 /**
  * 删除主键
  */
 private Integer id;
 /**
  * 判断删除是否成功
  */
 private Boolean flag;
/**
  * 影院管理实体
  */
 public T0112_GENREMESSAGE genremessage;
 public T0112_GENREMESSAGE getGenremessage()
{
    return genremessage;
}
public void setGenremessage(T0112_GENREMESSAGE genremessage)
{
    this.genremessage = genremessage;
}
public Integer getPageSize()
{
    return pageSize;
}
public void setPageSize(Integer pageSize)
{
    this.pageSize = pageSize;
}
public Integer getPageNo()
{
    return pageNo;
}
public void setPageNo(Integer pageNo)
{
    this.pageNo = pageNo;
}
public Integer getId()
{
    return id;
}
public void setId(Integer id)
{
    this.id = id;
}
public Boolean getFlag()
{
    return flag;
}
public void setFlag(Boolean flag)
{
    this.flag = flag;
}
/**
  * request对象
  */
 private HttpServletRequest request = ServletActionContext.getRequest();
 private HttpServletResponse response = ServletActionContext.getResponse();
 private HttpSession session = request.getSession();
 /**
  * 电影分类管理
  * @return
  */
 public String queryfilmtypemanage(){
     PageList filmtypelist=bs.findPage("select_genremessage", "select_genremessagecount", genremessage, pageNo, pageSize);
     SessionUtil.setPageList(request, filmtypelist);
     request.setAttribute("filmtypelist", filmtypelist);
     return "queryfilmtypemanage";
 }
 /**
  * 添加电影分类
  * @return
  */
 public String savefilmtype(){
	 try{
		 bs.save("insert_genremessage", genremessage);
		 request.setAttribute("msg", "添加成功!");
			logger.info("用户id"+SessionUtil.getEmp(session).getId()+"添加电影分类成功");	
			this.flag=true;
	 }catch(Exception e){
		 request.setAttribute("msg", "添加失败!");
			logger.error("用户id"+SessionUtil.getEmp(session).getId()+"添加电影分类失败，异常："+e.getMessage());
			this.flag=false;
	 }
	 
	 return "savefilmtype";
 }
 /**
  * 删除电影分类
  */
 public String delfilmtype(){
     try{
         bs.delete("del_filmtype", this.id);
         logger.info("用户id"+SessionUtil.getEmp(session).getId()+"删除电影分类成功");
         this.flag=true;
     }catch (Exception e) {
         logger.error("用户id"+SessionUtil.getEmp(session).getId()+"删除电影分类失败，异常："+e.getMessage());
         this.flag=false;
    }
     return "delfilmtype";
 }
 /**
  * 查看电影分类明细
  * @return editfilmtype
  */
 public String editfilmtype(){
	 genremessage=(T0112_GENREMESSAGE)bs.queryForObject("edit_filmtype", this.id);
	 request.setAttribute("genremessage", genremessage);
     return "editfilmtype";
 }
 /**
  * 执行电影分类修改
  * @return exCmanage.
  */
 public String updatefilmtype(){
	 try {
		 bs.update("update_filmtype", genremessage);
		 request.setAttribute("msg", "修改成功!");
		 logger.info("用户id"+SessionUtil.getEmp(session).getId()+"修改电影分类成功");
	} catch (Exception e) {
		// TODO: handle exception
		request.setAttribute("msg", "修改失败!");
		logger.error("用户id"+SessionUtil.getEmp(session).getId()+"删除电影分类失败，异常："+e.getMessage());
	}
	return "updatefilmtype";
 }
 public String queryfilmtypemanage1(){
     List list=(List)bs.queryForList("select_filmtypee");
     request.setAttribute("list", list);
     return "queryfilmtypemanage1";
 }
}
