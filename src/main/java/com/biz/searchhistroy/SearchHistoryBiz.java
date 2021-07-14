package com.biz.searchhistroy;

import java.util.List;

import com.dto.FBWDto;

public interface SearchHistoryBiz {
	public List<FBWDto> SearchHistoryView();
	public boolean SearchHistoryDelete();

}
