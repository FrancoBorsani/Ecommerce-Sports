package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;


@Component("claveTemporalConverter")
public class ClaveTemporalConverter {
	@Autowired
	@Qualifier("claveTemporalConverter")
	private ClaveTemporalConverter claveTemporalConverter;
	
	
public	ClaveTemporalModel entityToModel(ClaveTemporal claveTemporal) {
		return new ClaveTemporalModel(claveTemporal.getId(), claveTemporal.getClave());
	}
	
	public ClaveTemporal modelToEntity(ClaveTemporalModel claveTemporalModel) {
		return new ClaveTemporal(claveTemporalModel.getId(), claveTemporalModel.getClave());
	}
	
}