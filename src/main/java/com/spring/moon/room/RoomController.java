package com.spring.moon.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoomController {
	
	  @RequestMapping("/room/amenity") 
	  public ModelAndView amenity() { 
	 	ModelAndView mav=new ModelAndView();
	 	mav.setViewName("room/amenity");
	  return mav; 
	  }
	  @RequestMapping("/room/deluxeDetail") 
	  public ModelAndView deluxeDetail() { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("room/deluxeDetail");
		  return mav; 
	  }
	  @RequestMapping("/room/monoDetail") 
	  public ModelAndView monoDetail() { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("room/monoDetail");
		  return mav; 
	  }
	  @RequestMapping("/room/room_main") 
	  public ModelAndView room_main() { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("room/room_main");
		  return mav; 
	  }
	  @RequestMapping("/room/suitDetail") 
	  public ModelAndView suitDetail() { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("room/suitDetail");
		  return mav; 
	  }
	  @RequestMapping("/room/supeDetail") 
	  public ModelAndView supeDetail() { 
		  ModelAndView mav=new ModelAndView();
		  mav.setViewName("room/supeDetail");
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
