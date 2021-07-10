package com.biz.admin;

import java.util.List;

import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.UserDto;

public interface AdminBiz {
	public List<DecrationDto> AdminDeclarationView();
	public List<UserDto> AdminUserView();
	public List<UserDto> AdminUserSearch(String info);
	public boolean AdminUpdateInfo(UserDto dto);
	public List<FBWDto> AdminFBWView();
	public boolean AddFBW(FBWDto dto);
	public boolean DeleteFBW(String FBWords);
	public UserDto UserSelect(int UserNum);
	public boolean UserSecession(int UserNum);
	public boolean DeleteDeclaration(int reviewNum);
	public boolean UserImgUdate(UserDto dto);
}
