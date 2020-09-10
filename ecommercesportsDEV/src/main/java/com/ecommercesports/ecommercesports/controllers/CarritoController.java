package com.ecommercesports.ecommercesports.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.services.ICarritoService;

@Controller
@RequestMapping("/carritos")
public class CarritoController {
	
	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CARRITO_INDEX);

		return mAV;
		
	}
	
	@PostMapping("/back")
	public RedirectView back() {
		
		return new RedirectView(ViewRouteHelpers.CARRITO_ROOT);
	}
	
}
