package com.ecommercesports.ecommercesports.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;

@RestController
@RequestMapping("api/carrito")
public class CarritoRestController {
	
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
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProductos() {
		return ResponseEntity.ok("1");
	}
	
	
	@PutMapping("/sumarAlItem/{id}")
	public ResponseEntity<?> agregarUnidadAlItem(@PathVariable("id") long idItem) {
		
		itemService.agregarUnidadAlItemYTraer(itemRepository.findByIdItem(idItem));
		
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());
						
		return ResponseEntity.ok(carrito.getTotal());
	}
	
	@PutMapping("/restarAlItem/{id}")
	public ResponseEntity<?> eliminarUnidadAlItem(@PathVariable("id") long idItem) {
		
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());//traigo el carrito al que pertenece el item (antes de que se pueda eliminar el item)
		itemService.restarAlItemYTraer_EliminarSiEsCero(itemRepository.findByIdItem(idItem));//descuento una inidad de la "cantidad" del item y si el item que con cantidad = 0 se elimina	
		eliminarCarrito_y_pedidoSiCorresponde(carrito);//si ya no tengo items borro pedido y carrito
		
		return ResponseEntity.ok(carrito.getTotal());
	}
	
	public boolean eliminarCarrito_y_pedidoSiCorresponde(Carrito carrito){
		if(itemRepository.itemsDelCarrito(carrito.getIdCarrito()).isEmpty()) {//si no tengo items en el carrito
			pedidoService.remove(pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito()).getIdPedido());//borro el pedido
			return carritoService.remove(carrito.getIdCarrito());//borro el carrito			
		}else {
			return false;
		}		
	}

}
