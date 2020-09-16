package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.UserRoleService;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;

@Controller	
public class UserController {	

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") User newUSer) {
		
		/*
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null) {
			System.out.println("la cuenta ya existe");
		}
		
		*/
		
		//userRepository.save(newUSer);
		
		System.out.println("-----------------------------------------");
		
		System.out.println(newUSer.getFirstName());
		System.out.println(newUSer.getLastName());
		System.out.println(newUSer.getEmail());
		System.out.println(newUSer.getPassword());
		
		System.out.println("-----------------------------------------");
		
		return "redirect:/";
	}
	
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