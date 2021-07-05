package com.dto;

public class FBWDto {

	private String FBWords;
	private String Reason;
	
	public FBWDto(String fBWords, String reason) {
		super();
		FBWords = fBWords;
		Reason = reason;
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
}