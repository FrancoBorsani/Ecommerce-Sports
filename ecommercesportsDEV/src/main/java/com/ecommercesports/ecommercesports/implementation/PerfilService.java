package com.ecommercesports.ecommercesports.implementation;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.repositories.IPerfilRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;
import com.ecommercesports.ecommercesports.services.IPerfilService;

@Service
public class PerfilService implements IPerfilService{

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
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
	public Perfil addNewProfile(User usuario) {
			return perfilRepository.save(new Perfil(usuario.getId(), usuario.getUsername(),userRoleRepository.findByIdUser(usuario.getId()).getRole(),
					usuario.getFirstName(), usuario.getLastName(), usuario.getCellPhone() ,usuario.getEmail()));
	}

	@Override
	public Perfil updateProfile(Perfil profileToModif, String username, String aboutMe ) {
		remove(profileToModif.getId());
		
		//modifico el perfil con el nuevo username
		profileToModif.setUsername(username);
		profileToModif.setAboutMe(aboutMe);
		return perfilRepository.save(profileToModif); 
	}

	
}
