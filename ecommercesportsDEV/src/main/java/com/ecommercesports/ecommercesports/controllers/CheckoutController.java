package com.ecommercesports.ecommercesports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.IPedidoService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);
		return mAV;	
	}
	
	@GetMapping("/envio")
	public ModelAndView envio() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.ENVIO);
		return mAV;	
	}
	
	@GetMapping("/envio/domicilio")
	public ModelAndView domicilio() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.FORM_DOMICILIO);
		mAV.addObject("pedido", new PedidoModel());
		return mAV;	
	}
	
	@GetMapping("/envio/pago")
	public ModelAndView pago() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);
		return mAV;	
	}
	
	
	 @PostMapping("/guardarPedido")
	    public RedirectView guardarPedido(@RequestParam("nombreapellido") String nombreApellido, @RequestParam("domicilio") String domicilio, @RequestParam("provincia") String provincia,@RequestParam("codigopostal") String codigoPostal, @RequestParam("telefono") String telefono, @RequestParam("comentario") String comentario ){
	    	Pedido p = new Pedido();
	    	p.setDomicilio(domicilio);
	    	
	    	String username = "";
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if( principal instanceof UserDetails) {
	    		username = ((UserDetails)principal).getUsername();
	    	}
	    	
	    	User currentUser = userRepository.findByUsername(username);
	    	System.out.println("Nombre de usuario:" + username);
	    	System.out.println("EMAIL DE USUARIO:" + currentUser.getEmail());
	      	p.setUser(currentUser);
	      	System.out.println("SE AGREGO EL USER");
	    	p.setCarrito(currentUser.getCarrito());
	    	System.out.println("SE AGREGO EL CARRITO");
	    	//p.setCantidad(currentUser.getCarrito().cantidadProductos());
	    	p.setCantidad(0);
	    	System.out.println("SE AGREGO LA CANTIDAD");
	    	//p.setImporteAPagar(currentUser.getCarrito().getTotal());
	    	p.setImporteAPagar(0);
	    	System.out.println("SE AGREGO EL IMPORTE");
	    	p.setMetodoPago("Efectivo");
	    	System.out.println("SE AGREGO EL METODO DE PAGO");
	    	p.setComentario(comentario);
	    	System.out.println("SE AGREGO EL COMENTARIO");
	    	p.setEstado("Activo");
	    	System.out.println("SE AGREGO EL ESTADO");
	    	pedidoService.insertOrUpdate(pedidoConverter.entityToModel(p));
	    	System.out.println("SE AGREGO EL PEDIDO");
	    //	p.setUser(user);
	    //	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
	    	
		    return new RedirectView("/envio/pago");
			//	 return mAV;
	    }

	
	

}
