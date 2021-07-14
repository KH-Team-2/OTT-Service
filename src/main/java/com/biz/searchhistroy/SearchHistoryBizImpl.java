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
	public List<FBWDto> SearchHistoryView() {
		Connection con = getConnection();
		List<FBWDto> list = dao.SearchHistoryView(con);

		close(con);
		return list;
	}

	@Override
	public boolean SearchHistoryDelete() {
		Connection con = getConnection();
		
		boolean res = dao.SearchHistroyDelete(con);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
		
		
	}
}

