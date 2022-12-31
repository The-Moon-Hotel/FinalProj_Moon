package com.spring.moon.reservRoom.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.Utility;
import com.spring.moon.reservRoom.model.ReservationService;
import com.spring.moon.room.model.RoomService;
import com.spring.moon.room.model.RoomVO;

@Controller
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	@Autowired
	RoomService roomService;
	//////////////////////////////////////////////////////////
	//////////////////////// G E T ///////////////////////////
	//////////////////////////////////////////////////////////
	/*
	@GetMapping("/reservRoom/")
	public  ModelAndView ()	{
		ModelAndView mav= new ModelAndView();
		
		mav.addObject("attributeName", attributeValue);
		mav.setViewName("");
		return mav;
	}
	*/
	@GetMapping("/reservRoom/reservation1")
	public  ModelAndView reservation1(HttpServletRequest request)	{
		ModelAndView mav= new ModelAndView();
		HttpSession session=request.getSession();
		String userid = (String)session.getAttribute("userid"); //로그인 정보

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(today);
		
		mav.addObject("userid", userid);
		mav.addObject("now", now);
		mav.setViewName("reservRoom/reservation1");
		return mav;
	}
	//////////////////////////////////////////////////////////
	////////////////////////P O S T///////////////////////////
	//////////////////////////////////////////////////////////
	/*
	@PostMapping("/reservRoom/")
	public  ModelAndView ()	{
		ModelAndView mav= new ModelAndView();
		
		mav.addObject("attributeName", attributeValue);
		mav.setViewName("");
		return mav;
	}
	*/
	@PostMapping("/reservRoom/reservation1")
	public  ModelAndView reservation1Post(Model model, HttpServletRequest request )	{
		ModelAndView mav= new ModelAndView();
		String locName = request.getParameter("locName");
		String ci_date = request.getParameter("ci_date");
		String co_date = request.getParameter("co_date");
		String adult = request.getParameter("adult");
		String kids = request.getParameter("kids");
		
		List<RoomVO> rlist = null;
			rlist = roomService.selectRoom(locName);
		//3. 출력 처리
		long gapDate = Utility.calDate(ci_date, co_date);
		DecimalFormat df = new DecimalFormat("#,###");
		mav.addObject("attributeName", attributeValue);
		mav.setViewName("");
		return mav;
	}
}
