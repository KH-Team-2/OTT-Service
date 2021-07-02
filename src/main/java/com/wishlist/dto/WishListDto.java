package com.wishlist.dto;

import java.util.Date;

public class WishListDto {
	
	private int WishNium;
	private int UserNum;
	private int MovieNum;
	private Date Date;
	private String Alarm;
	
	public WishListDto() {
		super();
	}

	public WishListDto(int wishNium, int userNum, int movieNum, java.util.Date date, String alarm) {
		super();
		WishNium = wishNium;
		UserNum = userNum;
		MovieNum = movieNum;
		Date = date;
		Alarm = alarm;
	}

	public int getWishNium() {
		return WishNium;
	}

	public void setWishNium(int wishNium) {
		WishNium = wishNium;
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

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getAlarm() {
		return Alarm;
	}

	public void setAlarm(String alarm) {
		Alarm = alarm;
	}
}
