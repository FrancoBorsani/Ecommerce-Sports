package com.ecommercesports.ecommercesports.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.CarritoModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;


@Service("carritoService")
public class CarritoService implements ICarritoService{

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private IItemRepository itemRepository;

	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;

	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;

	@Override
	public List<Carrito> getAll(){

		return carritoRepository.findAll();

	}
	@Override
	public CarritoModel findByIdCarrito(long idCarrito) {	
		return carritoConverter.entityToModel(carritoRepository.findByIdCarrito(idCarrito));

	}


	@Override
	public CarritoModel insertOrUpdate(CarritoModel carritoModel) {

		Carrito carrito = carritoRepository.save(carritoConverter.modelToEntity(carritoModel));
		return carritoConverter.entityToModel(carrito);

	}



	@Override
	public boolean remove(long idCarrito) {

		try {

			carritoRepository.deleteById(idCarrito);;
			return true;
		}catch(Exception e) {

			return false;

		}

	}


	@Override	
	public Carrito carritoDelUserLogueado() {
		int idUserLogueado = userLogueadoService.traerUserLogueado().getId();
		Carrito carrito = carritoRepository.findByIdCarrito(pedidoRepository.traerPedidoByIdUserAndDateNow(idUserLogueado).getCarrito().getIdCarrito());

		return carrito;
	};	

	@Override
	public Carrito insertarCarritoConFecha_y_Traer() {
		CarritoModel carritoModel = new CarritoModel();
		carritoModel.setFecha(LocalDate.now());
		carritoModel.setTotal(0);
		insertOrUpdate(carritoModel);
		return getAll().get(getAll().size()-1);//Le agrego el carrito que guardé (el último que se agregó en la BD)
	}
	
	@Override	
	public Carrito agregarProductoAlCarrito(Producto producto) {
		Carrito carrito = carritoDelUserLogueado();
		if(carrito != null) {
			Item item = itemService.itemsByProducto(producto);
			if(item!=null) {
				itemService.agregarUnidadAlItemYTraer(item);
			}else {
			    carrito.getListaItems().add(itemService.insertarItemConProducto_y_Traer(producto,carrito));
			}
		}else{
			carrito = insertarCarritoConFecha_y_Traer();
			pedidoService.insertarPeedidoConCarrito_y_User_y_Traer(carrito);
			itemService.insertarItemConProducto_y_Traer(producto,carrito);
		}

		return carrito;
	}	
	
	@Override	
	public double traerMontoTotalDelCarrito(Carrito carrito) {
        double total = 0;
        for(Item item: itemRepository.itemsDelCarrito(carrito.getIdCarrito())) {
        	total += item.getProducto().getPrecio() * item.getCantidad();
        }
		return total;
	}
	
	@Override	
	public int traerCantidaDeArticulosDelCarrito(Carrito carrito) {
        int cantidad = 0;
        for(Item item: itemRepository.itemsDelCarrito(carrito.getIdCarrito())) {
        	cantidad += item.getCantidad();
        }
		return cantidad;
	}
	
	@Override	
	public boolean eliminarCarrito_PedidoSiEstaVacio(Carrito carrito) {
		if(itemRepository.itemsDelCarrito(carrito.getIdCarrito()).isEmpty()) {
			return remove(carrito.getIdCarrito());
		}else {	
		    return false;
		}
	}
	

}//Fin class
