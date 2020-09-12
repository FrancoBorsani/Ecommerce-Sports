package com.ecommercesports.ecommercesports.controllers;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_INDEX);

        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {

        return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
    }

}
