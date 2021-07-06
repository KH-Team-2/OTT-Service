package com.biz.search;

import java.util.List;

import com.dto.ContentsDto;

public interface SearchBiz {
	
	public List<ContentsDto> SearchList ( String searchname );

}
