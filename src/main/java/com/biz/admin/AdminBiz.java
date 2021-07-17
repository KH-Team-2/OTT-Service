package com.biz.admin;

import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.NoticeDto;
import com.dto.UserDto;

import java.util.List;

public interface AdminBiz {
	public List<DecrationDto> AdminDeclarationView();
	public List<UserDto> AdminUserView(int page);
	public List<UserDto> AdminUserSearch(String info);
	public boolean AdminUpdateInfo(UserDto dto);
	public List<FBWDto> AdminFBWView(int page);
	public boolean AddFBW(FBWDto dto);
	public boolean DeleteFBW(String FBWords);
	public UserDto UserSelect(int UserNum);
	public boolean UserSecession(int UserNum);
	public boolean DeleteDeclaration(int reviewNum);
	public boolean UserImgUdate(UserDto dto);

	public List<NoticeDto> NoticeSelect();
	public boolean DeleteNotice(int noticenum);

	public int CountNotice();

	public List<NoticeDto> NoticePagingList(int page);

	public boolean NoticeWrite(NoticeDto dto);
	public boolean NoticeUpdate(NoticeDto dto);
<<<<<<< HEAD
	public NoticeDto NoticeSelectOne(int noticenum);
=======
	public boolean NoticeDelete(String[] noticenum);
>>>>>>> feature/관리자_게시판
}
