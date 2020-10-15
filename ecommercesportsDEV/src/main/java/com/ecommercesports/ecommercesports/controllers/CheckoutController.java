package com.ecommercesports.ecommercesports.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.ecommercesports.ecommercesports.converters.PedidoConverter;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IPerfilService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("perfilService")
	private IPerfilService perfilService;
	
	
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
		
    	String username = "";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if( principal instanceof UserDetails) {
    		username = ((UserDetails)principal).getUsername();
    	}
    	
    	User currentUser = userRepository.findByUsername(username);
    	mAV.addObject("perfilUser", perfilService.findById(currentUser.getId()));
    	
    	List<Item> listaProductos = new ArrayList<Item>();
    	Pedido p = new Pedido();
    	
    	try {
    		mAV.addObject("pedido", pedidoRepository.traerPedidoPorUsuario(currentUser.getId()));
    		for (Item item : pedidoRepository.traerPedidoPorUsuario(currentUser.getId()).getCarrito().getListaItems()){
    	listaProductos.add(item);
		}
    	
    		mAV.addObject("items", listaProductos);
    		
		} catch (Exception e) {
			mAV.addObject("pedido", p);
    		mAV.addObject("items", listaProductos);
		}
    	finally { 
    		return mAV; 
    	}
		
		
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
	    public ModelAndView guardarPedido(@RequestParam("nombreapellido") String nombreApellido, @RequestParam("domicilio") String domicilio, @RequestParam("provincia") String provincia,@RequestParam("codigopostal") String codigoPostal, @RequestParam("telefono") String telefono, @RequestParam("comentario") String comentario ){
		 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);
				
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
	    	
	    	System.out.println("SE AGREGO EL PEDIDO");
	    //	p.setUser(user);
	    //	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
	    	mAV.addObject("pedido", pedidoService.insertOrUpdate(pedidoConverter.entityToModel(p)));
	    	
		    return mAV;
			//	 return mAV;
	    }

	 @Autowired
	    private SendMailService SendmailService;

	 @PostMapping("/pagar")
	    public ModelAndView pagar(@RequestParam("id") String id){
		 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);
					
		 	PedidoModel p = pedidoService.findByIdPedido(Long.parseLong(id));
	  //  	String message = "\n\n Datos del pedido: " + "\nID: " + p.getIdPedido() + "\nApellido: "+ p.getUser().getLastName() + "\nDomicilio: "+ p.getDomicilio() + "\nComentario: "+ p.getComentario() + "\nCarrito del pedido: "+ p.getUser().getCarrito().getIdCarrito() + "\nDetalles: "+ p.getUser().getCarrito().getListaItems() + "\nTotal: "+ p.getUser().getCarrito().getTotal();
	    //    String subject = "DETALLES DEL PEDIDO"+ p.getIdPedido() ;
	   //     SendmailService.sendMail("proyectodesoftwaretp@gmail.com", "proyectodesoftwaretp@gmail.com",subject,message);
	   //     SendmailService.sendMail("proyectodesoftwaretp@gmail.com", ""+ p.getUser().getEmail(), subject,message);
		     
	        
	    	return mAV;
			//	 return mAV;
	    }
	

}
