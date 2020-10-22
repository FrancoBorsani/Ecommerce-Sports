package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IProductoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@RequestMapping("/")
@Controller
public class HomeController {
	
	@Autowired
	private SendMailService SendmailService;
	
    @Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
    
    @Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;
    
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
	
	@GetMapping("")
	public ModelAndView index() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
		
		mAV.addObject("productos", productoService.getProductosSinOferta());
		
		mAV.addObject("ofertas", productoService.getProductosEnOferta());
		
		if(userLogueadoService.traerUserLogueado() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
		
		
		
		
		return mAV;
	}
	
	@GetMapping("/contacto")
	public ModelAndView contacto() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CONTACTO);
		
		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
		
		return mAV;
	}
	
	@GetMapping("/infoInstitucional")
	public ModelAndView info_Institucional() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CONTACTO);
		
		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
		
		return mAV;
	}
	
	@PostMapping("/contactar")
	public String contactar(@RequestParam("nombre") String nombre, @RequestParam("mail") String mail, @RequestParam("consulta") String consulta) {
	String subject = "CONSULTA EN ECOMMERCE-SPORTS";
	     
	 String message = "\nMensaje de: " + nombre + "\nMail de contacto: " + mail  + "\nMensaje: " + consulta;
     SendmailService.sendMail("proyectodesoftwaretp@gmail.com", ""+ mail, subject,message);
		
		return ViewRouteHelpers.HOME;
	}
	
}
