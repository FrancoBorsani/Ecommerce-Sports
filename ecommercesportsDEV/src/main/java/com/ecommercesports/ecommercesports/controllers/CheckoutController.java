package com.ecommercesports.ecommercesports.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ecommercesports.ecommercesports.entities.Descuento;
import com.ecommercesports.ecommercesports.repositories.IDescuentoRepository;
import com.ecommercesports.ecommercesports.services.*;
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
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;

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

	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;

	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;


	@SuppressWarnings("finally")
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

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

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

		return mAV;
	}

	@SuppressWarnings("finally")
	@GetMapping("/envio/domicilio")
	public ModelAndView domicilio() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.FORM_DOMICILIO);
		String username = "";

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

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

	@SuppressWarnings("finally")
	@GetMapping("/envio/sucursal")
	public ModelAndView sucursal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.FORM_SUCURSAL);
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
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

	@GetMapping("/envio/pago")
	public ModelAndView pago() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

		return mAV;
	}


	@SuppressWarnings("finally")
	@PostMapping("/guardarPedido")
	public ModelAndView guardarPedido(@RequestParam("nombreapellido") String nombreApellido, @RequestParam("domicilio") String domicilio, @RequestParam("provincia") String provincia,@RequestParam("codigopostal") String codigoPostal, @RequestParam("telefono") String telefono, @RequestParam("comentario") String comentario ){
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

		User currentUser = userRepository.findByUsername(username);
		mAV.addObject("perfilUser", perfilService.findById(currentUser.getId()));

		List<Item> listaProductos = new ArrayList<Item>();
		Pedido p = new Pedido();

		try {
			p = pedidoRepository.traerPedidoPorUsuario(currentUser.getId());
			mAV.addObject("pedido", pedidoRepository.traerPedidoPorUsuario(currentUser.getId()));
			for (Item item : pedidoRepository.traerPedidoPorUsuario(currentUser.getId()).getCarrito().getListaItems()){
				listaProductos.add(item);
			}
			p.setComentario(comentario);
			p.setDomicilio(domicilio);

			pedidoService.insertOrUpdate(pedidoConverter.entityToModel(p));

			mAV.addObject("items", listaProductos);

		} catch (Exception e) {
			mAV.addObject("pedido", p);
			mAV.addObject("items", listaProductos);
		}
		finally {
			return mAV;
		}

		//	 return mAV;
	}

	@Autowired
	private SendMailService SendmailService;

	@Autowired
	@Qualifier("descuentoRepository")
	private IDescuentoRepository descuentoRepository;

	//Agrego campo de descuento TODO agregar campo en la vista /pagar
	@SuppressWarnings("finally")
	@PostMapping("/pagar")
	public ModelAndView pagar(@RequestParam("id") String id, @RequestParam("codigoDescuento") String codigoDescuento) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CHECKOUT_INDEX);
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}

		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
			mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}

		User currentUser = userRepository.findByUsername(username);
		Descuento descuento = descuentoRepository.findByCode(codigoDescuento.toLowerCase());

		if (descuento == null) {
			descuento = descuentoRepository.findByIdDescuento(1);
		}

		//Trae mas de un pedido, agregue en la query que el pedido no este pagado
		Pedido p = pedidoRepository.traerPedidoPorUsuario(currentUser.getId());

		p.setImporteAPagar(p.getImporteAPagar() * (1 - descuento.getPorcentaje() / 100));

		String message = "\n\nDatos del pedido: " +
						 "\nID: " + p.getIdPedido() +
						 "\nApellido: "+ p.getUser().getLastName() +
						 "\nDomicilio: "+ p.getDomicilio() +
						 "\nCódigo de descuento: " + descuento.getCodigo() + " " + descuento.getPorcentaje() + "%" +
						 "\nTotal: "+ p.getImporteAPagar();

		String subject = "DETALLES DEL PEDIDO"+ p.getIdPedido() ;
		SendmailService.sendMail("proyectodesoftwaretp@gmail.com", "proyectodesoftwaretp@gmail.com",subject,message);
		SendmailService.sendMail("proyectodesoftwaretp@gmail.com", ""+ p.getUser().getEmail(), subject,message);
		p.setPagado(true);

		pedidoService.insertOrUpdate(pedidoConverter.entityToModel(p));
		currentUser.setCantidadcompras(currentUser.getCantidadcompras()+1);
		userRepository.save(currentUser);
		List<Item> listaProductos = new ArrayList<Item>();
		Pedido p2 = new Pedido();

		try {
			p = pedidoRepository.traerPedidoPorUsuario(currentUser.getId());
			mAV.addObject("pedido", pedidoRepository.traerPedidoPorUsuario(currentUser.getId()));
			for (Item item : pedidoRepository.traerPedidoPorUsuario(currentUser.getId()).getCarrito().getListaItems()){
				listaProductos.add(item);
			}

			mAV.addObject("items", listaProductos);

		} catch (Exception e) {
			mAV.addObject("pedido", p2);
			mAV.addObject("items", listaProductos);
		}
		finally {
			return mAV;
		}
	}
}