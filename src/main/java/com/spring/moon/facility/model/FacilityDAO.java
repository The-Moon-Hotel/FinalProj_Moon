package com.spring.moon.facility.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 부대시설을 예약하기 위한 정보와 객실 예약 번호를 매개변수로 받아와서 부대시설 예약하는 메서드
	public int insertFacility(FacilityVO facVo, int reservNo) {
		String sql = "insert into facility(reservNo, facInfoNo, adultNo, kidsNo) values(?, ?, ?, ?)";
		int cnt = jdbcTemplate.update(sql, reservNo, facVo.getFacInfoNo(), facVo.getAdultNo(), facVo.getKidsNo());
		return cnt;
	}

	// 객실 예약 정보를 받아 조식 예약 정보를 조회하는 메서드
	public FacilityVO selectBreakFastInfo(int reservNo) {
		String sql = "select * from facility where reservNo=" + reservNo + " and facinfoNo=1";
		FacilityVO facVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<FacilityVO>(FacilityVO.class));
		return facVo;
	}

	// 객실 예약 정보를 받아 수영장 예약 정보를 조회하는 메서드
	public FacilityVO selectPoolInfo(int reservNo) {
		String sql = "select * from facility where reservNo=" + reservNo + " and facinfoNo=2";
		FacilityVO facVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<FacilityVO>(FacilityVO.class));
		return facVo;
	}

	// 객실 예약 정보를 받아 사우나 예약 정보를 조회하는 메서드
	public FacilityVO selectSaunaInfo(int reservNo) {
		String sql = "select * from facility where reservNo=" + reservNo + " and facinfoNo=3";
		FacilityVO facVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<FacilityVO>(FacilityVO.class));
		return facVo;
	}

	// 객실 예약 정보를 받아 헬스장 예약 정보를 조회하는 메서드
	public FacilityVO selectGymInfo(int reservNo) {
		String sql = "select * from facility where reservNo=" + reservNo + " and facinfoNo=3";
		FacilityVO facVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<FacilityVO>(FacilityVO.class));
		return facVo;
	}

	// 예약번호를 매개 변수로 부대시설 예약 삭제하는 메서드
	public int deleteFacility(int reservNo) {
		String sql = "delete from facility where reservNo = ?" ;
		int cnt = jdbcTemplate.update(sql,reservNo);
		return cnt;
	}
}
