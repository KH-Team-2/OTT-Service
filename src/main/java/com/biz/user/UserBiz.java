package com.biz.user;

import com.dto.UserDto;

public interface UserBiz {
	public boolean CreateAccount(UserDto dto);
	public UserDto Login(String id, String pw);
	public boolean UpdateInfo(UserDto dto);
	public String SearchID(String name, String email, String phone);
	public String SearchPW(String ID, String name, String email, String code);
	public boolean SendEmailCode(String ID, String name, String email);
	public boolean UserDel(int usernum);
	public boolean IDCheck(String id);
	public UserDto selectOne(int userNum);
	public boolean ChangePW(String id, String pw);
}
