package com.ecommercesports.ecommercesports.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IProductoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@Controller
@RequestMapping("/carritos")
public class CarritoController {
	
	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CARRITO_INDEX);
		User user = userLogueadoService.traerUserLogueado();
        if(user!=null) {
		    mAV.addObject("carrito",carritoService.carritoDelUserLogueadoParaController()); 
		}else {
		  mAV.setViewName("acceso/ingreso");
		}

		return mAV;	
	}
	
	@PostMapping("/back")
	public RedirectView back() {
		
		return new RedirectView(ViewRouteHelpers.CARRITO_ROOT);
	}
	
	
	@GetMapping("/productoAlCarrito/x/{url}/{id}")//la "x" es agregado para evitar que se pueda confundir con una posible ruta, ya que lo que llega "url" se pasa leteral (cambia depende de dónde venga)
	public String productoAlCarrito(@PathVariable("url") String url,@PathVariable("id") long idProducto) {
        Producto producto = productoRepository.findByIdProducto(idProducto);
		User user = userLogueadoService.traerUserLogueado();
        if(user!=null) { //	si el usuario está logueado agrego el producto a un item-carrito-pedido (si no existen se crean) para el pedido del usuario
		    carritoService.agregarProductoAlCarrito(producto); 	        	
        	return "redirect:/productos/"+url;
		}else {//	si el usuario NO está logueado
			return "redirect:/ingreso";//mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);
		}
    }
	
}//Fin class
