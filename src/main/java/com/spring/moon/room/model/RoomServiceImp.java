package com.spring.moon.room.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoomServiceImp  implements RoomService{
	@Autowired
	RoomDAO roomDao;
	
	//지점명을 매개변수로 받아서 객실 정보 반환하는 메서드
	@Override
	public List<RoomVO> selectRoom(String locName) {
		return this.roomDao.selectRoom(locName);
	}
	
	// 지점 정보, 객실 타입을 매개변수로 하여 객실 정보를 받환하는 매서드
	@Override
	public RoomVO selectRoomByType(String locName, String roomType)  {
		return this.roomDao.selectRoomByType(locName, roomType);
	}
	
	// 객실번호를 매개변수로 하여 객실 정보를 받환하는 매서드
	@Override
	public RoomVO selectRoomByRoomNo(int roomNo)  {
		return this.roomDao.selectRoomByRoomNo(roomNo);
	}
}
