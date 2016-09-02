package cn.cinema.manage.entity.filmmanage;

import java.util.Date;

public class T0301_NEWS
{
    private Integer id;
    private Integer placeinfo_id;
    private String title;
    private String image;
    private String content;
    private String author;
    private String source;
    private Date addtime;
    private Integer state;
    private Integer recommend;
    private Integer type;
    private String filmnos;
    private Integer cityno;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getPlaceinfo_id()
    {
        return placeinfo_id;
    }
    public void setPlaceinfo_id(Integer placeinfoId)
    {
        placeinfo_id = placeinfoId;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getImage()
    {
        return image;
    }
    public void setImage(String image)
    {
        this.image = image;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public String getSource()
    {
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
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
    public Integer getRecommend()
    {
        return recommend;
    }
    public void setRecommend(Integer recommend)
    {
        this.recommend = recommend;
    }
    public Integer getType()
    {
        return type;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
    public String getFilmnos()
    {
        return filmnos;
    }
    public void setFilmnos(String filmnos)
    {
        this.filmnos = filmnos;
    }
    public Integer getCityno()
    {
        return cityno;
    }
    public void setCityno(Integer cityno)
    {
        this.cityno = cityno;
    }
}
