package com.ecommercesports.ecommercesports.models;


import java.time.LocalDate;
import java.util.Set;

import com.ecommercesports.ecommercesports.entities.Item;

public class CarritoModel {

	private long idCarrito;
	private Set<Item> listaItems;
	private LocalDate fecha;
	private float total;
	
	public CarritoModel() {
	}
	
	public CarritoModel(long idCarrito, LocalDate fecha) {
		super();
		this.idCarrito = idCarrito;
		this.fecha = fecha;

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

	@Override
	public String toString() {
		return "CarritoModel [idCarrito=" + idCarrito + ", listaItems=" + listaItems + ", fecha=" + fecha + ", total="
				+ total +"]";
	}

	
	
	
	
}//Fin class