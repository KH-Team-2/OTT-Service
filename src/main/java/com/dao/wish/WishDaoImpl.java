package com.dao.wish;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;import com.dto.Paging;
import com.dto.WishDto;
import com.dto.WishListDto;

public class WishDaoImpl implements WishDao{
//String WishAddSQL = " INSERT INTO SP_WISHLIST VALUES(SAMPLE_WISH_SQ, ?, ?, SYSDATE, ?)";
//	String WishListSQL = " SELECT * FROM SP_WISHLIST ORDER BY WISHDATE DESC ";
	//String WishDeleteSQL = " DELETE FROM SP_WISHLIST WHERE USERNUM=? AND MOVIENUM=? ";
	
	
	
	@Override
	public boolean WishAdd(Connection con, int usernum, int movienum) {
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(WishAddSQL);
			pstm.setInt(1, usernum);
			pstm.setInt(2, movienum);
			pstm.setString(3, "N");
			System.out.println("03. query 준비: "+WishAddSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		
		return (res>0)?true:false;
	}
	
	

	@Override
	public List<WishDto> WishList(Connection con, int usernum,int page) {
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<WishDto> list = new ArrayList<WishDto>();
		String sql = "SELECT * FROM(SELECT ROWNUM AS rnum,WISHNUM,USERNUM,TITLE,MOVIEADDR,WISHDATE,ALARM FROM WISHLIST JOIN CONTENTS USING(MOVIENUM) WHERE USERNUM=? ORDER BY WISHNUM DESC) WHERE rnum>=? AND rnum<=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usernum);
			pstm.setInt(2, startNum);
			pstm.setInt(3, endNum);
			System.out.println("03. query 준비: "+sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				WishDto dto = new WishDto();
				dto.setWishnum(rs.getInt(2));
				dto.setUsernum(rs.getInt(3));
				dto.setTitle(rs.getString(4));
				dto.setMovieaddr(rs.getString(5));
				dto.setWishdate(rs.getDate(6));
				dto.setAlarm(rs.getString(7));
				System.out.println(rs.getString(5));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return list;
	}
	
	

	@Override
	public boolean WishDelete(Connection con, int usernum, int movienum) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(WishDeleteSQL);
			pstm.setInt(1, usernum);
			pstm.setInt(2, movienum);
			System.out.println("03. query 준비: "+WishDeleteSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return (res>0)?true:false;
	}
	
	public int WishCount(Connection con, int usernum) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;

		try {
			pstm = con.prepareStatement(WishCountSQL);
			pstm.setInt(1, usernum);
			
			rs = pstm.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}



	@Override
	public WishListDto wishfound(int usernum, int movinum, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		WishListDto dto = null;
		String sql = "SELECT * FROM WISHLIST WHERE USERNUM=? AND MOVIENUM=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usernum);
			pstm.setInt(2, movinum);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				dto = new WishListDto();
				dto.setWishNium(rs.getInt(1));
				dto.setUserNum(rs.getInt(2));
				dto.setMovieNum(rs.getInt(3));
				dto.setDate(rs.getDate(4));
				dto.setAlarm(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	

}