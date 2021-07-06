package com.biz.alarm;


import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.dao.alarm.AlarmDao;
import com.dto.AlarmDto;

public class AlarmBizImpl implements AlarmBiz{

	@Override
	public void AlarmSwitch(int num) {
		Connection con = getConnection();
		
		
	}

	@Override
	public List<AlarmDto> AlarmLoading() {
		Connection con = getConnection();
		List<AlarmDto> list = dao.AlarmList(dto, con);
		
		
		close(con);
		return list;
	}
	
}
