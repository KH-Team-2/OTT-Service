package com.alarm.dto;

import java.util.Date;

public class AlarmDto {
	private int AlarmNum;
	private int UserNum;
	private String Content;
	private String Status;
	private Date RegDate;
	
	public AlarmDto() {
		super();
	}

	public AlarmDto(int alarmNum, int userNum, String content, String status, Date regDate) {
		super();
		AlarmNum = alarmNum;
		UserNum = userNum;
		Content = content;
		Status = status;
		RegDate = regDate;
	}

	public int getAlarmNum() {
		return AlarmNum;
	}

	public void setAlarmNum(int alarmNum) {
		AlarmNum = alarmNum;
	}

	public int getUserNum() {
		return UserNum;
	}

	public void setUserNum(int userNum) {
		UserNum = userNum;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getRegDate() {
		return RegDate;
	}

	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}
	
	
	
	
	

}

