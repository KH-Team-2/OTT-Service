package com.dto;

import java.util.Date;

public class WHDto {
	private int HistoryNum;
	private int UserNum;
	private int MovieNum;
	private Date ViewDate;
	private String Status;
	
	public WHDto() {
		super();
	}

	public WHDto(int historyNum, int userNum, int movieNum, Date viewDate, String status) {
		super();
		HistoryNum = historyNum;
		UserNum = userNum;
		MovieNum = movieNum;
		ViewDate = viewDate;
		Status = status;
	}

	public int getHistoryNum() {
		return HistoryNum;
	}

	public void setHistoryNum(int historyNum) {
		HistoryNum = historyNum;
	}

	public int getUserNum() {
		return UserNum;
	}

	public void setUserNum(int userNum) {
		UserNum = userNum;
	}

	public int getMovieNum() {
		return MovieNum;
	}

	public void setMovieNum(int movieNum) {
		MovieNum = movieNum;
	}

	public Date getViewDate() {
		return ViewDate;
	}

	public void setViewDate(Date viewDate) {
		ViewDate = viewDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	

}
