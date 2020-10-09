package com.ecommercesports.ecommercesports.services;

import java.net.URL;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;


public interface IPerfilService {
	
	public abstract java.util.List<Perfil>getAll();
	
	public abstract Perfil findById(int id);
	
	public Perfil addNewProfile(User usuario);
	
	public Perfil updateProfile(Perfil profileToModif , String username  , String aboutMe);
	
	
	public abstract boolean remove(int id);
	
}
