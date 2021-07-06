package com.biz.review;

import java.util.List;

import com.dto.ReviewDto;

public interface ReviewBiz {
	
	public boolean ReviewWrite ( ReviewDto rdto );
	public boolean ReviewUpdate ( ReviewDto rdto );
	public boolean ReviewDelete ( int reviewnum ) ;
	public List<ReviewDto> ReviewList ( int movienum );
	public boolean ReviewReport ( int reviewnum );
}
