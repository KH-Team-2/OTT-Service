package com.biz.alarm;

import java.util.List;

import com.dto.AlarmDto;

public interface AlarmBiz {
	
	public boolean AlarmSwitch( int usernum, int movienum );
	public List<AlarmDto> AlarmLoading(int usernum);
}
