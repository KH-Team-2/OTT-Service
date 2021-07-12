package com.dao.wish;

import java.sql.Connection;
import java.util.List;

import com.dto.WishListDto;

public interface WishDao {
	
	String WishAddSQL = " INSERT INTO WISHLIST VALUES(WISH_SQ.NEXTVAL, ?, ?, SYSDATE, ?)";
	String WishListSQL = " SELECT * FROM WISHLIST WHERE USERNUM=? ORDER BY WISHDATE DESC ";
	String WishDeleteSQL = " DELETE FROM WISHLIST WHERE USERNUM=? AND MOVIENUM=? ";
	String WishCountSQL = " SELECT COUNT(*) FROM WISHLIST WHERE USERNUM = ? ";
	
	public boolean WishAdd(Connection con, int usernum, int movienum);
	public List<WishListDto> WishList(Connection con, int usernum);
	public boolean WishDelete(Connection con, int usernum, int movienum);
	public int WishCount(Connection con, int usernum);
}
