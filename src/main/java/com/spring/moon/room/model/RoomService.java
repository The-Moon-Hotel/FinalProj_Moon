package com.spring.moon.room.model;

import java.util.List;

public interface RoomService {
	
	//지점명을 매개변수로 받아서 객실 정보 반환하는 메서드
	 List<RoomVO> selectRoom(String locName);
	
	// 지점 정보, 객실 타입을 매개변수로 하여 객실 정보를 받환하는 매서드
	 RoomVO selectRoomByType(String locName, String roomType);
	
	// 객실번호를 매개변수로 하여 객실 정보를 받환하는 매서드
	 RoomVO selectRoomByRoomNo(int roomNo);
}
