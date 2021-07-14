package com.biz.searchhistroy;

import java.util.List;

import com.dto.FBWDto;

public interface SearchHistoryBiz {
	public List<FBWDto> SearchHistoryView(String FBWords,String Reason,int FBWordsNum);
	public boolean SearchHistoryDelete(String FBWords,String Reason,int FBWordsNum);

}
