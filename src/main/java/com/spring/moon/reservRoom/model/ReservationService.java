package com.spring.moon.reservRoom.model;

import java.util.List;
import java.util.Map;


public interface ReservationService {
	
	//예약할 방의 정보를 매개변수로 받아서 reservation 테이블에 저장하는 메서드
	 int insertReservation (ReservationVO reservVo) ;
	
	//회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 번호를 찾는 메서드
	 int searchReservNo(int guestNo) ;
	
	//회원 번호를 매개 변수로 받아서 조회한 회원의 가장 최근 예약 정보 조회하는 메서드
	 ReservationVO searchReserv(int guestNo);
	
	//회원번호를 매개변수로 모든 예약 조회 검색하기
	 List<ReservationVO> selectAllReserv(int guestNo);
	 
	// 회원번호를 매개변수로 이름 호텔 포함  모든 예약 조회 검색하기
	List<Map<String,Object>> selectNameLocAllReserv(int guestNo);
		
	//예약번호를 매개 변수로 받아서 예약 정보 조회하는 메서드
	 ReservationVO searchReservByNo(int reservNo) ;
	
	//=====관리자 기능
	//회원번호를 매개변수로 모든 예약 조회 검색하기
	 List<Map<String,Object>> selectAllReserv() ;
	
	 List<Map<String,Object>> selectAllReserv(String startDate, String endDate) ;
	
	 List<Map<String,Object>> selectCondition(String condition) ;
	
	//예약번호를 매개번수로 받아서 방을 취소하는 메서드
	 int deleteReserv(int reservNo) ;
}
