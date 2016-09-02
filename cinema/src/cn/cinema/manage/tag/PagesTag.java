package cn.cinema.manage.tag;
/**
 * 分页标签.
 * chenbl.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import cn.cinema.manage.util.PageList;
import cn.cinema.manage.util.SessionUtil;

import com.opensymphony.xwork2.util.ValueStack;

public class PagesTag extends ComponentTagSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 7687566759629395513L;
    /**
     * 查询form方法.
     */
    private String formId;
    /**
     * 样式名.
     */
    private String className;
    
    /**
     * 分页对象.
     */
    private PageList pageList;
    public String getFormId()
    {
        return formId;
    }

    public void setFormId(String formId)
    {
        this.formId = formId;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    @Override
    public Component getBean(ValueStack valueStack, HttpServletRequest req,
        HttpServletResponse res)
    {
       //从request中获得pageList 对象. 
        pageList = SessionUtil.getPageList(req);
        
      // System.out.println(pageList.getBegin()+"---"+pageList.getEnd()+"----"+pageList.getNumPerPage());
        // TODO Auto-generated method stub
        
        return new PagesComponent(valueStack);
    }
    /**
     * 
     */
    protected void populateParams() {
     super.populateParams();
     PagesComponent page = (PagesComponent) component;
     page.setPageList(pageList);
     page.setFormId(formId);
     page.setClassName(className);
    }
}
