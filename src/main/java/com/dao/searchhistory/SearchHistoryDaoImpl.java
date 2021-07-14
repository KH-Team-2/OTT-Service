package com.dao.searchhistory;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.FBWDto;

public class SearchHistoryDaoImpl implements SearchHistoryDao {

	@Override
	public List<FBWDto> SearchHistoryView(Connection con, String FBWords, String Reason) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<FBWDto> list = new ArrayList<FBWDto>();
		
		try {
			pstm = con.prepareStatement(SearchHistoryViewSQL);
			pstm.setString(1, FBWords);
			pstm.setString(2, Reason);
			System.out.println("03쿼리준비: "+SearchHistoryViewSQL);
		}catch (SQLException e) {
			System.out.println("3,4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
			System.out.println("05.db종료\n");
		}
		
		return list;
	}

	@Override
	public boolean SearchHistroyDelete(Connection con,String FBWords, String Reason, int FBWordsNum) {
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(SearchHistroyDeleteSQL);
			pstm.setString(1, FBWords);
			pstm.setString(2, Reason);
			pstm.setInt(3, FBWordsNum);
			System.out.println("03쿼리준비:"+SearchHistroyDeleteSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. 쿼리 실행 및 리턴");
		}catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return (res>0)?true:false;
		
	}
	
}
