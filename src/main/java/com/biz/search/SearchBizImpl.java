package com.biz.search;

import com.dao.search.SearchDao;
import com.dao.search.SearchDaoImpl;
import com.dto.ContentsDto;
import com.dto.FBWDto;

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

	@Override
	public List<ContentsDto> SearchNewList() {
		Connection con = getConnection();
		List<ContentsDto> list = dao.SearchNewList(con);
		close(con);

		return list;
	}

	@Override
	public List<ContentsDto> SearchPopList() {
		Connection con = getConnection();
		List<ContentsDto> list = dao.SearchPopList(con);
		close(con);

		return list;
	}


	@Override
	public ContentsDto SearchDetail(String title) {
		Connection con = getConnection();
		ContentsDto dto = dao.SearchDetail(con, title);
		close(con);
		return dto;
	}

	@Override
	public List<FBWDto> SearchFBW(String title) {
		Connection con = getConnection();
		List<FBWDto> list = dao.SearchFBW(con, title);
		close(con);
		return list;
	}

	@Override
	public List<ContentsDto> SearchAllList(int page) {
		Connection con = getConnection();
		List<ContentsDto> list = dao.SearchAllList(con, page);
		close(con);
		return list;
	}

	@Override
	public int ContentsListCount() {
		Connection con = getConnection();
		int res = dao.ContentsListCount(con);
		close(con);
		return res;
	}
	

}
