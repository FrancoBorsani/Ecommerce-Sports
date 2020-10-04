package com.ecommercesports.ecommercesports.converters;

import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.models.PedidoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pedidoConverter")
public class PedidoConverter {
	
	  @Autowired
	    @Qualifier("carritoConverter")
	    private CarritoConverter carritoConverter;

	    public PedidoModel entityToModel(Pedido pedido) {
	        return new PedidoModel(pedido.getIdPedido() , pedido.getDomicilio(), pedido.getUser(), carritoConverter.entityToModel(pedido.getCarrito()), pedido.getCantidad() 
	        		, pedido.getImporteAPagar() , pedido.getMetodoPago() , pedido.getComentario() , pedido.getEstado());
	    }

	    public Pedido modelToEntity(PedidoModel pedidoModel) {
	        return new Pedido(pedidoModel.getIdPedido() , pedidoModel.getDomicilio(), pedidoModel.getUser(), carritoConverter.modelToEntity(pedidoModel.getCarritoModel()) , pedidoModel.getCantidad() ,
	        		pedidoModel.getImporteAPagar() , pedidoModel.getMetodoPago() , pedidoModel.getComentario() , pedidoModel.getEstado());
	    }
	    
	    public PedidoModel entityToModel2(Pedido pedido) {
	        return new PedidoModel(pedido.getIdPedido(), pedido.getUser(), carritoConverter.entityToModel(pedido.getCarrito()));
	    }

	    public Pedido modelToEntity2(PedidoModel pedidoModel) {
	        return new Pedido(pedidoModel.getIdPedido(), pedidoModel.getUser(), carritoConverter.modelToEntity(pedidoModel.getCarritoModel()));
	    }
}//Fin class
