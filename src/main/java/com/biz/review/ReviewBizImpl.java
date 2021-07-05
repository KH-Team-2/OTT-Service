package com.biz.review;

import java.sql.Connection;
import java.util.List;

import com.dao.review.ReviewDao;
import com.dao.review.ReviewDaoImpl;
import com.dto.ReviewDto;
import com.dto.UserDto;

import static common.JDBCTemplate.*;

public class ReviewBizImpl implements ReviewBiz {
	
	private ReviewDao dao = new ReviewDaoImpl();

	@Override
	public boolean ReviewWrite( ReviewDto rdto ) {
			
		Connection con = getConnection();
		boolean res = dao.ReviewWrite(con, rdto );
		
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
		
		if(res) { commit(con); }
		else { rollback(con); }
		
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
	public boolean ReviewReport(int reviewnum) {

		Connection con = getConnection();
		boolean res = dao.ReviewDelete(con, reviewnum);
		
		if(res) { commit(con); }
		else { rollback(con); }
		
		close(con);
		
		return res;
	}

}
