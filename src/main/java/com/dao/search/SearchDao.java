package com.dao.search;

import com.dto.ContentsDto;

import java.sql.Connection;
import java.util.List;

public interface SearchDao {
    public List<ContentsDto> SearchList(Connection con, String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre);
    public List<ContentsDto> SearchNewList(Connection con);
    public List<ContentsDto> SearchPopList(Connection con);
    public ContentsDto SearchDetail(Connection con, String title);
}
