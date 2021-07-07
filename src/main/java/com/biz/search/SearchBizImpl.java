package com.biz.search;

import com.dao.search.SearchDao;
import com.dao.search.SearchDaoImpl;
import com.dto.ContentsDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class SearchBizImpl implements SearchBiz {
	
	private final SearchDao dao = new SearchDaoImpl();

	@Override
	public List<ContentsDto> SearchList(String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre) {
		Connection con = getConnection();
		List<ContentsDto> list = dao.SearchList(con, searchBar, startdate, enddate, startgrade, endgrade, genre);
		close(con);

		return list;
	}

}
