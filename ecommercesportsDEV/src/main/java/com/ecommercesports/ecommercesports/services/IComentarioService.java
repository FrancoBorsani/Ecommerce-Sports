package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.models.ComentarioModel;

public interface IComentarioService {
	public List<Comentario> getAll();
	
	public ComentarioModel insertOrUpdate(ComentarioModel comentarioModel);
	
	public ComentarioModel findByIdComentario(long idComentario);
	
	public boolean remove(long idComentario);
}
