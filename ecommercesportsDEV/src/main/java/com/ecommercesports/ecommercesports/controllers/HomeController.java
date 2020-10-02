package com.ecommercesports.ecommercesports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;

@RequestMapping("/")
@Controller
public class HomeController {
	
	@GetMapping("")
	public String index() {
		return ViewRouteHelpers.HOME;
	}
	
	@GetMapping("/contacto")
	public String contacto() {
		return ViewRouteHelpers.CONTACTO;
	}
	
	@GetMapping("/infoInstitucional")
	public String info_Institucional() {
		return ViewRouteHelpers.INFO;
	}
	
}
