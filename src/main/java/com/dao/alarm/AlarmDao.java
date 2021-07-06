package com.dao.alarm;

import java.sql.Connection;
import java.util.List;
import com.dto.AlarmDto;
	
public interface AlarmDao {
	public void AlarmSwitch(Connection con, int alarm);
	public List<AlarmDto> AlarmLoading(Connection con,AlarmDto dto);

}
