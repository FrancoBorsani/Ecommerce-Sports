package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.entities.Carrito;
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


	@GetMapping("/deleteItem/{id}")
	public String eliminarItem(@PathVariable("id") long idItem) {
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());//traigo el carrito al que pertenece el item (antes de que se elimine el item)
		itemService.remove(idItem);//elimino el item
		eliminarCarrito_e_itemSiCorresponde(carrito);//si ya no tengo items borro pedido y carrito
		
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
		eliminarCarrito_e_itemSiCorresponde(carrito);//si ya no tengo items borro pedido y carrito

		return "redirect:/carritos";
	}

	public boolean eliminarCarrito_e_itemSiCorresponde(Carrito carrito){
		if(itemRepository.itemsDelCarrito(carrito.getIdCarrito()).isEmpty()) {//si no tengo items en el carrito
			pedidoService.remove(pedidoRepository.traerPedidoDelCarrito(carrito.getIdCarrito()).getIdPedido());//borro el pedido
			return carritoService.remove(carrito.getIdCarrito());//borro el carrito			
		}else {
			return false;
		}
		
	}
	
}//Fin class
