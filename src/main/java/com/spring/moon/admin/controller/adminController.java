package com.spring.moon.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.AccountRequired;
import com.spring.moon.common.PagingVO;
import com.spring.moon.facility.model.FacilityService;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;
import com.spring.moon.reservRoom.model.ReservationService;
import com.spring.moon.reservRoom.model.ReservationVO;
import com.spring.moon.room.model.RoomService;
import com.spring.moon.room.model.RoomVO;

@Controller
@AccountRequired(admin = true)
public class adminController {
	
	@Autowired
	ReservationService reservService;
	@Autowired
	GuestService guestService;
	@Autowired
	RoomService roomService;
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
	@RequestMapping("/admin/guestList")
	public ModelAndView guestList(HttpServletRequest request,@RequestParam Map<String, Object> map,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			GuestVO g_vo = new GuestVO();
			g_vo = guestService.selectByUserid(userid);
			
			List<GuestVO> glist = guestService.selectAllGuest((String) map.get("condition"),(String) map.get("keyword"));
			
			/*
			 * for (int i = 0; i < list.size(); i++) { System.out.println((String)
			 * list.get(i).get("uName")); }
			 */
			// 게시판 검색 관련소스
			String keyField = ""; // DB의 컬럼명
			String keyWord = ""; // DB의 검색어

			if (map.get("keyWord") != null) {
				keyField = (String) map.get("keyField");
				keyWord = (String) map.get("keyWord");
			}
			/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
			// paging 처리 관련

			int totalRecord = 0;
			if (glist != null) {
				totalRecord = glist.size();
			}
			int pageSize = 5;
			int blockSize = 10;
			PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);

			/////////////////////// 페이징 관련 속성 값 끝///////////////////////////


			mav.addObject("map", map);
			mav.addObject("glist", glist);
			mav.addObject("pageVo", pageVo);
			mav.addObject("g_vo", g_vo);

			mav.setViewName("admin/guestList");
		}
		return mav;
	}
	@GetMapping("/admin/guestDetail") 
	public ModelAndView guestDetail(HttpServletRequest request,@RequestParam int guestNo) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			System.out.println("guestNo="+guestNo);
			List<Map<String, Object>> rlist= reservService.selectNameLocAllReserv(guestNo);
				
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String now = sdf.format(today);
			int thisday = Integer.parseInt(now);
			
			mav.addObject("thisday", thisday);
			mav.addObject("rlist", rlist);
			mav.addObject("userid", userid);
			mav.setViewName("admin/guestDetail");
		}
		return mav;
	}
	@GetMapping("/admin/guestReservDetail") 
	public ModelAndView guestReservDetail(HttpServletRequest request,@RequestParam int reservNo) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			ReservationVO reservVo = new ReservationVO();
			RoomVO roomVo = new RoomVO();
			GuestVO guestVo = new GuestVO();
			
			reservVo = reservService.searchReservByNo(reservNo);
			roomVo = roomService.selectRoomByRoomNo(reservVo.getRoomNo());
			guestVo = guestService.selectByGuestNo(reservVo.getGuestNo());

			mav.addObject("reservNo", reservNo);
			mav.addObject("reservVo", reservVo);
			mav.addObject("roomVo", roomVo);
			mav.addObject("guestVo", guestVo);
			mav.setViewName("admin/guestReservDetail");
		}
		return mav;
	}
	@Transactional
	@PostMapping("/admin/reservDelete")
	public ModelAndView reservDelete(HttpServletRequest request,@RequestParam int reservNo) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			facService.deleteFacility(reservNo);
			int reservDel = reservService.deleteReserv(reservNo);
			
			String msg="예약취소 실패하였습니다", url="javascript:history.back()";
			if(reservDel > 0){
				msg="예약이 취소 되었습니다.";
				url="/admin/reservList";
			}
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		}
		return mav;
	}
	@GetMapping("/admin/reservDetail") 
	public ModelAndView reservDetail(HttpServletRequest request,@RequestParam int reservNo) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			ReservationVO reservVo = new ReservationVO();
			RoomVO roomVo = new RoomVO();
			GuestVO guestVo = new GuestVO();
			
			reservVo = reservService.searchReservByNo(reservNo);
			roomVo = roomService.selectRoomByRoomNo(reservVo.getRoomNo());
			guestVo = guestService.selectByGuestNo(reservVo.getGuestNo());
		
			mav.addObject("reservNo", reservNo);
			mav.addObject("reservVo", reservVo);
			mav.addObject("roomVo", roomVo);
			mav.addObject("guestVo", guestVo);
			mav.setViewName("admin/reservDetail");
		}
		return mav;
	}
	@RequestMapping(value="/admin/reservList") 
	public ModelAndView reservList(HttpServletRequest request,@RequestParam Map<String, Object> map,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			String condition = (String) map.get("condition");
			String startDate = (String) map.get("startDate");
			String endDate = (String) map.get("endDate");
			System.out.println("condition=" + condition);
			System.out.println("startDate=" + startDate);
			System.out.println("endDate=" + endDate);

			List<Map<String, Object>> rlist=null;
			ReservationVO reservVo = new ReservationVO();
			RoomVO roomVo = new RoomVO();
			
			rlist = reservService.selectAllReserv();
			
			if(condition == null) map.put("condition", "all");
			
			if((String) map.get("startDate") != null && (String) map.get("endDate") != null){
				rlist = reservService.selectAllReserv(startDate, endDate);
				
				if(condition != null){
					rlist = reservService.selectCondition(condition);
				}
			}else{
				rlist = reservService.selectCondition(condition);
			}
			
			/*
			 * for (int i = 0; i < list.size(); i++) { System.out.println((String)
			 * list.get(i).get("uName")); }
			 */
			
			/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
			// paging 처리 관련

			int totalRecord = 0;
			if (rlist != null) {
				totalRecord = rlist.size();
			}
			int pageSize = 5;
			int blockSize = 10;
			PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);

			/////////////////////// 페이징 관련 속성 값 끝///////////////////////////
			
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String now = sdf.format(today);
			System.out.println("now="+now);
			int thisday = Integer.parseInt(now);
			
			mav.addObject("thisday", thisday);
			mav.addObject("rlist", rlist);
			mav.addObject("userid", userid);

			mav.addObject("map", map);
			mav.addObject("rlist", rlist);
			mav.addObject("pageVo", pageVo);

			mav.setViewName("admin/reservList");
		}
		return mav;
	}

	//////////////////////////////////////////////////////////
	//////////////////////// P O S T /////////////////////////
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
	
}
