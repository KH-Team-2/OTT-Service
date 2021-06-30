package common;

import java.sql.*;

public class JDBCTemplate {

    public static Connection getConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("01. 드라이버 연결");
        } catch (ClassNotFoundException e) {
            System.out.println("01. 드라이버 연결 실패");
            e.printStackTrace();
        }

        String url = "jdbc:oracle:thin:@wjwan.hopto.org:1521:xe";
        String id = "KH";
        String pw = "KH";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, id, pw);
            System.out.println("02. 계정 접속");

            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            System.out.println("02. 계정 접속 실패");
            throwables.printStackTrace();
        }


        return connection;
    }

    //    close()
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //    commit, rollback
    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
