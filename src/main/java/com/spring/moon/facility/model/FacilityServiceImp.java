package com.spring.moon.facility.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImp implements FacilityService {
	@Autowired
	FacilityDAO facDao;

	// 부대시설을 예약하기 위한 정보와 객실 예약 번호를 매개변수로 받아와서 부대시설 예약하는 메서드
	public int insertFacility(FacilityVO facVo, int reservNo) {
		return this.facDao.insertFacility(facVo, reservNo);
	}

	// 객실 예약 정보를 받아 조식 예약 정보를 조회하는 메서드
	public FacilityVO selectBreakFastInfo(int reservNo) {
		return this.facDao.selectBreakFastInfo(reservNo);
	}

	// 객실 예약 정보를 받아 수영장 예약 정보를 조회하는 메서드
	public FacilityVO selectPoolInfo(int reservNo) {
		return this.facDao.selectPoolInfo(reservNo);
	}

	// 객실 예약 정보를 받아 사우나 예약 정보를 조회하는 메서드
	public FacilityVO selectSaunaInfo(int reservNo) {
		return this.facDao.selectPoolInfo(reservNo);
	}

	// 객실 예약 정보를 받아 헬스장 예약 정보를 조회하는 메서드
	public FacilityVO selectGymInfo(int reservNo) {
		return this.facDao.selectGymInfo(reservNo);
	}

	// 예약번호를 매개 변수로 부대시설 예약 삭제하는 메서드
	public int deleteFacility(int reservNo) {
		return this.facDao.deleteFacility(reservNo);
	}
}
