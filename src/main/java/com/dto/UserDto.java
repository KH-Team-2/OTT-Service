package com.dto;

import java.util.Date;

public class UserDto {
	private int UserNum;
	private String ID;
	private String PW;
	private String Email;
	private String Phone;
	private String Name;
	private Date Birth;
	private String Gender;
	private String NickName;
	private String ImgURL;
	private String Status;
	private String Grade;
	private Date UserDate;
	
	public UserDto(int userNum, String iD, String pW, String email, String phone, String name, Date birth,
			String gender, String nickName, String imgURL, String status, String grade, Date userDate) {
		super();
		UserNum = userNum;
		ID = iD;
		PW = pW;
		Email = email;
		Phone = phone;
		Name = name;
		Birth = birth;
		Gender = gender;
		NickName = nickName;
		ImgURL = imgURL;
		Status = status;
		Grade = grade;
		UserDate = userDate;
	}
	public int getUserNum() {
		return UserNum;
	}

	public void setUserNum(int userNum) {
		UserNum = userNum;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getBirth() {
		return Birth;
	}

	public void setBirth(Date birth) {
		Birth = birth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getImgURL() {
		return ImgURL;
	}

	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public Date getUserDate() {
		return UserDate;
	}

	public void setUserDate(Date userDate) {
		UserDate = userDate;
	}
}