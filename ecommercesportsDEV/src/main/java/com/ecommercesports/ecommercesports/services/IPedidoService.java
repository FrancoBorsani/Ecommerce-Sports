package com.ecommercesports.ecommercesports.services;

import java.util.List;

import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.models.PedidoModel;

public interface IPedidoService {
	 public List<Pedido> getAll();

	    public PedidoModel insertOrUpdate(PedidoModel pedidoModel);

	    public PedidoModel findByIdPedido(long idPedido);

	    public boolean remove(long idPedido);

}
