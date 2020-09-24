package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.ClaveTemporal;


@Repository("claveTemporalRepository")
public interface IClaveTemporalRepository extends JpaRepository<ClaveTemporal, Serializable> {
	 public abstract ClaveTemporal findById(long id);
}
