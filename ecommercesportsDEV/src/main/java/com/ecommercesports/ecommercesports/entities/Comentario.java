package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comentario")
public class Comentario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComentario;

    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	
    @Column(name = "comentario")
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name="producto_idProducto", nullable=false)
    private Producto producto;

    
    
    public Comentario() {
    	
    	
    }
    
    public Comentario(long idComentario, User user, String comentario, Producto producto) {
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
