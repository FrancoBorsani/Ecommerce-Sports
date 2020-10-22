package com.ecommercesports.ecommercesports.models;

import javax.persistence.Column;
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
    private double totalPuntaje;
    private int cantidadValoraciones;
 
	 
	public ValoracionModel() {
		
		
	}
	
	
	public ValoracionModel(long idValoracion, User user, Producto producto,
			double totalPuntaje, int cantidadValoraciones) {
		this.idValoracion = idValoracion;
		this.user = user;
		this.producto = producto;		
		this.totalPuntaje = totalPuntaje;
		this.cantidadValoraciones = cantidadValoraciones;
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

	
	public void setTotalPuntaje(double totalPuntaje) {
		this.totalPuntaje = (this.totalPuntaje + totalPuntaje) / this.cantidadValoraciones;
	}


	public int getCantidadValoraciones() {
		return cantidadValoraciones;
	}


	public void setCantidadValoraciones(int cantidadValoraciones) {
		this.cantidadValoraciones = cantidadValoraciones;
	}


	public double getTotalPuntaje() {
		return totalPuntaje;
	}
	
	
	
	
	
}
