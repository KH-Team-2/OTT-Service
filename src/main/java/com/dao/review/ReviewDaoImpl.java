package com.dao.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ReviewDto;
import com.dto.UserDto;

import static common.JDBCTemplate.*;

public class ReviewDaoImpl implements ReviewDao {

	@Override
	public boolean ReviewWrite(Connection con, ReviewDto rdto ) {
		
		String sql = " INSERT INTO REVIEW VALUES ( REVIEW_SQ.NEXTVAL, ?, ?, ?, SYSDATE, 0 ) ";

		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, rdto.getUserNum());
			pstm.setInt(2, rdto.getMovieNum());
			pstm.setString(3, rdto.getReviewInfo());
			
			res = pstm.executeUpdate();
		}
		catch ( SQLException e ) { e.printStackTrace(); }
		finally { close(pstm); }
		
		return res>=1 ? true:false;
	}

	@Override
	public boolean ReviewUpdate(Connection con, ReviewDto dto ) {
		
		String sql = " UPDATE REVIEW SET REVIEWINFO = ? WHERE REVIEWNUM = ? ";

		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getReviewInfo());
			pstm.setInt(2, dto.getReviewNum());
			
			res = pstm.executeUpdate();
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		finally { close(pstm); }
		
		return res>0?true:false;
	}

	@Override
	public boolean ReviewDelete(Connection con, int reviewnum) {
		
		String sql = " DELETE REVIE WHERE REVIEWNUM = ? ";

		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setInt( 1, reviewnum );
			
			res = pstm.executeUpdate();
		}
		catch (SQLException e) { e.printStackTrace();}
		finally { close(pstm); close(con); }
		
		return (res>0)?true:false;
	}

	@Override
	public List<ReviewDto> ReviewList(Connection con, int movienum) {

		String sql = "";

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ReviewDto> res = new ArrayList<ReviewDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ReviewDto temp = new ReviewDto( rs.getInt(1), rs.getInt(2),
											  rs.getInt(3), rs.getString(4),
											  rs.getDate(5), rs.getInt(6));
				
				res.add(temp);
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { close(rs); close(pstm); }
		
		return res;
	}

	@Override
	public boolean ReviewReport(Connection con, int reviewnum) {

		String sql = " UPDATE REVIEW SET COUNT = COUNT + 1 WHERE REVIEWNUM = ? ";

		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, reviewnum);
			
			res = pstm.executeUpdate();
		}
		
		catch (SQLException e) { e.printStackTrace(); }
		finally { close(pstm); }
		
		return res>0?true:false;
	}

}
