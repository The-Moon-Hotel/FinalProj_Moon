package com.spring.moon.reservRoom.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		String sql = "insert into reservation(guestNo, roomNo, adult, kids, ci_date, co_date, totalPrice) "
				+ " values(?, ?, ?, ?, ?, ?, ?)";
		int cnt = jdbcTemplate.update(sql, reservVo.getGuestNo(), reservVo.getRoomNo(), reservVo.getAdult(), reservVo.getKids()
				, reservVo.getCi_date(), reservVo.getCo_date(), reservVo.getTotalPrice());
		return cnt;
	}

	// 회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 번호를 찾는 메서드
	public int searchReservNo(int guestNo) {
		String sql = "select resve.reservNo as reservNo from "
				+ " (select @rownum := @rownum + 1 as rownum, r.* from reservation r,(select @rownum :=0) tmp order by reservNo desc) resve "
				+ " where rownum <=1 and resve.guestNo=  ?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, guestNo);
		int reservNo = Integer.parseInt(map.get("reservNo").toString());
		return reservNo;
	}

	// 회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 정보 조회하는 메서드
	public ReservationVO searchReserv(int guestNo) {
		String sql = "select reservNo, guestNo, RoomNo, adult, kids, ci_date, co_date,totalPrice from "
				+ " (select @rownum := @rownum + 1 as rownum, r.* from reservation r,(select @rownum :=0) tmp order by reservNo desc) reserv "
				+ " where rownum <=1 and guestNo= "+guestNo;
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return reservVo;
	}

	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<ReservationVO> selectAllReserv(int guestNo) {
		String sql = "select * from Reservation where guestNo=? order by reservNo desc";
		List<ReservationVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return rlist;
	}
	// 회원번호를 매개변수로 이름 호텔 포함  모든 예약 조회 검색하기
	public List<Map<String,Object>> selectNameLocAllReserv(int guestNo) {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from Reservation resrve left join room r on resrve.roomNo=r.roomNo where resrve.guestNo= ? order by reservNo desc" + 
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
		String sql = "select * from Reservation where reservNo="+reservNo;
		ReservationVO reservVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReservationVO>(ReservationVO.class));
		return reservVo;
	}

	// =====관리자 기능
	// 회원번호를 매개변수로 모든 예약 조회 검색하기
	public List<Map<String,Object>> selectAllReserv() {
		String sql = "select * from" + 
				"	(" + 
				"		select resrve.*, r.locName from Reservation resrve left join room r on resrve.roomNo=r.roomNo order by reservNo desc" + 
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
				"		select resrve.*, r.locName from Reservation resrve left join room r on resrve.roomNo=r.roomNo "+ 
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
				"		select resrve.*, r.locName from Reservation resrve left join room r on resrve.roomNo=r.roomNo ";

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
