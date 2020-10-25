package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Marca;

@Repository("marcaRepository")
public interface IMarcaRepository extends JpaRepository<Marca,Serializable>{

    public abstract Marca findByIdMarca(long idMarca);
	
	@Query(nativeQuery=true,value="select * from marca where nombre = (:nombreMarca)")
	public abstract Marca traerMarcaPorNombre(String nombreMarca);
	
}//Fin class
