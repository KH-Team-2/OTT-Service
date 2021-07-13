package com.biz.review;

import com.dto.ReviewDto;

import java.util.List;

public interface ReviewBiz {
	
	public boolean ReviewWrite ( int usernum, int movienum, String reviewinfo );
	public boolean ReviewUpdate ( ReviewDto rdto );
	public boolean ReviewDelete ( int reviewnum ) ;
	public List<ReviewDto> ReviewList ( int movienum );
	public boolean ReviewReport ( int reviewnum );
	int RiviewCount(int movienum);
}
