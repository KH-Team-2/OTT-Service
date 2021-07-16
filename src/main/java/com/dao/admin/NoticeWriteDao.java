package com.dao.admin;

import java.sql.Connection;

import com.dto.NoticeDto;

public interface NoticeWriteDao {
	String NoticeWriteSQL = "INSERT INTO NOTICE VALUES (NOTICE_SQ.nextval, ?, ?, ? , SYSDATE, 0)";
	String NoticeUpdateSQL = " UPDATE NOTICE SET TITLE=? CONTENT=? WHERE USERNUM=? ";
	
	public boolean NoticeWrite(NoticeDto dto, Connection con);
	public boolean NoticeUpdate(NoticeDto dto, Connection con);

}
