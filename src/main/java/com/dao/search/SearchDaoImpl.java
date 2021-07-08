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
                " JOIN PLATFORM P on Contents.MovieNum = P.MOVIENUM\n" +
                " JOIN PLATFORMCODE P2 on P.PFCode = P2.PFCODE " +
                " WHERE TITLE LIKE '%' || ? || '%'\n " +
                " AND TO_NUMBER(SUBSTR(OpenYear, 1, 4)) BETWEEN ? AND ?\n " +
                " AND Rate BETWEEN ? AND ? AND Genre LIKE '%' || ? || '%' ";

        String delGenreSql = " SELECT *\n " +
                " FROM CONTENTS\n " +
                " JOIN PLATFORM P on Contents.MovieNum = P.MOVIENUM\n" +
                " JOIN PLATFORMCODE P2 on P.PFCode = P2.PFCODE " +
                " WHERE TITLE LIKE '%' || ? || '%'\n " +
                " AND TO_NUMBER(SUBSTR(OpenYear, 1, 4)) BETWEEN ? AND ?\n " +
                " AND Rate BETWEEN ? AND ? ";

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ContentsDto> res = new ArrayList<>();

        try {
            if (genre.equals("none")) {
                pstm = con.prepareStatement(delGenreSql);
                pstm.setString(1, searchBar);
                pstm.setString(2, startdate.substring(0, 4));
                pstm.setString(3, enddate.substring(0, 4));
                pstm.setDouble(4, startgrade);
                pstm.setDouble(5, endgrade);
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
                            rs.getString(11), // movieimg
                            rs.getString(17) // 플랫폼 ImgUrl
                    );
                    res.add(temp);
                }
            } else {
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
                            rs.getString(11), // movieimg
                            rs.getString(17) // 플랫폼 ImgUrl
                    );
                    res.add(temp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstm);
        }

        return res;
    }

    @Override
    public ContentsDto SearchDetail(Connection con, String title) {
        ContentsDto dto = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = " SELECT *\n" +
                " FROM CONTENTS\n" +
                " JOIN PLATFORM P on Contents.MovieNum = P.MOVIENUM\n" +
                " JOIN PLATFORMCODE P2 on P.PFCode = P2.PFCODE " +
                "WHERE TITLE LIKE '%' || ? || '%' ";

        try {
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, title);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dto = new ContentsDto(
                        resultSet.getInt(1), // num
                        resultSet.getString(2), // title
                        resultSet.getString(3), // open year
                        resultSet.getString(4), // direc
                        resultSet.getString(5), // actor
                        resultSet.getDouble(6), // rate
                        resultSet.getString(7), // genre
                        resultSet.getString(8), // summary
                        resultSet.getString(9), // movieaddr
                        resultSet.getDate(10), // update year
                        resultSet.getString(11), // movieimg
                        resultSet.getString(17) // 플랫폼 ImgUrl
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

}
