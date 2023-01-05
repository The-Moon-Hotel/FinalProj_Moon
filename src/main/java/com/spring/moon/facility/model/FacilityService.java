package com.spring.moon.facility.model;

public interface FacilityService {
	//부대시설을 예약하기 위한 정보와 객실 예약 번호를 매개변수로 받아와서 부대시설 예약하는 메서드
	 int insertFacility(FacilityVO facVo, int reservNo);
	
	//객실 예약 정보를 받아 조식 예약 정보를 조회하는 메서드
	 FacilityVO selectBreakFastInfo (int reservNo);
	
	// 객실 예약 정보를 받아 수영장 예약 정보를 조회하는 메서드
	 FacilityVO selectPoolInfo(int reservNo);
	
	// 객실 예약 정보를 받아 사우나 예약 정보를 조회하는 메서드
	 FacilityVO selectSaunaInfo(int reservNo);
	
	// 객실 예약 정보를 받아 헬스장 예약 정보를 조회하는 메서드
	 FacilityVO selectGymInfo(int reservNo);
	
	//예약번호를 매개 변수로 부대시설 예약 삭제하는 메서드
	 int deleteFacility(int reservNo);
}
