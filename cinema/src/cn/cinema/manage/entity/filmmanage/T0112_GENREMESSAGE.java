package cn.cinema.manage.entity.filmmanage;

import java.util.Date;

public class T0112_GENREMESSAGE
{
    private Integer id; //主键
    private String title; //类型名
    private Date addtime; //时间
    private Integer state; //状态
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public Date getAddtime()
    {
        return addtime;
    }
    public void setAddtime(Date addtime)
    {
        this.addtime = addtime;
    }
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
}
