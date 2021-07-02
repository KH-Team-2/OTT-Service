
package com.dto;

public class PlatformCodeDto {
	
	// Test
	
	private String PFCode;
	private String PFName;
	private String PFAddr;
	private String PFIconAddr;
	
	public PlatformCodeDto(String pFCode, String pFName, String pFAddr, String pFIconAddr) {
		super();
		PFCode = pFCode;
		PFName = pFName;
		PFAddr = pFAddr;
		PFIconAddr = pFIconAddr;
	}
	
	public String getPFCode() {
		return PFCode;
	}
	public void setPFCode(String pFCode) {
		PFCode = pFCode;
	}
	public String getPFName() {
		return PFName;
	}
	public void setPFName(String pFName) {
		PFName = pFName;
	}
	public String getPFAddr() {
		return PFAddr;
	}
	public void setPFAddr(String pFAddr) {
		PFAddr = pFAddr;
	}
	public String getPFIconAddr() {
		return PFIconAddr;
	}
	public void setPFIconAddr(String pFIconAddr) {
		PFIconAddr = pFIconAddr;
	}
	
}