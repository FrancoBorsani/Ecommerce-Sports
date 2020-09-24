package com.ecommercesports.ecommercesports.entities;

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
	
	public ClaveTemporal() {
		
	}
	public ClaveTemporal(long id) {
		super();
		this.id = id;
		
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
}
