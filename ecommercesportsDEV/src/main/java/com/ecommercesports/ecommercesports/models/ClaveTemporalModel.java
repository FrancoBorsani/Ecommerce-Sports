package com.ecommercesports.ecommercesports.models;

public class ClaveTemporalModel {

	private long id;
	private int clave;
	private String correo;
	
	public ClaveTemporalModel(){
		
	}
	
	public ClaveTemporalModel(long id, int clave, String correo) {
		this.id = id;
		this.clave = clave;
		this.correo = correo;
		
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
