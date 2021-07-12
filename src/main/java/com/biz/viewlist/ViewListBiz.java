package com.biz.viewlist;

import com.dto.WHDto;

import java.util.List;

public interface ViewListBiz {
	public List<WHDto> ViewListLoading(int usernum);
	public boolean ViewListDelete(int historynum);
	public boolean ViewListAdd(int movienum, int usernum);



}
