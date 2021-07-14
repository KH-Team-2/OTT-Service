package com.biz.searchhistroy;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.biz.searchhistroy.SearchHistoryBiz;
import com.dao.searchhistory.SearchHistoryDao;
import com.dao.searchhistory.SearchHistoryDaoImpl;
import com.dto.FBWDto;

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

