package com.ecommercesports.ecommercesports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;

@Controller
public class UserController {
  //  @GetMapping("")
  //  public ModelAndView index() {
  //      ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);

  //      return mAV;
 //   }
    
	@GetMapping("/ingreso")
	public ModelAndView ingreso() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);
	 return mAV;
	}
    
	@GetMapping("/registro")
	public ModelAndView registro() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_REGISTRO);
	 return mAV;
	}
    
}
