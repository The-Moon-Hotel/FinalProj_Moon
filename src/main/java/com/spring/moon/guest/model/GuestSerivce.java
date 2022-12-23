package com.spring.moon.guest.model;

import java.sql.SQLException;
import java.util.List;

public interface GuestSerivce {
	//아이디 중복확인 상수
	public static final int EXIST_ID=1;	//존재하는 아이디=>사용불가
	public static final int NOT_EXIST_ID=2;// 존재하지 않는 아이디 => 사용가능
	
	//로그인 할때 상수
	public static final int LOGIN_OK=1;	//로그인 성공 
	public static final int DISAGREE_PWD=2;	//비밀번호 불일치
	public static final int NONE_USERID=3;	//해당 아이디 존재하지 않음
	
	//관리자 계정 여부 상수
	public static final int GUEST_ACCOUNT=1;	//Guset 계정
	public static final int ADMIN_ACCOUNT=2;	// 관리자 계정

	
	//회원가입
	int insertGuest(GuestVO vo);
	
	//중복확인
	int duplicateUserid(String userid);
	
	//로그인 
	int loginCheck(String userid, String pwd);
	//회원정보 페이제에 회원정보 불러올때
	GuestVO selectByUserid(String userid);
	// 회원정보 수정
	int updateGuset(GuestVO vo);
	//회원탈퇴
	int OutGuset(String userid , String pwd) ;
	
	//매개변수 회원번호로 회원정보 불러오는 매서드
	GuestVO selectByGuestNo(int guestNo);
	
	//전체 회원 조회
	List<GuestVO> selectAllGuest(String condition, String keyword);
}
