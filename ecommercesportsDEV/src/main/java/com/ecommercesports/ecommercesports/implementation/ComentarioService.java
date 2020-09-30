package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.ComentarioConverter;
import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.models.ComentarioModel;
import com.ecommercesports.ecommercesports.repositories.IComentarioRepository;
import com.ecommercesports.ecommercesports.services.IComentarioService;


@Service("comentarioService")
public class ComentarioService implements IComentarioService{

	@Autowired
	@Qualifier("comentarioRepository")
	private IComentarioRepository comentarioRepository;
	
	@Autowired
	@Qualifier("comentarioConverter")
	private ComentarioConverter comentarioConverter;
	
	
	@Override
	public List<Comentario> getAll(){
		
		return comentarioRepository.findAll();
		
	}
	
	
	
	@Override
	public ComentarioModel findByIdComentario(long idComentario) {	
		return comentarioConverter.entityToModel(comentarioRepository.findByIdComentario(idComentario));
		
	}
	
	
	@Override
	public ComentarioModel insertOrUpdate(ComentarioModel comentarioModel) {
		
		Comentario comentario = comentarioRepository.save(comentarioConverter.modelToEntity(comentarioModel));
		return comentarioConverter.entityToModel(comentario);
		
	}
	
	
	
	@Override
	public boolean remove(long idComentario) {
		
		try {
			
			comentarioRepository.deleteById(idComentario);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	
}