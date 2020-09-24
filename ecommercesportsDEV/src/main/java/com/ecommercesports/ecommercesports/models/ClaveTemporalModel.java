package com.ecommercesports.ecommercesports.models;

public class ClaveTemporalModel {

	private long id;
	private int clave;
	
	
	public ClaveTemporalModel(){
		
	}
	
	public ClaveTemporalModel(long id, int clave) {
		this.id = id;
		this.clave = clave;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}
	
	
	
}
