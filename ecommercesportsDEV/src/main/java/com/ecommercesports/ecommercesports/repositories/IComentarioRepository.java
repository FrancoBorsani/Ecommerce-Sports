package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Comentario;

@Repository("comentarioRepository")
public interface IComentarioRepository extends JpaRepository<Comentario, Serializable>{
	public abstract Comentario findByIdComentario(long idComentario);
	
	@Query("SELECT u FROM Comentario u WHERE u.producto.idProducto = (:idProducto)")
	public abstract List<Comentario> findByIdProducto(@Param("idProducto") long idProducto);
		
	
	
	
}
