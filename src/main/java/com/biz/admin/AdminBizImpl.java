package com.biz.admin;

import java.util.List;

import com.dto.FBWDto;
import com.dto.ReviewDto;
import com.dto.UserDto;

public interface AdminBizImpl {
	public List<ReviewDto> AdminDeclarationView();
	public List<UserDto> AdminUserView();
	public List<UserDto> AdminUserSearch(String info);
	public boolean AdminUpdateInfo(UserDto dto);
	public List<FBWDto> AdminFBWView();
	public boolean AddFBW(FBWDto dto);
	public boolean DeleteFBW(String FBWords);
}
