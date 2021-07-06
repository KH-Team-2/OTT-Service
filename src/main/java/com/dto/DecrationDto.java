package com.dto;

public class DecrationDto {
	private int ReviewNum;
	private String Name;
	private String ReviewInfo;
	private int Count;
	
	public DecrationDto() {
		super();
	}

	public DecrationDto(int reviewNum, String name, String reviewInfo, int count) {
		super();
		ReviewNum = reviewNum;
		Name = name;
		ReviewInfo = reviewInfo;
		Count = count;
	}

	public int getReviewNum() {
		return ReviewNum;
	}

	public void setReviewNum(int reviewNum) {
		ReviewNum = reviewNum;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getReviewInfo() {
		return ReviewInfo;
	}

	public void setReviewInfo(String reviewInfo) {
		ReviewInfo = reviewInfo;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}
	
}
