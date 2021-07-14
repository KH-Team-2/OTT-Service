package com.dao.searchhistory;

import java.sql.Connection;
import java.util.List;

import com.dto.FBWDto;

public interface SearchHistoryDao {
	void SearchHistroyDelete(Connection con);
	List<FBWDto> SearchHistoryView(Connection con);
}