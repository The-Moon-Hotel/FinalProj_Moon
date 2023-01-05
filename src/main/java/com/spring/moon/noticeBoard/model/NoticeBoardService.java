package com.spring.moon.noticeBoard.model;

import java.util.List;

public interface NoticeBoardService {
	int insertNotice(NoticeBoardVO vo) ;

	List<NoticeBoardVO> selectAll(String condition, String keyword) ;

	NoticeBoardVO selectByNo(int noticeNo) ;

	int updateNoticeBoard(NoticeBoardVO vo,int noticeNo) ;

	int deleteNoticeBoard(int noticeNo) ;

	int updateN_count(int noticeNo) ;

}
