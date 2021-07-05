package com.dao.wish;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public List<WishListDto> WishList(Connection con, int usernum) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<WishListDto> list = null;
		
		try {
			pstm = con.prepareStatement(WishListSQL);
			pstm.setInt(1, usernum);
			System.out.println("03. query 준비: "+WishListSQL);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				WishListDto dto = new WishListDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
				
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

}