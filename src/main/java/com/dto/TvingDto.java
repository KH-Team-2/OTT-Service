package com.dto;

public class TvingDto {
	
	private int MovieNum;
	private String Title;
	private String Year;
	private String Director;
	private String Actor;
	private double Rate;
	private String Genere;
	private String Summary;
	private String MovieImg;
	
	public TvingDto() {
		
	}
	public TvingDto(int movieNum, String title, String year, String director, String actor, double rate, String genere,
			String summary, String movieImg) {
		super();
		MovieNum = movieNum;
		Title = title;
		Year = year;
		Director = director;
		Actor = actor;
		Rate = rate;
		Genere = genere;
		Summary = summary;
		MovieImg = movieImg;
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
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
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
	public double getRate() {
		return Rate;
	}
	public void setRate(double rate) {
		Rate = rate;
	}
	public String getGenere() {
		return Genere;
	}
	public void setGenere(String genere) {
		Genere = genere;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public String getMovieImg() {
		return MovieImg;
	}
	public void setMovieImg(String movieImg) {
		MovieImg = movieImg;
	}


}
