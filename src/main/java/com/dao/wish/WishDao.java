package com.dao.wish;

import java.sql.Connection;
import java.util.List;

import com.dto.WishListDto;

public interface WishDao {
	String WishAddSQL = " INSERT INTO SP_WISHLIST VALUES(SAMPLE_WISH_SQ, ?, ?, SYSDATE, ?)";
	String WishListSQL = " SELECT * FROM SP_WISHLIST WHERE USERNUM=? ORDER BY WISHDATE DESC ";
	String WishDeleteSQL = " DELETE FROM SP_WISHLIST WHERE USERNUM=? AND MOVIENUM=? ";
	
	public boolean WishAdd(Connection con, int usernum, int movienum);
	public List<WishListDto> WishList(Connection con, int usernum);
	public boolean WishDelete(Connection con, int usernum, int movienum);
	

}
