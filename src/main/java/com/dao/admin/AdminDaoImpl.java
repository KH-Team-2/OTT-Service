package com.dao.admin;

import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.NoticeDto;
import com.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;

public class AdminDaoImpl implements AdminDao {

    @Override
    public List<DecrationDto> AdminDeclarationView(Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<DecrationDto> list = new ArrayList<DecrationDto>();
        String sql = "SELECT REVIEWNUM,NAME,REVIEWINFO,COUNT FROM REVIEW JOIN USERTB USING(USERNUM) WHERE COUNT>=1";

        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                DecrationDto dto = new DecrationDto();
                dto.setReviewNum(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setReviewInfo(rs.getString(3));
                dto.setCount(rs.getInt(4));

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
    public List<UserDto> AdminUserView(Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserDto> list = new ArrayList<UserDto>();
        String sql = "SELECT * FROM USERTB WHERE STATUS='Y'";

        try {
            pstm = con.prepareStatement(sql);
            System.out.println("query 준비" + sql);
            rs = pstm.executeQuery();
            System.out.println("query 실행 및 리턴");
            while (rs.next()) {
                UserDto dto = new UserDto();
                dto.setUserNum(rs.getInt(1));
                dto.setID(rs.getString(2));
                dto.setPW(rs.getString(3));
                dto.setEmail(rs.getString(4));
                dto.setPhone(rs.getString(5));
                dto.setName(rs.getString(6));
                dto.setBirth(rs.getDate(7));
                dto.setGender(rs.getString(8));
                dto.setNickName(rs.getString(9));
                dto.setImgURL(rs.getString(10));
                dto.setStatus(rs.getString(11));
                dto.setGrade(rs.getString(12));
                dto.setUserDate(rs.getDate(13));

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
    public List<UserDto> AdminUserSearch(String info, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserDto> list = new ArrayList<UserDto>();

        String sql = "SELECT * FROM USERTB WHERE (LOWER(ID) LIKE ('%' || ? || '%') OR LOWER(NAME) LIKE LOWER('%' || ? || '%') OR LOWER(NICKNAME) LIKE LOWER('%' || ? || '%')) AND STATUS='Y'";


        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, info);
            pstm.setString(2, info);
            pstm.setString(3, info);
            System.out.println("03.query 준비" + sql);
            rs = pstm.executeQuery();
            System.out.println("04.query 실행 및 리턴");
            while (rs.next()) {
                UserDto dto = new UserDto();

                dto.setUserNum(rs.getInt(1));
                dto.setID(rs.getString(2));
                dto.setPW(rs.getString(3));
                dto.setEmail(rs.getString(4));
                dto.setPhone(rs.getString(5));
                dto.setName(rs.getString(6));
                dto.setBirth(rs.getDate(7));
                dto.setGender(rs.getString(8));
                dto.setNickName(rs.getString(9));
                dto.setImgURL(rs.getString(10));
                dto.setStatus(rs.getString(11));
                dto.setGrade(rs.getString(12));
                dto.setUserDate(rs.getDate(13));

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
    public boolean AdminUpdateInfo(UserDto dto, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;
        boolean result = false;
        String sql = "UPDATE USERTB SET ID=?,PW=?,EMAIL=?,PHONE=?,NAME=?,BIRTH=?,GENDER=?,NICKNAME=? WHERE USERNUM=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, dto.getID());
            pstm.setString(2, dto.getPW());
            pstm.setString(3, dto.getEmail());
            pstm.setString(4, dto.getPhone());
            pstm.setString(5, dto.getName());
            pstm.setDate(6, dto.getBirth());
            pstm.setString(7, dto.getGender());
            pstm.setString(8, dto.getNickName());
            pstm.setInt(9, dto.getUserNum());

            res = pstm.executeUpdate();

            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }
        return result;
    }

    @Override
    public List<FBWDto> AdminFBWView(Connection con, int page) {
        int startNum = (page - 1) * 10 + 1;
        int endNum = page * 10;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<FBWDto> list = new ArrayList<FBWDto>();
        System.out.println(page + "+" + startNum + "+" + endNum);
        String sql = "SELECT * FROM("
                + "SELECT * FROM("
                + "SELECT ROWNUM as rnum,FORBIDDENWORD.* FROM FORBIDDENWORD"
                + ") WHERE rnum>=?"
                + ") WHERE rnum<=?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, startNum);
            pstm.setInt(2, endNum);
            rs = pstm.executeQuery();
            while (rs.next()) {
                FBWDto dto = new FBWDto();
                dto.setFBWords(rs.getString(2));
                dto.setReason(rs.getString(3));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstm);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        return list;
    }

    @Override
    public boolean AddFBW(FBWDto dto, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;
        boolean result = true;
        String sql = "INSERT INTO FORBIDDENWORD VALUES(?,?)";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, dto.getFBWords());
            pstm.setString(2, dto.getReason());

            res = pstm.executeUpdate();

            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }

        return result;
    }

    @Override
    public boolean DeleteFBW(String FBWords, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;
        boolean result = true;
        String sql = "DELETE FROM FORBIDDENWORD WHERE FBWORDS = ?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, FBWords);

            res = pstm.executeUpdate();

            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }


        return result;
    }

    @Override
    public UserDto UserSelect(int UserNum, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        UserDto dto = new UserDto();
        String sql = "SELECT * FROM USERTB WHERE USERNUM=?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, UserNum);

            rs = pstm.executeQuery();
            if (rs.next()) {
                dto.setUserNum(rs.getInt(1));
                dto.setID(rs.getString(2));
                dto.setPW(rs.getString(3));
                dto.setEmail(rs.getString(4));
                dto.setPhone(rs.getString(5));
                dto.setName(rs.getString(6));
                dto.setBirth(rs.getDate(7));
                dto.setGender(rs.getString(8));
                dto.setNickName(rs.getString(9));
                dto.setImgURL(rs.getString(10));
                dto.setStatus(rs.getString(11));
                dto.setGrade(rs.getString(12));
                dto.setUserDate(rs.getDate(13));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstm);
        }

        return dto;
    }

    @Override
    public boolean UserSecession(int UserNum, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;
        boolean result = true;
        String sql = "UPDATE USERTB SET STATUS=? WHERE USERNUM=?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, "N");
            pstm.setInt(2, UserNum);

            res = pstm.executeUpdate();

            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }
        return result;

    }

