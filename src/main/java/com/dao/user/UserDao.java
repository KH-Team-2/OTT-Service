package com.dao.user;

import java.sql.Connection;
import java.sql.Date;

import com.dto.UserDto;

public interface UserDao {
	
	String CreateAccountSQL = " INSERT INTO SP_USERTB VALUES(SAMPLE_USER_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,Y,?,SYSDATE ) ";
	String LoginSQL = " SELECT * FROM SP_USERTB WHERE ID=? AND PW=? AND STATUS=Y ";
	String UpdateInfoSQL = " UPDATE SP_USERTB SET PW=?, PHONE=?, EMAIL=?, GENDER=?, NICKNAME=?, IMGURL=? WHERE USERNUM=? ";
	String SearchIDSQL = " SELECT * FROM SP_USERTB WHERE NAME=? AND EMAIL=? AND PHONE=? ";
	String SearchPWSQL = " SELECT * FROM SP_USERTB WHERE ID=? AND NAME=? AND EMAIL=? ";
	String UserDelSQL = " DELETE FROM SP_USERTB WHERE ID = ? ";
	
	public boolean CreateAccount(UserDto dto, Connection con);
	public UserDto Login(String id, String pw, Connection con);
	public boolean UpdateInfo(UserDto dto, Connection con);
	public String SearchID(String name, String email, String phone, Connection con);
	public String SearchPW(String ID, String name, String email, Connection con);
	public void SendEmailCode(String ID, String name, String email, Connection con);
	public boolean UserDel(int usernum, Connection con);
}





