package com.spring.moon.guest.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

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
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.invalidate();
		mav.setViewName("redirect:/");
		return mav;
	}

	@GetMapping("/login/checkLogin")
	public ModelAndView checkLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String l_userid = (String) session.getAttribute("userid");
		mav.addObject("l_userid", l_userid);
		mav.setViewName("/login/checkLogin");
		return mav;
	}

	@GetMapping("/guest/signUp")
	public ModelAndView signUp() {
		return new ModelAndView("guest/signUp");
	}

	@GetMapping("/guest/provision")
	public String provision(Model model) {
		return "guest/provision";
	}

	@RequestMapping("/guest/checkUserid")
	public ModelAndView checkUserid(Model model, @RequestParam String userid) {
		ModelAndView mav = new ModelAndView();
		int result = 0;
		if (userid != null && !userid.isEmpty()) {
			result = this.guestService.duplicateUserid(userid);
		}
		int EXIST_ID = GuestService.EXIST_ID;
		int NOT_EXIST_ID = GuestService.NOT_EXIST_ID;
		System.out.println("????????? ???????????? result=" + result);
		System.out.println("EXIST_ID=" + EXIST_ID);
		System.out.println("NOT_EXIST_ID =" + NOT_EXIST_ID);
		mav.addObject("EXIST_ID", EXIST_ID);
		mav.addObject("NOT_EXIST_ID", NOT_EXIST_ID);
		mav.addObject("result", result);
		mav.addObject("userid", userid);
		mav.setViewName("guest/checkUserid");
		return mav;
	}

	@GetMapping("/guest/guestEdit_pwdChk")
	public ModelAndView guestEdit_pwdChk(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String O_userid = (String) session.getAttribute("userid");

		if (O_userid == null) {
			String msg = "???????????? ???????????????.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			mav.addObject("O_userid", O_userid);
			mav.setViewName("guest/guestEdit_pwdChk");
		}
		return mav;
	}

	@GetMapping("/guest/signEdit")
	public ModelAndView signEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		GuestVO guestVO = this.guestService.selectByUserid(userid);
		System.out.println("guestVO.getName()"+guestVO.getName()+"guestVO.getTel()="+guestVO.getTel()+", guestVO.getEmail()="+guestVO.getEmail());
		String tel = guestVO.getTel();
		String tel1 = "", tel2 = "", tel3 = "";
		if (tel != null) {
			String[] telArr = guestVO.getTel().split("-");
			tel1 = telArr[0];
			tel2 = telArr[1];
			tel3 = telArr[2];
		}
		String email = guestVO.getEmail();
		String email1 = "", email2 = "";
		if (email != null ) {
			String[] emailArr = guestVO.getEmail().split("@");
			
			email1 = emailArr[0];
			email2 = emailArr[1];
		}
		mav.addObject("guestVO", guestVO);
		mav.addObject("tel1", tel1);
		mav.addObject("tel2", tel2);
		mav.addObject("tel3", tel3);
		mav.addObject("email1", email1);
		mav.addObject("email2", email2);
		mav.addObject("userid", userid);
		mav.setViewName("guest/signEdit");
		return mav;
	}
	@GetMapping("/guest/guestOut")
	public ModelAndView guestOut(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String O_userid = (String) session.getAttribute("userid");

		if (O_userid == null) {
			String msg = "???????????? ???????????????.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			mav.addObject("O_userid", O_userid);
			mav.setViewName("guest/guestOut");
		}
		return mav;
	}
	@GetMapping("/guest/myReservDetail")
	public ModelAndView myReservDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			String msg = "???????????? ???????????????.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			mav.addObject("userid", userid);
			mav.setViewName("guest/myReservDetail");
		}
		return mav;
	}
	@GetMapping("/guest/myReservList")
	public ModelAndView myReservList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			String msg = "???????????? ???????????????.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			mav.addObject("userid", userid);
			mav.setViewName("guest/myReservList");
		}
		return mav;
	}
	//////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////P O S T/////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/login/login")
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String userid = (String) map.get("userid");
		String pwd = (String) map.get("pwd");
		String chkSave = (String) map.get("chkSave");
		// 2
		String msg = "????????? ?????? ??????", url = "javascript:history.back()";

		int result = guestService.loginCheck(userid, pwd);
		HttpSession session = request.getSession();
		if (result == GuestService.LOGIN_OK) {
			// session??? ??????
			session.setAttribute("userid", userid);
			// ????????? ?????? - ????????? ??????????????? ??????????????????
			Cookie ck = new Cookie("ck_userid", userid);
			ck.setPath("/");

			// ???????????? on, ?????? ????????? null
			if (chkSave != null) {// ????????? ??????
				ck.setMaxAge(90 * 24 * 60 * 60);// ?????? ???????????? : 90???
				response.addCookie(ck);
			} else { // ?????? ?????? ??????
				ck.setMaxAge(0);// ?????? ??????
				response.addCookie(ck);
			}
			msg = userid + "?????? ????????????????????????.";
			url = "/";
		} else if (result == GuestService.DISAGREE_PWD) {
			msg = "??????????????? ???????????? ????????????.";
		} else if (result == GuestService.NONE_USERID) {
			msg = "?????? ???????????? ???????????? ????????????.";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}

	@PostMapping("/guest/signUp")
	public ModelAndView signUpPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String name = (String) map.get("name");
		String userid = (String) map.get("userid");
		String pwd = (String) map.get("pwd");
		String email1 = (String) map.get("email1");
		String email2 = (String) map.get("email2");
		String tel1 = (String) map.get("tel1");

		String tel2 = (String) map.get("tel2");
		String tel3 = (String) map.get("tel3");
		System.out.println("????????????=" + tel1 + "-" + tel2 + "-" + tel3);
		String tel = "", email = "";
		if (tel2 != null && !tel2.isEmpty() && tel3 != null && !tel3.isEmpty()) {
			tel = tel1 + "-" + tel2 + "-" + tel3;
		}

		if (email1 != null && !email1.isEmpty()) {
			if (email2 != null && !email2.isEmpty()) {
				email = email1 + "@" + email2;
			}
		}
		GuestVO guestVo = new GuestVO(name, userid, pwd, email, tel);

		String msg = "???????????? ??????", url = "javascript:history.back()";

		int cnt = this.guestService.insertGuest(guestVo);
		if (cnt > 0) {
			msg = "???????????????????????????.";
			url = "/";
		}

		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}

	@PostMapping("/guest/guestEdit_pwdChk")
	public ModelAndView guestEdit_pwdChkPost(HttpServletRequest request, @RequestParam String pwd) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "???????????? ???????????????.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			// db?????? - ???????????? ??????

			int result = this.guestService.loginCheck(userid, pwd);
			if (result == GuestService.EXIST_ID) {
				mav.setViewName("redirect:/guest/signEdit");

			} else {
				String msg = "??????????????? ???????????????.", url = "javascript:history.back()";
				mav.addObject("msg", msg);
				mav.addObject("url", url);
				mav.setViewName("common/message");
			}
		}
		return mav;
	}

	@PostMapping("/guest/signEdit")
	public ModelAndView signEditPost(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		String msg = "???????????? ???????????????.", url = "/login/login";
		if (userid == null) {
			msg = "???????????? ???????????????.";
			url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			String pwd = (String) map.get("pwd");
			String email1 = (String) map.get("email1");
			String email2 = (String) map.get("email2");
			String tel1 = (String) map.get("tel1");
			String tel2 = (String) map.get("tel2");
			String tel3 = (String) map.get("tel3");

			String tel = "", email = "";
			if (tel2 != null && !tel2.isEmpty() && tel3 != null && !tel3.isEmpty()) {
				tel = tel1 + "-" + tel2 + "-" + tel3;
			}
			if (email1 != null && !email1.isEmpty()) {
				if (email2 != null && !email2.isEmpty()) {
					email = email1 + "@" + email2;
				}
			}
			GuestVO guestVo = new GuestVO();
			guestVo.setUserid(userid);
			guestVo.setPwd(pwd);
			guestVo.setEmail(email);
			guestVo.setTel(tel);

			msg = "???????????? ?????? ??? ?????? ??????!";
			url = "javascript:history.back()";

			int cnt = this.guestService.updateGuset(guestVo);
			if (cnt > 0) {
				msg = "???????????? ?????? ??????!";
				url = "redirect:guest/signEdit";
			}

		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}
	@PostMapping("/guest/guestOut")
	public ModelAndView guestOutPost(HttpServletRequest request,@RequestParam String pwd) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		String msg = "???????????? ???????????????.", url = "/login/login";
		if (userid == null) {
			msg = "???????????? ???????????????.";
			url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {

			msg = "???????????? ??????!";
			url = "javascript:history.back()";

			int result = this.guestService.loginCheck(userid, pwd);
			if(result==GuestService.EXIST_ID){
				int cnt= this.guestService.OutGuset(userid, pwd);
				if (cnt > 0) {
					session.invalidate();
					Cookie[] ck= request.getCookies();
					for(int i=0; i<ck.length;i++){
						if(ck[i].getName()==userid){
							ck[i].setMaxAge(0);
							break;
						}
					}
					msg = "?????? ?????? ???????????????";
					url = "/";
				}
			}else{
				msg="??????????????? ???????????????";
			}

		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}
}
