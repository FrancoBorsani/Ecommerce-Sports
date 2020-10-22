package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.ValoracionConverter;
import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.entities.Valoracion;
import com.ecommercesports.ecommercesports.models.ComentarioModel;
import com.ecommercesports.ecommercesports.models.ValoracionModel;
import com.ecommercesports.ecommercesports.repositories.IValoracionRepository;
import com.ecommercesports.ecommercesports.services.IValoracionService;


@Service("valoracionService")
public class ValoracionService implements IValoracionService{

	@Autowired
	@Qualifier("valoracionRepository")
	private IValoracionRepository valoracionRepository;
	
	@Autowired
	@Qualifier("valoracionConverter")
	private ValoracionConverter valoracionConverter;
	
	
	@Override
	public List<Valoracion> getAll(){
		
		return valoracionRepository.findAll();
		
	}
	
	
	
	@Override
	public ValoracionModel findByIdValoracion(long idValoracion) {	
		return valoracionConverter.entityToModel(valoracionRepository.findByIdValoracion(idValoracion));
		
	}
	
	
	@Override
	public ValoracionModel insertOrUpdate(ValoracionModel valoracionModel) {
		
		Valoracion valoracion = valoracionRepository.save(valoracionConverter.modelToEntity(valoracionModel));
		return valoracionConverter.entityToModel(valoracion);
		
	}
	
	
	
	@Override
	public boolean remove(long idValoracion) {
		
		try {
			
			valoracionRepository.deleteById(idValoracion);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	
}