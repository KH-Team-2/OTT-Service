package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class insertWatcha extends JDBCTemplate {

    public int insert(List<watchaDto> list) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String sql = " INSERT INTO SAMPLE_CONTENTS VALUES" +
                " (SAMPLE_MOVIE_SQ.NEXTVAL, ?, ?, ?, ?, ?, ? ) ";
        String sql2 = " INSERT INTO SAMPLE_PLATFORM VALUES" +
                " ('WC', SAMPLE_MOVIEPF_SQ.NEXTVAL ) ";

        int res = 0;
        int res1 = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);

            int cnt = 0;
            for (int i = 0; i < list.size(); i++) {
                watchaDto dto = list.get(i);
                preparedStatement.setString(1, dto.getTitle());
                preparedStatement.setString(2, dto.getYear());
                preparedStatement.setString(3, dto.getDirector());
                preparedStatement.setString(4, dto.getActor());
                preparedStatement.setDouble(5, dto.getRate());
                preparedStatement.setString(6, dto.getSummary());

                preparedStatement.addBatch();

                cnt++;
            }

            int[] result = preparedStatement.executeBatch();

            for (int i :
                    result) {
                if (i == -2) {
                    res++;
                }
            }
            if (res == list.size()) {
                preparedStatement1 = connection.prepareStatement(sql2);

                res1 = preparedStatement1.executeUpdate();

                if (res1 > 0) {
                    commit(connection);
                } else {
                    rollback(connection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(preparedStatement1);
            close(connection);
        }
        return res;
    }
}
