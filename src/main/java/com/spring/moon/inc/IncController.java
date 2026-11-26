package com.spring.moon.inc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.AccountAccess;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

@Controller
public class IncController {
	private final AccountAccess accountAccess;

	public IncController(AccountAccess accountAccess) {
		this.accountAccess = accountAccess;
	}

	@GetMapping("/inc/top")
	public ModelAndView create(HttpServletRequest request) {
		GuestVO guest = accountAccess.currentGuest(request);
		ModelAndView mav = new ModelAndView("inc/top");
		mav.addObject("t_login", guest != null);
		mav.addObject("t_admin", guest != null && guest.getSys() == GuestService.ADMIN_ACCOUNT);
		return mav;
	}
}
