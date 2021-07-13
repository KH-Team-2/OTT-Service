package com.dto;

import java.sql.Date;

public class WishDto {
	private int wishnum;
	private int usernum;
	private String title;
	private String movieaddr;
	private Date wishdate;
	private String alarm;
	
	public WishDto() {
		super();
	}

	public WishDto(int wishnum, int usernum, String title, String movieaddr, Date wishdate, String alarm) {
		super();
		this.wishnum = wishnum;
		this.usernum = usernum;
		this.title = title;
		this.movieaddr = movieaddr;
		this.wishdate = wishdate;
		this.alarm = alarm;
	}

	public int getWishnum() {
		return wishnum;
	}

	public void setWishnum(int wishnum) {
		this.wishnum = wishnum;
	}

	public int getUsernum() {
		return usernum;
	}

	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMovieaddr() {
		return movieaddr;
	}

	public void setMovieaddr(String movieaddr) {
		this.movieaddr = movieaddr;
	}

	public Date getWishdate() {
		return wishdate;
	}

	public void setWishdate(Date wishdate) {
		this.wishdate = wishdate;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	
	
	
	
	
}
