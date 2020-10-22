package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Comentario;
import com.ecommercesports.ecommercesports.entities.Valoracion;

@Repository("valoracionRepository")
public interface IValoracionRepository extends JpaRepository<Valoracion, Serializable>{
	public abstract Valoracion findByIdValoracion(long idValoracion);
	
	@Query("SELECT u FROM Valoracion u WHERE u.user.id = (:id)")
	public abstract List<Valoracion> findByIdUser(@Param("id") long id);
		
	
	@Query("SELECT u FROM Valoracion u WHERE u.producto.idProducto = (:idProducto)")
	public abstract List<Valoracion> findByIdProducto(@Param("idProducto") long idProducto);
		
	
	@Query("SELECT u FROM Valoracion u WHERE u.producto.idProducto = (:idProducto)")
	public abstract Valoracion findVByIdProducto(@Param("idProducto") long idProducto);
		
	
	
	@Query("SELECT u FROM Valoracion u")
	public abstract List<Valoracion> obtenerValoraciones();
		
	
}
