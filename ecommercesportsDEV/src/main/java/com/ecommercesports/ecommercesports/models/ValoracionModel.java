package com.ecommercesports.ecommercesports.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.entities.User;

public class ValoracionModel {

    private long idValoracion;
    private User user;
    private Producto producto;
	
	 
	public ValoracionModel() {
		
		
	}
	
	
	public ValoracionModel(long idValoracion, User user, Producto producto) {
		this.idValoracion = idValoracion;
		this.user = user;
		this.producto = producto;		
	}


	public long getIdValoracion() {
		return idValoracion;
	}


	public void setIdValoracion(long idValoracion) {
		this.idValoracion = idValoracion;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	
	
}
