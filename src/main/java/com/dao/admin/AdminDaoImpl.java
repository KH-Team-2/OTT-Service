package com.dao.admin;

import java.sql.Connection;
import java.util.List;

import com.dto.FBWDto;
import com.dto.ReviewDto;
import com.dto.UserDto;

public interface AdminDaoImpl {
	public List<ReviewDto> AdminDeclarationView(Connection con);
	public List<UserDto> AdminUserView(Connection con);
	public UserDto AdminUserSearch(String info, Connection con);
	public boolean AdminUpdateInfo(UserDto dto, Connection con);
	public List<FBWDto> AdminFBWView(Connection con);
	public boolean AddFBW(FBWDto dto);
	public boolean DeleteFBW(String FBWords);
}
