package com.ecommercesports.ecommercesports.controllers;

import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @GetMapping("/productos")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);

        return mAV;
    }

    @GetMapping("/producto")
    public ModelAndView seleccionado() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_SELECCIONADO);

        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {

        return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
    }
}