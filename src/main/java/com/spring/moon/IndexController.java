package com.spring.moon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	// 인덱스 페이지 시작
		@RequestMapping(value = "/")
		public ModelAndView index() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/index");
			return mav;
		}
		// 인덱스 페이지 끝

}
