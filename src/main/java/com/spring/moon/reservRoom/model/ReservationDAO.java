package com.spring.moon.reservRoom.model;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// 예약할 방의 정보를 매개변수로 받아서 reservation 테이블에 저장하는 메서드
	public int insertReservation(ReservationVO reservVo) {
		String sql = "insert into reservation(guestNo, roomNo, adult, kids, ci_date, co_date, totalPrice) "
				+ " values(?, ?, ?, ?, ?, ?, ?)";
		GeneratedKeyHolder keys = new GeneratedKeyHolder();
		int cnt = jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, reservVo.getGuestNo());
			statement.setInt(2, reservVo.getRoomNo());
			statement.setInt(3, reservVo.getAdult());
			statement.setInt(4, reservVo.getKids());
			statement.setString(5, reservVo.getCi_date());
			statement.setString(6, reservVo.getCo_date());
			statement.setInt(7, reservVo.getTotalPrice());
			return statement;
		}, keys);
		if (cnt > 0) {
			reservVo.setReservNo(keys.getKey().intValue());
		}
		return cnt;
	}

	// 회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 정보 조회하는 메서드
	public ReservationVO searchReserv(int guestNo) {
		String sql = "select * from reservation where guestNo = ? order by reservNo desc limit 1";
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReservationVO.class), guestNo);
		return reservVo;
	}

	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<ReservationVO> selectAllReserv(int guestNo) {
		String sql = "select * from reservation where guestNo=? order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReservationVO.class), guestNo);
		return rlist;
	}
	// 회원번호를 매개변수로 이름 호텔 포함  모든 예약 조회 검색하기
	public List<Map<String,Object>> selectNameLocAllReserv(int guestNo) {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from reservation resrve left join room r on resrve.roomNo=r.roomNo where resrve.guestNo= ? order by reservNo desc" +
				"    ) res" + 
				"    left join " + 
				"    (" + 
				"		select guestNo, name from guest " + 
				"    ) g " + 
				"    on res.guestNo=g.guestNo";
		List<Map<String,Object>> rlist = jdbcTemplate.queryForList(sql, guestNo);
		return rlist;
	}

	// 예약번호를 매개 변수로 받아서 예약 정보 조회하는 메서드
	public ReservationVO searchReservByNo(int reservNo) {
		String sql = "select * from reservation where reservNo="+reservNo;
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return reservVo;
	}

	// =====관리자 기능
	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<Map<String,Object>> selectAllReserv() {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from reservation resrve left join room r on resrve.roomNo=r.roomNo order by reservNo desc" +
				"    ) res" + 
				"    left join " + 
				"    (" + 
				"		select guestNo, name from guest " + 
				"    ) g " + 
				"    on res.guestNo=g.guestNo";
		List<Map<String,Object>> rlist = jdbcTemplate.queryForList(sql);
		return rlist;
	}

	public List<Map<String,Object>> selectAllReserv(String startDate, String endDate) {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from reservation resrve left join room r on resrve.roomNo=r.roomNo "+
				" where  DATE_FORMAT(ci_date, '%Y-%m-%d') >=  DATE_FORMAT(?, '%Y-%m-%d')"+
				" and  DATE_FORMAT(co_date, '%Y-%m-%d') <=  DATE_FORMAT(?, '%Y-%m-%d')" + 
				" order by reservNo desc"+
				"    ) res" + 
				"    left join " + 
				"    (" + 
				"		select guestNo, name from guest " + 
				"    ) g " + 
				"    on res.guestNo=g.guestNo";
		List<Map<String,Object>> rlist = jdbcTemplate.queryForList(sql, startDate, endDate);

		return rlist;
	}

	public List<Map<String,Object>> selectCondition(String condition) {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from reservation resrve left join room r on resrve.roomNo=r.roomNo ";

		if ("complete".equals(condition)) {
			sql += " where  DATE_FORMAT(ci_date, '%y-%m-%d') < now()-1";
		} else if ("incomplete".equals(condition)) { // 예정
			sql += " where  DATE_FORMAT(ci_date, '%y-%m-%d') > now()";
		} else if ("ing".equals(condition)) {
			sql += " where  DATE_FORMAT(ci_date, '%y-%m-%d') <=now() and  DATE_FORMAT(co_date, '%y-%m-%d')>now()";
		}

		sql += " order by reservNo desc"+
				"    ) res" + 
				"    left join " + 
				"    (" + 
				"		select guestNo, name from guest " + 
				"    ) g " + 
				"    on res.guestNo=g.guestNo";
		List<Map<String,Object>> rlist = jdbcTemplate.queryForList(sql);

		return rlist;
	}

	// 예약번호를 매개번수로 받아서 방을 취소하는 메서드
	public int deleteReserv(int reservNo) {
		String sql = "delete from reservation\r\n" + "where reservNo = ?";
		int cnt = jdbcTemplate.update(sql,reservNo);
		return cnt;
	}
}
