package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.SendMailService;

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
	
	 @Autowired
	    private SendMailService SendmailService;
	
	@PostMapping("/contactar")
	public String contactar(@RequestParam("nombre") String nombre, @RequestParam("mail") String mail, @RequestParam("consulta") String consulta) {
	String subject = "CONSULTA EN ECOMMERCE-SPORTS";
	     
	 String message = "\nMensaje de: " + nombre + "\nMail de contacto: " + mail  + "\nMensaje: " + consulta;
     SendmailService.sendMail("proyectodesoftwaretp@gmail.com", ""+ mail, subject,message);
    
		
		
		
		return ViewRouteHelpers.HOME;
	}
	
	
	
	
	
}
