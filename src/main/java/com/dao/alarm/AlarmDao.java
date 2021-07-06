package com.dao.alarm;

import java.sql.Connection;
import java.util.List;
import com.dto.UserDto;
	
public interface AlarmDao {
	public void AlarmSwitch(Connection con, int alarm);
	public List<UserDto> AlarmLoading(Connection con,UserDto dto);

}
