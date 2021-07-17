package com.dao.search;

import com.dto.ContentsDto;
import com.dto.FBWDto;

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
//                    System.out.println("2번");
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
    public List<ContentsDto> SearchNewList(Connection con) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ContentsDto> list = new ArrayList<>();
        String sql = " SELECT *\n" +
                "FROM CONTENTS\n" +
                "WHERE ROWNUM <= 10\n" +
                "ORDER BY MovieNum DESC  ";

        try {
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ContentsDto dto = new ContentsDto(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getDouble(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getDate(10),
                        resultSet.getString(11)
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


        return list;
    }

    @Override
    public List<ContentsDto> SearchPopList(Connection con) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ContentsDto> list = new ArrayList<>();
        String sql = " SELECT *\n" +
                "FROM CONTENTS\n" +
                "WHERE ROWNUM <= 10\n" +
                "ORDER BY Rate DESC ";

        try {
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ContentsDto dto = new ContentsDto(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getDouble(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getDate(10),
                        resultSet.getString(11)
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


        return list;
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

    @Override
    public List<FBWDto> SearchFBW(Connection con, String title) {
        List<FBWDto> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = " SELECT *\n" +
                "FROM ForbiddenWord\n" +
                "WHERE FBWords LIKE '%' || ? || '%' ";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, title);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FBWDto dto = new FBWDto(resultSet.getString(1), resultSet.getString(2));

                list.add(dto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }

        return list;
    }

	@Override
	public List<ContentsDto> SearchAllList(Connection con, int page) {
		int startNum = (page - 1) * 20 + 1;
        int endNum = page * 20;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT B.* FROM(SELECT ROWNUM as rnum,A.* FROM(SELECT * FROM CONTENTS ORDER BY MOVIENUM ASC) A) B WHERE rnum>=? and rnum<=?";
		List<ContentsDto> list = new ArrayList<ContentsDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, startNum);
			pstm.setInt(2, endNum);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				ContentsDto dto = new ContentsDto();
				dto.setMovieNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setOpenYear(rs.getString(4));
				dto.setDirector(rs.getString(5));
				dto.setActor(rs.getString(6));
				dto.setRate(rs.getDouble(7));
				dto.setGenre(rs.getString(8));
				dto.setSummary(rs.getString(9));
				dto.setMovieAddr(rs.getString(10));
				dto.setUpdateYear(rs.getDate(11));
				dto.setMovieImg(rs.getString(12));
				
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
	public int ContentsListCount(Connection con) {
		PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) as count FROM CONTENTS";
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return count;
	}

}
