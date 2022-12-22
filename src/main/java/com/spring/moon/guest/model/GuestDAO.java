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
	public int insertGuest(GuestVO vo) throws SQLException {
		int res = 0;
		String sql = "insert into guest(guestNo,name,userid,pwd,email,tel)" + " values(guest_seq.nextval, ?,?,?,?,?)";
		return res;
	}

	// 중복확인
	public int duplicateUserid(String userid) throws SQLException {
		int res = 0;
		String sql = "select count(*) from guest where userid=?";
		return res;
	}

	// 로그인
	public int loginCheck(String userid, String pwd) throws SQLException {
		int res = 0;
		String sql = "select pwd from guest " + " where userid = ? and outdate is null";
		return res;
	}

	// 회원정보 페이지에 회원정보 불러올때
	public GuestVO selectByUserid(String userid) throws SQLException {
		GuestVO vo = new GuestVO();
		String sql = "select * from guest where userid = ?";
		 vo = jdbcTemplate.queryForObject(sql,new Object[]{userid}, new BeanPropertyRowMapper<GuestVO>(GuestVO.class));
		return vo;
	}

	// 매개변수 회원번호로 회원정보 불러오는 매서드
	public GuestVO selectByGuestNo(int guestNo) throws SQLException {

		GuestVO vo = new GuestVO();

		String sql = "select * from guest where guestNo = ?";
		return vo;
	}

	// 회원정보 수정
	public int updateGuset(GuestVO vo) throws SQLException {
		int res=0;
			String sql = "update guest" + " set email=?, pwd=?, tel=?" + " where userid=? ";
			return res;
	}

	// 회원탈퇴
	public int OutGuset(String userid, String pwd) throws SQLException {
		int res=0;
			String sql = "update guest" + " set outdate=sysdate" + " where userid=? and pwd=?";
			return res;
	}

	// 전체 회원 조회
	public List<GuestVO> selectAllGuest(String condition, String keyword) throws SQLException {
		
		List<GuestVO> glist = new ArrayList<>();

			String sql = "select * from guest ";

			if (keyword != null && !keyword.isEmpty()) {
				sql += " where " + condition + " like  '%' || ? || '%' ";
			}

			sql += "order by guestNo desc";

			if (keyword != null && !keyword.isEmpty()) {
				/*ps.setString(1, keyword);*/
			}

			return glist;

	}
}
