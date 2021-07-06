package com.dao.review;

import java.sql.Connection;
import java.util.List;

import com.dto.ReviewDto;

public interface ReviewDao {
	
	public boolean ReviewWrite ( Connection con, ReviewDto rdto );
	public boolean ReviewUpdate ( Connection con, ReviewDto rdto );
	public boolean ReviewDelete ( Connection con, int count ) ;
	public List<ReviewDto> ReviewList ( Connection con, int movienum );
	public boolean ReviewReport ( Connection con, int reviewnum );
}
