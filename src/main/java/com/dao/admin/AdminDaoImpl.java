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

import static common.JDBCTemplate.*;

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
    public List<UserDto> AdminUserView(Connection con,int page) {
    	int startNum = (page - 1) * 10 + 1;
        int endNum = page * 10;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UserDto> list = new ArrayList<UserDto>();
        String sql = "SELECT * FROM(SELECT* FROM(SELECT ROWNUM as rnum,USERTB.* FROM USERTB WHERE STATUS='Y')WHERE rnum>=?)WHERE rnum<=?";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, startNum);
            pstm.setInt(2, endNum);
            System.out.println("query 준비" + sql);
            rs = pstm.executeQuery();
            System.out.println("query 실행 및 리턴");
            while (rs.next()) {
                UserDto dto = new UserDto();
                dto.setUserNum(rs.getInt(2));
                dto.setID(rs.getString(3));
                dto.setPW(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setPhone(rs.getString(6));
                dto.setName(rs.getString(7));
                dto.setBirth(rs.getDate(8));
                dto.setGender(rs.getString(9));
                dto.setNickName(rs.getString(10));
                dto.setImgURL(rs.getString(11));
                dto.setStatus(rs.getString(12));
                dto.setGrade(rs.getString(13));
                dto.setUserDate(rs.getDate(14));

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
        String sql = "UPDATE USERTB SET ID=?,PW=?,EMAIL=?,PHONE=?,NAME=?,BIRTH=?,GENDER=?,NICKNAME=?, GRADE=? WHERE USERNUM=?";
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
            pstm.setString(9, dto.getGrade());
            pstm.setInt(10, dto.getUserNum());

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
        
        String sql2 = "UPDATE USERTB SET STATUS=? WHERE USERNUM=?";

        try {
            pstm = con.prepareStatement(sql2);
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

    @Override
    public boolean DeleteNotice(Connection connection, int noticenum) {
        int res = 0;
        PreparedStatement preparedStatement = null;
        String sql = " DELETE FROM NOTICE WHERE NOTICENUM = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noticenum);

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }

        return res > 0;
    }

    @Override
    public int CountNotice(Connection connection) {
        int res = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = " SELECT COUNT(*) as count FROM NOTICE ";

        try {
            preparedStatement = connection.prepareStatement(sql);

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
    public List<NoticeDto> NoticePagingList(Connection con, int page) {
        int startNum = (page - 1) * 10 + 1;
        int endNum = page * 10;
        String sql = " SELECT A.*, u.Nickname\n" +
                "FROM (SELECT *\n" +
                "FROM (\n" +
                "    SELECT ROWNUM as rnum, NOTICE.*\n" +
                "    FROM NOTICE ORDER BY NoticeNum DESC )\n" +
                "WHERE rnum >= ?\n" +
                "    ) A\n" +
                "    JOIN USERTB u\n" +
                "on A.UserNum = u.USERNUM\n" +
                "WHERE rnum <= ? ORDER BY A.NoticeNum DESC ";

        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        List<NoticeDto> res = new ArrayList<>();

        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, startNum);
            pstm.setInt(2, endNum);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                NoticeDto temp = new NoticeDto(resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getDate(6), resultSet.getInt(7),
                        resultSet.getString(8));

                res.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(pstm);
        }

        return res;
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


    @Override
    public boolean NoticeWrite(NoticeDto dto, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;

        try {
            pstm = con.prepareStatement(NoticeWriteSQL);
            //usernum title content
            pstm.setInt(1, dto.getUsernum());
            pstm.setString(2, dto.getTitle());
            pstm.setString(3, dto.getContent());
            System.out.println("03. query 준비: " + NoticeWriteSQL);

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
    public boolean NoticeUpdate(NoticeDto dto, Connection con) {
        PreparedStatement pstm = null;
        int res = 0;

        try {
            pstm = con.prepareStatement(NoticeUpdateSQL);
            pstm.setString(1, dto.getTitle());
            pstm.setString(2, dto.getContent());
            pstm.setInt(3, dto.getNum());
            System.out.println("03. query 준비");

            res = pstm.executeUpdate();
            System.out.println("04. query 실행 및 리턴");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstm);
            System.out.println("5. db 종료\n");
        }

        return res > 0;
    }

    @Override
    public NoticeDto NoticeSelectOne(Connection connection, int noticenum) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        NoticeDto dto = null;
        String countSql = " UPDATE NOTICE SET COUNT = COUNT+1 WHERE NOTICENUM = ? ";
        String sql = " SELECT N.*, U.NICKNAME\n" +
                "FROM NOTICE N\n" +
                "         JOIN USERTB U on N.UserNum = U.USERNUM\n" +
                "WHERE NoticeNum = ? ";

        try {
            preparedStatement = connection.prepareStatement(countSql);
            preparedStatement.setInt(1, noticenum);

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, noticenum);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dto = new NoticeDto(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5), resultSet.getInt(6),
                        resultSet.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
        }


        return dto;
    }
	@Override
	public boolean NoticeDelete(String[] noticenum, Connection con) {
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(NoticeDeleteSQL);
			
			for(int i=0; i<noticenum.length; i++) {
				pstm.setString(1, noticenum[i]);
				
				pstm.addBatch();
				System.out.println("03. query 준비: "+NoticeDeleteSQL+" (삭제번호: "+noticenum[i]+")");
				
			}
			cnt = pstm.executeBatch();
			System.out.println("04. query 실행 및 리턴");
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;		
				}
			}
			if(noticenum.length == res) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return noticenum.length == res;
	}


}
