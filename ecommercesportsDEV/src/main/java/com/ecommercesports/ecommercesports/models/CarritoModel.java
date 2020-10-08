package com.ecommercesports.ecommercesports.models;


import java.time.LocalDate;
import java.util.Set;

import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.User;

public class CarritoModel {

	private long idCarrito;
	private Set<Item> listaItems;
	private LocalDate fecha;
	private float total;
	private User user;
	
	public CarritoModel() {
	}
	
	public CarritoModel(long idCarrito, LocalDate fecha, User user) {
		super();
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.user = user;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Set<Item> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<Item> listaItems) {
		this.listaItems = listaItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
}//Fin class