package com.spring.moon.guest.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.guest.model.GuestService;

@Controller
public class GuestController {
	@Autowired
	GuestService guestService;

	@GetMapping("/login/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String ck_value = "";
		Cookie[] ckArr = request.getCookies();
		if (ckArr != null) {
			for (int i = 0; i < ckArr.length; i++) {
				if (ckArr[i].getName().equals("ck_userid")) {
					ck_value = ckArr[i].getValue();
					break;
				}
			} // for
		}
		mav.addObject("ck_value", ck_value);
		mav.setViewName("login/login");
		return mav;
	}
	@GetMapping("/login/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		session.invalidate();
		mav.setViewName("redirect:/");
		return mav;
	}
	@GetMapping("/login/checkLogin")
	public ModelAndView checkLogin(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		String l_userid=(String)session.getAttribute("userid");
		mav.addObject("l_userid",l_userid);
		mav.setViewName("/login/checkLogin");
		return mav;
	}
////////////////////////////////////////////P O S T/////////////////////////////////////////////////////
	@PostMapping("/login/login")
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) map.get("userid");
		String pwd = (String) map.get("pwd");
		String chkSave = (String) map.get("chkSave");
		// 2
		String msg = "로그인 처리 실패", url = "javascript:history.back()";

		int result = guestService.loginCheck(userid, pwd);
		HttpSession session = request.getSession();
		if (result == guestService.LOGIN_OK) {
			// session에 저장
			session.setAttribute("userid", userid);
			// 쿠키에 저장 - 아이디 저장하기가 체크되었다면
			Cookie ck = new Cookie("ck_userid", userid);
			ck.setPath("/");

			// 체크하면 on, 체크 안하면 null
			if (chkSave != null) {// 체크된 경우
				ck.setMaxAge(90 * 24 * 60 * 60);// 쿠키 유지시간 : 90일
				response.addCookie(ck);
			} else { // 체크 안한 경우
				ck.setMaxAge(0);// 쿠키 삭제
				response.addCookie(ck);
			}
			msg = userid + "님이 로그인되었습니다.";
			url = "/";
		} else if (result == guestService.DISAGREE_PWD) {
			msg = "비밀번호가 일치하지 않습니다.";
		} else if (result == guestService.NONE_USERID) {
			msg = "해당 아이디가 존재하지 않습니다.";
		}
		mav.addObject("msg",msg);
		mav.addObject("url",url);
		mav.setViewName("common/message");
		return mav;
	}
	/*
	  @PostMapping("/login/login") public ModelAndView loginPost(@RequestParam
	  Map<String, Object> map) { 
	 	ModelAndView mav=new ModelAndView();
	  
	  return mav 
	  }
	 */
}
