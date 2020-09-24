package com.ecommercesports.ecommercesports.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.ClaveTemporalConverter;
import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;
import com.ecommercesports.ecommercesports.repositories.IClaveTemporalRepository;
import com.ecommercesports.ecommercesports.services.IClaveTemporalService;

@Service("claveTemporalService")
public class ClaveTemporalService implements IClaveTemporalService {
	@Autowired
	@Qualifier("claveTemporalRepository")
	private IClaveTemporalRepository claveTemporalRepository;
	
	@Autowired
	@Qualifier("claveTemporalConverter")
	private ClaveTemporalConverter claveTemporalConverter;
	
	
	
	
	@Override
	public ClaveTemporalModel findById(long id) {	
		return claveTemporalConverter.entityToModel(claveTemporalRepository.findById(id));
		
	}
	
	
	@Override
	public ClaveTemporalModel insertOrUpdate(ClaveTemporalModel claveTemporalModel) {
		
		ClaveTemporal claveTemporal = claveTemporalRepository.save(claveTemporalConverter.modelToEntity(claveTemporalModel));
		return claveTemporalConverter.entityToModel(claveTemporal);
		
	}
	
	
	
	@Override
	public boolean remove(long id) {
		
		try {
			
			claveTemporalRepository.deleteById(id);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
}
