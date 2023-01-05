package com.spring.moon.noticeBoard.model;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeBoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertNotice(NoticeBoardVO vo) {
		String sql = "insert into noticeBoard(guestNo, n_title, n_content) values(?,?,?)";
		int cnt = jdbcTemplate.update(sql, vo.getGuestNo(), vo.getN_title(), vo.getN_content());

		return cnt;
	}

	public List<NoticeBoardVO> selectAll(String condition, String keyword) {
		String sql = "select n.noticeNo as noticeNo, n.guestNo as guestNo, n.n_title as n_title, n.n_regdate as n_regdate, n.n_content as n_content, g.userid as userid"
				+ " from NoticeBoard n, guest g";

		if (keyword != null && !keyword.isEmpty()) {
			sql += " where " + condition + " like '%" + keyword + "%'" + " and n.guestno = g.guestno"
					+ " order by noticeNo desc";
		} else {
			sql += " where n.guestno = g.guestno order by noticeNo desc";
		}
		List<NoticeBoardVO> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<NoticeBoardVO>(NoticeBoardVO.class));
		System.out.println(
				"글 전체 조회 결과 list.size=" + list.size() + ", 매개변수 condition=" + condition + ", keyword=" + keyword);
		return list;
	}

	public NoticeBoardVO selectByUserid(int guestNo) {
		String sql = "select * from noticeBoard where guestno = " + guestNo;
		NoticeBoardVO vo = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<NoticeBoardVO>(NoticeBoardVO.class));
		return vo;
	}

	public NoticeBoardVO selectByNo(int noticeNo) {
		String sql = "select * from noticeBoard where noticeNo = " + noticeNo;
		NoticeBoardVO vo = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<NoticeBoardVO>(NoticeBoardVO.class));
		return vo;
	}

	public int updateNoticeBoard(NoticeBoardVO vo, int noticeNo) {
		String sql = "update noticeBoard set n_title =?, n_content=? where noticeNo = ?";
		int cnt = jdbcTemplate.update(sql, vo.getN_title(), vo.getN_content(), noticeNo);
		return cnt;
	}
	// 해당 게스트의 비밀번호와 일치하는지 확인을 해야함

	public int deleteNoticeBoard(int noticeNo) {
		String sql = "delete from noticeBoard where noticeNo =?";
		int cnt = jdbcTemplate.update(sql, noticeNo);

		return cnt;
	}

	public int updateN_count(int noticeNo) {
		String sql = "update noticeBoard set n_count = n_count+1 where noticeNo=?";
		int cnt = jdbcTemplate.update(sql, noticeNo);

		return cnt;
	}
}
