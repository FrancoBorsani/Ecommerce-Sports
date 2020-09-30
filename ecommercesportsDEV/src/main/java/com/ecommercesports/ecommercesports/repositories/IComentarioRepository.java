package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Comentario;

@Repository("comentarioRepository")
public interface IComentarioRepository extends JpaRepository<Comentario, Serializable>{
	public abstract Comentario findByIdComentario(long idComentario);
}
