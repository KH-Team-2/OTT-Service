package com.dao.wish;

import java.sql.Connection;
import java.util.List;

import com.dto.WishDto;
import com.dto.WishListDto;

public interface WishDao {
	
	String WishAddSQL = " INSERT INTO WISHLIST VALUES(WISH_SQ.NEXTVAL, ?, ?, SYSDATE, ?)";
	String WishListSQL = "SELECT WISHNUM,USERNUM,TITLE,MOVIEADDR,WISHDATE,ALARM FROM WISHLIST JOIN CONTENTS USING(MOVIENUM) WHERE USERNUM=? ORDER BY WISHNUM DESC";
	String WishDeleteSQL = " DELETE FROM WISHLIST WHERE USERNUM=? AND MOVIENUM=? ";
	String WishCountSQL = " SELECT COUNT(*) as count FROM WISHLIST WHERE USERNUM = ? ";
	String WishMulDelSQL = " DELETE FROM WISHLIST WHERE WISHNUM=? AND USERNUM=?";
	
	public boolean WishAdd(Connection con, int usernum, int movienum);
	public List<WishDto> WishList(Connection con, int usernum,int page);
	public boolean WishDelete(Connection con, int usernum, int movienum);
	public int WishCount(Connection con, int usernum);
	public WishListDto wishfound(int usernum,int movinum,Connection con);
	public boolean WishNumDel(int wishnum,Connection con);
	public boolean WishMulDel(String usernum, String[] wishnum, Connection con);
}
