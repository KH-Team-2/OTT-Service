package com.biz.search;

import com.dto.ContentsDto;

import java.util.List;

public interface SearchBiz {
	

    List<ContentsDto> SearchList(String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre);
    List<ContentsDto> SearchNewList();
    List<ContentsDto> SearchPopList();

    ContentsDto SearchDetail(String title);
}
