package com.biz.searchhistroy;

import com.dao.searchhistory.SearchHistoryDao;
import com.dao.searchhistory.SearchHistoryDaoImpl;
import com.dto.FBWDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class SearchHistoryBizImpl implements SearchHistoryBiz {
	private SearchHistoryDao dao = new SearchHistoryDaoImpl();


	@Override
	public List<FBWDto> SearchHistoryView(String FBWords, String Reason, int FBWordsNum) {
		Connection con = getConnection();
		List<FBWDto> list = dao.SearchHistoryView(con, FBWords, Reason, FBWordsNum);
				
		close(con);
		return list;
	}

	@Override
	public boolean SearchHistoryDelete(String FBWords, String Reason, int FBWordsNum) {
		Connection con = getConnection();
		
		boolean res = dao.SearchHistroyDelete(con, FBWords, Reason, FBWordsNum);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
		
		
	}
}

