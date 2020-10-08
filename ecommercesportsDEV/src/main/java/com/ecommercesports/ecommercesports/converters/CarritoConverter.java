package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.models.CarritoModel;


@Component("carritoConverter")
public class CarritoConverter {
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;
	
	
public	CarritoModel entityToModel(Carrito carrito) {
		return new CarritoModel(carrito.getIdCarrito(), carrito.getFecha(), carrito.getUser());
	}
	
	public Carrito modelToEntity(CarritoModel carritoModel) {
		return new Carrito(carritoModel.getIdCarrito(), carritoModel.getFecha(), carritoModel.getUser());
	}
	
}