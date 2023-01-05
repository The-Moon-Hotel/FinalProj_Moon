package com.spring.moon.noticeBoard.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardServiceImp implements NoticeBoardService{
	@Autowired
	NoticeBoardDAO noticeBoardDAO;

	@Override
	public int insertNotice(NoticeBoardVO vo)  {
			return this.noticeBoardDAO.insertNotice(vo);
	}
	@Override
	public List<NoticeBoardVO> selectAll(String condition, String keyword)  {
		return this.noticeBoardDAO.selectAll(condition, keyword);
	}
	@Override
	public NoticeBoardVO selectByNo(int noticeNo)  {
		return this.noticeBoardDAO.selectByNo(noticeNo);
	}
	@Override
	public int updateNoticeBoard(NoticeBoardVO vo,int noticeNo)  {
		return this.noticeBoardDAO.updateNoticeBoard(vo, noticeNo);
	}
	@Override
	public int deleteNoticeBoard(int noticeNo)  {
		return this.noticeBoardDAO.deleteNoticeBoard(noticeNo);
	}
	@Override
	public int updateN_count(int noticeNo)  {
		return this.noticeBoardDAO.updateN_count(noticeNo);
	}

}
