package cn.cinema.manage.entity.advertisemanage;

@SuppressWarnings("serial")
public class T0406Picture implements java.io.Serializable {

	// Fields

	private Integer id;
	private String picturename; // 图片地址
	private String pictureurl; // 图片地址
	private String type; // 类型 1-巨幕 2-影厅 3-商品 4-票务
	private String htmltext; // 富文本
	private String url; // 图片跳转地址
	private String state;// 是否可用 0-可用 1-不可用
	private String memo;
	private String sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicturename() {
		return picturename;
	}

	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHtmltext() {
		return htmltext;
	}

	public void setHtmltext(String htmltext) {
		this.htmltext = htmltext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
