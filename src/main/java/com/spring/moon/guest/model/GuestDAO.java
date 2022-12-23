package com.spring.moon.guest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
/*import com.spring.moon.reservation.model.ReservationVO;*/

@Repository
public class GuestDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 회원가입
	public int insertGuest(GuestVO vo) {
		String sql = "insert into guest(name,userid,pwd,email,tel) values(?,?,?,?,?)";
		int cnt= jdbcTemplate.update(sql, vo.getName(), vo.getUserid(),vo.getPwd(),vo.getEmail(), vo.getTel());
		return cnt;
	}

	// 중복확인
	public int duplicateUserid(String userid)  {
		String sql = "select count(*) from guest where userid="+userid;
		int res = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Integer>());
		
		return res;
	}

	// 로그인
	public GuestVO loginCheck(String userid, String pwd)  {
		String sql = "select pwd from guest where userid = "+userid+" and outdate is null";
		GuestVO guestVo = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<GuestVO>(GuestVO.class));		
		return guestVo;
	}

	// 회원정보 페이지에 회원정보 불러올때
	public GuestVO selectByUserid(String userid)  {
		String sql = "select * from guest where userid ="+userid;
		GuestVO guestVo = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<GuestVO>(GuestVO.class));
		return guestVo;
	}

	// 매개변수 회원번호로 회원정보 불러오는 매서드
	public GuestVO selectByGuestNo(int guestNo)  {
		String sql = "select * from guest where guestNo = "+guestNo;
		GuestVO vo = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<GuestVO>(GuestVO.class));
		return vo;
	}

	// 회원정보 수정
	public int updateGuset(GuestVO vo)  {
		String sql = "update guest" + " set email=?, pwd=?, tel=?" + " where userid=? ";
		int cnt=jdbcTemplate.update(sql,vo.getEmail(),vo.getPwd(),vo.getTel(),vo.getUserid());
		return cnt;
	}

	// 회원탈퇴
	public int OutGuset(String userid, String pwd)  {
		String sql = "update guest set outdate=sysdate where userid=? and pwd=?";
		int cnt=jdbcTemplate.update(sql,userid,pwd);
		return cnt;
	}

	// 전체 회원 조회
	public List<GuestVO> selectAllGuest(String condition, String keyword)  {	
		String sql = "select * from guest ";
		if (keyword != null && !keyword.isEmpty()) {
			sql += " where " + condition + " like  '%' || "+keyword+" || '%' ";
		}
		sql += "order by guestNo desc";

		List<GuestVO> glist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<GuestVO>(GuestVO.class)) ;
		return glist;

	}
}
