package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
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
	
	@GetMapping("/deleteItem/{id}")
	public String eliminarItem(@PathVariable("id") long idItem) {
		itemService.remove(idItem);
		
        return "redirect:/carritos";
    }
	

	@GetMapping("/sumarAlItem/{id}")
	public String agregarUnidadAlItem(@PathVariable("id") long idItem) {
		itemService.agregarUnidadAlItemYTraer(itemRepository.findByIdItem(idItem));
		
        return "redirect:/carritos";
    }
	
	@GetMapping("/restarAlItem/{id}")
	public String restarUnidadAlItem(@PathVariable("id") long idItem) {
		Carrito carrito = carritoRepository.findByIdCarrito(itemService.findByIdItem(idItem).getCarritoModel().getIdCarrito());
		itemService.restarAlItemYTraer_EliminarSiEsCero(itemRepository.findByIdItem(idItem));
		carritoService.eliminarCarrito_PedidoSiEstaVacio(carrito);//revisar
		
        return "redirect:/carritos";
    }
	
}//Fin class
