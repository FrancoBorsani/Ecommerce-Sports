package com.ecommercesports.ecommercesports.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarritoModel {

	private long idCarrito;
	//private List<Producto> listaProductos = new ArrayList<Producto>();
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

	

}