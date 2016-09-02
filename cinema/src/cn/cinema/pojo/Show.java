package cn.cinema.pojo;

/**
 * Show排期
 * 
 * @author tangfx. 
 * 
 */
public class Show {
//	<show hallId="4" seqNo="086085348" showSeqNo="1" date="2012-11-19"
//		time="1025" updateLevel="0" updateType="I">
//		<film id="00110340201201" name="寒战" language="国语" title="寒战"
//			duration="105" imax="0" dimensional="2D" />
//		<price lowest="0">
//			<section id="01" standard="50" name="普通区" />
//		</price>
//		<through flag="N"></through>
//	</show>
	private String hallId;		
	private String seqNo;		
	private String showSeqNo;		
	private String date;		
	private String time;		
	private String updateLevel;		
	private String updateType;		
	private String film_id;		
	private String film_name;		
	private String film_language;		
	private String film_title;		
	private String film_duration;		
	private String film_imax;		
	private String film_dimensional;
	private String price_section_id;
	private String price_section_standard;
	private String price_section_name;
	private String price_dimensional;
	private String flag;
	
	public String getPrice_section_id() {
		return price_section_id;
	}
	public void setPrice_section_id(String priceSectionId) {
		price_section_id = priceSectionId;
	}
	public String getPrice_section_standard() {
		return price_section_standard;
	}
	public void setPrice_section_standard(String priceSectionStandard) {
		price_section_standard = priceSectionStandard;
	}
	public String getPrice_section_name() {
		return price_section_name;
	}
	public void setPrice_section_name(String priceSectionName) {
		price_section_name = priceSectionName;
	}
	public String getPrice_dimensional() {
		return price_dimensional;
	}
	public void setPrice_dimensional(String priceDimensional) {
		price_dimensional = priceDimensional;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getShowSeqNo() {
		return showSeqNo;
	}
	public void setShowSeqNo(String showSeqNo) {
		this.showSeqNo = showSeqNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUpdateLevel() {
		return updateLevel;
	}
	public void setUpdateLevel(String updateLevel) {
		this.updateLevel = updateLevel;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getFilm_id() {
		return film_id;
	}
	public void setFilm_id(String filmId) {
		film_id = filmId;
	}
	public String getFilm_name() {
		return film_name;
	}
	public void setFilm_name(String filmName) {
		film_name = filmName;
	}
	public String getFilm_language() {
		return film_language;
	}
	public void setFilm_language(String filmLanguage) {
		film_language = filmLanguage;
	}
	public String getFilm_title() {
		return film_title;
	}
	public void setFilm_title(String filmTitle) {
		film_title = filmTitle;
	}
	public String getFilm_duration() {
		return film_duration;
	}
	public void setFilm_duration(String filmDuration) {
		film_duration = filmDuration;
	}
	public String getFilm_imax() {
		return film_imax;
	}
	public void setFilm_imax(String filmImax) {
		film_imax = filmImax;
	}
	public String getFilm_dimensional() {
		return film_dimensional;
	}
	public void setFilm_dimensional(String filmDimensional) {
		film_dimensional = filmDimensional;
	}	
}
