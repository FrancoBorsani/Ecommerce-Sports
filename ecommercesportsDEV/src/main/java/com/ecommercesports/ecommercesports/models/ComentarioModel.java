package com.ecommercesports.ecommercesports.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.entities.User;

public class ComentarioModel {

    private long idComentario;
    private User user;
    private String comentario;
	private Producto producto;
	
	
	public ComentarioModel() {
		
		
	}
	
	
	public ComentarioModel(long idComentario, User user, String comentario, Producto producto) {
		this.idComentario = idComentario;
		this.user = user;
		this.comentario = comentario;
		this.producto = producto;
		
	}


	public long getIdComentario() {
		return idComentario;
	}


	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
