package com.dao.user;

import com.dto.UserDto;

import java.sql.Connection;

public interface UserDao {
	
	String CreateAccountSQL = " INSERT INTO USERTB VALUES(USER_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,'Y','USER', SYSDATE ) ";
	String LoginSQL = " SELECT * FROM USERTB WHERE ID=? AND PW=? AND STATUS='Y' ";
	String UpdateInfoSQL = " UPDATE SP_USERTB SET PW=?, PHONE=?, EMAIL=?, GENDER=?, NICKNAME=?, IMGURL=? WHERE USERNUM=? ";
	String SearchIDSQL = " SELECT * FROM SP_USERTB WHERE NAME=? AND EMAIL=? AND PHONE=? ";
	String SearchPWSQL = " SELECT * FROM SP_USERTB WHERE ID=? AND NAME=? AND EMAIL=? ";
	String UserDelSQL = " DELETE FROM SP_USERTB WHERE ID = ? ";
	String IDCheckSQL = " SELECT COUNT(*) FROM USERTB WHERE ID = ? ";
	String selectOneSql = " SELECT * FROM SP_USERTB WHERE USERNUM=? ";
	
	public boolean CreateAccount(UserDto dto, Connection con);
	public UserDto Login(String id, String pw, Connection con);
	public boolean UpdateInfo(UserDto dto, Connection con);
	public String SearchID(String name, String email, String phone, Connection con);
	public String SearchPW(String ID, String name, String email, Connection con);
	public void SendEmailCode(String ID, String name, String email, Connection con);
	public boolean UserDel(int usernum, Connection con);
	public boolean IDCheck(String id, Connection con);
	public UserDto selectOne(int userNum, Connection con);
}