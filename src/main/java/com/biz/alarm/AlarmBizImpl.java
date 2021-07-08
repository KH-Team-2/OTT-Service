package com.biz.alarm;/*
import com.biz.alarm.AlarmBiz;
import com.dao.alarm.AlarmDao;
import com.dto.UserDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class AlarmBizImpl implements AlarmBiz {

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

*/
