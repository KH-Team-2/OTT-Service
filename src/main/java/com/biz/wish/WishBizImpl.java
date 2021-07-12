package com.biz.wish;

import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;
import com.dao.wish.WishDao;
import com.dao.wish.WishDaoImpl;
import com.dto.WishListDto;

public class WishBizImpl implements WishBiz{
	private WishDao dao = new WishDaoImpl();

	@Override
	public boolean WishAdd(int usernum, int movienum) {
		Connection con = getConnection();
		
		boolean res = dao.WishAdd(con, usernum, movienum);

		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return res;
	}

	@Override
	public List<WishListDto> WishList(int usernum) {
		Connection con = getConnection();
		
		List<WishListDto> list = dao.WishList(con, usernum);
		
		close(con);
		
		return list;
	}

	@Override
	public boolean WishDelete(int usernum, int movienum) {
		Connection con = getConnection();
		
		boolean res = dao.WishDelete(con, usernum, movienum);
		
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);

		return res;
	}
	
	@Override
	public int WishCount(int usernum) {
		
		Connection con = getConnection();
		
		int res = dao.WishCount(con, usernum);
		
		close(con);

		return res;
		
	}

}
