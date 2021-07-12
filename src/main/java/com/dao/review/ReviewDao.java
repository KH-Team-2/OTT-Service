package com.dao.review;

import com.dto.ReviewDto;

import java.sql.Connection;
import java.util.List;

public interface ReviewDao {
	
	public boolean ReviewWrite ( Connection con, int usernum, int movienum, String reviewinfo );
	public boolean ReviewUpdate ( Connection con, ReviewDto rdto );
	public boolean ReviewDelete ( Connection con, int count ) ;
	public List<ReviewDto> ReviewList ( Connection con, int movienum );
	public boolean ReviewReport ( Connection con, int reviewnum );
}
