package com.biz.alarm;


import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.dao.alarm.AlarmDao;
import com.dto.AlarmDto;
import com.dto.UserDto;

public class AlarmBizImpl implements AlarmBiz{

	@Override
	public void AlarmSwitch(int num) {
		Connection con = getConnection();
		
		
	}

	@Override
	public List<UserDto> AlarmLoading() {
		Connection con = getConnection();
		List<UserDto> list = AlarmDao.alarmloading(con);
		
		
		close(con);
		return list;
	}
	
}
