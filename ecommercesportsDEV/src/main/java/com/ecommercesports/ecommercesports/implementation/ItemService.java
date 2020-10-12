package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.ItemConverter;
import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ItemModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IProductoService;
import com.ecommercesports.ecommercesports.services.ICarritoService;

@Service("itemService")
public class ItemService implements IItemService{
	@Autowired
	@Qualifier("itemRepository")
	private IItemRepository itemRepository;
	
	@Autowired
	@Qualifier("itemConverter")
	private ItemConverter itemConverter;
	
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;

	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;
	
	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	
	@Override
	public List<Item> getAll(){
		
		return itemRepository.findAll();
		
	}
	@Override
	public ItemModel findByIdItem(long idItem) {	
		return itemConverter.entityToModel(itemRepository.findByIdItem(idItem));
		
	}
	
	@Override
	public ItemModel insertOrUpdate(ItemModel itemModel) {
		Item item = itemRepository.save(itemConverter.modelToEntity(itemModel));
		return itemConverter.entityToModel(item);
	}
	@Override
	public boolean remove(long idItem) {
		
		try {
			
			itemRepository.deleteById(idItem);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	@Override
	public  Item itemsByProducto(Producto producto) {
		Item item = null;
		try {
			return item = itemRepository.itemsByIdProducto(producto.getIdProducto());
		}catch(Exception e) {
			return item;
		}
		
	}
	
	@Override
	public Item insertarItemConProducto_y_Traer(Producto producto, Carrito carrito) {
		Item item = new Item();
		item.setProducto(producto);
		item.setCantidad(1);
		item.setCarrito(carrito);
		return itemRepository.save(item);//Le devuelvo el item que guardé (el último que se agregó en la BD)
	}
	
	@Override
	public Item agregarUnidadAlItemYTraer(Item item) {
		 int cantidad = item.getCantidad()+1;
		 item.setCantidad(cantidad);
		 Carrito carrito = carritoRepository.findByIdCarrito(item.getCarrito().getIdCarrito());
         Pedido pedido = pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito());
         carrito.setTotal(carrito.getTotal()+(float)item.getProducto().getPrecio());
         pedido.setCantidad(pedido.getCantidad()+1);
         pedido.setImporteAPagar(pedido.getImporteAPagar()+item.getProducto().getPrecio());
         carritoRepository.save(carrito);//para actualizar o guardar un carrito con datos no puedo usar insertOrUpdate porque se pierde uno de los atributos por como está hecho el converter
         pedidoService.insertOrUpdate(pedidoConverter.entityToModel(pedido));
		 return itemRepository.save(item);
	}
	
	@Override
	public Item restarAlItemYTraer_EliminarSiEsCero(Item item) {
		 int cantidad = item.getCantidad()-1;
		 item.setCantidad(cantidad);
		 Carrito carrito = carritoRepository.findByIdCarrito(item.getCarrito().getIdCarrito());
         Pedido pedido = pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito());
         carrito.setTotal(carrito.getTotal()-(float)item.getProducto().getPrecio());
         pedido.setCantidad(pedido.getCantidad()-1);
         pedido.setImporteAPagar(pedido.getImporteAPagar()-item.getProducto().getPrecio());
         carritoRepository.save(carrito);//para actualizar o guardar un carrito con datos no puedo usar insertOrUpdate porque se pierde uno de los atributos por como está hecho el converter
         pedidoService.insertOrUpdate(pedidoConverter.entityToModel(pedido));
		 if(cantidad==0) {
			 remove(item.getIdItem());
			return item = null; 
		 }else {
		   return itemRepository.save(item);
		 }
	}	
	
}//Fin class
