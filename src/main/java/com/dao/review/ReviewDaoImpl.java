package com.dao.review;

import com.dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public boolean ReviewWrite(Connection con, int usernum, int movienum, String reviewinfo) {

        String sql = " INSERT INTO REVIEW VALUES ( REVIEW_SQ.NEXTVAL, ?, ?, ?, SYSDATE, 0 ) ";

        PreparedStatement pstm = null;

        int res = 0;

        try {
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, usernum);
            pstm.setInt(2, movienum);
            pstm.setString(3, reviewinfo);

            res = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }

        return res >= 1 ? true : false;
    }

    @Override
    public boolean ReviewUpdate(Connection con, ReviewDto dto) {

        String sql = " UPDATE REVIEW SET REVIEWINFO = ? WHERE REVIEWNUM = ? ";

        PreparedStatement pstm = null;

        int res = 0;

        try {
            pstm = con.prepareStatement(sql);

            pstm.setString(1, dto.getReviewInfo());
            pstm.setInt(2, dto.getReviewNum());

            res = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }

        return res > 0;
    }

    @Override
    public boolean ReviewDelete(Connection con, int reviewnum) {

        String sql = " DELETE FROM REVIEW WHERE REVIEWNUM = ? ";

        PreparedStatement pstm = null;
        int res = 0;

        try {
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, reviewnum);

            res = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
            close(con);
        }

        return res > 0;
    }

    @Override
    public List<ReviewDto> ReviewList(Connection con, int movienum) {

        String sql = " SELECT A.*, U.Nickname FROM REVIEW A\n" +
                " JOIN USERTB U on A.UserNum = U.USERNUM\n" +
                " WHERE MOVIENUM = ? ORDER BY ReviewDate DESC ";

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ReviewDto> res = new ArrayList<ReviewDto>();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, movienum);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ReviewDto temp = new ReviewDto(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4),
                        rs.getDate(5), rs.getInt(6), rs.getString(7));

                res.add(temp);
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
    public boolean ReviewReport(Connection con, int reviewnum) {

        String sql = " UPDATE REVIEW SET COUNT = COUNT + 1 WHERE REVIEWNUM = ? ";

        PreparedStatement pstm = null;

        int res = 0;

        try {
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, reviewnum);

            res = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }

        return res > 0;
    }

    @Override
    public int ReviewCount(Connection con, int movienum) {
        int res = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = " SELECT COUNT(*) as count FROM REVIEW WHERE MovieNum = ? ";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, movienum);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                res = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }

        return res;
    }

    @Override
    public List<ReviewDto> ReviewPagingList(Connection con, int movienum, int page) {
        int startNum = (page - 1) * 10 + 1;
        int endNum = page * 10;
        String sql = " SELECT A.*, u.Nickname\n" +
                "FROM (\n" +
                "         SELECT *\n" +
                "         FROM (\n" +
                "                  SELECT ROWNUM as rnum, REVIEW.*\n" +
                "                  FROM REVIEW\n" +
                "              )\n" +
                "         WHERE rnum >= ? AND MOVIENUM = ?  \n" +
                "     ) A\n" +
                "JOIN USERTB u on A.UserNum = u.USERNUM\n" +
                "WHERE rnum <= ? ";

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ReviewDto> res = new ArrayList<ReviewDto>();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, startNum);
            pstm.setInt(2, movienum);
            pstm.setInt(3, endNum);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ReviewDto temp = new ReviewDto(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(7), rs.getString(8));

                res.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstm);
        }

        return res;
    }

}
