package cn.cinema.manage.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 业务字典TAG
 * 
 * @author chenbl.
 * 
 */
public class DictTag extends ComponentTagSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * pojo 名.
     */
    private String pojoName = "";
    /**
     * 显示列名.
     */
    private String showColName = "";
    /**
     * 值列名.
     */
    private String hiddColName = "";
    /**
     * defValue 默认选中值.
     */
    private String defValue = "";
    /**
     * 查询条件.
     */
    private String where = "";
    /**
     * 控件类型.
     */
    private String type = "";
    /**
     * 名称.
     */
    private String name = "";
    /**
     * 是否可用.
     */
    private String disabled = "";
    /**
     * 样式名.
     */
    private String className = "";
    /**
     * JS 调用方法.
     */
    private String func = "";
    /**
     * 是否只为输出.
     */
    private String outPrint="";
    /**
     * 固定选项.
     */
    private String fixedOption="";
 
    
    public String getOutPrint()
    {
        return outPrint;
    }


    public void setOutPrint(String outPrint)
    {
        this.outPrint = outPrint;
    }


    public String getFunc()
    {
        return func;
    }


    public void setFunc(String func)
    {
        this.func = func;
    }


    public String getPojoName()
    {
        return pojoName;
    }


    public void setPojoName(String pojoName)
    {
        this.pojoName = pojoName;
    }


    public String getShowColName()
    {
        return showColName;
    }


    public void setShowColName(String showColName)
    {
        this.showColName = showColName;
    }


    public String getHiddColName()
    {
        return hiddColName;
    }


    public void setHiddColName(String hiddColName)
    {
        this.hiddColName = hiddColName;
    }


    public String getDefValue()
    {
        return defValue;
    }


    public void setDefValue(String defValue)
    {
        this.defValue = defValue;
    }


    public String getWhere()
    {
        return where;
    }


    public void setWhere(String where)
    {
        this.where = where;
    }


    public String getType()
    {
        return type;
    }


    public void setType(String type)
    {
        this.type = type;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getDisabled()
    {
        return disabled;
    }


    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
    }


    public String getClassName()
    {
        return className;
    }


    public void setClassName(String className)
    {
        this.className = className;
    }
    

    public String getFixedOption()
    {
        return fixedOption;
    }


    public void setFixedOption(String fixedOption)
    {
        this.fixedOption = fixedOption;
    }


    @Override
    public Component getBean(ValueStack arg0, HttpServletRequest arg1,
        HttpServletResponse arg2)
    {
        // TODO Auto-generated method stub
        return new DictComponent(arg0);
    }
    

    /**
     * 
     */
    protected void populateParams()
    {
        super.populateParams();
        DictComponent fun = (DictComponent) component;
        fun.setClassName(className);
        fun.setDefValue(defValue);
        fun.setDisabled(disabled);
        fun.setFunc(func);
        fun.setHiddColName(hiddColName);
        fun.setShowColName(showColName);
        fun.setPojoName(pojoName);
        fun.setName(name);
        fun.setType(type);
        fun.setWhere(where);
        fun.setOutPrint(outPrint);
        fun.setFixedOption(fixedOption);
    }
}
