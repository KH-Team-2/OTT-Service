package com.biz.admin;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dao.admin.AdminDaoImpl;
import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.UserDto;

public class AdminBizImpl implements AdminBiz{
	
	private AdminDaoImpl dao = new AdminDaoImpl();
	
	@Override
	public List<DecrationDto> AdminDeclarationView() {
		Connection con = getConnection();
		List<DecrationDto> list = dao.AdminDeclarationView(con);
		return list;
	}

	@Override
	public List<UserDto> AdminUserView() {
		Connection con = getConnection();
		List<UserDto> list = dao.AdminUserView(con);
		close(con);
		return list;
	}

	@Override
	public List<UserDto> AdminUserSearch(String info) {
		Connection con = getConnection();
		List<UserDto> list = dao.AdminUserSearch(info, con);
		close(con);
		return list;
	}

	@Override
	public boolean AdminUpdateInfo(UserDto dto) {
		Connection con = getConnection();
		boolean res = dao.AdminUpdateInfo(dto, con);
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
	}

	@Override
	public List<FBWDto> AdminFBWView(int page) {
		Connection con = getConnection();
		List<FBWDto> list = dao.AdminFBWView(con,page);
		close(con);
		return list;
	}

	@Override
	public boolean AddFBW(FBWDto dto) {
		Connection con = getConnection();
		boolean res = dao.AddFBW(dto, con);
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
	}

	@Override
	public boolean DeleteFBW(String FBWords) {
		Connection con = getConnection();
		boolean res = dao.DeleteFBW(FBWords, con);
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
	}

	@Override
	public UserDto UserSelect(int UserNum) {
		Connection con = getConnection();
		UserDto dto = dao.UserSelect(UserNum, con);
		return dto;
	}

	@Override
	public boolean UserSecession(int UserNum) {
		Connection con = getConnection();
	      boolean res = dao.UserSecession(UserNum, con);
	      if(res) {
	         commit(con);
	      }else {
	         rollback(con);
	      }
	      close(con);
	      
	      return res;
	}

	@Override
	public boolean DeleteDeclaration(int reviewNum) {
		Connection con = getConnection();
		boolean result = dao.DeleteDeclaration(reviewNum, con);
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	@Override
	public boolean UserImgUdate(UserDto dto) {
		Connection con = getConnection();
		boolean result = dao.UserImgUdate(dto, con);
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
	public int FBWCount() {
		Connection con= getConnection();
		int res = dao.FBWCount(con);
		close(con);
		return res;
		
	}
	public int UserCount() {
		Connection con = getConnection();
		int res = dao.UserCount(con);
		close(con);
		return res;
	}
	public int DecrationCount() {
		Connection con = getConnection();
		int res = dao.DecrationCount(con);
		close(con);
		
		return res;
	}
}