package com.dao.alarm;

import java.sql.Connection;
import java.util.List;

import com.dto.AlarmDto;
import com.dto.UserDto;
	
public interface AlarmDao {
	
	public boolean AlarmSwitch(Connection con, int alarmnum, int movienum);
	public List<AlarmDto> AlarmLoading(Connection con, int alarmnum);
}
