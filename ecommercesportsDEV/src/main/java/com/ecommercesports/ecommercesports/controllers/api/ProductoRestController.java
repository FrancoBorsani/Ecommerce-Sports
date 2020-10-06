package com.ecommercesports.ecommercesports.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercesports.ecommercesports.models.ProductoModel;
import com.ecommercesports.ecommercesports.services.IProductoService;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
	
	@Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getProductos() {
		return ResponseEntity.ok(productoService.getAll());
	}
	
	@PatchMapping("/visibility/{id}")
	public ResponseEntity<?> changeVisibility(@PathVariable("id") long idProducto) {
								
		ProductoModel producto = productoService.findByIdProducto(idProducto);
		
		productoService.changeVisible(!producto.isVisible(), producto.getIdProducto());
		
		String result = "El " + producto.getDescripcionLarga() + " ahora " + (producto.isVisible()? "no es visible en el catalogo": "es visible en el catalogo");		
    	    			
		return ResponseEntity.ok(result);
	}

}
