package com.dao.alarm;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.UserDto;
import com.dto.WishListDto;

public class AlarmDaoImpl {
	
	public boolean AlarmSwitch(Connection con, int usernum, int movienum ) {
		
		PreparedStatement pstm = null;

		String sql = "UPDATE WISHLIST\r\n" + 
				"SET ALARM = \r\n" + 
				"	( CASE\r\n" + 
				"		WHEN ALARM='Y' THEN 'N'\r\n" + 
				"		WHEN ALARM='N' THEN 'Y'\r\n" + 
				"	END )\r\n" + 
				"WHERE USERNUM=? AND MOVIENUM=?";

		int res = 0;

		try {	
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usernum);
			pstm.setInt(2, movienum);
			
			res = pstm.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res>=1?true:false;
	}
	
	
	public List<UserDto> AlarmLoading(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserDto> list = new ArrayList<UserDto>();
		String sql = "SELECT * FROM Notification";
		
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
				dto.setBirth(rs.getDate(6));
				dto.setGender(rs.getString(7));
				dto.setNickName(rs.getString(8));
				dto.setImgURL(rs.getString(9));
				dto.setStatus(rs.getString(10));
				dto.setGrade(rs.getString(11));
				dto.setUserDate(rs.getDate(12));
				
			
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
			}
		
		return list;
	}
}