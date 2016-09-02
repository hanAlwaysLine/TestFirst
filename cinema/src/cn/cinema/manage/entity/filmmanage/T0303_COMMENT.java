package cn.cinema.manage.entity.filmmanage;

import java.util.Date;

public class T0303_COMMENT
{
    private Integer id;
    private Integer type_id;
    private Integer page_id;
    private String username;
    private String user_id;
    private String title;
    private String content;
    private Date addtime;
    private Integer state;
    private String email;
    private Integer preferences;
    private Integer anonymous;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getType_id()
    {
        return type_id;
    }
    public void setType_id(Integer typeId)
    {
        type_id = typeId;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUser_id()
    {
        return user_id;
    }
    public void setUser_id(String userId)
    {
        user_id = userId;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
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
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public Integer getPreferences()
    {
        return preferences;
    }
    public void setPreferences(Integer preferences)
    {
        this.preferences = preferences;
    }
    public Integer getAnonymous()
    {
        return anonymous;
    }
    public void setAnonymous(Integer anonymous)
    {
        this.anonymous = anonymous;
    }
	public Integer getPage_id() {
		return page_id;
	}
	public void setPage_id(Integer pageId) {
		page_id = pageId;
	}
}
