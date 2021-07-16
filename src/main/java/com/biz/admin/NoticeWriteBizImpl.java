package com.biz.admin;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import com.dao.admin.NoticeWriteDao;
import com.dao.admin.NoticeWriteDaoImpl;
import com.dto.NoticeDto;

public class NoticeWriteBizImpl implements NoticeWriteBiz {
	private NoticeWriteDao dao = new NoticeWriteDaoImpl();
	
	@Override
	public boolean NoticeWrite(NoticeDto dto) {
		
		Connection con = getConnection();
		
		boolean res = dao.NoticeWrite(dto, con);
		
		if(res) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return res;
	}

	@Override
	public boolean NoticeUpdate(NoticeDto dto) {
		Connection con = getConnection();
		
		boolean res = dao.NoticeUpdate(dto, con);
		
		if(res) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

}
