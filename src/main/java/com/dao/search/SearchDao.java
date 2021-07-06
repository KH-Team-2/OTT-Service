package com.dao.search;

import java.sql.Connection;
import java.util.List;

import com.dto.ContentsDto;

public interface SearchDao {
	
	public List<ContentsDto> SearchList ( Connection con, String searchname );

}
