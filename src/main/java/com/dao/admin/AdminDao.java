package com.dao.admin;

import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.NoticeDto;
import com.dto.UserDto;

import java.sql.Connection;
import java.util.List;

public interface AdminDao {
	public List<DecrationDto> AdminDeclarationView(Connection con);
	public List<UserDto> AdminUserView(Connection con,int page);
	public List<UserDto> AdminUserSearch(String info, Connection con);
	public boolean AdminUpdateInfo(UserDto dto, Connection con);
	public List<FBWDto> AdminFBWView(Connection con,int page);
	public boolean AddFBW(FBWDto dto, Connection con);
	public boolean DeleteFBW(String FBWords, Connection con);
	public UserDto UserSelect(int UserNum, Connection con);
	public boolean UserSecession(int UserNum, Connection con);
	public boolean DeleteDeclaration(int reviewNum,Connection con);
	public boolean UserImgUdate(UserDto dto,Connection con);

    public List<NoticeDto> NoticeSelect(Connection connection);

	public boolean DeleteNotice(Connection connection, int noticenum);

    public int CountNotice(Connection connection);

	List<NoticeDto> NoticePagingList(Connection connection, int page);

	String NoticeWriteSQL = "INSERT INTO NOTICE VALUES (NOTICE_SQ.nextval, ?, ?, ? , SYSDATE, 0)";
	String NoticeUpdateSQL = " UPDATE NOTICE SET TITLE=? CONTENT=? WHERE USERNUM=? ";

	public boolean NoticeWrite(NoticeDto dto, Connection con);
	public boolean NoticeUpdate(NoticeDto dto, Connection con);


	public NoticeDto NoticeSelectOne(Connection connection, int noticenum);
}
