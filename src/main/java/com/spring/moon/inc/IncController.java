package com.spring.moon.inc;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

@Controller
public class IncController {
	@Autowired
	GuestService guestSerivce;
//////////////////////////////G E T 방식 /////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/inc/top", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		HttpSession session =request.getSession();
		String t_userid=(String)session.getAttribute("userid");
		ModelAndView mav=new ModelAndView();
		
		boolean t_login=false;
		int GuestOrAdmin=GuestService.GUEST_ACCOUNT;
		int GUEST_ACCOUNT=GuestService.GUEST_ACCOUNT;
		GuestVO guestVo=null;
		if(t_userid!=null&& !t_userid.isEmpty()){
			t_login=true;
			guestVo=guestSerivce.selectByUserid(t_userid);
			int sys=guestVo.getSys();
			if(sys==GuestService.ADMIN_ACCOUNT){
				GuestOrAdmin=GuestService.ADMIN_ACCOUNT;
			}
		}else{
			t_userid="";
		}
		mav.addObject("t_userid",t_userid);
		mav.addObject("guestVo",guestVo);
		mav.addObject("t_login",t_login);
		mav.addObject("GuestOrAdmin",GuestOrAdmin);
		mav.addObject("GUEST_ACCOUNT",GUEST_ACCOUNT);
		mav.setViewName("inc/top");
		return mav;
	}
}
