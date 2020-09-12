package com.ecommercesports.ecommercesports.repositories;

import com.ecommercesports.ecommercesports.entities.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.io.Serializable;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {
	public abstract Pedido findByIdPedido(long idPedido);

}
