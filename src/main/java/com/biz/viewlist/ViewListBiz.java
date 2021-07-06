package com.biz.viewlist;

import java.util.List;

import com.dto.WHDto;

public interface ViewListBiz {
	public List<WHDto> ViewListLoading(int usernum);
	public boolean ViewListDelete(WHDto dto);
	

}
