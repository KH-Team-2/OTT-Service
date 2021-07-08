package com.biz.search;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dao.review.ReviewDao;
import com.dao.review.ReviewDaoImpl;
import com.dao.search.SearchDao;
import com.dao.search.SearchDaoImpl;
import com.dto.ContentsDto;

public class SearchBizImpl implements SearchBiz {
	
	private SearchDao dao = new SearchDaoImpl();

	@Override
	public List<ContentsDto> SearchList(String searchname) {
		
		Connection con = getConnection();
		List<ContentsDto> list = dao.SearchList(con, searchname);
		close(con);
		
		return list;
	}

}
