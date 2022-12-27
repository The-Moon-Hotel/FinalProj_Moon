package com.spring.moon.askBoard.model;

import java.util.List;

public interface CommentService {
	List<CommentVO> selectComment(int askNo);
	
	int insertcomment(CommentVO vo);
	
	int deleteComment(int no);
}
