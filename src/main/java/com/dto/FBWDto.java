package com.dto;

public class FBWDto {



	private String FBWords;
	private String Reason;
	private int FBWordsNum;
	
	
	public FBWDto() {
		super();
	}
	public FBWDto(String fBWords, String reason, int fBWordsNum) {
		super();
		FBWords = fBWords;
		Reason = reason;
		FBWordsNum = fBWordsNum;
	}
	public String getFBWords() {
		return FBWords;
	}
	public void setFBWords(String fBWords) {
		FBWords = fBWords;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public int getFBWordsNum() {
		return FBWordsNum;
	}
	public void setFBWordsNum(int fBWordsNum) {
		FBWordsNum = fBWordsNum;
	}

}