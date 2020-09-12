package com.ecommercesports.ecommercesports.implementation;

import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	  @Autowired
	    @Qualifier("pedidoRepository")
	    private IPedidoRepository pedidoRepository;

	    @Autowired
	    @Qualifier("pedidoConverter")
	    private PedidoConverter pedidoConverter;

	    @Override
	    public List<Pedido> getAll() {
	        return pedidoRepository.findAll();
	    }

	    @Override
	    public PedidoModel findByIdPedido(long idPedido) {
	        return pedidoConverter.entityToModel(pedidoRepository.findByIdPedido(idPedido));
	    }

	    @Override
	    public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
	        Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
	        return pedidoConverter.entityToModel(pedido);
	    }

	    @Override
	    public boolean remove(long idPedido) {
	        try {
	            pedidoRepository.deleteById(idPedido);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

}
