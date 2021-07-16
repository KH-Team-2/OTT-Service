package com.dto;

public class TvingDramaDto {
	private int DramaNum;
	private String Title;
	private String Year;
	private String Actor;
	private double Rate;
	private String Genere;
	private String Summary;
	private String DramaImg;

	public TvingDramaDto() {
		
	}
	
	public TvingDramaDto(int dramaNum, String title, String year, String actor, double rate, String genere,
			String summary, String dramaImg) {
		super();
		DramaNum = dramaNum;
		Title = title;
		Year = year;
		Actor = actor;
		Rate = rate;
		Genere = genere;
		Summary = summary;
		DramaImg = dramaImg;
	}
	
	public int getDramaNum() {
		return DramaNum;
	}

	public void setDramaNum(int dramaNum) {
		DramaNum = dramaNum;
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

	public String getDramaImg() {
		return DramaImg;
	}

	public void setDramaImg(String dramaImg) {
		DramaImg = dramaImg;
	}

}
