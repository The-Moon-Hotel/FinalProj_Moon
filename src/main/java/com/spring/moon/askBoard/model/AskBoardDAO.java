package com.spring.moon.askBoard.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AskBoardDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 문의글 insert
	 * 
	 * @param vo
	 * @return
	 * @throws SQLExceptions
	 */
	public int insert(AskBoardVO vo) {
		String sql = "insert into askboard(guestno, a_title, a_content,filename, originalFilename,filesize)" + " values( ?, ?, ?, ?, ?, ?)";
		int cnt = jdbcTemplate.update(sql, vo.getGuestNo(), vo.getA_title(), vo.getA_content(), vo.getFileName(), vo.getOriginalFileName(), vo.getFileSize());
		
		return cnt;
	}

	/**
	 * 문의글 관리자 전체 selectAll
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<AskBoardVO> selectAll(String condition, String keyword) {
		String sql = "select askNo, guestno, a_title, a_content, filename, originalFilename, a_regdate from askboard";
		// 검색의 경우
		if (keyword != null && !keyword.isEmpty()) {
			sql += " where " + condition + " like '%" + keyword + "%'";
		}
		sql += " order by askNo desc";
		List<AskBoardVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AskBoardVO>(AskBoardVO.class));
		
		return list;
	}

	/**
	 * askNo를 이용하여 문의글 상세보기
	 * 
	 * @param guestno
	 * @return
	 * @throws SQLException
	 */
	public AskBoardVO selectByAskNo(int askNo) {
		String sql = "select * from askboard where askNo =" + askNo;
		AskBoardVO vo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<AskBoardVO>(AskBoardVO.class));
		
		return vo;
	}

	/**
	 * 문의글 update
	 * 
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public int updateAskboard(AskBoardVO vo) {
		String sql = "update askboard" + " set a_title = ?, a_content = ?" + " where askNo = ?";
		int cnt = jdbcTemplate.update(sql, vo.getA_title(), vo.getA_content(), vo.getAskNo());
		
		return cnt;
	}

	/**
	 * 문의글 delete
	 * 
	 * @param askNo
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public int deleteAskboard(int askNo){
		String sql = "delete from askboard where askNo = ?";
		int cnt = jdbcTemplate.update(sql, askNo);
		
		return cnt;
	}

	/**
	 * 회원별 문의글 조회(회원번호)
	 * 
	 * @param guestno
	 * @return
	 * @throws SQLException
	 */
	public List<AskBoardVO> selectByGuestno(int guestno){
		String sql = "select * from askboard where guestno = " + guestno + " order by askNo desc";
		List<AskBoardVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AskBoardVO>(AskBoardVO.class));

		return list;

	}

}
