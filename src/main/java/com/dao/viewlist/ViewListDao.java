package com.dao.viewlist;

import com.dto.WHDto;

import java.sql.Connection;
import java.util.List;

public interface ViewListDao {

    String ViewListLoadingSQL = " SELECT A.*, C2.Title, C2.MOVIENUM\n" +
            "FROM WATCHHISTORY A\n" +
            "JOIN CONTENTS C2 on A.MovieNum = C2.MOVIENUM\n" +
            "WHERE USERNUM = ? AND A.STATUS = 'F' ORDER BY A.HistoryNum DESC ";
    String ViewListDeleteSQL = " UPDATE WATCHHISTORY SET STATUS='T' WHERE HISTORYNUM=? ";
    String ViewListAddSQL = " INSERT INTO WATCHHISTORY VALUES( WATCH_SQ.NEXTVAL, ?, ?, SYSDATE, 'F') ";
    String ViewOverlapSQL = " DELETE\n" +
            "FROM WatchHistory a\n" +
            "WHERE ROWID\n" +
            "          < (SELECT MAX (ROWID)\n" +
            "             FROM WatchHistory b\n" +
            "             WHERE b.MovieNum = a.MovieNum AND a.UserNum = b.UserNum ) ";

    String ViewListCntSQL = " SELECT COUNT(*) FROM WatchHistory WHERE UserNum = ? ";

    String ViewListPagingSQL = " SELECT A.*, b.Title\n" +
            "FROM (\n" +
            "         SELECT *\n" +
            "         FROM (\n" +
            "                  SELECT ROWNUM as rnum, WatchHistory.*\n" +
            "                  FROM WatchHistory\n" +
            "                  WHERE ROWNUM >= 1\n" +
            "                    AND UserNum = ?\n" +
            "                    AND Status = 'F'\n" +
            "              )\n" +
            "     ) A\n" +
            "         JOIN CONTENTS b on a.MovieNum = b.MovieNum\n" +
            "WHERE  rnum >= ? AND rnum <= ?\n" +
            "ORDER BY A.HistoryNum DESC ";


    public List<WHDto> ViewListLoading(int usernum, Connection con);

    public boolean ViewListDelete(int historynum, Connection con);

    public boolean ViewListAdd(int movienum, int usernum, Connection con);

    public int ViewListCount(int usernum, Connection con);

    public List<WHDto> ViewListPaging(int usernum, int page, Connection con);
}
