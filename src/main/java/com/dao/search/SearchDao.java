package com.dao.search;

import com.dto.ContentsDto;

import java.sql.Connection;
import java.util.List;

public interface SearchDao {
    List<ContentsDto> SearchList(Connection con, String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre);
}
