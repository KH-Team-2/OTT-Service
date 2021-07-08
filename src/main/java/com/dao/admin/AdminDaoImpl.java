package com.dao.admin;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.ReviewDto;
import com.dto.UserDto;
public class AdminDaoImpl implements AdminDao{

	@Override
	public List<DecrationDto> AdminDeclarationView(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<DecrationDto> list = new ArrayList<DecrationDto>();
		String sql ="SELECT REVIEWNUM,NAME,REVIEWINFO,COUNT FROM REVIEW JOIN USERTB USING(USERNUM) WHERE COUNT>1";
		
		try {
			pstm = con.prepareStatement(sql);
			rs= pstm.executeQuery();
			
			while(rs.next()) {
				DecrationDto dto = new DecrationDto();
				dto.setReviewNum(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setReviewInfo(rs.getString(3));
				dto.setCount(rs.getInt(4));
				
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
		String sql = "SELECT * FROM USERTB WHERE STATUS='Y'";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("query 준비" + sql);
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
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
		String sql = "SELECT * FROM USERTB WHERE (LOWER(ID) LIKE ('%' || ? || '%') OR LOWER(NAME) LIKE LOWER('%' || ? || '%') OR LOWER(NICKNAME) LIKE LOWER('%' || ? || '%')) AND STATUS='Y'";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, info);
			pstm.setString(2, info);
			pstm.setString(3, info);
			System.out.println("03.query 준비" + sql);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
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
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<FBWDto> list = new ArrayList<FBWDto>();
		String sql = "SELECT * FROM FORBIDDENWORD";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				FBWDto dto = new FBWDto();
				dto.setFBWords(rs.getString(1));
				dto.setReason(rs.getString(2));
				
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
	public boolean AddFBW(FBWDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		boolean result = true;
		String sql = "INSERT INTO FORBIDDENWORD VALUES(?,?)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getFBWords());
			pstm.setString(2, dto.getReason());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				result = true;
			}else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		
		return result;
	}

	@Override
	public boolean DeleteFBW(String FBWords, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		boolean result = true;
		String sql = "DELETE FROM FORBIDDENWORD WHERE FBWORDS = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, FBWords);
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				result=true;
			}else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		
		
		return result;
	}

	@Override
	public UserDto UserSelect(int UserNum, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserDto dto = new UserDto();
		String sql = "SELECT * FROM USERTB WHERE USERNUM=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, UserNum);
			
			rs = pstm.executeQuery();
			if(rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return dto;
	}

	@Override
	public boolean UserSecession(int UserNum, Connection con) {
		 PreparedStatement pstm = null;
	      int res = 0;
	      boolean result = true;
	      String sql = "UPDATE USERTB SET STATUS=? WHERE USERNUM=?";
	      
	      try {
	         pstm = con.prepareStatement(sql);
	         pstm.setString(1, "N");
	         pstm.setInt(2, UserNum);
	         
	         res = pstm.executeUpdate();
	         
	         if(res>0) {
	            result=true;
	         }else {
	            result = false;
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(pstm);
	      }   
	      return result;

	}

	@Override
	public boolean DeleteDeclaration(int reviewNum, Connection con) {
		PreparedStatement pstm = null;
		boolean result = true;
		int res = 0;
		String sql = "DELETE FROM REVIEW WHERE REVIEWNUM=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, reviewNum);
			
			res=pstm.executeUpdate();
			if(res>0) {
				result = true;
			}else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
}
