package com.biz.wish;

import java.util.List;

import com.dto.WishListDto;

public interface WishBiz{
	
	public boolean WishAdd(int usernum, int movienum);
	public List<WishListDto> WishList(int usernum);
	public boolean WishDelete(int usernum, int movienum);
	
}
