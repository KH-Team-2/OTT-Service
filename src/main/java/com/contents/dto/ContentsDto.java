package com.contents.dto;

public class ContentsDto {
	private int MoivceNum;
	private String Title;
	private String Year;
	private String Director;
	private String Actor;
	private Double Rate;
	private String Plaform;
	private String Summary;
	
	public ContentsDto(int moivceNum, String title, String year, String director, String actor, Double rate,
			String plaform, String summary) {
		super();
		MoivceNum = moivceNum;
		Title = title;
		Year = year;
		Director = director;
		Actor = actor;
		Rate = rate;
		Plaform = plaform;
		Summary = summary;
	}
	public int getMoivceNum() {
		return MoivceNum;
	}

	public void setMoivceNum(int moivceNum) {
		MoivceNum = moivceNum;
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

	public Double getRate() {
		return Rate;
	}

	public void setRate(Double rate) {
		Rate = rate;
	}

	public String getPlaform() {
		return Plaform;
	}

	public void setPlaform(String plaform) {
		Plaform = plaform;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

}