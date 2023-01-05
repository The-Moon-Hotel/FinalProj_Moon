package com.spring.moon.reservRoom.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.Utility;
import com.spring.moon.facility.model.FacilityService;
import com.spring.moon.facility.model.FacilityVO;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;
import com.spring.moon.reservRoom.model.ReservationService;
import com.spring.moon.reservRoom.model.ReservationVO;
import com.spring.moon.room.model.RoomService;
import com.spring.moon.room.model.RoomVO;

@Controller
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	@Autowired
	RoomService roomService;
	@Autowired
	GuestService guestService;
	@Autowired
	FacilityService facService;

	//////////////////////////////////////////////////////////
	//////////////////////// G E T ///////////////////////////
	//////////////////////////////////////////////////////////
	/*
	@GetMapping("/reservRoom/") 
	public ModelAndView (HttpServletRequest request) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			mav.addObject("userid", userid);
			mav.setViewName("reservRoom/");
		}
		return mav;
	}
	 */
	@GetMapping("/reservRoom/reservation1")
	public ModelAndView reservation1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid"); // 로그인 정보

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(today);

		mav.addObject("userid", userid);
		mav.addObject("now", now);
		mav.setViewName("reservRoom/reservation1");
		return mav;
	}

	@GetMapping("/reservRoom/reservation2")
	public ModelAndView reservation2() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservRoom/reservation2");
		return mav;
	}

	@GetMapping("/reservRoom/reservation3")
	public ModelAndView reservation3(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			mav.addObject("userid", userid);
			mav.setViewName("reservRoom/reservation3");
		}
		return mav;
	}

	@GetMapping("/reservRoom/reservConfirm")
	public ModelAndView reservConfirm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid"); // 로그인 정보
		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
			return mav;
		} else {
			GuestVO guestVo = this.guestService.selectByUserid(userid);
			int guestNo = guestVo.getGuestNo();
			FacilityVO bVo = new FacilityVO();
			FacilityVO pVo = new FacilityVO();
			FacilityVO sVo = new FacilityVO();
			FacilityVO gVo = new FacilityVO();
	
			// 예약 정보 받아오기
			ReservationVO reservVo = this.reservationService.searchReserv(guestNo);
			int reservNo = reservVo.getReservNo();
			RoomVO roomVo = roomService.selectRoomByRoomNo(reservVo.getRoomNo());
			bVo = facService.selectBreakFastInfo(reservNo);
			pVo = facService.selectPoolInfo(reservNo);
			sVo = facService.selectSaunaInfo(reservNo);
			gVo = facService.selectGymInfo(reservNo);
			
			mav.addObject("roomVo", roomVo);
			mav.addObject("bVo", bVo);
			mav.addObject("pVo", pVo);
			mav.addObject("sVo", sVo);
			mav.addObject("gVo", gVo);
			mav.setViewName("reservRoom/reservConfirm");
			return mav;
		}
	}

	//////////////////////////////////////////////////////////
	//////////////////////// P O S T///////////////////////////
	//////////////////////////////////////////////////////////
	/*
	@PostMapping("/reservRoom/") 
	public ModelAndView (HttpServletRequest request) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			mav.addObject("userid", userid);
			mav.setViewName("reservRoom/");
		}
		return mav;
	}
	*/
	@PostMapping("/reservRoom/reservation1")
	public ModelAndView reservation1Post(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String locName = request.getParameter("locName");
		String ci_date = request.getParameter("ci_date");
		String co_date = request.getParameter("co_date");

		List<RoomVO> rlist = roomService.selectRoom(locName);
		for (RoomVO vo : rlist) {
			System.out.println(vo.getLocName() + vo.getRoomType());
		}
		// 3. 출력 처리
		long gapDate = Utility.calDate(ci_date, co_date);
		System.out.println("숙박일 gapDate="+gapDate);
		mav.addObject("rlist", rlist);
		mav.addObject("gapDate", gapDate);
		mav.setViewName("reservRoom/reservation2");
		return mav;
	}

	@PostMapping("/reservRoom/reservation3")
	public ModelAndView reservation3Post(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
			return mav;
		} else {

			GuestVO guestVo = this.guestService.selectByUserid(userid);
			int guestNo = guestVo.getGuestNo();

			String locName = request.getParameter("locName");
			String ci_date = request.getParameter("ci_date");
			String co_date = request.getParameter("co_date");
			String adult = request.getParameter("adult");
			String kids = request.getParameter("kids");
			String roomType = request.getParameter("roomType");
			String totalPrice = request.getParameter("totalPrice");
			String roomImage = request.getParameter("roomImage");

			totalPrice = totalPrice.replaceAll(",", "");

			/* 조식 인원 */
			String b_adult = request.getParameter("b_adult");
			String b_kids = request.getParameter("b_kids");

			/* 수영장 인원 */
			String p_adult = request.getParameter("p_adult");
			String p_kids = request.getParameter("p_kids");

			/* 사우나 인원 */
			String s_adult = request.getParameter("s_adult");

			/* 헬스장 인원 */
			String g_adult = request.getParameter("g_adult");

			// 2. db 작업

			// RoomNo 정보 읽어오기
			RoomVO roomVo = roomService.selectRoomByType(locName, roomType);
			ReservationVO reservVo = new ReservationVO();
			FacilityVO facVo = new FacilityVO();
			// 객실 예약하기
			reservVo.setGuestNo(guestNo);
			reservVo.setRoomNo(roomVo.getRoomNo());
			reservVo.setAdult(Integer.parseInt(adult));
			reservVo.setKids(Integer.parseInt(kids));
			reservVo.setCi_date(ci_date);
			reservVo.setCo_date(co_date);
			reservVo.setTotalPrice(Integer.parseInt(totalPrice));

			String msg = "예약에 실패했습니다.", url = "/reservRoom/reservation1";

			int cnt = this.reservationService.insertReservation(reservVo);

			if (cnt > 0) {

				int reservNo = this.reservationService.searchReservNo(guestNo);

				if (b_adult != null) { // 조식 예약
					facVo.setAdultNo(Integer.parseInt(b_adult));
					facVo.setKidsNo(Integer.parseInt(b_kids));
					facVo.setFacInfoNo(1);

					int facCnt = facService.insertFacility(facVo, reservNo);
				}
				if (p_adult != null) { // 수영장 예약
					facVo.setAdultNo(Integer.parseInt(p_adult));
					facVo.setKidsNo(Integer.parseInt(p_kids));
					facVo.setFacInfoNo(2);
					int facCnt = facService.insertFacility(facVo, reservNo);

				}
				if (s_adult != null) { // 사우나 예약
					facVo.setAdultNo(Integer.parseInt(s_adult));
					facVo.setKidsNo(0);

					facVo.setFacInfoNo(3);
					int facCnt = facService.insertFacility(facVo, reservNo);

				}
				if (g_adult != null) { // 헬스장 예약
					facVo.setAdultNo(Integer.parseInt(g_adult));
					facVo.setKidsNo(0);
					facVo.setFacInfoNo(4);
					int facCnt = facService.insertFacility(facVo, reservNo);
				}

				msg = "예약되었습니다.";
				url = "/reservRoom/reservConfirm";
			}
			System.out.println("예약확인 url="+url);
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("/common/message");;
			return mav;
		}
	}
}
