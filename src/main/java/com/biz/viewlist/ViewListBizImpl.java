package com.biz.viewlist;

import com.dao.viewlist.ViewListDao;
import com.dao.viewlist.ViewListDaoImpl;
import com.dto.WHDto;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

public class ViewListBizImpl implements ViewListBiz{	
	
	ViewListDao dao = new ViewListDaoImpl();
	
	
	@Override
	public List<WHDto> ViewListLoading(int usernum) {
		Connection con = getConnection();
		
		List<WHDto> list = dao.ViewListLoading(usernum, con);
		
		close(con);
		
		return list;
	}

	@Override
	public boolean ViewListDelete(int historynum) {
		Connection con = getConnection();
		
		boolean res = dao.ViewListDelete(historynum, con);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public boolean ViewListAdd(int movienum, int usernum) {
		Connection connection = getConnection();

		boolean res = dao.ViewListAdd(movienum, usernum, connection);

		if (res) {
			commit(connection);
		} else {
			rollback(connection);
		}
		close(connection);

		return res;
	}

}
