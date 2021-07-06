package com.biz.admin;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.dao.admin.AdminDao;
import com.dto.FBWDto;
import com.dto.ReviewDto;
import com.dto.UserDto;

public class AdminBiz implements AdminBizImpl{
	
	AdminDao dao = new AdminDao();
	
	@Override
	public List<ReviewDto> AdminDeclarationView() {
		Connection con = getConnection();
		List<ReviewDto> list = dao.AdminDeclarationView(con);
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
	public List<FBWDto> AdminFBWView() {
		Connection con = getConnection();
		List<FBWDto> list = dao.AdminFBWView(con);
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
	public boolean UserSecession ( int UserNum ) {
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
	
}
