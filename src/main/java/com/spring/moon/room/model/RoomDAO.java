package com.spring.moon.room.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RoomDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	// 지점명을 매개변수로 받아서 객실 정보 반환하는 메서드
	public List<RoomVO> selectRoom(String locName) {
		String sql = "select * from room where locName= '"+locName+"' ";
		List<RoomVO> rlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RoomVO>(RoomVO.class));
		return rlist;
	}

	// 지점 정보, 객실 타입을 매개변수로 하여 객실 정보를 받환하는 매서드
	public RoomVO selectRoomByType(String locName, String roomType) {
		String sql = "select * from room where locName= '"+locName+"' and roomType= '"+roomType+"' ";
		RoomVO roomVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<RoomVO>(RoomVO.class));
		return roomVo;
	}

	// 객실번호를 매개변수로 하여 객실 정보를 받환하는 매서드
	public RoomVO selectRoomByRoomNo(int roomNo) {
		String sql = "select * from room where roomNo = "+roomNo;
		RoomVO roomVo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<RoomVO>(RoomVO.class));
		return roomVo;
	}
}
