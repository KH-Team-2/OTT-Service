package com.dto;

public class TvingDto {
	

	private int MovieNum;
	private String Title;
    private String Year;
    private String Director;
    private String Actor;
    private double Rate = (Math.random()*(7-3))+3;
    private String Genre;
    private String Summary;
    private String Contenturl;
    private String MovieImg;
	
    public TvingDto() {
    	super();
    }

    public TvingDto(int movieNum, String title, String year, String director, String actor, double rate, String genre,
			String summary, String contenturl, String movieImg) {
		super();
		MovieNum = movieNum;
		Title = title;
		Year = year;
		Director = director;
		Actor = actor;
		Rate = rate;
		Genre = genre;
		Summary = summary;
		Contenturl = contenturl;
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

	public String getContenturl() {
		return Contenturl;
	}

	public void setContenturl(String contenturl) {
		Contenturl = contenturl;
	}

	public String getMovieImg() {
		return MovieImg;
	}

	public void setMovieImg(String movieImg) {
		MovieImg = movieImg;
	}
}
