
package com.biz.alarm;
import com.dao.alarm.AlarmDao;
import com.dto.AlarmDto;
import com.dto.UserDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class AlarmBizImpl implements AlarmBiz {

	private AlarmDao dao;

	@Override
	public boolean AlarmSwitch(int usernum, int movienum) {
		
		Connection con = getConnection();
		boolean res = dao.AlarmSwitch(con, usernum, movienum);	
		close(con);
		return res;
	}
	
	@Override
	public List<AlarmDto> AlarmLoading(int usernum) {
		Connection con = getConnection();
		List<AlarmDto> list = dao.AlarmLoading(con, usernum);	
		close(con);
		return list;
	}
	
}
