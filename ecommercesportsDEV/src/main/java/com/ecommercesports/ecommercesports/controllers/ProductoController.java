package com.ecommercesports.ecommercesports.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.entities.Producto;
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

    @GetMapping({"", "/_DisplayType_LF"})
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        mAV.addObject("productos", productoService.getAll());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    }
    
    @GetMapping("/categorias/{id}/_DisplayType_G")
    public ModelAndView categoryCards(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        mAV.addObject("productos", productoService.findByCategoria(categoria));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    }
    
    @GetMapping("/marcas/{id}/_DisplayType_G")
    public ModelAndView brandsCards(@PathVariable("id") String marca) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        mAV.addObject("productos", productoService.filterByMarca(marca));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    }
    
    @GetMapping("/_DisplayType_G")
    public ModelAndView cards() {
        ModelAndView mAV = new ModelAndView("producto/cards");
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
    
    @GetMapping("/categorias/{id}_DisplayType_LF")
    public ModelAndView getByCategoriaDisplayType_LF(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.findByCategoria(categoria));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/categorias/{id}_DisplayType_G")
    public ModelAndView getByCategoriaDisplayType_G(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
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
    
    @GetMapping("/getAllProducts")
	@ResponseBody
	public List<Producto> getAllProducts(){
		return productoService.getAll();
	}
    
    @GetMapping("/search={id}")
    public ModelAndView search(@PathVariable("id") String keyword,Model model) {
    	
    	if (productoService.searchProduct(keyword).size() == 0) { 
    		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_NOT_FOUND);
    		model.addAttribute("searchTerm", keyword);
    		
    		return mAV;
    	}else {
    		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
            
            mAV.addObject("productos",productoService.searchProduct(keyword));
            mAV.addObject("categorias", categoriaService.getAll());
            mAV.addObject("marcas", marcaService.getAll());
            model.addAttribute("searchTerm", keyword);
            
            return mAV;
    	}
    	
    }
    
    @GetMapping("/search={id}_DisplayType_G")
    public ModelAndView searchDisplayType_G(@PathVariable("id") String keyword) {
    	
    	if (productoService.searchProduct(keyword).size() == 0) { 
    		return new ModelAndView("producto/notFound");
    	}else {
    		ModelAndView mAV = new ModelAndView("producto/cards");
            
            mAV.addObject("productos",productoService.searchProduct(keyword));
            mAV.addObject("categorias", categoriaService.getAll());
            mAV.addObject("marcas", marcaService.getAll());
            
            return mAV;
    	}
    	
    }
    
    @GetMapping("/_OrderId_PRICE*DESC")
    public ModelAndView orderByPriceDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByPriceDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_OrderId_PRICE*ASC")
    public ModelAndView orderByPriceAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByPriceAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_OrderId_NAME*DESC")
    public ModelAndView orderByNameDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByNameDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_OrderId_NAME*ASC")
    public ModelAndView orderByNameAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByNameAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_PRICE*DESC")
    public ModelAndView orderByPriceDescDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByPriceDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_PRICE*ASC")
    public ModelAndView orderByPriceAscDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByPriceAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_NAME*DESC")
    public ModelAndView orderByNameDescDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByNameDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_NAME*ASC")
    public ModelAndView orderByNameAscDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByNameAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());

        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {
        return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
    }
    
    @GetMapping("/destacados_DPT_LF")
    public ModelAndView destacados_DisplayType_LF() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_G")
    public ModelAndView destacados_DisplayType_G() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        return mAV;
    } 
    
}