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

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.UserRoleService;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;

@Controller	
public class UserController {	

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	
	@GetMapping("/login")	
	public String login(Model model,	
						@RequestParam(name="error",required=false) String error,	
						@RequestParam(name="logout", required=false) String logout) {	

		if(error!=null){
			model.addAttribute("error", error);
		}
			
		model.addAttribute("logout", logout);
		
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
//		System.out.println(username);
		if(!username.isEmpty()) {
			return "redirect:/";
		}
		else {
			return "/user/login";
		}
	}	

	@GetMapping("/logout")	
	public String logout(Model model) {	
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		
		if(username.isEmpty()) {
			return "redirect:/";	
		}
		else return "/user/login";	
	}	

	@GetMapping("/loginsuccess")	
	public String loginCheck() {	
		return "redirect:/";	
	}	
	
	@GetMapping("/signin")
	public String register() {
		return ViewRouteHelpers.USER_LOGIN;
	}
	
	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") User newUSer) {
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null) {
			System.out.println("la cuenta ya existe");
		}
		
		//userRepository.save(newUSer);
		return "redirect:/";
	}
	
}

