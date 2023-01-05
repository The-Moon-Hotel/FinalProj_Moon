package com.spring.moon.reviewBoard.model;

import java.util.List;

public interface ReviewBoardService {
	 int insertReview(ReviewBoardVO vo);

	 List<ReviewBoardVO> selectAll(String condition, String keyword) ;

	 ReviewBoardVO selectByNo(int reviewNo) ;

	 int updatereviewBoard(ReviewBoardVO vo,int reviewNo) ;

	 int deletereviewBoard(int reviewNo) ;
}
