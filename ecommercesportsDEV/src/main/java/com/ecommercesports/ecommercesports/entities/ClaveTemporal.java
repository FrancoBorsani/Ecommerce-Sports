package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="claveTemporal")
public class ClaveTemporal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	 @Column(name = "clave")
	    private int clave;
	
	public ClaveTemporal() {
		
	}
	public ClaveTemporal(long id, int clave) {
		super();
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
