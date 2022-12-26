package com.spring.moon.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {
	@RequestMapping("/about/aboutBranch") 
	  public ModelAndView aboutBranch() { 
	 	ModelAndView mav=new ModelAndView();
	 	mav.setViewName("about/aboutBranch");
	  return mav; 
	  }
	@RequestMapping("/about/greetings") 
	  public ModelAndView greetings() { 
	 	ModelAndView mav=new ModelAndView();
	 	mav.setViewName("about/greetings");
	  return mav; 
	  }
}
