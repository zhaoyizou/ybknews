package com.visionet.platform.cooperation.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class JPushDto {

	private List<String> des;
	private String alert;
	private JSONObject content;
	private long timeToLive;
	private int type;
	private int pushType;
	private int userType;

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getPushType() {
		return pushType;
	}

	public void setPushType(int pushType) {
		this.pushType = pushType;
	}

	public List<String> getDes() {
		return des;
	}

	public void setDes(List<String> des) {
		this.des = des;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}

	public long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "JPushDto [des=" + des + ", alert=" + alert + ", content=" + content + ", timeToLive=" + timeToLive
				+ ", type=" + type + ", pushType=" + pushType + ", userType=" + userType + "]";
	}

}
