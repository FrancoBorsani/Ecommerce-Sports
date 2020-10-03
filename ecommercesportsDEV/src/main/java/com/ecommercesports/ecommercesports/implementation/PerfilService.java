package com.ecommercesports.ecommercesports.implementation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.repositories.IPerfilRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.IPerfilService;

@Service
public class PerfilService implements IPerfilService{

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Override
	public List<Perfil> getAll() {
		return perfilRepository.findAll();
	}
	
	@Override
	public Perfil findById(int id) {
		return perfilRepository.findById(id);
	}

	@Override
	public boolean remove(int id) {
		try {
			
			perfilRepository.deleteById(id);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
	}

	@Override
	public Perfil insertOrUpdateProfile(User usuario) {
		Perfil perfil = null;
		
		if(perfilRepository.findById(usuario.getId()) == null) {
			perfil = perfilRepository.save(new Perfil(usuario.getId(), usuario.getUsername(), 
					usuario.getLastName(), usuario.getFirstName(), usuario.getEmail()));
		}
		else {
			Perfil perfilUpdate = perfilRepository.findById(usuario.getId());
			remove(usuario.getId());
			
			//modifico el perfil con el nuevo username
			perfilUpdate.setUsername(usuario.getUsername());
			perfilUpdate.setFirstName(usuario.getFirstName());
			perfilUpdate.setLastName(usuario.getLastName());
			perfilRepository.save(perfilUpdate);
		}
		return perfil;
	}

	
	
}
