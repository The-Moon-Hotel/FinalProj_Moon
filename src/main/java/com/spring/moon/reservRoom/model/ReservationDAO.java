package com.spring.moon.reservRoom.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// 예약할 방의 정보를 매개변수로 받아서 reservation 테이블에 저장하는 메서드
	public int insertReservation(ReservationVO reservVo) {
		String sql = "insert into reservation(guestNo, roomNo, adult, "
				+ " kids, ci_date, co_date, totalPrice)\r\n" + "values(?, ?, ?, ?, "
				+ "?, ?, ?)";
		int cnt = jdbcTemplate.update(sql, reservVo.getGuestNo(), reservVo.getRoomNo(), reservVo.getAdult(), reservVo.getKids()
				, reservVo.getKids(), reservVo.getCi_date(), reservVo.getCo_date(), reservVo.getTotalPrice());
		return cnt;
	}

	// 회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 번호를 찾는 메서드
	public int searchReservNo(int guestNo) {
		String sql = "select reservNo from (select * from reservation order by reservNo desc)\r\n"
				+ "where rownum <=1 and guestNo="+guestNo;
		int reservNo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Integer>());
		return reservNo;
	}

	// 회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 정보 조회하는 메서드
	public ReservationVO searchReserv(int guestNo) {
		String sql = "select * from (select * from reservation order by reservNo desc)\r\n"
				+ "where rownum <=1 and guestNo="+guestNo;
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return reservVo;
	}

	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<ReservationVO> selectAllReserv(int guestNo) {
		String sql = "select * from Reservation where guestNo=? order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return rlist;
	}

	// 예약번호를 매개 변수로 받아서 예약 정보 조회하는 메서드
	public ReservationVO searchReservByNo(int reservNo) {
		String sql = "select * from Reservation where reservNo="+reservNo;
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return reservVo;
	}

	// =====관리자 기능
	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<ReservationVO> selectAllReserv() {
		String sql = "select * from reservation order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return rlist;
	}

	public List<ReservationVO> selectAllReserv(String startDate, String endDate) {
		String sql = "select * from reservation\r\n"
				+ "where to_date(ci_date, 'yyyy-mm-dd') >= to_date(?, 'yyyy-mm-dd')"
				+ " and to_date(co_date, 'yyyy-mm-dd') <= to_date(?, 'yyyy-mm-dd')" + " order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));

		return rlist;
	}

	public List<ReservationVO> selectCondition(String condition) {
		String sql = "select * from reservation "; // 2022-05-08

		if (condition.equals("complete")) {
			sql += " where to_date(ci_date, 'YY-MM-DD') < now()-1";
		} else if (condition.equals("incomplete")) { // 예정
			sql += " where to_date(ci_date, 'YY-MM-DD') > now()";
		} else if (condition.equals("ing")) {
			sql += " where to_date(ci_date, 'YY-MM-DD') <=now() and to_date(co_date, 'YY-MM-DD')>now()";
		}

		sql += " order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));

		return rlist;
	}

	// 예약번호를 매개번수로 받아서 방을 취소하는 메서드
	public int deleteReserv(int reservNo) {
		String sql = "delete from reservation\r\n" + "where reservNo = ?";
		int cnt = jdbcTemplate.update(sql,reservNo);
		return cnt;
	}
}
