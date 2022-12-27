package com.spring.moon.askBoard.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CommentVO> selectComment(int askNo) {
			String sql="select * from comments"
					+ "	where askNo = ?";
			List<CommentVO> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentVO>(CommentVO.class));
			return list;
	}
	
	
	/**
	 * 댓글달기
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public int insertcomment(CommentVO vo){
			String sql="insert into comments(name, content, askno)"
				+ " values( ?, ?, ?)";
			int cnt=jdbcTemplate.update(sql, vo.getName(), vo.getContent(), vo.getAskno());
			return cnt;
	}
	
	
	
	
	public int deleteComment(int no){
			String sql = "delete from comments where no = ?";
			int cnt = jdbcTemplate.update(sql, no);
			return cnt;
	}
}
