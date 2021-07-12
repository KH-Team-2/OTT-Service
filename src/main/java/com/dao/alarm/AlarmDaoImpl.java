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

	
public void AlarmSwitch(Connection con, WishListDto dto) {
	PreparedStatement pstm = null;

	String sql = "UPDATE SP_WISHLIST SET ALARM = CASE WHEN ALARM='Y' THEN 'N' WHEN ALARM='Y' THEM 'N' WHERE USERNUM=? AND MOVIENUM=?;";

	int res = 0;

	try {	
		pstm = con.prepareStatement(sql);
		pstm.setString(1, dto.getAlarm());
		
		res = pstm.executeUpdate();
		
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstm);
	}
	return;
}

}