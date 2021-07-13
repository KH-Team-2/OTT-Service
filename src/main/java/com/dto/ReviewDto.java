package com.dto;

import java.util.Date;

public class ReviewDto {

	private int rnum;
	private int ReviewNum;
	private int UserNum;
	private int MovieNum;
	private String ReviewInfo;
	private Date Date;
	private int Count;
	private String NickName;
	
	public ReviewDto() { super(); }

	public ReviewDto(int rnum, int reviewNum, int userNum, int movieNum, String reviewInfo, java.util.Date date, int count, String nickName) {
		this.rnum = rnum;
		ReviewNum = reviewNum;
		UserNum = userNum;
		MovieNum = movieNum;
		ReviewInfo = reviewInfo;
		Date = date;
		Count = count;
		NickName = nickName;
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

	public ReviewDto(int reviewNum, String reviewInfo) {
		ReviewNum = reviewNum;
		ReviewInfo = reviewInfo;
	}

	public ReviewDto(int reviewNum, int userNum, int movieNum, String reviewInfo, java.util.Date date, int count, String nickName) {
		ReviewNum = reviewNum;
		UserNum = userNum;
		MovieNum = movieNum;
		ReviewInfo = reviewInfo;
		Date = date;
		Count = count;
		NickName = nickName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
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
