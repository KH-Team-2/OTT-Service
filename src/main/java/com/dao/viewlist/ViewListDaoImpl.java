package com.dao.viewlist;

import com.dto.WHDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;

public class ViewListDaoImpl implements ViewListDao {

    @Override
    public List<WHDto> ViewListLoading(int usernum, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<WHDto> list = new ArrayList<WHDto>();

        try {
            pstm = con.prepareStatement(ViewListLoadingSQL);
            pstm.setInt(1, usernum);
            System.out.println("03. query 준비: " + ViewListLoadingSQL);

            rs = pstm.executeQuery();
            System.out.println("04. query 실행 및 리턴");

            while (rs.next()) {
                WHDto tmp = new WHDto(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6));

                list.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println("3/4단계 에러");
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstm);
            System.out.println("05. db 종료\n");
        }

        return list;
    }


    @Override
    public boolean ViewListDelete(int historynum, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;

        try {
            pstm = con.prepareStatement(ViewListDeleteSQL);
            pstm.setInt(1, historynum);
            System.out.println("03. query 준비: " + ViewListDeleteSQL);

            res = pstm.executeUpdate();
            System.out.println("04. query 실행 및 리턴");


        } catch (SQLException e) {
            System.out.println("3/4단계 에러");
            e.printStackTrace();
        } finally {
            close(pstm);
            System.out.println("05. db 종료\n");
        }


        return res > 0;
    }

    @Override
    public boolean ViewListAdd(int movienum, int usernum, Connection con) {
        PreparedStatement preparedStatement = null;
        int res = 0;

        try {
            preparedStatement = con.prepareStatement(ViewListAddSQL);
            preparedStatement.setInt(1, usernum);
            preparedStatement.setInt(2, movienum);

            res = preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(ViewOverlapSQL);
            preparedStatement.executeUpdate();
            System.out.println("중복제거");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return res > 0;
    }

}
