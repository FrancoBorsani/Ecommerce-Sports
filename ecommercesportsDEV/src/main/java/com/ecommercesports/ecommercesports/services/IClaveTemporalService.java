package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;

public interface IClaveTemporalService {
	public ClaveTemporalModel insertOrUpdate(ClaveTemporalModel claveModel);
	
	public ClaveTemporalModel findById(long id);
	
	public boolean remove(long id);
}
