package com.dao.viewlist;

import com.dto.WHDto;

import java.sql.Connection;
import java.util.List;

public interface ViewListDao {
	
	String ViewListLoadingSQL = " SELECT A.*, C2.Title, C2.MOVIENUM\n" +
			"FROM WATCHHISTORY A\n" +
			"JOIN CONTENTS C2 on A.MovieNum = C2.MOVIENUM\n" +
			"WHERE USERNUM = ? AND A.STATUS = 'F' ORDER BY A.HistoryNum DESC ";
	String ViewListDeleteSQL = " UPDATE FROM WATCHHISTORY SET STATUS='T' WHERE HISTORYNUM=? ";
	String ViewListAddSQL = " INSERT INTO WATCHHISTORY VALUES( WATCH_SQ.NEXTVAL, ?, ?, SYSDATE, 'F') ";
	String ViewOverlapSQL = " DELETE\n" +
			"FROM WatchHistory a\n" +
			"WHERE ROWID\n" +
			"          < (SELECT MAX (ROWID)\n" +
			"             FROM WatchHistory b\n" +
			"             WHERE b.MovieNum = a.MovieNum) ";
	
	
	public List<WHDto> ViewListLoading(int usernum, Connection con);
	public boolean ViewListDelete(int historynum, Connection con);
	public boolean ViewListAdd(int movienum, int usernum, Connection con);
	
}
