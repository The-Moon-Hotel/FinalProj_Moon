package com.spring.moon.inc;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.guest.model.GuestSerivce;
import com.spring.moon.guest.model.GuestVO;

@Controller
public class IncController {
	@Autowired
	GuestSerivce guestSerivce;
//////////////////////////////G E T 방식 /////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/inc/top", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		HttpSession session =request.getSession();
		String t_userid=(String)session.getAttribute("userid");
		ModelAndView mav=new ModelAndView();
		
		boolean t_login=false;
		int GuestOrAdmin=GuestSerivce.GUEST_ACCOUNT;
		GuestVO guestVo=new GuestVO();
		if(t_userid!=null&& !t_userid.isEmpty()){
			t_login=true;
			try{
				guestVo=guestSerivce.selectByUserid(t_userid);
				int sys=guestVo.getSys();
				if(sys==GuestSerivce.ADMIN_ACCOUNT){
					GuestOrAdmin=GuestSerivce.ADMIN_ACCOUNT;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			t_userid="";
		}
		mav.addObject("t_userid",t_userid);
		mav.addObject("guestVo",guestVo);
		mav.addObject("t_login",t_login);
		mav.addObject("GuestOrAdmin",GuestOrAdmin);
		mav.setViewName("inc/top");
		return mav;
	}
}
