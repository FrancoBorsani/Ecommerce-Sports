package com.ecommercesports.ecommercesports.controllers;

import com.ecommercesports.ecommercesports.entities.Descuento;
import com.ecommercesports.ecommercesports.implementation.CarritoService;
import com.ecommercesports.ecommercesports.implementation.DescuentoService;
import com.ecommercesports.ecommercesports.models.DescuentoModel;
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
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.IPedidoService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	@Qualifier("carritoService")
	private CarritoService carritoService;

	@Autowired
	@Qualifier("descuentoService")
	private DescuentoService descuentoService;

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
	    public ModelAndView guardarPedido(@RequestParam("nombreapellido") String nombreApellido,
										  @RequestParam("domicilio") String domicilio,
										  @RequestParam("provincia") String provincia,
										  @RequestParam("codigopostal") String codigoPostal,
										  @RequestParam("telefono") String telefono,
										  @RequestParam("comentario") String comentario,
										  @RequestParam("codigo") String descuento) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);
		Pedido p = new Pedido();
		String username = "";
		DescuentoModel descuento1;

		if (descuento != null || descuento != "") {
			descuento1 = descuentoService.findByCode(descuento.toUpperCase());
		} else {
			descuento1 = descuentoService.findByIdDescuento(1);
		}
		double montoCompra;

		p.setDomicilio(domicilio);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
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

		montoCompra = currentUser.getCarrito().getTotal();
		montoCompra -= montoCompra * descuento1.getPorcentaje();

		p.setImporteAPagar(montoCompra);

		System.out.println("SE AGREGO EL IMPORTE");
		p.setMetodoPago("Efectivo");
		System.out.println("SE AGREGO EL METODO DE PAGO");
		p.setComentario(comentario);
		System.out.println("SE AGREGO EL COMENTARIO");
		p.setEstado("Activo");
		System.out.println("SE AGREGO EL ESTADO");
		System.out.println("SE AGREGO EL PEDIDO");
		System.out.println("CODIGO DE DESCUENTO UTILIZADO: " + descuento1.getCodigo());

		mAV.addObject("pedido", pedidoService.insertOrUpdate(pedidoConverter.entityToModel(p)));

		return mAV;
	    }

	 @Autowired
	    private SendMailService SendmailService;

	 @PostMapping("/pagar")
	    public ModelAndView pagar(@RequestParam("id") String id){
		 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);
					
		 	PedidoModel p = pedidoService.findByIdPedido(Long.parseLong(id));
	    	String message = "\n\n Datos del pedido: " + "\nID: " + p.getIdPedido() + "\nApellido: "+ p.getUser().getLastName() + "\nDomicilio: "+ p.getDomicilio() + "\nComentario: "+ p.getComentario() + "\nCarrito del pedido: "+ p.getUser().getCarrito().getIdCarrito() + "\nDetalles: "+ p.getUser().getCarrito().getListaItems() + "\nTotal: "+ p.getUser().getCarrito().getTotal();
	        String subject = "DETALLES DEL PEDIDO"+ p.getIdPedido() ;
	        SendmailService.sendMail("proyectodesoftwaretp@gmail.com", "proyectodesoftwaretp@gmail.com",subject,message);
	        SendmailService.sendMail("proyectodesoftwaretp@gmail.com", ""+ p.getUser().getEmail(), subject,message);
		     
	        
	    	return mAV;
			//	 return mAV;
	    }
/*
	@GetMapping("/envio/pago/confirmar")
	public ModelAndView confirmarCompra() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CONFIRMAR_COMPRA);

		try {
			mAV.addObject("itemsCarrito", carritoService.carritoDelUserLogueado().getListaItems());
			mAV.addObject("totalCarrito", carritoService.carritoDelUserLogueado().getTotal());
		} catch (Exception e){
			//TODO ver que hacer si el carrito está vacío
			e.printStackTrace();
		}

		return mAV;
	}

	@PostMapping("/envio/pago/confirmar/finalizar")
	public ModelAndView finalizarCompra(@RequestParam ("codigo-descuento") String codigo) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.FINALIZAR_COMPRA);

		try {
			double totalCompra = carritoService.carritoDelUserLogueado().getTotal();

			if (descuentoService.findByCode(codigo.toUpperCase()) != null) {
				double descuento = descuentoService.findByCode(codigo.toUpperCase()).getPorcentaje();
				totalCompra -= totalCompra * descuento;
			}

			mAV.addObject("itemsCarrito", carritoService.carritoDelUserLogueado().getListaItems());
			mAV.addObject("codigoDescuento", descuentoService.findByCode(codigo.toUpperCase()).getCodigo().toUpperCase());
			mAV.addObject("totalCompra", totalCompra);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mAV;
	}

 */
}
