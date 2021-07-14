
package com.biz.alarm;
import com.dao.alarm.AlarmDao;
import com.dto.UserDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class AlarmBizImpl implements AlarmBiz {

	private AlarmDao dao;

	@Override
	public boolean AlarmSwitch(int num) {
		Connection con = getConnection();
		boolean res = dao.AlarmSwitch(con, num);
		
		close(con);
		return res;
		
	}

	@Override
	public List<UserDto> AlarmLoading() {
		Connection con = getConnection();

	
	List<UserDto> list = dao.AlarmLoading(con);	
		close(con);
		return list;
	}
	
}
