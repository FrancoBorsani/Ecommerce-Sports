package com.ecommercesports.ecommercesports.implementation;

 import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.CarritoModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
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
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;

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
	
	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;


	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	
	
	
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
	public Carrito carritoDelUserLogueadoParaController() {//en el controller no puede recibir en null ya que necesito usar una lista interna (carrito.listaItem) en el template
		Carrito carrito = new Carrito();//por lo anterior hize esto para que no tire error en la vista, ya que si era null daba error 		
		int idUserLogueado = userLogueadoService.traerUserLogueado().getId();
		    try{
		      return carrito = carritoRepository.findByIdCarrito(pedidoRepository.traerPedidoByIdUserAndDateNow(idUserLogueado).getCarrito().getIdCarrito());
			}catch(Exception e){
		       return carrito;
		    }
	};	
	@Override	
	public Carrito carritoDelUserLogueado() {
		Carrito carrito = null;//necesito saber cuando es null 		
		int idUserLogueado = userLogueadoService.traerUserLogueado().getId();
		    try{
		      return carrito = carritoRepository.findByIdCarrito(pedidoRepository.traerPedidoByIdUserAndDateNow(idUserLogueado).getCarrito().getIdCarrito());
			}catch(Exception e){
		       return carrito;
		    }
	};	


	@Override
	public Carrito insertarCarritoConFecha_y_Traer(Producto producto) {
		Carrito carrito = new Carrito();
		carrito.setFecha(LocalDate.now());
		carrito.setTotal((float)producto.getPrecio());
		carritoRepository.save(carrito);//para actualizar o guardar un carrito con datos no puedo usar insertOrUpdate porque se pierde uno de los atributos por como está hecho el converter
		return getAll().get(getAll().size()-1);//Le devuelvo el carrito que guardé (el último que se agregó en la BD)
	}
	
	
	public Carrito agregarItemYValoresAlCarritoYPedido(Producto producto, Carrito carrito) {
		carrito.getListaItems().add(itemService.insertarItemConProducto_y_Traer(producto,carrito));
        Pedido pedido = pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito());
        carrito.setTotal(carrito.getTotal()+(float)producto.getPrecio());
        pedido.setCantidad(pedido.getCantidad()+1);
        pedido.setImporteAPagar(pedido.getImporteAPagar()+producto.getPrecio());
        carritoRepository.save(carrito);//para actualizar o guardar un carrito con datos no puedo usar insertOrUpdate porque se pierde uno de los atributos por como está hecho el converter
        pedidoService.insertOrUpdate(pedidoConverter.entityToModel(pedido));
	  return carritoRepository.findByIdCarrito(carrito.getIdCarrito());
	}
	
	
	@Override	
	public Carrito agregarProductoAlCarrito(Producto producto) {
		Carrito carrito = carritoDelUserLogueado();
		if(carrito != null) {
			Item item = itemService.itemsByProducto(producto);
			if(item!=null) {
				itemService.agregarUnidadAlItemYTraer(item);
			}else {
				carrito = agregarItemYValoresAlCarritoYPedido(producto, carrito);
			}
		}else{
			carrito = insertarCarritoConFecha_y_Traer(producto);//lo creo con fecha actual y en total el precio del producto
			pedidoService.insertarPedidoConCarrito_y_User_y_Traer(producto,carrito);//creo el pedido y le agrergo el carrito con user y carrito el resto en 0  o  ""
			itemService.insertarItemConProducto_y_Traer(producto,carrito);//creo el item lo agrego al carrito y al carrito agrego el item
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
	

}//Fin class
