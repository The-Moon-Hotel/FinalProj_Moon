package com.spring.moon.reservRoom.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImp implements ReservationService{
	@Autowired
	ReservationDAO reservDao;
	
	
	//예약할 방의 정보를 매개변수로 받아서 reservation 테이블에 저장하는 메서드
	@Override
	public int insertReservation (ReservationVO reservVo)  {
		return this.reservDao.insertReservation(reservVo);
	}
	
	//회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 번호를 찾는 메서드
	@Override
	public int searchReservNo(int guestNo)  {
		return this.reservDao.searchReservNo(guestNo);
	}
	
	//회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 정보 조회하는 메서드
	@Override
	public ReservationVO searchReserv(int guestNo)  {
		return this.reservDao.searchReserv(guestNo);
	}
	
	//회원번호를 매개변수로 모든 예약 조회 검색하기
	@Override
	public List<ReservationVO> selectAllReserv(int guestNo) {
		return this.reservDao.selectAllReserv(guestNo);
	}
	
	@Override
	// 회원번호를 매개변수로 이름 호텔 포함  모든 예약 조회 검색하기
	public	List<Map<String,Object>> selectNameLocAllReserv(int guestNo){
		return this.reservDao.selectNameLocAllReserv(guestNo);
	}
	
	//예약번호를 매개 변수로 받아서 예약 정보 조회하는 메서드
	@Override
	public ReservationVO searchReservByNo(int reservNo)  {
		return this.reservDao.searchReservByNo(reservNo);
	}
	
	//=====관리자 기능
	//회원번호를 매개변수로 모든 예약 조회 검색하기
	@Override
	public
	List<Map<String,Object>> selectAllReserv() {
		return this.reservDao.selectAllReserv();
	}
	
	@Override
	public List<Map<String,Object>> selectAllReserv(String startDate, String endDate) {
		return this.reservDao.selectAllReserv(startDate, endDate);
	}
	
	@Override
	public List<Map<String,Object>> selectCondition(String condition) {
		return this.reservDao.selectCondition(condition);
	}
	
	//예약번호를 매개번수로 받아서 방을 취소하는 메서드
	@Override
	public int deleteReserv(int reservNo)  {
		return this.reservDao.deleteReserv(reservNo);
	}
}
