package com.biz.admin;

import com.dto.NoticeDto;

public interface NoticeWriteBiz {
	public boolean NoticeWrite(NoticeDto dto);
	public boolean NoticeUpdate(NoticeDto dto);
}
