package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Marca;

public interface IMarcaService {
	
	public List<Marca> getAll();

    public Marca traerMarcaPorNombre(String nombreMarca);
    
    public Marca traerMarcaPorNombreO_Crear(String nombreMarca);


}//Fin class 
