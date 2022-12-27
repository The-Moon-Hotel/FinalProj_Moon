package com.spring.moon.facility;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class FacilityController {
	@RequestMapping(value ="/facility/facility") 
	public ModelAndView facility() { 
		ModelAndView mav= new ModelAndView("facility/facility"); 
		return mav; 
	}
	@RequestMapping(value ="/facility/cabana") 
	public ModelAndView cabana() { 
		return new ModelAndView("facility/cabana"); 
	}
	@RequestMapping(value ="/facility/dining") 
	public ModelAndView dining() { 
		return new ModelAndView("facility/dining"); 
	}
	@RequestMapping(value ="/facility/gym") 
	public ModelAndView gym() { 
		return new ModelAndView("facility/gym"); 
	}
	@RequestMapping(value ="/facility/lounge") 
	public ModelAndView lounge() { 
		return new ModelAndView("facility/lounge"); 
	}
	@RequestMapping(value ="/facility/sauna") 
	public ModelAndView sauna() { 
		return new ModelAndView("facility/sauna"); 
	}
	@RequestMapping(value ="/facility/swimm") 
	public ModelAndView swimm() { 
		return new ModelAndView("facility/swimm"); 
	}
	@RequestMapping(value ="/facility/tera") 
	public ModelAndView tera() { 
		return new ModelAndView("facility/tera"); 
	}
}
