package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Carrito;

@Repository("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable>{

	public abstract Carrito findByIdCarrito(long idCarrito);
	
	
	//@Query("SELECT u FROM Carrito u WHERE u.user.email = (:email)")
	//public abstract Carrito findByUser(@Param("email") String email);
		
	
	
	
}