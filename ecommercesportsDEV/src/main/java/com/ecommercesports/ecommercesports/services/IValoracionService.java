package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Valoracion;
import com.ecommercesports.ecommercesports.models.ValoracionModel;

public interface IValoracionService {
	public List<Valoracion> getAll();
	
	public ValoracionModel insertOrUpdate(ValoracionModel valoracionModel);
	
	public ValoracionModel findByIdValoracion(long idValoracion);
	
	public boolean remove(long idValoracion);
}
