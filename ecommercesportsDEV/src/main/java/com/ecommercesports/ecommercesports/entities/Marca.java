package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="marca")
public class Marca {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMarca;

    @Column(name = "nombre")
    private String nombre;

	public Marca(long idMarca, String nombre) {
		super();
		this.idMarca = idMarca;
		this.nombre = nombre;
	}

	public Marca() {
		super();
	}

	public long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(long idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Marca [idMarca=" + idMarca + ", nombre=" + nombre + "]";
	}
    
}
