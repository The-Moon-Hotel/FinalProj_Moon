package com.spring.moon.askBoard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{
	@Autowired
	CommentDAO commentDAO;

	@Override
	public List<CommentVO> selectComment(int askNo) {
		return this.commentDAO.selectComment(askNo);
	}

	@Override
	public int insertcomment(CommentVO vo) {
		return this.commentDAO.insertcomment(vo);
	}

	@Override
	public int deleteComment(int no) {
		return this.commentDAO.deleteComment(no);
	}

	@Override
	public int deleteAskNoComment(int askNo) {
		return this.commentDAO.deleteAskNoComment(askNo);
	}
	
}
