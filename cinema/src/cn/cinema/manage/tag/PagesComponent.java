package cn.cinema.manage.tag;

/**
 * 分页标签.
 */
import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.Component;

import cn.cinema.manage.util.PageList;

import com.opensymphony.xwork2.util.ValueStack;

public class PagesComponent extends Component
{
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
    /**
     * strbuf;
     */
    private StringBuffer sb = new StringBuffer();


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


    public PageList getPageList()
    {
        return pageList;
    }


    public void setPageList(PageList pageList)
    {
        this.pageList = pageList;
    }


    public PagesComponent(ValueStack stack)
    {

        super(stack);
        // TODO Auto-generated constructor stub
    }


    // 将自己需要输出的逻辑通过writer输出字符串就可以了。
    public boolean start(Writer writer)
    {
        boolean result = super.start(writer);
      
        try
        {
            sb.append("<span><b>").append(pageList.getPage()).append("/").append(
                pageList.getPagesum()).append("总记录数:").append(
                pageList.getObjectsum()).append("</b></span>");
            
            
            sb.append("&nbsp;<select  name=\"pageSize\" onChange=\"chagePerNumPage(this,'"+formId+"')\">");
            
                switch(Integer.parseInt(pageList.getNumPerPage()+"")){
                    case 10:
                        sb.append("<option value='10' selected='true'>每页10条</option>");
                        sb.append("<option value='20'>每页20条</option>");
                        sb.append("<option value='50'>每页50条</option>");
                        sb.append("<option value='100'>每页100条</option>");
                        break;
                    case 20:
                        sb.append("<option value='10'>每页10条</option>");
                        sb.append("<option value='20' selected='true'>每页20条</option>");
                        sb.append("<option value='50'>每页50条</option>");
                        sb.append("<option value='100'>每页100条</option>");
                        break;
                    case 50:
                        sb.append("<option value='10'>每页10条</option>");
                        sb.append("<option value='20'>每页20条</option>");
                        sb.append("<option value='50' selected='true'>每页50条</option>");
                        sb.append("<option value='100'>每页100条</option>");
                        break;
                    case 100: 
                        sb.append("<option value='10'>每页10条</option>");
                        sb.append("<option value='20'>每页20条</option>");
                        sb.append("<option value='50'>每页50条</option>");
                        sb.append("<option value='100' selected='true'>每页100条</option>");
                        break;
                    
                }
                
                
                
                
            sb.append("</select>");
           
            // 如果当前页数为0,1
            // 则首页 ,第一页不可用.
            if (pageList.getPage() == 0 || pageList.getPage() == 1)
            {
                sb.append("&nbsp;&nbsp;<input type='button' value='首页' class='").append(
                    className).append("'  disabled='disabled'/>");
                sb.append("&nbsp;&nbsp;<input type='button' value='上一页' class='").append(
                    className).append("' disabled='disabled'/>");
            }
            else
            {
                sb.append("&nbsp;&nbsp;<input type='button' value='首页' class='").append(
                    className).append("' onClick=\"").append("gotoPage('1','"+formId+"')").append(" \"/>");
                sb.append("&nbsp;&nbsp;<input type='button' value='上一页' class='").append(
                    className).append("' onClick=\"").append("gotoPage('"+(pageList.getPage()-1)+"','"+formId+"')").append(" \"/>");
            }
            // 如果当前页等于最大页.
            if (pageList.getPage() == pageList.getPagesum())
            {
                sb.append("&nbsp;&nbsp;<input type='button' value='下一页' class='").append(
                    className).append("' onClick='' disabled='disabled'/>");
                sb.append("&nbsp;&nbsp;<input type='button' value='尾页' class='").append(
                    className).append("' onClick='' disabled='disabled'/>");
            }
            else
            {
                sb.append("&nbsp;&nbsp;<input type='button' value='下一页' class='").append(
                    className).append("' onClick=\"").append("gotoPage('"+(pageList.getPage()+1)+"','"+formId+"')").append(" \"/>");
                sb.append("&nbsp;&nbsp;<input type='button' value='尾页' class='").append(
                    className).append("' onClick=\"").append("gotoPage('"+pageList.getPagesum()+"','"+formId+"')").append(" \"/>");
            }
           
            writer.write(sb.toString());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}
