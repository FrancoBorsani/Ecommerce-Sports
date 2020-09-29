package com.ecommercesports.ecommercesports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);

		return mAV;	
	}
	
	@GetMapping("/envio")
	public ModelAndView envio() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.ENVIO);

		return mAV;	
	}
	

}
