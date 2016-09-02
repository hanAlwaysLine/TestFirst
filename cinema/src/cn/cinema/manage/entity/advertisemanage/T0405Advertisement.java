package cn.cinema.manage.entity.advertisemanage;

@SuppressWarnings("serial")
public class T0405Advertisement implements java.io.Serializable
{

    // Fields

    private Integer advertise_id;
    private Integer according_id;
    private Integer advertisement_type;
    private Integer placeinfo_id;
    private Integer position;
    private String  placename;
    private String  cityname;
    private String  cityno;
    private String  locale;
    
    public String getCityno()
    {
        return cityno;
    }
    public void setCityno(String cityno)
    {
        this.cityno = cityno;
    }
    public String getPlacename()
    {
        return placename;
    }
    public void setPlacename(String placename)
    {
        this.placename = placename;
    }
    public String getCityname()
    {
        return cityname;
    }
    public void setCityname(String cityname)
    {
        this.cityname = cityname;
    }
    public Integer getAdvertise_id()
    {
        return advertise_id;
    }
    public void setAdvertise_id(Integer advertiseId)
    {
        advertise_id = advertiseId;
    }
    
    public Integer getPosition()
    {
        return position;
    }
    public void setPosition(Integer position)
    {
        this.position = position;
    }
    public Integer getAccording_id()
    {
        return according_id;
    }
    public void setAccording_id(Integer accordingId)
    {
        according_id = accordingId;
    }
  
 
	public Integer getAdvertisement_type() {
		return advertisement_type;
	}
	public void setAdvertisement_type(Integer advertisementType) {
		advertisement_type = advertisementType;
	}
	public Integer getPlaceinfo_id()
    {
        return placeinfo_id;
    }
    public void setPlaceinfo_id(Integer placeinfoId)
    {
        placeinfo_id = placeinfoId;
    }
    public String getLocale()
    {
        return locale;
    }
    public void setLocale(String locale)
    {
        this.locale = locale;
    }
    
}
