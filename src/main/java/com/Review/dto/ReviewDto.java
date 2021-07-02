package com.Review.dto;

import java.util.Date;

public class ReviewDto {
	private int ReviewNum;
	private int UserNum;
	private int MovieNum;
	private String ReviewInfo;
	private Date Date;
	private int Count;
	
	public ReviewDto() {
		super();
	}

	public ReviewDto(int reviewNum, int userNum, int movieNum, String reviewInfo, java.util.Date date, int count) {
		super();
		ReviewNum = reviewNum;
		UserNum = userNum;
		MovieNum = movieNum;
		ReviewInfo = reviewInfo;
		Date = date;
		Count = count;
	}

	public int getReviewNum() {
		return ReviewNum;
	}

	public void setReviewNum(int reviewNum) {
		ReviewNum = reviewNum;
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

	public String getReviewInfo() {
		return ReviewInfo;
	}

	public void setReviewInfo(String reviewInfo) {
		ReviewInfo = reviewInfo;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}
	
}
