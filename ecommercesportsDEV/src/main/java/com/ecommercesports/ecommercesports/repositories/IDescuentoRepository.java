package com.ecommercesports.ecommercesports.repositories;

import com.ecommercesports.ecommercesports.entities.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("descuentoRepository")
public interface IDescuentoRepository extends JpaRepository<Descuento, Serializable> {

    Descuento findByIdDescuento(long idDescuento);

    @Query(nativeQuery=true, value="SELECT * FROM descuento WHERE codigo = (:codigo)")
    Descuento findByCode(String codigo);
}