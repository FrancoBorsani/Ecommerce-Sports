package com.ecommercesports.ecommercesports.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;

@Service("userRoleService")
public class UserRoleService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	public IUserRoleRepository userRoleRepository;
	
	public void saveUser(com.ecommercesports.ecommercesports.entities.UserRole user) {
		userRoleRepository.save(user);
	}

}