package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.TarifaEnvio;

@Repository("tarifaEnvioRepository")
public interface ITarifaEnvioRepository extends JpaRepository<TarifaEnvio, Serializable>{

}//Fin interface
