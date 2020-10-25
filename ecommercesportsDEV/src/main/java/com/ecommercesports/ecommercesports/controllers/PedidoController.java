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

import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.CarritoService;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

    @Autowired
    @Qualifier("pedidoRepository")
    private IPedidoRepository pedidoRepository;
    
	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
	
	@Autowired
	@Qualifier("carritoService")
	private CarritoService carritoService;
    
//    @GetMapping("")
//    public ModelAndView index() {
//        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_INDEX);
//        
//        mAV.addObject("pedidos", pedidoService.getAll());
//        
//        return mAV;
//    }
    @GetMapping("")
    public ModelAndView pedidosDelUser() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_INDEX);
        User user = userLogueadoService.traerUserLogueado();
        if(user!=null) {
        	mAV.addObject("pedidos", pedidoRepository.traerPedidosDelUser(user.getId()));
            mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
		}else {
		  mAV.setViewName("acceso/ingreso");
		}
        return mAV;
    }
    
    @GetMapping("/detallePedido/{id}")
    public ModelAndView detallesDelPedidoDelUser(@PathVariable("id") long idPedido) {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_DETALLE);
    	User currentUser = userLogueadoService.traerUserLogueado();
    	Pedido p = pedidoRepository.traerPedidoPorUsuarioYPedido(currentUser.getId(),idPedido);
    	
    	if(p != null) {
    		mAV.addObject("pedido", p);
    		mAV.addObject("listaItems", p.getCarrito().getListaItems());
    		mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
    	}
    	else {
    		mAV.setViewName("acceso/ingreso");
    	}
    	return mAV;
    }
    
    @GetMapping("/volver")
	public String volver() {
    	return "redirect:/pedidos/";
	}
    
    
    @PostMapping("/back")
    public RedirectView back() {
        return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
    }

}
