package com.spring.moon.reviewBoard.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewBoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertReview(ReviewBoardVO vo) {
		String sql = "insert into reviewboard(guestNo, r_title, r_content)" + "values(?,?,?)";
		int cnt = jdbcTemplate.update(sql, vo.getGuestNo(), vo.getR_title(), vo.getR_content());

		return cnt;
	}

	public List<ReviewBoardVO> selectAll(String condition, String keyword) {
		String sql = "select r.*, g.userid from reviewboard r join guest g on r.guestNo = g.guestNo";
		String orderBy = " order by reviewNo desc";
		BeanPropertyRowMapper<ReviewBoardVO> mapper = new BeanPropertyRowMapper<>(ReviewBoardVO.class);
		if (keyword == null || keyword.isEmpty()) {
			return jdbcTemplate.query(sql + orderBy, mapper);
		}
		String column = Map.of("userid", "g.userid", "regdate", "r.r_regdate")
				.getOrDefault(condition == null ? "" : condition, "r.r_title");
		return jdbcTemplate.query(sql + " where " + column + " like ?" + orderBy, mapper, "%" + keyword + "%");
	}

	public ReviewBoardVO selectByUserid(int guestNo) {
		String sql = "select * from reviewboard where guestno = "+guestNo;
		ReviewBoardVO vo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReviewBoardVO>(ReviewBoardVO.class));

		return vo;
	}

	public ReviewBoardVO selectByNo(int reviewNo) {
		String sql = "select * from reviewboard where reviewNo = "+reviewNo;
		ReviewBoardVO vo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReviewBoardVO>(ReviewBoardVO.class));

		return vo;
	}

	public int updatereviewBoard(ReviewBoardVO vo, int reviewNo) {
		String sql = "update reviewboard set r_title =?, r_content=? where reviewNo = ?";

		int cnt = jdbcTemplate.update(sql,vo.getR_title(), vo.getR_content(), reviewNo);
		System.out.println(cnt);
		return cnt;

	}
	// 해당 게스트의 비밀번호와 일치하는지 확인을 해야함

	public int deletereviewBoard(int reviewNo) {
		String sql = "delete from reviewboard where reviewNo ="+reviewNo;
		int cnt = jdbcTemplate.update(sql);

		return cnt;
	}
}
