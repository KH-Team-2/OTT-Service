package com.biz.wish;

import java.util.List;

import com.dto.WishDto;
import com.dto.WishListDto;

public interface WishBiz{
	
	public boolean WishAdd(int usernum, int movienum);
	public List<WishDto> WishList(int usernum,int page);
	public boolean WishDelete(int usernum, int movienum);
	public int WishCount(int usernum);
	public WishListDto wishfound(int usernum,int movinum);
}
