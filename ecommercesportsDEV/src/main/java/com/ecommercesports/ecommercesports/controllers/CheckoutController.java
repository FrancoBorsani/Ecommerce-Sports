package com.ecommercesports.ecommercesports.controllers;

import java.sql.Date;
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
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IPerfilService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

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
		ModelAndView mAV = new ModelAndView();
		
		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
		
		if(userLogueadoService.traerUserLogueado() == null) {
			mAV.setViewName(ViewRouteHelpers.USER_LOGIN);
        	return mAV;
		}
		else {
			mAV.setViewName(ViewRouteHelpers.CHECKOUT_INDEX);
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
	    		mAV.addObject("pedido", pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()));
	    		for (Item item : pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito().getListaItems()){
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
    		mAV.addObject("pedido", pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()));
    		for (Item item : pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito().getListaItems()){
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
	
	@GetMapping("/sucursal/pago")
	public ModelAndView pago() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PAGO);

		
		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
		
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
    		p = pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId());
    		mAV.addObject("pedido", pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()));
    		for (Item item : pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito().getListaItems()){
    				listaProductos.add(item);
    		}
    		
    	    		
    		mAV.addObject("items", listaProductos);
    		
		} catch (Exception e) {
			mAV.addObject("pedido", p);
    		mAV.addObject("items", listaProductos);
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
	    	mAV.addObject("costoEnvio",calcularCostoEnvio("Andreani",pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito()));
	    	
	    	List<Item> listaProductos = new ArrayList<Item>();
	    	Pedido p = new Pedido();
	    	
	    	try {
	    		p = pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId());
	    		mAV.addObject("pedido", pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()));
	    		for (Item item : pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito().getListaItems()){
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
			
	    }

	 @Autowired
	    private SendMailService SendmailService;

	@SuppressWarnings("finally")
	@PostMapping("/pagar")
	    public ModelAndView pagar(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("cardNumber") String cardNumber, @RequestParam("cvv") String cvv,@RequestParam("validDate") Date fecha){
		 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_INDEX);
		 
		 if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
	        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
	        }
		 
		 	String username = "";
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if( principal instanceof UserDetails) {
	    		username = ((UserDetails)principal).getUsername();
	    	}
	    	
	    	if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
	        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
	        }
	    	
	    	User currentUser = userRepository.findByUsername(username);
	    	
	    	
	    	  
	    	  
	   		  Pedido p = pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId());  
	   		  
	   		  
	   		  if(!p.getDomicilio().isEmpty()) {
	   			double costoEnvio = calcularCostoEnvio("Andreani",pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()).getCarrito());
	   			  p.setCostoEnvio(costoEnvio);
		   		  p.setImporteAPagar(p.getImporteAPagar()+ p.getCostoEnvio());
	   		  }
	   		  
	   		  p.setCVV(cvv);
	   		  p.setNumeroDeTarjeta(cardNumber);	  
	   		  p.setMetodoPago("Tarjeta de credito");
	   		  String message = "\n\n Datos del pedido: " + "\nID: " + p.getIdPedido() + "\nApellido: "+ p.getUser().getLastName() + "\nDomicilio: "+ p.getDomicilio() + "\nTotal: "+ p.getImporteAPagar() + "\n\nDatos del pedido: "+ p.getCarrito().mostrarParaPagar();
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
		    		//p = pedidoRepository.traerPedidoPorUsuario(currentUser.getId());
		    		p = pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId());
		    		mAV.addObject("pedido",pedidoRepository.traerPedidoPorIdUser_y_NoPagado(currentUser.getId()));
		    		for (Item item : pedidoRepository.traerPedidoPorUsuario(currentUser.getId()).getCarrito().getListaItems()){
		    			listaProductos.add(item);
		    		}
		    	
		    		mAV.addObject("items", listaProductos);
		    		
				} catch (Exception e) {
					mAV.addObject("pedido", p2);
		    		mAV.addObject("items", listaProductos);
				}
		    	finally { 
		    		mAV.addObject("pedidos", pedidoRepository.traerPedidosDelUser(currentUser.getId()));
		            mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());

		    		return mAV; 
		    	}
				
		        	
		    	
				//	 return mAV;
		    
	 
			//	 return mAV;*/
	    }
	
	
	public double calcularCostoEnvio(String empresa, Carrito carrito) {
		
	double largo = 20;
	double alto = 20;
	double ancho = 25;
		
	double pesoReal = calcularCostoReal();
				
	double pesoDefinitivo = 0;
		
				
		/* https://www.mercadolibre.com.ar/ayuda/C-mo-calcular-el-peso-de-tu-en_4420 */
		
		/* Ya conozco el peso físico y el volumétrico, ¿cuál uso para calcular el costo de mi envío?

		Si el peso volumétrico es menor o igual a 2, usá el peso físico de tu producto.
		
		Si el peso volumétrico es mayor a 2, usá el que sea mayor (físico o volumétrico).
		
		En nuestro ejemplo, el peso volumétrico es mayor a 2, por eso calcularemos el costo de envío usando ese peso. */
			
		
	double pesoVolumetrico = ( largo * alto * ancho ) / 4000;
		
	if(pesoVolumetrico<=pesoReal) {
			pesoDefinitivo = pesoReal;
	} else {
		pesoDefinitivo = (pesoVolumetrico>pesoReal)? pesoVolumetrico : pesoReal;
	}
		
	int nroColumna = 0;
		
		if(pesoDefinitivo>=0 && pesoDefinitivo < 0.5) {
			nroColumna = 1;
		} else if (pesoDefinitivo>=0.5 && pesoDefinitivo < 1) {
			nroColumna = 2;
		} else if (pesoDefinitivo>=1 && pesoDefinitivo < 2) {
			nroColumna = 3;
		} else if (pesoDefinitivo>=2 && pesoDefinitivo < 3) {
			nroColumna = 4;
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			nroColumna = 5;
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			nroColumna = 5;
		} else if (pesoDefinitivo>=5 && pesoDefinitivo < 10) {
			nroColumna = 6;
		} else if (pesoDefinitivo>=10 && pesoDefinitivo < 15) {
			nroColumna = 7;
		} else if (pesoDefinitivo>=15 && pesoDefinitivo < 20) {
			nroColumna = 8;
	} else if (pesoDefinitivo>=20 && pesoDefinitivo < 25) {
		nroColumna = 9;
	}

		return pedidoService.getCostoEnvio(empresa,nroColumna);
	}
	
	public double calcularCostoReal() {
		double costoReal = 0;
				
		
		for(Item i : carritoService.carritoDelUserLogueadoParaController().getListaItems()) {
			costoReal = costoReal + i.getCantidad() * i.getProducto().getPeso();
		}
				
		return costoReal;
	
	}
	
	

}
