package cn.cinema.manage.action.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.cinema.manage.entity.Activitymanage.T1000Job;
import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.Messages;
import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.ServiceLocator;
import cn.cinema.manage.util.SessionUtil;
/**
 * 活动管理
 *
 */
public class JobAction {
    /**
     * 操作日志
     */
    private Logger logger = Logger.getLogger(JobAction.class);
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
 private Messages messages = (Messages)ServiceLocator.getService("messages");
 
/**
  * 活动管理实体
  */
 public T1000Job job;
 
 public String jsonString;
 
public String getJsonString()
{
    return jsonString;
}
public void setJsonString(String jsonString)
{
    this.jsonString = jsonString;
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

public T1000Job getJob() {
	return job;
}
public void setJob(T1000Job job) {
	this.job = job;
}
public Boolean getModifySuc()
{
    return modifySuc;
}
public void setModifySuc(Boolean modifySuc)
{
    this.modifySuc = modifySuc;
}
public File getUploadimg()
{
    return uploadimg;
}
public void setUploadimg(File uploadimg)
{
    this.uploadimg = uploadimg;
}



public File getFile()
{
    return file;
}
public void setFile(File file)
{
    this.file = file;
}



/**
  * request对象
  */
 private HttpServletRequest request = ServletActionContext.getRequest();
 private HttpServletResponse response = ServletActionContext.getResponse();
 private HttpSession session = request.getSession();

 //查询列表
 public String jobList(){
         PageList joblist=bs.findPage("select_job", "select_jobcount", job, pageNo, pageSize);
         SessionUtil.setPageList(request, joblist);
         request.setAttribute("joblist", joblist); 
         return "queryjob";
 }
 
 /**
  * 跳转添加页面
  * @return
  */
 public String addjob(){
     return "addjob";
 }
 
 /**
  * 保存新增活动
  * @return
  */
 public String savejob(){

     try {
         bs.save("insert_job", job);
         request.setAttribute("msg", "添加成功!");
     } catch (Exception e) {
    	 e.printStackTrace();
         request.setAttribute("msg", "添加失败!");
     }
     return "update";
 }
//
///**
// * 根据用户id删除活动
// * @return
// */
public String delActivity() {
    try {
    	T1000Job t = new T1000Job();
        t.setJOB_ID(id);
        bs.delete("del_job", t);
        modifySuc = true;
    } catch (Exception e) {
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
    
	T1000Job t = new T1000Job();
    t.setJOB_ID(id);
    job=(T1000Job) bs.queryForObject("query_job",t);
    request.setAttribute("job", job);
    return "goedit";
    }

/**
 * 修改活动信息
 * @return
 */
public String updjob() {
        try {
            bs.update("update_job", job);
            request.setAttribute("msg", "修改招聘信息成功!");
    } catch (Exception e) {
        request.setAttribute("msg", "修改招聘信息失败!");
    }
    return "update";
}


}
