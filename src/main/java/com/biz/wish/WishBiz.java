package com.biz.wish;

import java.util.List;

import com.dto.WishDto;

public interface WishBiz{
	
	public boolean WishAdd(int usernum, int movienum);
	public List<WishDto> WishList(int usernum,int page);
	public boolean WishDelete(int usernum, int movienum);
	public int WishCount(int usernum);
	
}
