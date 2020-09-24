package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;


public interface IClaveTemporalService {
	public List<ClaveTemporal> getAll();
	
	
	public ClaveTemporalModel insertOrUpdate(ClaveTemporalModel claveTemporalModel);
	
	public ClaveTemporalModel findById(long id);
	
	public boolean remove(long id);
}
