package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.models.ComentarioModel;


@Component("comentarioConverter")
public class ComentarioConverter {
	@Autowired
	@Qualifier("comentarioConverter")
	private ComentarioConverter comentarioConverter;
	
	
public	ComentarioModel entityToModel(Comentario comentario) {
		return new ComentarioModel(comentario.getIdComentario(), comentario.getUser(), comentario.getComentario(), comentario.getProducto());
	}
	
	public Comentario modelToEntity(ComentarioModel comentarioModel) {
		return new Comentario(comentarioModel.getIdComentario(), comentarioModel.getUser(), comentarioModel.getComentario(), comentarioModel.getProducto());
	}
	
}
