package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> getAll();
	
	public Categoria findByIdCategoria(long idCategoria);
	
    public Categoria traerCategoriaPorNombre(String nombreCategoria);
    
    public Categoria traerCategoriaPorNombreO_Crear(String nombreCategoria);

	
}//Fin class 
