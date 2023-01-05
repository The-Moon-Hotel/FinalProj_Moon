package com.spring.moon.reviewBoard.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewBoardServiceImp implements ReviewBoardService{
	@Autowired
	ReviewBoardDAO reviewBoardDAO;
	
	@Override
	public int insertReview(ReviewBoardVO vo)  {
			return this.reviewBoardDAO.insertReview(vo);
	}
	@Override
	public List<ReviewBoardVO> selectAll(String condition, String keyword)  {
		return this.reviewBoardDAO.selectAll(condition, keyword);
	}
	@Override
	public ReviewBoardVO selectByNo(int reviewNo)  {
		return this.reviewBoardDAO.selectByNo(reviewNo);
	}
	@Override
	public int updatereviewBoard(ReviewBoardVO vo,int reviewNo)  {
		return this.reviewBoardDAO.updatereviewBoard(vo, reviewNo);
	}
	@Override
	public int deletereviewBoard(int reviewNo)  {
		return this.reviewBoardDAO.deletereviewBoard(reviewNo);
	}
}
