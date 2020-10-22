package com.ecommercesports.ecommercesports.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="valoracion")
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idValoracion;	
	
	 @ManyToOne
	 @JoinColumn(name="user_id", nullable=false)
	    private User user;

	 @ManyToOne
	 @JoinColumn(name="producto_idProducto", nullable=false)
	    private Producto producto;
	
	public Valoracion() {}
	

	public Valoracion(long idValoracion, User user, Producto producto) {
		super();
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

	
	
	

}//Fin class
