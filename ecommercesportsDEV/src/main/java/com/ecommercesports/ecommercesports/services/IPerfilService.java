package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;


public interface IPerfilService {
	
	public abstract java.util.List<Perfil>getAll();
	
	public abstract Perfil findById(int id);
	
	public abstract Perfil insertOrUpdateProfile(User usuario);
	
	public abstract boolean remove(int id);
	
}
