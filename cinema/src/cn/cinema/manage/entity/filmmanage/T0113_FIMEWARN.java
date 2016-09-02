package cn.cinema.manage.entity.filmmanage;

import java.util.Date;

public class T0113_FIMEWARN
{
    private Integer id;
    private Integer film_d;
    private String warnpic;
    private Integer warnno;
    private Integer warntype;
    private String warndesc;
    private Date addtime;
    private Integer state;
    private String prepic;
    private String warnpic_cut;
    
    
    
    public String getWarnpic_cut() {
		return warnpic_cut;
	}
	public void setWarnpic_cut(String warnpicCut) {
		warnpic_cut = warnpicCut;
	}
	public String getPrepic()
    {
        return prepic;
    }
    public void setPrepic(String prepic)
    {
        this.prepic = prepic;
    }
    public Integer getFilm_d()
    {
        return film_d;
    }
    public void setFilm_d(Integer filmD)
    {
        film_d = filmD;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getWarnpic()
    {
        return warnpic;
    }
    public void setWarnpic(String warnpic)
    {
        this.warnpic = warnpic;
    }
    public Integer getWarnno()
    {
        return warnno;
    }
    public void setWarnno(Integer warnno)
    {
        this.warnno = warnno;
    }
    public Integer getWarntype()
    {
        return warntype;
    }
    public void setWarntype(Integer warntype)
    {
        this.warntype = warntype;
    }
    public String getWarndesc()
    {
        return warndesc;
    }
    public void setWarndesc(String warndesc)
    {
        this.warndesc = warndesc;
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
