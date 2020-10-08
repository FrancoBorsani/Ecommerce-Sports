package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Categoria;

@Repository("categoriaRepository")
public interface ICategoriaRepository extends JpaRepository<Categoria, Serializable> {
	
	public Categoria findByIdCategoria(long idCategoria);
}
