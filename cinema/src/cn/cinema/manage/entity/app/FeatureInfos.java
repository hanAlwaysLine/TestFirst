package cn.cinema.manage.entity.app;

import java.util.List;

import cn.cinema.manage.entity.manage.T0201_FEATURE_APP;

public class FeatureInfos {

	private String resultcode;
	private String resultmsg;
	private List<T0201_FEATURE_APP> featureinfo;
	
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public List<T0201_FEATURE_APP> getFeatureinfo() {
		return featureinfo;
	}
	public void setFeatureinfo(List<T0201_FEATURE_APP> featureinfo) {
		this.featureinfo = featureinfo;
	}
}
