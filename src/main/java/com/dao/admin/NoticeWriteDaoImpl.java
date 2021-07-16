package com.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static common.JDBCTemplate.*;
import com.dto.NoticeDto;

public class NoticeWriteDaoImpl implements NoticeWriteDao {

	@Override
	public boolean NoticeWrite(NoticeDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(NoticeWriteSQL);
			//usernum title content
			pstm.setInt(1, dto.getUsernum());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03. query 준비: "+NoticeWriteSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return (res>0)?true:false;
	}

	@Override
	public boolean NoticeUpdate(NoticeDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(NoticeUpdateSQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getUsernum());
			System.out.println("03. query 준비");
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("5. db 종료\n");
		}
		
		return false;
	}

}
