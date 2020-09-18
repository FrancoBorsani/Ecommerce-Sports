package com.ecommercesports.ecommercesports.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;

@Controller	
public class UserController {	
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	
	@GetMapping("/registro")
	public ModelAndView registro() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_REGISTRO);
	 return mAV;
	}

	@PostMapping("/register")
	public RedirectView  registerUserAccount( @ModelAttribute("user") User newUSer, RedirectAttributes redirectAttrs) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		newUSer.setPassword(bCryptPasswordEncoder.encode(newUSer.getPassword())); 
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null ||  userRepository.findByEmail(newUSer.getEmail()) != null) {
			System.out.println("la cuenta ya existe");
			redirectAttrs.addFlashAttribute("mensaje","Ya existe un usuario con esos datos");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/registro");
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
		
		return new RedirectView(ViewRouteHelpers.ROUTE_INDEX);
	}
	
	@GetMapping("/ingreso")
	public ModelAndView ingreso() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);	 
	 
	 return mAV;
	}
	

}
	

