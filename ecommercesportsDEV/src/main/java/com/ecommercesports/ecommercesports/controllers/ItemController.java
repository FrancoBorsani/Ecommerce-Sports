package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;

	@Autowired
	@Qualifier("itemRepository")
	private IItemRepository itemRepository;

	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;


	@GetMapping("/deleteItem/{id}")
	public String eliminarItem(@PathVariable("id") long idItem) {
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());//traigo el carrito al que pertenece el item (antes de que se elimine el item)
		Item item = itemRepository.findByIdItem(idItem);//reservo los datos para usarlos en "descontarItemDeCarrito_y_Pedido"
		itemService.remove(idItem);//elimino el item
		if(!eliminarCarrito_y_pedidoSiCorresponde(carrito)){//si NO borro pedido y carrito
			descontarItemDeCarrito_y_Pedido(carrito, item);
		}
		
		return "redirect:/carritos";
	}


	@GetMapping("/sumarAlItem/{id}")
	public String agregarUnidadAlItem(@PathVariable("id") long idItem) {
		itemService.agregarUnidadAlItemYTraer(itemRepository.findByIdItem(idItem));

		return "redirect:/carritos";
	}

	@GetMapping("/restarAlItem/{id}")
	public String restarUnidadAlItem(@PathVariable("id") long idItem) {
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());//traigo el carrito al que pertenece el item (antes de que se pueda eliminar el item)
		itemService.restarAlItemYTraer_EliminarSiEsCero(itemRepository.findByIdItem(idItem));//descuento una inidad de la "cantidad" del item y si el item que con cantidad = 0 se elimina	
		eliminarCarrito_y_pedidoSiCorresponde(carrito);//si ya no tengo items borro pedido y carrito

		return "redirect:/carritos";
	}

	public boolean eliminarCarrito_y_pedidoSiCorresponde(Carrito carrito){
		if(itemRepository.itemsDelCarrito(carrito.getIdCarrito()).isEmpty()) {//si no tengo items en el carrito
			pedidoService.remove(pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito()).getIdPedido());//borro el pedido
			return carritoService.remove(carrito.getIdCarrito());//borro el carrito			
		}else {
			return false;
		}		
	}
	
	public void descontarItemDeCarrito_y_Pedido(Carrito carrito,Item item){
        Pedido pedido = pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito());
        carrito.setTotal(carrito.getTotal()-(float)(item.getProducto().getPrecio()*item.getCantidad()));
        pedido.setCantidad(pedido.getCantidad()-item.getCantidad());
        pedido.setImporteAPagar(pedido.getImporteAPagar()-item.getProducto().getPrecio()*item.getCantidad());
        carritoRepository.save(carrito);//para actualizar o guardar un carrito con datos no puedo usar insertOrUpdate porque se pierde uno de los atributos por como está hecho el converter
        pedidoService.insertOrUpdate(pedidoConverter.entityToModel(pedido));  
	}
	
	
}//Fin class
