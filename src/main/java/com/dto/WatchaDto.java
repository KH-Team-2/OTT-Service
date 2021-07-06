package com.dto;

public class WatchaDto {
    private int movienum;
    private String title;
    private String year;
    private String director;
    private String actor;
    private double rate;
    private String genre;
    private String summary;
    private String movieimg;

    public WatchaDto() {
    }

    public WatchaDto(int movienum, String title, String year, String director, String actor, double rate, String genre, String summary, String movieimg) {
        this.movienum = movienum;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actor = actor;
        this.rate = rate;
        this.genre = genre;
        this.summary = summary;
        this.movieimg = movieimg;
    }

    public int getMovienum() {
        return movienum;
    }

    public void setMovienum(int movienum) {
        this.movienum = movienum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMovieimg() {
        return movieimg;
    }

    public void setMovieimg(String movieimg) {
        this.movieimg = movieimg;
    }
}