package com.biz.viewlist;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dao.viewlist.ViewListDao;
import com.dao.viewlist.ViewListDaoImpl;
import com.dto.WHDto;
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
	public boolean ViewListDelete(WHDto dto) {
		Connection con = getConnection();
		
		boolean res = dao.ViewListDelete(dto, con);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

}
