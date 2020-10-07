package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.CarritoModel;

public interface ICarritoService {

	public List<Carrito> getAll();
	
	public CarritoModel insertOrUpdate(CarritoModel carritoModel);
	
	public CarritoModel findByIdCarrito(long idCarrito);
	
	public boolean remove(long idCarrito);

	public Carrito carritoDelUserLogueado();

	public Carrito insertarCarritoConFecha_y_Traer();	
	
	public Carrito agregarProductoAlCarrito(Producto producto);

	public int traerCantidaDeArticulosDelCarrito(Carrito carrito);
	
	public double traerMontoTotalDelCarrito(Carrito carrito);
	
	public boolean eliminarCarrito_PedidoSiEstaVacio(Carrito carrito);
	
}//Fin interface