package com.ecommercesports.ecommercesports.services;

import java.io.IOException;
import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;


public interface IPerfilService {
	
	public abstract java.util.List<Perfil>getAll();
	
	public abstract Perfil findById(int id);
	
	public Perfil addNewProfile(User usuario);
	
	public Perfil updateProfile(Perfil profileToModif , String username  ,MultipartFile imagen , String aboutMe)throws IOException;
	
	public abstract boolean remove(int id);
	
}
