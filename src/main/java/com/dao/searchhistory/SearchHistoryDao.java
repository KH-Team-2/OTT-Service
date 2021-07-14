package com.dao.searchhistory;

import com.dto.FBWDto;

import java.sql.Connection;
import java.util.List;

public interface SearchHistoryDao {
	String SearchHistoryViewSQL = "SELECT FROM FBWORDS WHERE FBWORDNUM=?";
	String SearchHistroyDeleteSQL= " DELETE FROM FBWORDS WHERE FBWORDNUM=?";
	List<FBWDto> SearchHistoryView(Connection con, String FBWords, String Reason, int FBWordsNum);
	boolean SearchHistroyDelete(Connection con, String FBWords, String Reason, int FBWordsNum);
}
