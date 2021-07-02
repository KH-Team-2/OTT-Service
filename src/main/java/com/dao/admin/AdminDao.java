package com.dao.admin;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.FBWDto;
import com.dto.ReviewDto;
import com.dto.UserDto;
public class AdminDao implements AdminDaoImpl{

	@Override
	public List<ReviewDto> AdminDeclarationView(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		String sql ="SELECT * FROM REVIEW WHERE COUNT>=1";
		
		try {
			pstm = con.prepareStatement(sql);
			rs= pstm.executeQuery();
			
			while(rs.next()) {
				ReviewDto dto = new ReviewDto();
				dto.setReviewNum(rs.getInt(1));
				dto.setUserNum(rs.getInt(2));
				dto.setMovieNum(rs.getInt(3));
				dto.setReviewInfo(rs.getString(4));
				dto.setDate(rs.getDate(5));
				dto.setCount(rs.getInt(6));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return list;
	}

	@Override
	public List<UserDto> AdminUserView(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserDto> list = new ArrayList<UserDto>();
		String sql = "SELECT * FROM USERTB";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				UserDto dto = new UserDto();
				dto.setUserNum(rs.getInt(1));
				dto.setID(rs.getString(2));
				dto.setPW(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setBirth(rs.getDate(7));
				dto.setGender(rs.getString(8));
				dto.setNickName(rs.getString(9));
				dto.setImgURL(rs.getString(10));
				dto.setStatus(rs.getString(11));
				dto.setGrade(rs.getString(12));
				dto.setUserDate(rs.getDate(13));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return list;
	}

	@Override
	public List<UserDto> AdminUserSearch(String info, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserDto> list = new ArrayList<UserDto>();
		String sql = "SELECT * FROM USERTB WHERE ID LIKE %?% OR NAME LIKE %?% OR NICKNAME LIKE %?%";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, info);
			pstm.setString(2, info);
			pstm.setString(3, info);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				UserDto dto = new UserDto();
				
				dto.setUserNum(rs.getInt(1));
				dto.setID(rs.getString(2));
				dto.setPW(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setBirth(rs.getDate(7));
				dto.setGender(rs.getString(8));
				dto.setNickName(rs.getString(9));
				dto.setImgURL(rs.getString(10));
				dto.setStatus(rs.getString(11));
				dto.setGrade(rs.getString(12));
				dto.setUserDate(rs.getDate(13));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
				
		
		return list;
	}

	@Override
	public boolean AdminUpdateInfo(UserDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		boolean result = true;
		String sql = "UPDATE USERTB SET ID=?,PW=?,EMAIL=?,PHONE=?,NAME=?,BIRTH=?,GENDER=?,NICKNAME=?,IMGURL=?,STATUS=?,GRADE=?,USERDATE=? WHERE USERNUM=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getID());
			pstm.setString(2, dto.getPW());
			pstm.setString(3, dto.getEmail());
			pstm.setString(4, dto.getPhone());
			pstm.setString(5, dto.getName());
			pstm.setDate(6, dto.getBirth());
			pstm.setString(7, dto.getGender());
			pstm.setString(8, dto.getNickName());
			pstm.setString(9, dto.getImgURL());
			pstm.setString(10, dto.getGrade());
			pstm.setDate(11, dto.getUserDate());
			pstm.setInt(12, dto.getUserNum());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				result= true;
			}else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return result;
	}

	@Override
	public List<FBWDto> AdminFBWView(Connection con) {
		
		return null;
	}

	@Override
	public boolean AddFBW(FBWDto dto, Connection con) {
		
		return false;
	}

	@Override
	public boolean DeleteFBW(String FBWords, Connection con) {
		
		return false;
	}

}
