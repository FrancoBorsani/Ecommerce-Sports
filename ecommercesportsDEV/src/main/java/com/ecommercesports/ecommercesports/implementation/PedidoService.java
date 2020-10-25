package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	  @Autowired
	    @Qualifier("pedidoRepository")
	    private IPedidoRepository pedidoRepository;

	    @Autowired
	    @Qualifier("pedidoConverter")
	    private PedidoConverter pedidoConverter;
	     
	    @Autowired
	    @Qualifier("carritoConverter")
	    private CarritoConverter carritoConverter;	    
	    
	    @Autowired
	    @Qualifier("carritoService")
	    private ICarritoService carritoService;
	    
	    @Autowired
	    @Qualifier("userLogueadoService")
	    private IUserLogueadoService userLogueadoService;

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
	    
		@Override
		public Pedido insertarPedidoConCarrito_y_User_y_Traer(Producto producto,Carrito carrito) {
			PedidoModel pedidoModel = new PedidoModel();
			pedidoModel.setCantidad(1);
			pedidoModel.setComentario("");
			pedidoModel.setDomicilio("");
			pedidoModel.setPagado(false);
			pedidoModel.setMetodoPago("");
			pedidoModel.setCarritoModel(carritoService.findByIdCarrito(carrito.getIdCarrito()));
			pedidoModel.setUser(userLogueadoService.traerUserLogueado());
			pedidoModel.setImporteAPagar(producto.getPrecio());
			insertOrUpdate(pedidoModel);
			return getAll().get(getAll().size()-1);//Le devuelvo el carrito que guardé (el último que se agregó en la BD)
		}

	//	@Override
	//	public double getCostoEnvio(String empresa, int nroColumna) {
	//		// TODO Auto-generated method stub
	//		return pedidoRepository.getCostoEnvio(empresa,nroColumna);
	//	}

	//	@Override
	//	public int updateCostoEnvio(double costoEnvio, long idCarrito) {
			// TODO Auto-generated method stub
	//		return pedidoRepository.updateCostoEnvio(costoEnvio, idCarrito);
	//	}
		
		

}//Fin class
