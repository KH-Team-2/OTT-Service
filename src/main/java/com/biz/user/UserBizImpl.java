package com.biz.user;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import com.dao.user.UserDao;
import com.dao.user.UserDaoImpl;
import com.dto.UserDto;

public class UserBizImpl implements UserBiz{
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public boolean CreateAccount(UserDto dto) {
		Connection con = getConnection();
		
		boolean res = dao.CreateAccount(dto, con);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		return res;
	}

	@Override
	public UserDto Login(String id, String pw) {
		Connection con = getConnection();
		
		UserDto dto = dao.Login(id, pw, con);
		
		close(con);
		
		return dto;
	}

	@Override
	public boolean UpdateInfo(UserDto dto) {
		Connection con = getConnection();
		
		boolean res = dao.UpdateInfo(dto, con);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public String SearchID(String name, String email, String phone) {
		Connection con = getConnection();
		
		String res = dao.SearchID(name, email, phone, con);
		
		close(con);
		
		return res;
	}

	@Override
	public String SearchPW(String ID, String name, String email, String code) {
		Connection con = getConnection();
		
		String res = dao.SearchPW(ID, name, email, con);
		
		close(con);
		
		return res;
	}

	@Override
	public void SendEmailCode(String ID, String name, String email) {
		
		
		
		
		
	}

}
