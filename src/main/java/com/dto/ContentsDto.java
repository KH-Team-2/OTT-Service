package com.dto;

import java.util.Date;

public class ContentsDto {
	
	private int MovieNum;
	private String Title;
	private String OpenYear;
	private String Director;
	private String Actor;
	private Double Rate;
	private String Genre;
	private String Summary;
	private String MovieAddr;
	private Date UpdateYear;
	private String MovieImg;
	private String pfimgurl;

	public ContentsDto() {
	}

	public ContentsDto(int movieNum, String title, String openYear, String director, String actor, Double rate, String genre, String summary, String movieAddr, Date updateYear, String movieImg) {
		MovieNum = movieNum;
		Title = title;
		OpenYear = openYear;
		Director = director;
		Actor = actor;
		Rate = rate;
		Genre = genre;
		Summary = summary;
		MovieAddr = movieAddr;
		UpdateYear = updateYear;
		MovieImg = movieImg;
	}

	public ContentsDto(int movieNum, String title, String openYear, String director, String actor, Double rate, String genre, String summary, String movieAddr, Date updateYear, String movieImg, String pfimgurl) {
		MovieNum = movieNum;
		Title = title;
		OpenYear = openYear;
		Director = director;
		Actor = actor;
		Rate = rate;
		Genre = genre;
		Summary = summary;
		MovieAddr = movieAddr;
		UpdateYear = updateYear;
		MovieImg = movieImg;
		this.pfimgurl = pfimgurl;
	}

	public String getPfimgurl() {
		return pfimgurl;
	}

	public void setPfimgurl(String pfimgurl) {
		this.pfimgurl = pfimgurl;
	}

	public int getMovieNum() {
		return MovieNum;
	}

	public void setMovieNum(int movieNum) {
		MovieNum = movieNum;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getOpenYear() {
		return OpenYear;
	}

	public void setOpenYear(String openYear) {
		OpenYear = openYear;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getActor() {
		return Actor;
	}

	public void setActor(String actor) {
		Actor = actor;
	}

	public Double getRate() {
		return Rate;
	}

	public void setRate(Double rate) {
		Rate = rate;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getMovieAddr() {
		return MovieAddr;
	}

	public void setMovieAddr(String movieAddr) {
		MovieAddr = movieAddr;
	}

	public Date getUpdateYear() {
		return UpdateYear;
	}

	public void setUpdateYear(Date updateYear) {
		UpdateYear = updateYear;
	}

	public String getMovieImg() {
		return MovieImg;
	}

	public void setMovieImg(String movieImg) {
		MovieImg = movieImg;
	}
}