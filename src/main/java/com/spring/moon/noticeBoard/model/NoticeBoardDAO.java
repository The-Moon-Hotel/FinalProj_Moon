package com.spring.moon.noticeBoard.model;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeBoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertNotice(NoticeBoardVO vo) {
		String sql = "insert into noticeboard(guestNo, n_title, n_content) values(?,?,?)";
		int cnt = jdbcTemplate.update(sql, vo.getGuestNo(), vo.getN_title(), vo.getN_content());

		return cnt;
	}

	public List<NoticeBoardVO> selectAll(String condition, String keyword) {
		String sql = "select n.*, g.userid from noticeboard n join guest g on n.guestNo = g.guestNo";
		String orderBy = " order by noticeNo desc";
		BeanPropertyRowMapper<NoticeBoardVO> mapper = new BeanPropertyRowMapper<>(NoticeBoardVO.class);
		if (keyword == null || keyword.isEmpty()) {
			return jdbcTemplate.query(sql + orderBy, mapper);
		}
		String column = Map.of("userid", "g.userid", "regdate", "n.n_regdate")
				.getOrDefault(condition == null ? "" : condition, "n.n_title");
		return jdbcTemplate.query(sql + " where " + column + " like ?" + orderBy, mapper, "%" + keyword + "%");
	}

	public NoticeBoardVO selectByUserid(int guestNo) {
		String sql = "select * from noticeboard where guestno = " + guestNo;
		NoticeBoardVO vo = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<NoticeBoardVO>(NoticeBoardVO.class));
		return vo;
	}

	public NoticeBoardVO selectByNo(int noticeNo) {
		String sql = "select * from noticeboard where noticeNo = " + noticeNo;
		NoticeBoardVO vo = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<NoticeBoardVO>(NoticeBoardVO.class));
		return vo;
	}

	public int updateNoticeBoard(NoticeBoardVO vo, int noticeNo) {
		String sql = "update noticeboard set n_title =?, n_content=? where noticeNo = ?";
		int cnt = jdbcTemplate.update(sql, vo.getN_title(), vo.getN_content(), noticeNo);
		return cnt;
	}
	// 해당 게스트의 비밀번호와 일치하는지 확인을 해야함

	public int deleteNoticeBoard(int noticeNo) {
		String sql = "delete from noticeboard where noticeNo =?";
		int cnt = jdbcTemplate.update(sql, noticeNo);

		return cnt;
	}

	public int updateN_count(int noticeNo) {
		String sql = "update noticeboard set n_count = n_count+1 where noticeNo=?";
		int cnt = jdbcTemplate.update(sql, noticeNo);

		return cnt;
	}
}
