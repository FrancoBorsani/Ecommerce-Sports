package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.models.CarritoModel;

public interface ICarritoService {

	public List<Carrito> getAll();
	
	public CarritoModel insertOrUpdate(CarritoModel carritoModel);
	
	public CarritoModel findByIdCarrito(long idCarrito);
	
	public boolean remove(long idCarrito);
	
}