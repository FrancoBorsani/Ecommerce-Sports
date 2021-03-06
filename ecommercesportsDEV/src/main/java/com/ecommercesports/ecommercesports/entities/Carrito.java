package com.ecommercesports.ecommercesports.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.javatuples.Pair;


@Entity
@Table(name="carrito")
public class Carrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCarrito;	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carrito")
	 private Set<Item> listaItems;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "total")
	private float total;
	
	
	public Carrito() {}
	

	public Carrito(long idCarrito, LocalDate fecha) {
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


	public Set<Item> getListaItems() {
		return listaItems;
	}


	public void setListaItems(Set<Item> listaItems) {
		this.listaItems = listaItems;
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


	
	public int cantidadProductos() {
		int cantidad = 0;
		cantidad = listaItems.size();
		return cantidad;
	}


	@Override
	public String toString() {
		return "Carrito [idCarrito=" + idCarrito + ", listaItems=" + listaItems + ", fecha=" + fecha + ", total="
				+ total + "]";
	}
	
	
	public  String mostrarParaPagar() {
	// Pair<Producto, Integer> listaMultiple = new Pair<Producto, Integer>(null, null);
	// int i = 0;
	 String pedido = "";
		for(Item item : this.getListaItems()) {
			//	listaMultiple.add(i, item.getProducto());
			//	i++;
				pedido = "\n" + pedido + item.getProducto().getDescripcionCorta() + "- x" + item.getCantidad() + "\n";
		}

		
		return pedido;
		
	}
	
	
	

}//Fin class
