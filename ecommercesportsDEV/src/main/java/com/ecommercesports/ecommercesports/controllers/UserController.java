package com.ecommercesports.ecommercesports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;

@Controller
@RequestMapping("/acceso")
public class UserController {
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);

        return mAV;
    }
    
	//@GetMapping("/login")
	//public String login() {
//		return ViewRouteHelpers.USER_LOGIN;
//	}
    
    
}
