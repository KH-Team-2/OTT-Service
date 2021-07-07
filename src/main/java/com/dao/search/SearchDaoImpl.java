package com.dao.search;

import com.dto.ContentsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;

public class SearchDaoImpl implements SearchDao {
	@Override
	public List<ContentsDto> SearchList(Connection con, String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre) {

		String allSql = " SELECT *\n " +
				" FROM CONTENTS\n " +
				" WHERE TITLE LIKE '%' || ? || '%'\n " +
				" AND ( OpenYear >= '%' || ? || '%' OR OpenYear <= '%' || ? || '%' )\n " +
				"  AND ( RATE >= ? OR RATE <= ? ) OR Genre LIKE '%' || ? || '%' ";


		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContentsDto> res = new ArrayList<>();

		try {
			pstm = con.prepareStatement(allSql);
			pstm.setString(1, searchBar);
			pstm.setString(2, startdate.substring(0, 4));
			pstm.setString(3, enddate.substring(0, 4));
			pstm.setDouble(4, startgrade);
			pstm.setDouble(5, endgrade);
			pstm.setString(6, genre);
			rs = pstm.executeQuery();


			while (rs.next()) {
				System.out.println("2번");
				ContentsDto temp = new ContentsDto(
						rs.getInt(1), // num
						rs.getString(2), // title
						rs.getString(3), // open year
						rs.getString(4), // direc
						rs.getString(5), // actor
						rs.getDouble(6), // rate
						rs.getString(7), // genre
						rs.getString(8), // summary
						rs.getString(9), // movieaddr
						rs.getDate(10), // update year
						rs.getString(11) // movieimg
				);
				res.add(temp);
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { close(rs); close(pstm);}

		return res;
	}

}
