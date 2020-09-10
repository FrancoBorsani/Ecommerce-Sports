package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Carrito;




@Repository("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable>{

	public abstract Carrito findByIdCarrito(long idCarrito);

	
}