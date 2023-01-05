package com.spring.moon.reviewBoard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewBoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertReview(ReviewBoardVO vo) {
		String sql = "insert into reviewBoard(guestNo, r_title, r_content)" + "values(?,?,?)";
		int cnt = jdbcTemplate.update(sql, vo.getGuestNo(), vo.getR_title(), vo.getR_content());

		return cnt;
	}

	public List<ReviewBoardVO> selectAll(String condition, String keyword) {
		String sql = "select r.reviewNo, r.guestNo, r.r_title, r.r_regdate, r.r_content, g.userid from ReviewBoard r, guest g";

		if (keyword != null && !keyword.isEmpty()) {
			sql += " where " + condition + " like '%" + keyword + "%' and r.guestno = g.guestno order by reviewNo desc";
		} else {
			sql += " where r.guestno = g.guestno order by reviewNo desc";
		}
		List<ReviewBoardVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReviewBoardVO>(ReviewBoardVO.class));

		System.out.println(
				"글 전체 조회 결과 list.size=" + list.size() + ", 매개변수 condition=" + condition + ", keyword=" + keyword);
		return list;

	}

	public ReviewBoardVO selectByUserid(int guestNo) {
		String sql = "select * from reviewBoard where guestno = "+guestNo;
		ReviewBoardVO vo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReviewBoardVO>(ReviewBoardVO.class));

		return vo;
	}

	public ReviewBoardVO selectByNo(int reviewNo) {
		String sql = "select * from reviewBoard where reviewNo = "+reviewNo;
		ReviewBoardVO vo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReviewBoardVO>(ReviewBoardVO.class));

		return vo;
	}

	public int updatereviewBoard(ReviewBoardVO vo, int reviewNo) {
		String sql = "update reviewBoard set r_title =?, r_content=? where reviewNo = ?";

		int cnt = jdbcTemplate.update(sql,vo.getR_title(), vo.getR_content(), reviewNo);
		System.out.println(cnt);
		return cnt;

	}
	// 해당 게스트의 비밀번호와 일치하는지 확인을 해야함

	public int deletereviewBoard(int reviewNo) {
		String sql = "delete from reviewBoard where reviewNo ="+reviewNo;
		int cnt = jdbcTemplate.update(sql);

		return cnt;
	}
}
