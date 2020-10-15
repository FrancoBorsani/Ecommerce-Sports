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
		}else {
		  mAV.setViewName("acceso/ingreso");
		}
        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {
        return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
    }

}
