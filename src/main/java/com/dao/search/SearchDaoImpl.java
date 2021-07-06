package com.dao.search;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ContentsDto;

public class SearchDaoImpl implements SearchDao {
	
	@Override
	public List<ContentsDto> SearchList( Connection con, String searchname ) {
		
		String sql = " SELECT * FROM CONTENTS WHERE TITLE LIKE '%?%' ";

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContentsDto> res = new ArrayList<ContentsDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, searchname);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ContentsDto temp = new ContentsDto(
						rs.getInt(1), // num
						rs.getString(2), // title
						rs.getString(3), // open year
						rs.getString(4), // direc
						rs.getString(5), // actor
						rs.getDouble(6), // rate
						rs.getString(7), // summary
						rs.getString(8), // movieaddr
						rs.getDate(9), // update year
						rs.getString(10) // movieimg
						);
				
				res.add(temp);
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { close(rs); close(pstm);}
		
		return res;
	}
	
}
