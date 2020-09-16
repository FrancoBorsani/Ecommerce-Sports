package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;

@Controller	
public class UserController {	
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") User newUSer) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		newUSer.setPassword(bCryptPasswordEncoder.encode(newUSer.getPassword())); 
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null ||  userRepository.findByEmail(newUSer.getUsername()) != null) {
			System.out.println("la cuenta ya existe");
		}
		else {
			userRepository.save(newUSer);
		}
		
		System.out.println("-----------------------------------------");
		
		System.out.println(newUSer.getFirstName());
		System.out.println(newUSer.getLastName());
		System.out.println(newUSer.getEmail());
		System.out.println(bCryptPasswordEncoder.encode(newUSer.getPassword()));
				
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