
package com.biz.alarm;
import com.biz.alarm.AlarmBiz;
import com.dao.alarm.AlarmDao;
import com.dto.UserDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class AlarmBizImpl implements AlarmBiz {

	private AlarmDao dao;

	@Override
	public void AlarmSwitch(int num) {
		Connection con = getConnection();
		
		
	}

	@Override
	public List<UserDto> AlarmLoading(int alarmnum) {
		Connection con = getConnection();

			List<UserDto> list = dao.AlarmLoading(con, alarmnum);
			
		

		
		
		close(con);
		return list;
	}
	
}
