package com.ecommercesports.ecommercesports.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IProductoService;
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
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CARRITO_INDEX);
		User user = userLogueadoService.traerUserLogueado();
        if(user!=null) {
		    mAV.addObject("carrito",carritoService.carritoDelUserLogueado()); 
		}else {
		  mAV.setViewName("acceso/ingreso");
		}

		return mAV;	
	}
	
	@PostMapping("/back")
	public RedirectView back() {
		
		return new RedirectView(ViewRouteHelpers.CARRITO_ROOT);
	}
	
}//Fin class