    @Override
    public boolean DeleteDeclaration(int reviewNum, Connection con) {
        PreparedStatement pstm = null;
        boolean result = true;
        int res = 0;
        String sql = "DELETE FROM REVIEW WHERE REVIEWNUM=?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, reviewNum);

            res = pstm.executeUpdate();
            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
        }
        return result;
    }

    @Override
    public boolean UserImgUdate(UserDto dto, Connection con) {
        PreparedStatement pstm = null;
        boolean result = false;
        int res = 0;
        String sql = "UPDATE USERTB SET IMGURL=? WHERE USERNUM=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, dto.getImgURL());
            pstm.setInt(2, dto.getUserNum());

            res = pstm.executeUpdate();
            if (res > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<NoticeDto> NoticeSelect(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<NoticeDto> list = new ArrayList<>();
        String sql = " SELECT N.*, U.NICKNAME FROM NOTICE N\n" +
                "    JOIN USERTB U on N.UserNum = u.USERNUM ";

        try {
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NoticeDto temp = new NoticeDto(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5), resultSet.getInt(6),
                        resultSet.getString(7));

                list.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


        return list;
    }

    public int FBWCount(Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) as count FROM FORBIDDENWORD";
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

    public int UserCount(Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) as count FROM USERTB";
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

    public int DecrationCount(Connection con) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) as count FROM REVIEW WHERE COUNT>=1";
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
