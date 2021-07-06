package com.dao.viewlist;

import java.sql.Connection;
import java.util.List;

import com.dto.WHDto;

public interface ViewListDao {
	
	String ViewListLoadingSQL = " SELECT * FROM SP_WATCHHISTORY WHERE USERNUM=? ";
	String ViewListDeleteSQL = " DELETE FROM SP_WATCHHISTORY WHERE USERNUM=? AND MOVIENUM=? ";
	
	
	public List<WHDto> ViewListLoading(int usernum, Connection con);
	public boolean ViewListDelete(WHDto dto, Connection con);
	
}
