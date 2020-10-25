package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.entities.User;


@Repository("claveTemporalRepository")
public interface IClaveTemporalRepository extends JpaRepository<ClaveTemporal, Serializable> {
	 public abstract ClaveTemporal findById(long id);
	 public abstract ClaveTemporal findByClave(int clave);
	  
	@Query("SELECT u FROM ClaveTemporal u WHERE u.clave = (:clave)")
	public abstract User findByClave(@Param("clave") String clave);
		
}