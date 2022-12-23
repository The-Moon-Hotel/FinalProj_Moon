package com.spring.moon.guest.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImp implements GuestService{
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
		@Autowired
		GuestDAO guestDAO;
		
		//회원가입
		@Override
		public int insertGuest(GuestVO vo) {
			return this.guestDAO.insertGuest(vo);
		}
		
		//중복확인
		@Override
		public int duplicateUserid(String userid)  {
			return this.guestDAO.duplicateUserid(userid);
		}
		
		//로그인
		@Override
		public int loginCheck(String userid, String pwd) {
			GuestVO guestVO=this.guestDAO.loginCheck(userid, pwd);
			
			String dbPwd=guestVO.getPwd();
			int result=0;
			if(pwd.equals(dbPwd)) {
				result=GuestService.LOGIN_OK;
			}else {
				result=GuestService.DISAGREE_PWD;
			}
			return result;
		}
		//회원정보 페이제에 회원정보 불러올때
		@Override
		public GuestVO selectByUserid(String userid) {
			return this.guestDAO.selectByUserid(userid);
		}
		// 회원정보 수정
		@Override
		public int updateGuset(GuestVO vo) {
			return this.guestDAO.updateGuset(vo);
		}
		//회원탈퇴
		@Override
		public int OutGuset(String userid , String pwd) {
			return this.guestDAO.OutGuset(userid, pwd);
		}
		
		//매개변수 회원번호로 회원정보 불러오는 매서드
		@Override
		public GuestVO selectByGuestNo(int guestNo)  {
			return this.guestDAO.selectByGuestNo(guestNo);
		}
		
		//전체 회원 조회
		@Override
		public List<GuestVO> selectAllGuest(String condition, String keyword) {
			return this.guestDAO.selectAllGuest(condition, keyword);
		}
}
