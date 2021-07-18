package com.biz.wish;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.dao.wish.WishDao;
import com.dao.wish.WishDaoImpl;
import com.dto.WishDto;
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
	public List<WishDto> WishList(int usernum,int page) {
		Connection con = getConnection();
		
		List<WishDto> list = dao.WishList(con, usernum,page);
		
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

	@Override
	public WishListDto wishfound(int usernum, int movinum) {
		Connection con = getConnection();
		WishListDto dto = dao.wishfound(usernum, movinum, con);
		close(con);
		return dto;
	}

	@Override
	public boolean WishNumDel(int wishnum) {
		Connection con = getConnection();
		boolean res = dao.WishNumDel(wishnum, con);
		if(res) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return res;
	}

	@Override
	public boolean WishMulDel(String usernum, String[] wishnum) {
		Connection con = getConnection();

		boolean res = dao.WishMulDel(usernum, wishnum, con);
		
		if(res) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return res;
	}

}
