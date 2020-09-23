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
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.services.ICategoriaService;
import com.ecommercesports.ecommercesports.services.IMarcaService;
import com.ecommercesports.ecommercesports.services.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
    
    @Autowired
    @Qualifier("categoriaService")
    private ICategoriaService categoriaService;
    
    @Autowired
    @Qualifier("marcaService")
    private IMarcaService marcaService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        mAV.addObject("productos", productoService.getAll());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    }
    
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long idProducto) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_SELECCIONADO);
        mAV.addObject("producto", productoService.findByIdProducto(idProducto));
        
        return mAV;
    }
    
    @GetMapping("/categorias/{id}")
    public ModelAndView getByCategoria(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.findByCategoria(categoria));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/marcas/{id}")
    public ModelAndView getByMarca(@PathVariable("id") String marca) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.filterByMarca(marca));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {

        return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
    }
}