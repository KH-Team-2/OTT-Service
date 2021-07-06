package com.dao.alarm;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.AlarmDto;
import com.dto.WishListDto;

public class AlarmDaoImpl {
	
	public List<AlarmDto> AlarmLoading(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AlarmDto> list = new ArrayList<AlarmDto>();
		String sql = "SELECT * FROM Notification";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				AlarmDto dto = new AlarmDto();
				dto.setAlarmNum(rs.getInt(1));
				dto.setUserNum(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setStatus(rs.getString(4));
				dto.setRegDate(rs.getDate(5));
				
				list.add(dto);
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

	String sql = "UPDATE WishList SET Alarm=?";

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