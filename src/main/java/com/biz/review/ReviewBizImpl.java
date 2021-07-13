package com.biz.review;

import com.dao.review.ReviewDao;
import com.dao.review.ReviewDaoImpl;
import com.dto.ReviewDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class ReviewBizImpl implements ReviewBiz {
	
	private ReviewDao dao = new ReviewDaoImpl();

	@Override
	public boolean ReviewWrite( int usernum, int movienum, String reviewinfo ) {
			
		Connection con = getConnection();
		boolean res = dao.ReviewWrite(con, usernum, movienum, reviewinfo );
		
		if(res) { commit(con); }
		else { rollback(con); }
		
		close(con);
		
		return res;
	}

	@Override
	public boolean ReviewUpdate( ReviewDto rdto ) {

		Connection con = getConnection();
		boolean res = dao.ReviewUpdate(con, rdto);
		
		if(res) { commit(con); }
		else { rollback(con); }
		
		close(con);
		
		return res;
	}

	@Override
	public boolean ReviewDelete(int reviewnum) {

		Connection con = getConnection();
		boolean res = dao.ReviewDelete(con, reviewnum);
		
		if(!res) { rollback(con); }

		close(con);
		
		return res;
	}

	@Override
	public List<ReviewDto> ReviewList(int movienum) {
		
		Connection con = getConnection();
		List<ReviewDto> list = dao.ReviewList(con, movienum);
		close(con);
		
		return list;
	}

	@Override
	public List<ReviewDto> ReviewPagingList(int movienum, int page) {
		Connection con = getConnection();
		List<ReviewDto> list = dao.ReviewPagingList(con, movienum, page);
		close(con);

		return list;
	}

	@Override
	public boolean ReviewReport(int reviewnum) {

		Connection con = getConnection();
		boolean res = dao.ReviewReport(con, reviewnum);
		
		if(res) { commit(con); }
		else { rollback(con); }
		
		close(con);
		
		return res;
	}

	@Override
	public int RiviewCount(int movienum) {
		Connection connection = getConnection();
		int res = dao.ReviewCount(connection, movienum);

		close(connection);

		return res;
	}

}
