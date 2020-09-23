package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Marca;

@Repository("marcaRepository")
public interface IMarcaRepository extends JpaRepository<Marca,Serializable>{

}
