package com.biz.search;

import com.dto.ContentsDto;
import com.dto.FBWDto;

import java.util.List;

public interface SearchBiz {
	

    List<ContentsDto> SearchList(String searchBar, String startdate, String enddate, double startgrade, double endgrade, String genre);
    List<ContentsDto> SearchNewList();
    List<ContentsDto> SearchPopList();

    ContentsDto SearchDetail(String title);

    List<FBWDto> SearchFBW(String title);
    
    List<ContentsDto> SearchAllList(int page);
    public int ContentsListCount();
}
