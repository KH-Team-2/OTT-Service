package com.dao.searchhistory;

import java.sql.Connection;
import java.util.List;

import com.dto.FBWDto;

public interface SearchHistoryDao {
	String SearchHistoryViewSQL = "SELECT FROM FBWORDS WHERE FBWORDNUM=?";
	String SearchHistroyDeleteSQL= " DELETE FROM FBWORDS WHERE FBWORDNUM=?";
	List<FBWDto> SearchHistoryView(Connection con, String FBWords, String Reason, int FBWordsNum);
	boolean SearchHistroyDelete(Connection con, String FBWords, String Reason, int FBWordsNum);
}
