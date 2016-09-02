package cn.cinema.manage.entity.filmmanage;

import java.util.Date;

public class T0003_AD_COMPETENCE
{
 private Integer id;
 private Integer father;
 private String title;
 private String link;
 private Date addtime;
 private Integer state;
 private Integer logo;
 private Integer recommended;
public Integer getId()
{
    return id;
}
public void setId(Integer id)
{
    this.id = id;
}
public Integer getFather()
{
    return father;
}
public void setFather(Integer father)
{
    this.father = father;
}
public String getTitle()
{
    return title;
}
public void setTitle(String title)
{
    this.title = title;
}
public String getLink()
{
    return link;
}
public void setLink(String link)
{
    this.link = link;
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
public Integer getLogo()
{
    return logo;
}
public void setLogo(Integer logo)
{
    this.logo = logo;
}
public Integer getRecommended()
{
    return recommended;
}
public void setRecommended(Integer recommended)
{
    this.recommended = recommended;
}
}
