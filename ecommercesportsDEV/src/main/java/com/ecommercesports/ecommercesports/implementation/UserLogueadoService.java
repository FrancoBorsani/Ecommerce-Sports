package com.ecommercesports.ecommercesports.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@Service("userLogueadoService")
public class UserLogueadoService implements IUserLogueadoService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
    @Override 
	public User traerUserLogueado() {
        String currentUserName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}

	 return userRepository.findByUsername(currentUserName);		
	
	};
	
	
}//Fin class
