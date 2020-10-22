package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.entities.Valoracion;
import com.ecommercesports.ecommercesports.models.ComentarioModel;
import com.ecommercesports.ecommercesports.models.ValoracionModel;


@Component("valoracionConverter")
public class ValoracionConverter {
	@Autowired
	@Qualifier("valoracionConverter")
	private ValoracionConverter valoracionConverter;
	
	
public	ValoracionModel entityToModel(Valoracion valoracion) {
		return new ValoracionModel(valoracion.getIdValoracion(), valoracion.getUser(), valoracion.getProducto(), valoracion.getTotalPuntaje(), valoracion.getCantidadValoraciones());
	}
	
	public Valoracion modelToEntity(ValoracionModel valoracionModel) {
		return new Valoracion(valoracionModel.getIdValoracion(), valoracionModel.getUser(), valoracionModel.getProducto(), valoracionModel.getTotalPuntaje(), valoracionModel.getCantidadValoraciones());
	}
	
}
