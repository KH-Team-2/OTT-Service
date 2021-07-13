package com.dao.user;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.UserDto;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean CreateAccount(UserDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;

		try {
		
			pstm = con.prepareStatement(CreateAccountSQL);
			pstm.setString(1, dto.getID()); 
			pstm.setString(2, dto.getPW());
			pstm.setString(3, dto.getEmail());
			pstm.setString(4, dto.getPhone());
			pstm.setString(5, dto.getName());
			pstm.setDate(6, dto.getBirth());
			pstm.setString(7, dto.getGender());
			pstm.setString(8, dto.getNickName());
			pstm.setString(9, dto.getImgURL());

			System.out.println("03. query 준비: "+ CreateAccountSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return (res>0)?true:false;
	}

	@Override
	public UserDto Login(String id, String pw, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserDto res = new UserDto();
		
		try {
			pstm = con.prepareStatement(LoginSQL);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			System.out.println("03. query 준비: " + LoginSQL);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");

			while(rs.next()) {
				res.setUserNum(rs.getInt(1));
				res.setID(rs.getString(2));
				res.setPW(rs.getString(3));
				res.setEmail(rs.getString(4));
				res.setPhone(rs.getString(5));
				res.setName(rs.getString(6));
				res.setBirth(rs.getDate(7));
				res.setGender(rs.getString(8));
				res.setNickName(rs.getString(9));
				res.setImgURL(rs.getString(10));
				res.setStatus(rs.getString(11));
				res.setGrade(rs.getString(12));
				res.setUserDate(rs.getDate(13));
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		
		return res;
	}

	@Override
	public boolean UpdateInfo(UserDto dto, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UpdateInfoSQL);
			pstm.setString(1, dto.getPW());
			pstm.setString(2, dto.getPhone());
			pstm.setString(3, dto.getEmail());
			pstm.setString(4, dto.getGender());
			pstm.setString(5, dto.getNickName());
			pstm.setString(6, dto.getImgURL());
			pstm.setInt(7, dto.getUserNum());
			System.out.println("03. query 준비: "+UpdateInfoSQL);
			
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
	public String SearchID(String name, String email, String phone, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		try {
			pstm = con.prepareStatement(SearchIDSQL);
			pstm.setString(1, name);
			pstm.setString(2, email);
			pstm.setString(3, phone);
			System.out.println("03. query 준비: "+SearchIDSQL);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(2);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}

	@Override
	public String SearchPW(String ID, String name, String email, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		try {
			pstm = con.prepareStatement(SearchPWSQL);
			pstm.setString(1, ID);
			pstm.setString(2, name);
			pstm.setString(3, email);
			System.out.println("03. query 준비: "+SearchPWSQL );
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(3);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}

	@Override
	public boolean SendEmailCode(String ID, String name, String email, Connection con) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(SendEmailSQL);
			pstm.setString(1, ID);
			pstm.setString(2, name);
			pstm.setString(3, email);
			System.out.println("03. query 준비: "+ SendEmailSQL );
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}

		return res>=1?true:false;
	}
	
	@Override
	public boolean UserDel ( int usernum, Connection con ) {
		
		PreparedStatement pstm = null;
		int res = 0;
		boolean result = false;
		
		try {
			pstm = con.prepareStatement(UserDelSQL);
			pstm.setInt(1, usernum);
			System.out.println("03. query 준비: "+UserDelSQL);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			if(res>0) {
				result=true;
			}else {
				result = false;
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return result;
	}
	
	@Override
	public boolean IDCheck ( String id, Connection con ) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(IDCheckSQL);
			pstm.setString(1, id);
			System.out.println("03. query 준비: "+ IDCheckSQL);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res>=1?true:false;
	}

	@Override
	public UserDto selectOne(int userNum, Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserDto res = null;
		
		try {
			pstm = con.prepareStatement(selectOneSql);
			pstm.setInt(1,userNum);
			System.out.println("03. query 준비: " + selectOneSql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 밀 리턴");
			
			if(rs.next()) {
				res = new UserDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), 
								rs.getString(12), rs.getDate(13));
				
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	@Override
	public boolean ChangePW(String id, String pw, Connection con) {
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(ChangePWSQL);
			pstm.setString(1, pw);
			pstm.setString(2, id);

			System.out.println("03. query 준비: "+ ChangePWSQL);
			
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
	
}
