package com.biz.wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dto.WishListDto;

public interface WishBiz{
	
	public boolean WishAdd(int usernum, int movienum);
	public List<WishListDto> WishList(int usernum);
	public boolean WishDelete(int usernum, int movienum);
	public int WishCount(int usernum);
	
}
